package org.essembeh.rtfm.fs.condition;

import java.util.ArrayList;
import java.util.List;

public abstract class MultipleCondition implements ICondition {

	protected final List<ICondition> conditions;

	public MultipleCondition() {
		conditions = new ArrayList<>();
	}

	public MultipleCondition addCondition(ICondition condition) {
		if (condition != null) {
			conditions.add(condition);
		}
		return this;
	}

}
