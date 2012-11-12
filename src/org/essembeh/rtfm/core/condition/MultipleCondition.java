package org.essembeh.rtfm.core.condition;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleCondition<T> implements ICondition<T> {

	/**
	 * 
	 */
	protected final List<ICondition<T>> conditions;

	/**
	 * 
	 */
	public MultipleCondition() {
		conditions = new ArrayList<ICondition<T>>();
	}

	/**
	 * 
	 * @param condition
	 */
	public void addCondition(ICondition<T> condition) {
		conditions.add(condition);
	}

}
