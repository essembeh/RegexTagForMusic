/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.utils.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class IdList<T, I extends Identifier<T>> implements Iterable<T> {

	private final Map<String, T> map = new ConcurrentHashMap<String, T>();

	private final Identifier<T> identifier;

	public IdList(Identifier<T> identifier) {
		if (identifier == null) {
			this.identifier = new Identifier<T>() {
				public String getId(T o) {
					return o.toString();
				};
			};
		} else {
			this.identifier = identifier;
		}
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

	public IdList<T, I> changeIdentifier(I newIdentifier) {
		IdList<T, I> o = new IdList<T, I>(newIdentifier);
		for (T element : this) {
			o.add(element);
		}
		return o;
	}

	public Identifier<T> getIdentifier() {
		return identifier;
	}

	public void clear() {
		map.clear();
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
		if (o != null) {
			map.put(identifier.getId(o), o);
		}
	}

	public void remove(String id) {
		map.remove(id);
	}

	public void remove(T object) {
		if (object != null) {
			remove(identifier.getId(object));
		}
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

	public boolean containsKey(String id) {
		return map.containsKey(id);
	}

	public boolean contains(T object) {
		return map.containsValue(object);
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

	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Iterator<T> iterator() {
		return map.values().iterator();
	}

	@Override
	public String toString() {
		return "IdList: [" + StringUtils.join(map.keySet(), ", ") + "]";
	}

}
