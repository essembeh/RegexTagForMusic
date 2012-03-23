package org.essembeh.rtfm.core.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbstractListenerContainer<T> implements Iterable<T> {

	private List<T> listeners;

	public AbstractListenerContainer() {
		listeners = new ArrayList<T>();
	}

	public void addListener(T listener) {
		if (listener != null && !listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void removeListener(T listener) {
		if (listener != null) {
			listeners.remove(listener);
		}
	}

	public void removeAllListeners() {
		listeners.clear();
	}

	@Override
	public Iterator<T> iterator() {
		return listeners.iterator();
	}
}
