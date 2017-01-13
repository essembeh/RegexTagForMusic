package org.essembeh.rtfm.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;
import org.essembeh.rtfm.model.rtfm.AttributeTool;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.ExternalProcess;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.model.utils.EmfModelUtils;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.tests.utils.ResourcesUtils;
import org.essembeh.rtfm.tests.utils.SysoutUtils;
import org.junit.Assert;
import org.junit.Test;

public class WorkflowTest {

	@Test
	public void testAttributeSetter() throws Exception {
		Configuration configuration = EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createConfiguration(), config -> {
			config.getFileHandlers().add(EmfModelUtils.configure(RtfmCustomFactory.createFileHandler("test", FileHandlerTest.MP3_PATTERN), fh -> {
				fh.getFactories().add(RtfmCustomFactory.createFixedAttributeFactory("test.foo", "bar"));
			}));
			config.getWorkflows().add(EmfModelUtils.configure(RtfmCustomFactory.createWorkflow("update"), w -> {
				w.getTasks().add(EmfModelUtils.configure(RtfmCustomFactory.eINSTANCE.createAttributeTool(), at -> {
					at.getAttributesToUpdate().put("test.foo", "barbar");
				}));
			}));
		});

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeExists("test.foo")).size());

		IStatus status = configuration.getWorkflows().get(0).execute(new BasicEList<>(NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeExists("test.foo"))),
				new NullProgressMonitor());
		SysoutUtils.dumpStatus(status);
		Assert.assertTrue(status.isOK());
		Assert.assertTrue(status.isMultiStatus());
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, status.getChildren().length);

		Assert.assertEquals(0, NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeEquals("test.foo", "bar")).size());
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeExists("test.foo")).size());
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeEquals("test.foo", "barbar")).size());
	}

	@Test
	public void testExternalProcess() throws Exception {
		Configuration configuration = RtfmCustomFactory.eINSTANCE.createConfiguration();
		{
			ExternalProcess externalProcess = RtfmCustomFactory.createExternalProcess("test", "test");
			externalProcess.getArguments().add("-f");
			externalProcess.getArguments().add("${" + AttributeConstants.File.FULLPATH + "}");
			Workflow workflow = RtfmCustomFactory.createWorkflow("test-file");
			workflow.getTasks().add(externalProcess);
			configuration.getWorkflows().add(workflow);
		}

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));

		IStatus status = configuration.getWorkflows().get(0).execute(new BasicEList<>(NodeUtils.recursiveStream(library.getRoot()).collect(Collectors.toList())), new NullProgressMonitor());
		SysoutUtils.dumpStatus(status);
		Assert.assertTrue(!status.isOK());
		Assert.assertTrue(status.isMultiStatus());
		Assert.assertEquals(SebLibraryTest.NODE_COUNT, status.getChildren().length);
		Assert.assertEquals(SebLibraryTest.FILE_COUNT, Arrays.asList(status.getChildren()).stream().filter(IStatus::isOK).count());
		Assert.assertEquals(SebLibraryTest.FOLDER_COUNT, Arrays.asList(status.getChildren()).stream().filter(s -> !s.isOK()).count());
	}

	@Test
	public void testResolveVariables() throws Exception {
		Configuration configuration = RtfmCustomFactory.eINSTANCE.createConfiguration();
		{
			ExternalProcess externalProcess = RtfmCustomFactory.createExternalProcess("env", "env");
			externalProcess.getArguments().add("file.size = ${file.size}");
			externalProcess.getEnv().put("RTFM_TEST", "foo ${file.extension} bar");
			Workflow workflow = RtfmCustomFactory.createWorkflow("test-file");
			workflow.getTasks().add(externalProcess);
			configuration.getWorkflows().add(workflow);
		}

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));

		IStatus status = configuration.getWorkflows().get(0).execute(new BasicEList<>(NodeUtils.recursiveStream(library.getRoot()).collect(Collectors.toList())), new NullProgressMonitor());
		SysoutUtils.dumpStatus(status);
		Assert.assertTrue(StatusUtils.hasNoError(status));

		List<String> content = new ArrayList<>();
		SysoutUtils.dumpStatus(status, l -> content.add(l));
		Assert.assertEquals(SebLibraryTest.MP3_COUNT, content.stream().filter(l -> l.contains("RTFM_TEST=foo mp3 bar")).count());
		Assert.assertEquals(NodeUtils.recursiveStream(library.getRoot()).filter(NodeFilterFactory.attributeEquals("file.extension", "mp3")).count(),
				content.stream().filter(l -> l.contains("RTFM_TEST=foo mp3 bar")).count());
	}

	@Test
	public void testSkip() throws Exception {
		Configuration configuration = RtfmCustomFactory.eINSTANCE.createConfiguration();
		{
			AttributeTool attributeSetter1 = RtfmCustomFactory.eINSTANCE.createAttributeTool();
			attributeSetter1.getAttributesToUpdate().put("test.foo", "bar1");

			ExternalProcess externalProcess = RtfmCustomFactory.createExternalProcess("false", "false");

			AttributeTool attributeSetter2 = RtfmCustomFactory.eINSTANCE.createAttributeTool();
			attributeSetter2.getAttributesToUpdate().put("test.foo", "bar2");

			Workflow workflow = RtfmCustomFactory.createWorkflow("test-file");
			workflow.getTasks().add(attributeSetter1);
			workflow.getTasks().add(externalProcess);
			workflow.getTasks().add(attributeSetter2);
			configuration.getWorkflows().add(workflow);
		}

		Library library = RtfmCustomFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, configuration, null)));

		IStatus status = configuration.getWorkflows().get(0).execute(new BasicEList<>(NodeUtils.recursiveStream(library.getRoot()).collect(Collectors.toList())), new NullProgressMonitor());
		SysoutUtils.dumpStatus(status);
		Assert.assertEquals(IStatus.ERROR, status.getSeverity());
		Assert.assertEquals(SebLibraryTest.NODE_COUNT, NodeUtils.filterNodes(library.getRoot(), NodeFilterFactory.attributeEquals("test.foo", "bar1")).size());

	}
}
