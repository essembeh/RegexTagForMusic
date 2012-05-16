package org.essembeh.rtfm.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.actions.listener.IJobListener;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.TextUtils;
import org.essembeh.rtfm.ui.model.JobModel;

public class JobDialogCustom extends JobDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2837294156772380930L;
	private final IJob job;

	public JobDialogCustom(final IJob job) {
		this.job = job;

		nameValue.setText(job.getWorkflowIdentifier().getIdentifier());
		descriptionValue.setText(job.getWorkflowIdentifier().getDescription());

		progressBar.setMinimum(0);
		progressBar.setMaximum(job.getMusicFiles().size() + 1);
		progressBar.setStringPainted(true);

		final JobModel jobModel = new JobModel(job.getMusicFiles());
		table.setModel(jobModel);

		job.addListener(new IJobListener() {
			@Override
			public void succeeded(Workflow workflow, IMusicFile musicFile) {
				synchronized (progressBar) {
					jobModel.jobFinished(musicFile, null);
					progressBar.setValue(progressBar.getValue() + 1);
				}
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
				synchronized (progressBar) {
					jobModel.jobFinished(musicFile, e);
					progressBar.setValue(progressBar.getValue() + 1);
				}
			}

			@Override
			public void end(Workflow workflow) {
				synchronized (progressBar) {
					progressBar.setValue(progressBar.getValue() + 1);
				}
				submitButton.setEnabled(true);
				cancelButton.setEnabled(true);
				statusValue.setText("Job executed on " + TextUtils.plural(job.getMusicFiles().size(), "file") + " with "
						+ TextUtils.plural(jobModel.getErrorCount(), "error"));
			}
		});
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				job.submit();
				submitButton.setEnabled(false);
				cancelButton.setEnabled(false);
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
