package org.essembeh.rtfm.core.actions;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.essembeh.rtfm.core.actions.listener.JobListenerContainer;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

/**
 * 
 * @author seb
 * 
 */
public class Job extends JobListenerContainer implements IJob {
	/**
	 * Attributes
	 */
	private final Workflow workflow;
	private final List<IMusicFile> files;
	private final Executor executor;

	/**
	 * Constructor
	 * 
	 * @param workflow
	 *            the workflow containing tasks
	 * @param files
	 *            the file to process
	 * @param executor
	 *            the executor used to submit tasks
	 */
	public Job(Workflow workflow, List<IMusicFile> files, Executor executor) {
		this.workflow = workflow;
		this.files = files;
		this.executor = executor;
	}

	/**
	 * 
	 * @param musicFile
	 */
	private void executeOneFile(IMusicFile musicFile) {
		process(workflow, musicFile);
		if (workflow.supportType(musicFile.getType())) {
			try {
				for (Task task : workflow.getTaskList()) {
					task.execute(musicFile);
				}
				succeeded(workflow, musicFile);
			} catch (ActionException e) {
				error(workflow, musicFile, e);
			}
		} else {
			error(workflow, musicFile, new ActionException("Unsopported filetype: " + musicFile));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.actions.IJob#submit()
	 */
	@Override
	public void submit() {
		start(workflow);
		final CountDownLatch latch = new CountDownLatch(files.size());
		for (final IMusicFile musicFile : files) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					executeOneFile(musicFile);
					latch.countDown();
				}
			});
		}
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					latch.await();
				} catch (InterruptedException e) {
					// TODO: wtf?
				}
				end(workflow);
			}
		});
	}
}
