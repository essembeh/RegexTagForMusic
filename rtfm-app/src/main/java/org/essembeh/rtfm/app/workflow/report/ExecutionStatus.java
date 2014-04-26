package org.essembeh.rtfm.app.workflow.report;

public class ExecutionStatus<T, S extends IStatus> extends MultiStatus<S> {

	private long startTimestamp;
	private long stopTimestamp;
	private final T object;

	public ExecutionStatus(T object) {
		this.object = object;
		this.startTimestamp = this.stopTimestamp = 0;
	}

	public synchronized long start() {
		return this.startTimestamp = this.stopTimestamp = System.currentTimeMillis();
	}

	public synchronized long end() {
		this.stopTimestamp = System.currentTimeMillis();
		return this.stopTimestamp;
	}

	public synchronized long getDuration() {
		return stopTimestamp - startTimestamp;
	}

	public T getObject() {
		return object;
	}
}
