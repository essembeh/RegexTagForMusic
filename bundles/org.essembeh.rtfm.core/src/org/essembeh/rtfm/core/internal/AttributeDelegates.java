package org.essembeh.rtfm.core.internal;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.essembeh.rtfm.core.utils.StatusUtils;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory;
import org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.AttributesUtils;

public class AttributeDelegates {

	public static IStatus updateAttribute(Configuration conf, Node node) {
		MultiStatus out = StatusUtils.newMultistatus("Update attributes of " + node);
		for (FileHandler fileHandler : conf.getFileHandlers()) {
			if (fileHandler.accept(node.getVirtualPath())) {
				out.add(updateAttributes(fileHandler, node));
			}
		}
		return out;
	}

	public static IStatus updateAttributes(FileHandler fh, Node node) {
		MultiStatus out = StatusUtils.newMultistatus("File handler " + fh);
		for (AbstractAttributeFactory factory : fh.getFactories()) {
			if (skip(node, factory.getName(), factory.getStrategy())) {
				out.add(StatusUtils.newStatus(IStatus.INFO, "Skip attribute " + factory.getName()));
			} else {
				out.add(updateAttributes(factory, node));
			}
		}
		return out;
	}

	private static IStatus updateAttributes(AbstractAttributeFactory factory, Node node) {
		IStatus out = Status.OK_STATUS;
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
		return out;

	}

	private static boolean skip(Node node, String attributeName, AttributeFactoryStrategy strategy) {
		if (strategy == AttributeFactoryStrategy.CREATE_ONLY) {
			return node.getAttributes().containsKey(attributeName);
		}
		if (strategy == AttributeFactoryStrategy.UPDATE_ONLY) {
			return !node.getAttributes().containsKey(attributeName);
		}
		return false;
	}

	public static void createDefaultAttributes(Node node, File file, long scanTimestamp) {
		node.getAttributes().put(AttributeConstants.File.FULLPATH, file.getAbsolutePath());
		if (!node.getAttributes().containsKey(AttributeConstants.App.IMPORTED)) {
			node.getAttributes().put(AttributeConstants.App.IMPORTED, "" + scanTimestamp);
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
	}
}
