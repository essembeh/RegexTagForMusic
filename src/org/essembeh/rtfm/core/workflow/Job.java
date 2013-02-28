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
package org.essembeh.rtfm.core.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.WorkflowException;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.tasks.IExecutable;

/**
 * 
 * @author seb
 * 
 */
public class Job {
	/**
	 * Attributes
	 */
	private final static Logger LOGGER = Logger.getLogger(Job.class);
	private final List<IXFile> files;
	private final List<IExecutable> executables;
	private final Executor executor;

	/**
	 * 
	 * @param executables
	 * @param files
	 * @param executor
	 */
	public Job(List<IExecutable> executables, List<IXFile> files, Executor executor) {
		this.executables = executables;
		this.files = new ArrayList<IXFile>(files);
		this.executor = executor;
	}

	/**
	 * 
	 * @param file
	 * @param progressMonitor
	 */
	private void executeOneFile(IXFile file, IJobProgressMonitor progressMonitor) {
		progressMonitor.process(file);
		try {
			for (IExecutable executable : executables) {
				executable.execute(file);
			}
			progressMonitor.succeeded(file);
		} catch (WorkflowException e) {
			LOGGER.error("Error on current file; " + file, e);
			progressMonitor.error(file, e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<IXFile> getFileList() {
		return Collections.unmodifiableList(files);
	}

	/**
	 * 
	 * @param progressMonitor
	 */
	public void submit(final IJobProgressMonitor progressMonitor) {
		LOGGER.info("Starting workflow on " + files.size() + " files");
		progressMonitor.start();
		final CountDownLatch latch = new CountDownLatch(files.size());
		for (final IXFile musicFile : files) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					executeOneFile(musicFile, progressMonitor);
					latch.countDown();
				}
			});
		}
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					latch.await();
				} catch (InterruptedException ignored) {
				}
				progressMonitor.end();
			}
		});
	}
}
