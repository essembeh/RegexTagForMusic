package org.essembeh.rtfm.core.utils.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class IdList<T, I extends Identifier<T>> implements Iterable<T> {

	Map<String, T> map = new HashMap<String, T>();

	I identifier;

	public IdList(I identifier) {
		this.identifier = identifier;
	}

	public IdList(IdList<T, I> other) {
		this(other.identifier);
		addAll(other);
	}

	public IdList(I identifier, T... array) {
		this(identifier);
		addAll(array);
	}

	public IdList(I identifier, List<T> other) {
		this(identifier);
		addAll(other);
	}

	public IdList<T, I> newEmptyOne() {
		IdList<T, I> o = new IdList<T, I>(identifier);
		return o;
	}

	public void addAll(List<T> list) {
		if (list != null) {
			for (T t : list) {
				add(t);
			}
		}
	}

	public void addAll(T... array) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				add(array[i]);
			}
		}
	}

	public void addAll(IdList<T, I> other) {
		for (T t : other) {
			add(t);
		}
	}

	public void add(T o) {
		map.put(identifier.getId(o), o);
	}

	public void remove(String id) {
		map.remove(id);
	}

	public void remove(T object) {
		remove(identifier.getId(object));
	}

	public T get(String id) {
		return map.get(id);
	}

	public IdList<T, I> sub(Pattern patternOnId) {
		IdList<T, I> sub = newEmptyOne();
		for (String id : map.keySet()) {
			if (patternOnId.matcher(id).matches()) {
				sub.add(get(id));
			}
		}
		return sub;
	}

	public boolean contains(String id) {
		return map.containsKey(id);
	}

	public int size() {
		return map.size();
	}

	public List<T> toList() {
		List<T> list = new ArrayList<T>();
		for (T t : this) {
			list.add(t);
		}
		return list;
	}

	@Override
	public Iterator<T> iterator() {
		// return map.values().iterator();
		return new SortedIterator<T>(map);
	}

	@Override
	public String toString() {
		return StringUtils.join(map.values(), ", ");
	}

}
