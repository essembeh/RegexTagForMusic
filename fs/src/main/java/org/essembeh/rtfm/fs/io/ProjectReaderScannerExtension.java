package org.essembeh.rtfm.fs.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;
import org.essembeh.rtfm.model.filesystem.TAttributeList;
import org.essembeh.rtfm.model.filesystem.TProject;
import org.essembeh.rtfm.model.filesystem.TResourceList;
import org.essembeh.rtfm.model.filesystem.TResourceList.Resource;

public class ProjectReaderScannerExtension implements IScannerExtension {

	private final Map<String, Resource> content;
	private final TProject model;

	public ProjectReaderScannerExtension(TProject model) {
		this.model = model;
		this.content = new HashMap<String, Resource>();
		if (model != null) {
			for (Resource resource : model.getResources().getResource()) {
				content.put(resource.getVirtualpath(), resource);
			}
		}
	}

	public File getRootFolder() {
		return new File(model.getPath());
	}

	private void updateResource(IResource resource, TResourceList.Resource model) {
		if (model != null && model.getAttributes() != null) {
			for (TAttributeList.Attribute attribute : model.getAttributes().getAttribute()) {
				if (AttributesHelper.ERROR_KEY.equals(attribute.getName())) {
					AttributesHelper.updateError(resource, attribute.getValue());
				} else {
					AttributesHelper.set(resource, attribute.getName(), attribute.getValue());
				}
			}
		}
	}

	@Override
	public boolean skipResource(File file) {
		return false;
	}

	@Override
	public void postCreation(IResource resource) {
		String key = resource.getVirtualPath().toString();
		if (content.containsKey(key)) {
			updateResource(resource, content.get(key));
		}
	}
}
