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
package org.essembeh.rtfm.core.utils.listener;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Class to manage listeners
 * 
 * @author seb
 * 
 * @param <T>
 */
public class ListenerContainer<T> implements IListenable<T> {

	/**
	 * List of listeners
	 */
	private final Set<T> listeners;

	/**
	 * Constructor
	 */
	public ListenerContainer() {
		listeners = new CopyOnWriteArraySet<T>();
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 */
	public void addListener(T listener) {
		listeners.add(listener);

	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 */
	public void removeListener(T listener) {
		listeners.remove(listener);
	}

	/**
	 * Removes all listeners
	 */
	public void removeAllListener() {
		listeners.clear();
	}

	/**
	 * Get all listeners
	 * 
	 * @return
	 */
	public Set<T> getListeners() {
		return listeners;
	}

	/**
	 * Executes an action on every listener
	 * 
	 * @param action
	 */
	public void forEachListener(ListenerAction<T> action) {
		for (T listener : listeners) {
			action.execute(listener);
		}
	}

	/**
	 * 
	 * @author seb
	 * 
	 * @param <T>
	 */
	public interface ListenerAction<T> {
		void execute(T listener);
	}
}
