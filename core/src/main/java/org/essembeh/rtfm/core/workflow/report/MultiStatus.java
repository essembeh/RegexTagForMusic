package org.essembeh.rtfm.core.workflow.report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiStatus<T extends IStatus> extends AbstractStatus {

	private final List<T> statusList;

	public MultiStatus() {
		this.statusList = new ArrayList<>();
	}

	public synchronized MultiStatus<T> addStatus(T status) {
		if (status == null) {
			throw new IllegalArgumentException();
		}
		statusList.add(status);
		return this;
	}

	public synchronized List<T> getList() {
		return Collections.unmodifiableList(statusList);
	}

	private synchronized IStatus getWorst() {
		if (statusList.isEmpty()) {
			return IStatus.UNDEFINED;
		}
		IStatus out = IStatus.OK;
		for (IStatus s : statusList) {
			if (s.getSeverity().isKo() && s.getSeverity().isHigherThan(out.getSeverity())) {
				out = s;
			}
		}
		return out;
	}

	@Override
	public synchronized Severity getSeverity() {
		return getWorst().getSeverity();
	}

	@Override
	public synchronized String getMessage() {
		return getWorst().getMessage();
	}
}
