package org.essembeh.rtfm.model.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.impl.ExternalProcessImpl;
import org.essembeh.rtfm.model.utils.AttributesUtils;
import org.essembeh.rtfm.model.utils.ProcessUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;

public class ExternalProcessImpl2 extends ExternalProcessImpl {

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public IStatus execute(Node node, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Execute task " + getExecutable() + " on node " + node);
		List<String> command = new ArrayList<>();
		StrSubstitutor substitutor = AttributesUtils.newSubstitutor(node);
		command.add(getExecutable());
		for (String arg : getArguments()) {
			try {
				command.add(resolve(substitutor, arg));
			} catch (IllegalStateException e) {
				out.add(StatusUtils.newStatus(IStatus.INFO, e.getMessage()));
			}
		}
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		for (Entry<String, String> e : getEnv().entrySet()) {
			try {
				processBuilder.environment().put(e.getKey(), resolve(substitutor, e.getValue()));
			} catch (IllegalStateException ex) {
				out.add(StatusUtils.newStatus(IStatus.INFO, ex.getMessage()));
			}
		}
		out.add(ProcessUtils.execute(processBuilder, monitor));
		return out;
	}

	private static String resolve(StrSubstitutor substitutor, String in) {
		String out = substitutor.replace(in);
		if (out.contains("${")) {
			throw new IllegalStateException("Cannot resolve variable in " + in);
		}
		return out;
	}
}
