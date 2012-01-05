package org.essembeh.rtfm.core.utils.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SortedIterator<T> implements Iterator<T> {

	Map<String, T> map;

	Iterator<String> iteratorOnKeys;

	String currentKey;

	public SortedIterator(Map<String, T> map) {
		this.map = map;
		List<String> sortedKeys = new ArrayList<String>();
		for (String string : map.keySet()) {
			sortedKeys.add(string);
		}
		Collections.sort(sortedKeys);
		iteratorOnKeys = sortedKeys.iterator();
	}

	@Override
	public boolean hasNext() {
		return iteratorOnKeys.hasNext();
	}

	@Override
	public T next() {
		currentKey = iteratorOnKeys.next();
		return map.get(currentKey);
	}

	@Override
	public void remove() {
		map.remove(currentKey);
	}
}
