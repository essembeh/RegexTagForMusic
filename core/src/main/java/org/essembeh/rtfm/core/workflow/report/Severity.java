package org.essembeh.rtfm.core.workflow.report;

public enum Severity {
	OK, UNDEFINED, WARNING, ERROR;

	public boolean isOk() {
		return this == OK;
	}

	public boolean isKo() {
		return this == WARNING || this == ERROR;
	}

	public boolean isHigherThan(Severity s) {
		return this.compareTo(s) > 0;
	}
};
