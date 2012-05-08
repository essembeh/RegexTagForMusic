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
package org.essembeh.rtfm.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.Job;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.actions.listener.IJobListener;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

public class JobDialog extends JDialog {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 1L;
	private final IJob job;
	private final Map<IMusicFile, ActionException> model;
	private final JTextArea textArea;
	private final JProgressBar progressBar;

	public JobDialog(IJob job) {
		this.job = job;
		this.model = new TreeMap<IMusicFile, ActionException>();
		this.textArea = new JTextArea();
		this.progressBar = new JProgressBar();
		// Set model
		for (IMusicFile musicFile : job.getMusicFiles()) {
			model.put(musicFile, null);
		}
		// UI
		setLayout(new BorderLayout());
		add(createNorth(), BorderLayout.NORTH);
		add(createCenter(), BorderLayout.CENTER);
		add(createSouth(), BorderLayout.SOUTH);
		setPreferredSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		setTitle(Translator.get(StringId.HELLO));
		pack();

		// Job listener
		job.addListener(new IJobListener() {
			@Override
			public void succeeded(Workflow workflow, IMusicFile musicFile) {
				incrementProgressBar();
			}

			@Override
			public void start(Workflow workflow) {
				synchronized (progressBar) {
					progressBar.setValue(0);
				}
			}

			@Override
			public void process(Workflow workflow, IMusicFile musicFile) {
			}

			@Override
			public void error(Workflow workflow, IMusicFile musicFile, ActionException e) {
				incrementProgressBar();
				appendError(musicFile, e);
			}

			@Override
			public void end(Workflow workflow) {
				incrementProgressBar();
			}
		});
	}

	private Component createNorth() {
		// Panel
		JPanel panel = new JPanel();
		return panel;
	}

	private Component createCenter() {
		// Log
		JPanel panel = new JPanel();
		panel.setLayout(new VerticalLayout());
		// Log
		panel.add(new JScrollPane(textArea));
		// Progress bar
		progressBar.setMaximum(job.getMusicFiles().size() + 1);
		progressBar.setStringPainted(true);
		panel.add(progressBar);
		return panel;
	}

	private Component createSouth() {
		// Panel
		JPanel panel = new JPanel();
		panel.setLayout(new HorizontalLayout());
		// Buttons
		JButton submit = new JButton("Submit job");
		panel.add(submit);
		JButton close = new JButton("Close");
		panel.add(close);

		// Listeners
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				job.submit();
			}
		});
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		return panel;
	}

	protected void incrementProgressBar() {
		synchronized (progressBar) {
			progressBar.setValue(progressBar.getValue() + 1);
		}
	}

	protected void appendError(IMusicFile musicFile, Exception e) {
		synchronized (textArea) {
			textArea.append(musicFile.getVirtualPath() + " --> " + e.getMessage() + "\n");
		}
	}

	public static void main(String[] args) {
		Job job = new Job(new Workflow("id", "desc"), new ArrayList<IMusicFile>(), Executors.newFixedThreadPool(1));
		JobDialog dialog = new JobDialog(job);
		dialog.setVisible(true);
	}

}
