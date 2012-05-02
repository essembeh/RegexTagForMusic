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
