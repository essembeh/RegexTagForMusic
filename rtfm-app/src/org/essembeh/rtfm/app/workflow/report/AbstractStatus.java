package org.essembeh.rtfm.app.workflow.report;

public abstract class AbstractStatus implements IStatus {

	@Override
	public int compareTo(IStatus o) {
		return getSeverity().compareTo(o.getSeverity());
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", getSeverity(), getMessage());
	}
}
