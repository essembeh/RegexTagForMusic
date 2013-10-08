package org.essembeh.rtfm.app.workflow;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.id.Identifiable;
import org.essembeh.rtfm.fs.condition.ICondition;

public interface IWorkflow extends Identifiable {

	String getDescription();

	ICondition getCondition();

	boolean isAuto();

	boolean isUser();

	List<ImmutablePair<ITask, IExecutable>> getExecutables() throws TaskInstanciationException;
}
