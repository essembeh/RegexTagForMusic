package org.essembeh.rtfm.model;

import java.io.File;

import org.essembeh.rtfm.model.custom.AttributeToolImpl2;
import org.essembeh.rtfm.model.custom.ConfigurationImpl2;
import org.essembeh.rtfm.model.custom.ExternalProcessImpl2;
import org.essembeh.rtfm.model.custom.FileHandlerImpl2;
import org.essembeh.rtfm.model.custom.FixedAttributeFactoryImpl2;
import org.essembeh.rtfm.model.custom.LibraryImpl2;
import org.essembeh.rtfm.model.custom.NodeImpl2;
import org.essembeh.rtfm.model.custom.RegexAttributeFactoryImpl2;
import org.essembeh.rtfm.model.custom.WorkflowImpl2;
import org.essembeh.rtfm.model.rtfm.AttributeTool;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.ExternalProcess;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.FixedAttributeFactory;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.RegexAttributeFactory;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.model.rtfm.impl.RtfmFactoryImpl;
import org.essembeh.rtfm.model.utils.EmfModelUtils;

public class RtfmCustomFactory extends RtfmFactoryImpl {

	public static ExternalProcess createExternalProcess(String name, String executable) {
		return EmfModelUtils.configure(eINSTANCE.createExternalProcess(), out -> {
			out.setName(name);
			out.setExecutable(executable);
		});
	}

	public static FileHandler createFileHandler(String name, String pattern) {
		return EmfModelUtils.configure(eINSTANCE.createFileHandler(), out -> {
			out.setPattern(pattern);
			out.setName(name);
		});
	}

	public static FixedAttributeFactory createFixedAttributeFactory(String name, String value) {
		return EmfModelUtils.configure(eINSTANCE.createFixedAttributeFactory(), out -> {
			out.setName(name);
			out.setValue(value);
		});
	}

	public static Node createNode(String name, boolean root, boolean folder) {
		return EmfModelUtils.configure(eINSTANCE.createNode(), out -> {
			out.setName(name);
			out.setRoot(root);
			out.setFolder(folder);
		});
	}

	public static Node createNode(File file, boolean root) {
		return createNode(file.getName(), root, file.isDirectory());
	}

	public static RegexAttributeFactory createRegexAttributeFactory(String name, int group, boolean optional) {
		return EmfModelUtils.configure(eINSTANCE.createRegexAttributeFactory(), out -> {
			out.setName(name);
			out.setGroup(group);
			out.setOptional(optional);
		});
	}

	public static Workflow createWorkflow(String name) {
		return EmfModelUtils.configure(eINSTANCE.createWorkflow(), out -> {
			out.setName(name);
		});
	}

	@Override
	public AttributeTool createAttributeTool() {
		return new AttributeToolImpl2();
	}

	@Override
	public Configuration createConfiguration() {
		return new ConfigurationImpl2();
	}

	/**
	 * EMF Factory
	 */

	@Override
	public ExternalProcess createExternalProcess() {
		return new ExternalProcessImpl2();
	}

	@Override
	public FileHandler createFileHandler() {
		return new FileHandlerImpl2();
	}

	@Override
	public FixedAttributeFactory createFixedAttributeFactory() {
		return new FixedAttributeFactoryImpl2();
	}

	@Override
	public Library createLibrary() {
		return new LibraryImpl2();
	}

	@Override
	public Node createNode() {
		return new NodeImpl2();
	}

	@Override
	public RegexAttributeFactory createRegexAttributeFactory() {
		return new RegexAttributeFactoryImpl2();
	}

	@Override
	public Workflow createWorkflow() {
		return new WorkflowImpl2();
	}
}
