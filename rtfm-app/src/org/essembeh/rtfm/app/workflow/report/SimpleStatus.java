package org.essembeh.rtfm.app.workflow.report;

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
