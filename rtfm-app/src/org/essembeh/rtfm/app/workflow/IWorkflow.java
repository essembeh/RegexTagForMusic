package org.essembeh.rtfm.app.workflow;

import java.util.List;

import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.fs.condition.ICondition;

public interface IWorkflow extends Comparable<IWorkflow> {

	String getId();

	String getDescription();

	List<IExecutable> getExecutables() throws TaskInstanciationException;

	ICondition getCondition();

}
