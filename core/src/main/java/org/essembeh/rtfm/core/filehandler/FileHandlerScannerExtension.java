package org.essembeh.rtfm.core.filehandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.core.exception.AttributeException;
import org.essembeh.rtfm.core.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.io.IScannerExtension;
import org.essembeh.rtfm.fs.util.AttributesHelper;

public class FileHandlerScannerExtension implements IScannerExtension {

	public enum AttributeErrorOption {
		UPDATE_ERROR_ATTRIBUTE, CREATE_EMPTY_ATTRIBUTE, DO_NOTHING
	}

	private final List<FileHandler> fileHandlers;
	private AttributeErrorOption attributeErrorOption;

	public FileHandlerScannerExtension(List<FileHandler> fileHandlers) {
		this.fileHandlers = new ArrayList<>(fileHandlers);
		this.attributeErrorOption = AttributeErrorOption.DO_NOTHING;
	}

	public void setAttributeErrorOption(AttributeErrorOption attributeErrorOption) {
		this.attributeErrorOption = attributeErrorOption;
	}

	@Override
	public boolean skipResource(File file) {
		return false;
	}

	@Override
	public void postCreation(IResource resource) {
		for (FileHandler fileHandler : fileHandlers) {
			if (fileHandler.matches(resource)) {
				for (IAttributeGenerator attributeGenerator : fileHandler.getAttributes()) {
					try {
						String value = attributeGenerator.getValue(resource);
						if (value != null) {
							AttributesHelper.set(resource, attributeGenerator.getName(), value);
						}
					} catch (AttributeException e) {
						switch (attributeErrorOption) {
						case UPDATE_ERROR_ATTRIBUTE:
							AttributesHelper.updateError(resource, e);
							break;
						case CREATE_EMPTY_ATTRIBUTE:
							AttributesHelper.set(resource, attributeGenerator.getName(), "");
						default:
							// case DO_NOTHING
							break;
						}
					}
				}
				break;
			}
		}

	}
}
