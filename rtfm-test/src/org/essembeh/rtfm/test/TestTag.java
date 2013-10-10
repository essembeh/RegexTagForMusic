package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.JobUtils;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.DefaultJobProgressMonitor;
import org.essembeh.rtfm.fs.condition.AndCondition;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.util.ProjectVisitor;

public class TestTag {

	// private final static ICondition CONDITION = new VirtualPathMatches(Pattern.compile(Pattern.quote("/Singles/Harry (feat. Sally) - Just a Song.mp3")));
	private final static ICondition CONDITION = null;

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
		System.out.println("Folder: " + project.getRootFolder().getFile().getAbsolutePath());
		System.out.println("Resources: " + project.getRootFolder().countResources());
		System.out.println("----------------------------------------------------------");
	}

	private static void tagFiles(IProject project, IWorkflowManager workflowManager) throws TaskInstanciationException,
			InterruptedException {
		List<IResource> files;
		files = project.getRootFolder().getFilteredResources(
				new AndCondition().addCondition(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString()))
						.addCondition(CONDITION));
		System.out.println("Non tagged files: " + files.size());
		IWorkflow workflow = workflowManager.getWorkflow("10-tag-eyed3");
		JobUtils.synExec(workflowManager.createJob(workflow, files), new DefaultJobProgressMonitor());
		files = project.getRootFolder().getFilteredResources(
				new AndCondition().addCondition(new AttributeValueEquals("music:tagged?", Boolean.FALSE.toString()))
						.addCondition(CONDITION));
		System.out.println("Non tagged files: " + files.size());
		for (IResource iResource : files) {
			System.err.println(iResource.getVirtualPath());
		}
	}

	private static void untagFiles(IProject project, IWorkflowManager workflowManager)
			throws TaskInstanciationException, InterruptedException {
		List<IResource> files;
		files = project.getRootFolder().getFilteredResources(
				new AndCondition().addCondition(new AttributeValueEquals("music:tagged?", Boolean.TRUE.toString()))
						.addCondition(CONDITION));
		System.out.println("Tagged files: " + files.size());
		IWorkflow workflow = workflowManager.getWorkflow("20-remove-tags");
		JobUtils.synExec(workflowManager.createJob(workflow, files), new DefaultJobProgressMonitor());
		files = project.getRootFolder().getFilteredResources(
				new AndCondition().addCondition(new AttributeValueEquals("music:tagged?", Boolean.TRUE.toString()))
						.addCondition(CONDITION));
		System.out.println("Tagged files: " + files.size());
		for (IResource iResource : files) {
			System.err.println(iResource.getVirtualPath());
		}
	}

	public static void main(String[] args) throws MissingTaskException, FileNotFoundException, JAXBException,
			FileSystemException, TaskInstanciationException, InterruptedException {
		File rootFolder = new File("resources/roots/seb.folder");

		System.out.println("Load app");
		Application app = AppFactory.createApplication();

		System.out.println("Scan folder");
		IProject project = app.scanFolder(rootFolder);
		dumpProject(project);

		tagFiles(project, app.getWorkflowManager());
		untagFiles(project, app.getWorkflowManager());
		tagFiles(project, app.getWorkflowManager());
	}
}
