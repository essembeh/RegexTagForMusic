package org.essembeh.rtfm.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import org.essembeh.rtfm.core.exception.WorkflowException;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.TextUtils;
import org.essembeh.rtfm.core.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.core.workflow.Job;
import org.essembeh.rtfm.core.workflow.Workflow;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.model.JobModel;
import org.essembeh.rtfm.ui.utils.Image;

public class JobDialogCustom extends JobDialog {

	private final IJobProgressMonitor progressMonitor;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2837294156772380930L;

	public JobDialogCustom(Workflow workflow, final Job job) {
		setSize(600, 400);
		setTitle("Executing job: " + workflow.getId());

		descriptionValue.setText(workflow.getDescription());
		descriptionValue.setEditable(false);
		statusValue.setEditable(false);

		progressBar.setMinimum(0);
		progressBar.setMaximum(job.getFileList().size() + 1);
		progressBar.setStringPainted(true);

		final JobModel jobModel = new JobModel(job.getFileList(), true);
		table.setModel(jobModel);

		progressMonitor = new IJobProgressMonitor() {
			private Date startTime;

			@Override
			public void succeeded(IXFile musicFile) {
				synchronized (progressBar) {
					jobModel.jobFinished(musicFile, null);
					progressBar.setValue(progressBar.getValue() + 1);
				}
			}

			@Override
			public void start() {
				startTime = new Date();
				synchronized (progressBar) {
					progressBar.setValue(0);
				}
			}

			@Override
			public void process(IXFile File) {
			}

			@Override
			public void error(IXFile musicFile, WorkflowException e) {
				synchronized (progressBar) {
					jobModel.jobFinished(musicFile, e);
					progressBar.setValue(progressBar.getValue() + 1);
				}
			}

			@Override
			public void end() {
				synchronized (progressBar) {
					progressBar.setValue(progressBar.getValue() + 1);
				}
				cancelButton.setEnabled(true);
				int totalTime = (int) ((new Date().getTime() - startTime.getTime()) / 1000);
				statusValue.setText("Job executed on " + TextUtils.plural(job.getFileList().size(), "file") + " with "
						+ TextUtils.plural(jobModel.getErrorCount(), "error") + " in "
						+ TextUtils.plural(totalTime, "second"));
			}
		};
		submitButton.setAction(new DefaultRtfmAction("Submit", Image.DIALOG_YES));
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				job.submit(progressMonitor);
				submitButton.setEnabled(false);
				cancelButton.setEnabled(false);
			}
		});
		cancelButton.setAction(new DefaultRtfmAction("Close", Image.DIALOG_NO));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
