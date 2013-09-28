package org.essembeh.rtfm.app.filehandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.app.exception.RegexAttributeException;
import org.essembeh.rtfm.app.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.io.IScannerExtension;

public class FileHandlerScannerExtension implements IScannerExtension {

	public enum AttributeExistsOption {
		UPDATE, DO_NOTHING
	}

	public enum AttributeErrorOption {
		UPDATE_ERROR_ATTRIBUTE, CREATE_EMPTY_ATTRIBUTE, DO_NOTHING
	}

	private final List<FileHandler> fileHandlers;
	private final AttributeErrorOption attributeErrorOption;
	private final AttributeExistsOption attributeExistsOption;

	public FileHandlerScannerExtension(AttributeErrorOption attributeErrorOption, AttributeExistsOption attributeExistsOption, List<FileHandler> fileHandlers) {
		this.fileHandlers = new ArrayList<>(fileHandlers);
		this.attributeErrorOption = attributeErrorOption;
		this.attributeExistsOption = attributeExistsOption;
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
					if (!resource.getAttributes().contains(attributeGenerator.getName()) || attributeExistsOption == AttributeExistsOption.UPDATE) {
						try {
							String value = attributeGenerator.getValue(resource);
							if (value != null) {
								resource.getAttributes().setValue(attributeGenerator.getName(), value);
							}
						} catch (RegexAttributeException e) {
							switch (attributeErrorOption) {
							case UPDATE_ERROR_ATTRIBUTE:
								resource.getAttributes().updateError(e.getMessage());
								break;
							case CREATE_EMPTY_ATTRIBUTE:
								resource.getAttributes().setValue(attributeGenerator.getName(), "");
							default:
								break;
							}
						}
					}
				}
				break;
			}
		}

	}
}
