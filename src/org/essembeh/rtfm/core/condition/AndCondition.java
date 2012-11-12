package org.essembeh.rtfm.core.condition;

public class AndCondition<T> extends MultipleCondition<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T input) {
		boolean out = true;
		for (ICondition<T> condition : conditions) {
			if (!condition.isTrue(input)) {
				out = false;
				break;
			}
		}
		return out;
	}

}
