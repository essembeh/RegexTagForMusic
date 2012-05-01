package org.essembeh.rtfm.core.utils.listener;

public interface IListenable<T> {
	/**
	 * Adds a listener
	 * 
	 * @param listener
	 */
	public void addListener(T listener);

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 */
	public void removeListener(T listener);

	/**
	 * Removes all listeners
	 */
	public void removeAllListener();
}
