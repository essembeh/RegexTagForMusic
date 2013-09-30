package org.essembeh.rtfm.ui.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.SwingUtilities;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.utils.TextUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.model.JobModel;
import org.essembeh.rtfm.ui.utils.Image;

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

		final JobModel jobModel = new JobModel(job.getResources(), true);
		table.setModel(jobModel);

		progressMonitor = new IJobProgressMonitor() {
			private Date startTime;

			@Override
			public void start() {
				startTime = new Date();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						progressBar.setValue(0);
					}
				});
			}

			@Override
			public void end() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						progressBar.setValue(progressBar.getMaximum());
						cancelButton.setEnabled(true);
						int totalTime = (int) ((new Date().getTime() - startTime.getTime()) / 1000);
						statusValue.setText("Job executed on " + TextUtils.plural(job.getResources().size(), "file")
								+ " with " + TextUtils.plural(jobModel.getErrorCount(), "error") + " in "
								+ TextUtils.plural(totalTime, "second"));
					}
				});
			}

			@Override
			public void error(final IResource resource, final ExecutionException e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						jobModel.jobFinished(resource, e);
						progressBar.setValue(progressBar.getValue() + 1);
					}
				});
			}

			@Override
			public void succeeded(final IResource resource) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						jobModel.jobFinished(resource, null);
						progressBar.setValue(progressBar.getValue() + 1);
					}
				});
			}

			@Override
			public void process(IResource resource) {

			}

			@Override
			public void notSupportedResource(IResource resource) {
				error(resource, new ExecutionException("Resource not supported"));

			}
		};
		submitButton.setAction(new DefaultRtfmAction("Submit", Image.DIALOG_YES));
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
		cancelButton.setAction(new DefaultRtfmAction("Close", Image.DIALOG_NO));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
}
