package org.essembeh.rtfm.core.workflow.report;

public class SimpleStatus extends AbstractStatus {

	private final Severity severity;
	private final String message;

	public SimpleStatus(Severity severity, String message) {
		this.severity = severity;
		this.message = message;
	}

	@Override
	public Severity getSeverity() {
		return severity;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
