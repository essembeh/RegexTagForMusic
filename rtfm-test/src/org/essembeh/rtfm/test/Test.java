package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.DefaultJobProgressMonitor;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.util.ProjectVisitor;

public class Test {

	private static void dumpProject(IProject project) {
		System.out.println("------------ " + project.getName() + " -------------");
		new ProjectVisitor() {
			@Override
			protected void onEachResource(IResource resource) {
				System.out.println(resource.getVirtualPath());
				for (String k : resource.getAttributes().sortedKeys()) {
					System.out.println("    " + k + " = " + resource.getAttributes().getValue(k));
				}
				System.out.println("");
			}
		}.visitProject(project);
		System.out.println("Folder: " + project.getFile().getAbsolutePath());
		System.out.println("Resources: " + project.countResources());
		System.out.println("----------------------------------------------------------");
	}

	public static void main(String[] args) throws UnknownTaskException, FileNotFoundException, JAXBException, FileSystemException, TaskInstanciationException, InterruptedException {
		File configurationFile = new File("resources/seb.xml");
		File rootFolder = new File("resources/roots/seb");
		File save1 = new File("save1.xml");
		File save2 = new File("save2.xml");

		System.out.println("Load app");
		Application app = new Application();
		app.loadConfiguration(configurationFile);

		System.out.println("Scan folder");
		IProject project = app.scanFolder(rootFolder);
		dumpProject(project);

		System.out.println("Nontagged files: " + project.getFilteredResources(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString())).size());

		System.out.println("Save project");
		app.saveProject(save1);

		IWorkflowManager workflowManager = app.getWorkflowManager();
		System.out.println("Workflows: ");
		for (String id : workflowManager.getWorkflowIds()) {
			System.out.println("    " + id + ": " + workflowManager.getWorkflow(id).getDescription());
		}
		IWorkflow workflow = workflowManager.getWorkflow("99-dummy");
		IJob job = workflowManager.createJob(workflow, project.getFilteredResources(null));
		job.submit(new DefaultJobProgressMonitor() {
			@Override
			public void start() {
				System.out.println("Start execution");
			}

			@Override
			public void end() {
				System.out.println("End execution");
			}
		});

		System.out.println("Nontagged files: " + project.getFilteredResources(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString())).size());

		System.out.println("Save project");
		app.saveProject(save2);

		System.out.println("Project load");
		project = app.loadProject(save2);
		dumpProject(project);
		System.out.println("Nontagged files: " + project.getFilteredResources(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString())).size());
	}
}
