package org.essembeh.rtfm.app.workflow;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.workflow.impl.TaskDescription;
import org.essembeh.rtfm.fs.condition.ICondition;

public interface IWorkflow extends Comparable<IWorkflow> {

	String getId();

	String getDescription();

	ICondition getCondition();

	boolean isAuto();

	boolean isUser();

	List<ImmutablePair<TaskDescription, IExecutable>> getExecutables() throws TaskInstanciationException;

}
