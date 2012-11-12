package org.essembeh.rtfm.core.utils.list;

import java.util.concurrent.ConcurrentHashMap;

public class UidMap<T extends IIdentifiable> extends ConcurrentHashMap<String, T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914116850749155934L;

	/**
	 * 
	 * @param element
	 * @return
	 */
	public T add(T element) {
		return put(element.getUid(), element);
	}
}
