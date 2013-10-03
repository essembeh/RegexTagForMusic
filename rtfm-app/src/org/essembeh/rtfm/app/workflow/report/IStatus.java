package org.essembeh.rtfm.app.workflow.report;

public interface IStatus extends Comparable<IStatus> {

	final static IStatus OK = new SimpleStatus(Severity.OK, "");
	final static IStatus UNDEFINED = new SimpleStatus(Severity.UNDEFINED, "");

	Severity getSeverity();

	String getMessage();
}
