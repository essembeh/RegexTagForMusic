/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.actions.listener;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.listener.ListenerContainer;

public class JobListenerContainer extends ListenerContainer<IJobListener> implements IJobListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.actions.listener.IJobListener#start(org.essembeh .rtfm.core.actions.Workflow)
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
	 * @see org.essembeh.rtfm.core.actions.listener.IJobListener#process(org.essembeh .rtfm.core.actions.Workflow,
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
	 * @see org.essembeh.rtfm.core.actions.listener.IJobListener#succeeded(org.essembeh .rtfm.core.actions.Workflow,
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
	 * @see org.essembeh.rtfm.core.actions.listener.IJobListener#error(org.essembeh .rtfm.core.actions.Workflow, org.essembeh.rtfm.core.library.file.IMusicFile,
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
	 * @see org.essembeh.rtfm.core.actions.listener.IJobListener#end(org.essembeh .rtfm.core.actions.Workflow)
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
