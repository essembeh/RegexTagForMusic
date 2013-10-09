package org.essembeh.rtfm.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import org.essembeh.rtfm.app.utils.TextUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.model.JobModel;
import org.essembeh.rtfm.ui.utils.ImageUtils;

public class JobDialogCustom extends JobDialog {

	private final IJobProgressMonitor progressMonitor;

	private static final long serialVersionUID = -2837294156772380930L;

	public JobDialogCustom(IWorkflow workflow, final IJob job) {
		setSize(600, 400);
		setTitle("Executing job: " + workflow.getId());

		descriptionValue.setText(workflow.getDescription());
		descriptionValue.setEditable(false);
		statusValue.setEditable(false);

		progressBar.setMinimum(0);
		progressBar.setMaximum(job.getResources().size() + 1);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);

		final JobModel jobModel = new JobModel(job, true);
		table.setModel(jobModel);

		progressMonitor = new IJobProgressMonitor() {
			@Override
			public void resourceDone(final ExecutionStatus<IResource, SimpleStatus> s) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						jobModel.updateStatus(s);
					}
				});
			}

			@Override
			public void end(final ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						progressBar.setValue(progressBar.getMaximum());
						cancelButton.setEnabled(true);
						int totalTime = (int) (status.getDuration() / 1000);
						statusValue.setText("Job executed on " + TextUtils.plural(job.getResources().size(), "file")
								+ " in " + TextUtils.plural(totalTime, "second") + ", " + status);
					}
				});
			}
		};
		submitButton.setAction(new DefaultRtfmAction("Submit", ImageUtils.DIALOG_YES));
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						try {
							job.submit(progressMonitor);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
							statusValue.setText("Error:" + e1.getMessage());
						}
					}
				}.run();
				submitButton.setEnabled(false);
				cancelButton.setEnabled(false);
			}
		});
		cancelButton.setAction(new DefaultRtfmAction("Close", ImageUtils.DIALOG_NO));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
