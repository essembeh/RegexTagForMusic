package org.essembeh.rtfm.core.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.essembeh.rtfm.core.utils.FileUtils2;
import org.essembeh.rtfm.core.utils.StatusUtils;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory;
import org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.AttributesUtils;

public class LibraryScanner {

	public static IStatus refresh(Library library, Configuration configuration, IProgressMonitor monitor) throws IOException {
		return new LibraryScanner(library, configuration).refreshLibrary(monitor == null ? new NullProgressMonitor() : monitor);
	}

	private final Library library;
	private final Configuration configuration;

	public LibraryScanner(Library library, Configuration configuration) {
		this.library = library;
		this.configuration = configuration;
	}

	public IStatus refreshLibrary(IProgressMonitor monitor) throws IOException {
		MultiStatus out = StatusUtils.newMultistatus("Refresh library");
		File rootFolder = new File(library.getPath());
		// Check library root
		if (library.getRoot() != null) {
			if (!rootFolder.equals(library.getRoot().getResource())) {
				out.add(StatusUtils.newStatus(IStatus.INFO, "Root folder has changed"));
				library.setRoot(null);
			}
		}
		Node rootNode = library.getRoot();
		if (rootNode == null) {
			rootNode = RtfmCustomFactory.createNode(rootFolder, true);
			library.setRoot(rootNode);
		}
		library.setLastScan(System.currentTimeMillis());
		rootNode.getAttributes().put(AttributeConstants.App.LAST_SCAN, "" + library.getLastScan());
		// Scan folder
		out.add(refreshNode(rootNode, rootFolder, monitor));
		return out;

	}

	private IStatus refreshNode(Node node, File file, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Node " + file.getAbsolutePath());
		out.add(updateAttributes(node, file));
		node.setFolder(file.isDirectory());
		if (!node.isFolder()) {
			node.getContent().clear();
		} else if (AttributesUtils.isTrue(node, AttributeConstants.App.SKIP)) {
			out.add(StatusUtils.newStatus(IStatus.INFO, "Skip folder " + node));
			node.getContent().clear();
		} else {
			monitor.subTask("Scan " + file.getAbsolutePath());
			List<File> children = FileUtils2.browse(file);
			for (File childFile : children) {
				String childName = childFile.getName();
				Node childNode = node.getContent().get(childName);
				if (childNode == null) {
					node.getContent().put(childName, childNode = RtfmCustomFactory.createNode(childFile, false));
				}
				out.add(refreshNode(childNode, childFile, monitor));
			}
			node.getContent().removeIf(n -> !children.stream().anyMatch(f -> n.getKey().equals(f.getName())));
		}
		return out;
	}

	private IStatus updateAttributes(Node node, File file) {
		String virtualPath = node.getVirtualPath();
		MultiStatus out = StatusUtils.newMultistatus("Node " + virtualPath);
		// Mandatory attributes
		node.getAttributes().put(AttributeConstants.File.FULLPATH, file.getAbsolutePath());
		if (!node.getAttributes().containsKey(AttributeConstants.App.IMPORTED)) {
			node.getAttributes().put(AttributeConstants.App.IMPORTED, "" + library.getLastScan());
		}
		if (file.exists()) {
			if (file.isFile()) {
				node.getAttributes().put(AttributeConstants.File.EXTENSION, FilenameUtils.getExtension(file.getName()));
				node.getAttributes().put(AttributeConstants.File.LAST_MODIFIED, "" + file.lastModified());
				node.getAttributes().put(AttributeConstants.File.SIZE, "" + file.length());
				node.getAttributes().put(AttributeConstants.File.TYPE, AttributeConstants.File.TYPE_FILE);
			} else {
				node.getAttributes().put(AttributeConstants.File.TYPE, AttributeConstants.File.TYPE_FOLDER);
			}
		}
		// Filehandlers
		for (FileHandler fileHandler : configuration.getFileHandlers()) {
			if (fileHandler.accept(virtualPath)) {
				for (AbstractAttributeFactory factory : fileHandler.getFactories()) {
					out.add(createAttribute(node, factory));
				}
			}
		}
		return out;
	}

	private static IStatus createAttribute(Node node, AbstractAttributeFactory factory) {
		IStatus out = Status.OK_STATUS;
		if ((AttributeFactoryStrategy.CREATE_ONLY.equals(factory.getStrategy()) && node.getAttributes().containsKey(factory.getName()))
				|| (AttributeFactoryStrategy.UPDATE_ONLY.equals(factory.getStrategy()) && !node.getAttributes().containsKey(factory.getName()))) {
			out = StatusUtils.newStatus(IStatus.OK, "Skip attribute " + factory.getName());
		} else {
			try {
				String newValue = factory.getAttributeValue(node.getVirtualPath());
				if (newValue == null) {
					throw new IllegalStateException("Value should not be null");
				}
				if (factory.getStrategy() == AttributeFactoryStrategy.CREATE_OR_APPEND && node.getAttributes().containsKey(factory.getName())) {
					newValue = AttributesUtils.appendIfNeeded(node.getAttributes().get(factory.getName()), newValue);
				}
				node.getAttributes().put(factory.getName(), newValue);
				out = StatusUtils.newStatus(IStatus.OK, "Set " + factory.getName() + " -> " + newValue);
			} catch (Exception e) {
				if (!factory.isOptional()) {
					AttributesUtils.handleError(node, e);
					out = StatusUtils.newStatus(IStatus.ERROR, "Error with attribute " + factory.getName(), e);
				} else {
					out = StatusUtils.newStatus(IStatus.OK, "Error with optionnal attribute " + factory.getName(), e);
				}
			}
		}
		return out;

	}
}
