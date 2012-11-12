package org.essembeh.rtfm.core.condition;

public interface ICondition<T> {
	/**
	 * 
	 * @param input
	 * @return
	 */
	boolean isTrue(T input);
}
