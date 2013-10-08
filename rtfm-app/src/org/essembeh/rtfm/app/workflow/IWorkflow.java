package org.essembeh.rtfm.app.workflow;

import org.essembeh.rtfm.fs.condition.ICondition;

public interface IWorkflow extends Comparable<IWorkflow> {

	String getId();

	String getDescription();

	ICondition getCondition();

	boolean isAuto();

	boolean isUser();
}
