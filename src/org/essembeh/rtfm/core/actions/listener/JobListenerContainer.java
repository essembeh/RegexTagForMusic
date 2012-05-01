package org.essembeh.rtfm.core.actions.listener;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.listener.ListenerContainer;

public class JobListenerContainer extends ListenerContainer<IJobListener> implements IJobListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.listener.IJobListener#start(org.essembeh
	 * .rtfm.core.actions.Workflow)
	 */
	@Override
	public void start(final Workflow workflow) {
		forEachListener(new ListenerAction<IJobListener>() {
			@Override
			public void execute(IJobListener listener) {
				listener.start(workflow);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.listener.IJobListener#process(org.essembeh
	 * .rtfm.core.actions.Workflow,
	 * org.essembeh.rtfm.core.library.file.IMusicFile)
	 */
	@Override
	public void process(final Workflow workflow, final IMusicFile musicFile) {
		forEachListener(new ListenerAction<IJobListener>() {
			@Override
			public void execute(IJobListener listener) {
				listener.process(workflow, musicFile);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.listener.IJobListener#succeeded(org.essembeh
	 * .rtfm.core.actions.Workflow,
	 * org.essembeh.rtfm.core.library.file.IMusicFile)
	 */
	@Override
	public void succeeded(final Workflow workflow, final IMusicFile musicFile) {
		forEachListener(new ListenerAction<IJobListener>() {
			@Override
			public void execute(IJobListener listener) {
				listener.succeeded(workflow, musicFile);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.listener.IJobListener#error(org.essembeh
	 * .rtfm.core.actions.Workflow,
	 * org.essembeh.rtfm.core.library.file.IMusicFile,
	 * org.essembeh.rtfm.core.exception.ActionException)
	 */
	@Override
	public void error(final Workflow workflow, final IMusicFile musicFile, final ActionException e) {
		forEachListener(new ListenerAction<IJobListener>() {
			@Override
			public void execute(IJobListener listener) {
				listener.error(workflow, musicFile, e);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.listener.IJobListener#end(org.essembeh
	 * .rtfm.core.actions.Workflow)
	 */
	@Override
	public void end(final Workflow workflow) {
		forEachListener(new ListenerAction<IJobListener>() {
			@Override
			public void execute(IJobListener listener) {
				listener.end(workflow);
			}
		});
	}

}
