package org.essembeh.rtfm.model.custom;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.essembeh.rtfm.model.filter.INodeFilter;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.Task;
import org.essembeh.rtfm.model.rtfm.impl.WorkflowImpl;
import org.essembeh.rtfm.model.utils.StatusUtils;

public class WorkflowImpl2 extends WorkflowImpl {

	@Override
	public boolean accept(Node node) {
		if (StringUtils.isNotBlank(getPattern())) {
			String regex = getPattern();
			if (getConfiguration() != null) {
				regex = getConfiguration().resolvePattern(regex);
			}
			if (!Pattern.matches(regex, node.getVirtualPath())) {
				return false;
			}
		}
		for (String string : filters) {
			INodeFilter filter = NodeFilterFactory.fromString(string);
			if (!filter.test(node)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public IStatus execute(EList<Node> nodes, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Execute workflow " + getName() + " on " + nodes.size() + " resource(s)");
		for (Node node : nodes) {
			monitor.subTask(node.toString());
			if (monitor.isCanceled()) {
				out.add(Status.CANCEL_STATUS);
				break;
			}
			monitor.worked(1);
			out.add(executeOnNode(node, monitor));
		}
		return out;
	}

	private IStatus executeOnNode(Node node, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Execute workflow " + getName() + " on " + node.getVirtualPath());
		try {
			for (Task task : getTasks()) {
				if (StatusUtils.hasNoError(out)) {
					out.add(task.execute(node, monitor));
				} else {
					out.add(StatusUtils.newStatus(IStatus.WARNING, "Skip task " + task + " for " + node));
				}
			}
		} catch (Exception e) {
			out.add(StatusUtils.newErrorStatus(e));
		}
		return out;
	}
}
