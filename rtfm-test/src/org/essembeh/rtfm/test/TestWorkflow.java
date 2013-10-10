package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.DefaultJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.util.ProjectVisitor;

public class TestWorkflow {

	private static void dumpProject(IProject project, final boolean printAttributes) {
		System.out.println("------------ " + project.getName() + " -------------");
		new ProjectVisitor() {
			@Override
			protected void onEachResource(IResource resource) {
				System.out.println(resource.getVirtualPath());
				if (printAttributes) {
					for (String k : resource.getAttributes().sortedKeys()) {
						System.out.println("    " + k + " = " + resource.getAttributes().getValue(k));
					}
					System.out.println("");
				}
			}
		}.visitProject(project);
		System.out.println("Folder: " + project.getRootFolder().getFile().getAbsolutePath());
		System.out.println("Resources: " + project.getRootFolder().countResources());
		System.out.println("----------------------------------------------------------");
	}

	private static void executeWorkflow(Application app, String w) throws TaskInstanciationException,
			InterruptedException {
		IWorkflowManager workflowManager = app.getWorkflowManager();
		System.out.println("Execute workflows: " + w);
		IWorkflow workflow = workflowManager.getWorkflow(w);
		IJob job = workflowManager.createJob(workflow, app.getProject().getRootFolder().getAllResources());
		final Semaphore semaphore = new Semaphore(0);
		job.submit(new DefaultJobProgressMonitor() {
			@Override
			public void resourceDone(ExecutionStatus<IResource, SimpleStatus> s) {
				System.out.println("Sub status: " + s);
			}

			@Override
			public void end(ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status) {
				System.out.println("End status: " + status);
				semaphore.release();
			}
		});
		semaphore.acquire();
		job.updateErrorResources();
		System.out.println("-------------------------------");
	}

	public static void main(String[] args) throws MissingTaskException, FileNotFoundException, JAXBException,
			FileSystemException, TaskInstanciationException, InterruptedException {
		File rootFolder = new File("resources/roots/seb.folder");
		File save1 = new File("save1.xml");

		System.out.println("Load app");
		Application app = AppFactory.createApplication();

		System.out.println("Scan folder");
		IProject project = app.scanFolder(rootFolder);
		dumpProject(project, false);

		System.out.println("Workflows: ");
		IWorkflowManager workflowManager = app.getWorkflowManager();
		for (String id : workflowManager.getWorkflowIds()) {
			System.out.println("    " + id + ": " + workflowManager.getWorkflow(id).getDescription());
		}

		executeWorkflow(app, "99-test-OK");
		executeWorkflow(app, "99-test-WARNING");
		executeWorkflow(app, "99-test-ERROR");

		System.out.println("Save project");
		app.saveProject(save1);

		System.out.println("Load project");
		project = app.loadProject(save1);

		dumpProject(project, true);
	}
}
