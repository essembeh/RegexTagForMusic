package org.essembeh.rtfm.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

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

	public JobDialogCustom(final IJob job) {
		setSize(600, 400);
		setTitle("Executing job: " + job.getWorkflowIdentifier().getIdentifier());

		descriptionValue.setText(job.getWorkflowIdentifier().getDescription());
		descriptionValue.setEditable(false);
		statusValue.setEditable(false);

		progressBar.setMinimum(0);
		progressBar.setMaximum(job.getMusicFiles().size() + 1);
		progressBar.setStringPainted(true);

		final JobModel jobModel = new JobModel(job.getMusicFiles(), true);
		table.setModel(jobModel);

		job.addListener(new IJobListener() {
			private Date startTime;

			@Override
			public void succeeded(Workflow workflow, IMusicFile musicFile) {
				synchronized (progressBar) {
					jobModel.jobFinished(musicFile, null);
					progressBar.setValue(progressBar.getValue() + 1);
				}
			}

			@Override
			public void start(Workflow workflow) {
				startTime = new Date();
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
				cancelButton.setEnabled(true);
				int totalTime = (int) ((new Date().getTime() - startTime.getTime()) / 1000);
				statusValue.setText("Job executed on " + TextUtils.plural(job.getMusicFiles().size(), "file")
						+ " with " + TextUtils.plural(jobModel.getErrorCount(), "error") + " in "
						+ TextUtils.plural(totalTime, "second"));
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
