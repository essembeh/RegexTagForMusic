package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.JobUtils;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.DefaultJobProgressMonitor;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.exception.FileSystemException;

public class Music {

	public static void main(String[] args) throws MissingTaskException, FileNotFoundException, JAXBException,
			FileSystemException, TaskInstanciationException, InterruptedException {
		File rootFolder = new File("/media/raid/Multimedia/Music");
		File save1 = new File("music.xml");

		System.out.println("Load app");
		Application app = AppFactory.createApplication();

		System.out.println("Scan folder");
		IProject project = app.scanFolder(rootFolder);

		System.out.println("Folder: " + project.getRootFolder().getFile().getAbsolutePath());
		System.out.println("Resources: " + project.getRootFolder().countResources());

		System.out.println("Nontagged files: "
				+ project.getRootFolder()
						.getFilteredResources(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString()))
						.size());

		System.out.println("Save project");
		app.saveProject(save1);

		IWorkflowManager workflowManager = app.getWorkflowManager();
		System.out.println("Workflows: ");
		for (String id : workflowManager.getWorkflowIds()) {
			System.out.println("    " + id + ": " + workflowManager.getWorkflow(id).getDescription());
		}
		IWorkflow workflow = workflowManager.getWorkflow("99-test-OK");
		JobUtils.synExec(workflowManager.createJob(workflow, project.getRootFolder().getAllResources()),
				new DefaultJobProgressMonitor());

		System.out.println("Nontagged files: "
				+ project.getRootFolder()
						.getFilteredResources(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString()))
						.size());

	}
}
