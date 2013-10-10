package org.essembeh.rtfm.fs.io;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.model.gen.filesystem.v1.TAttributeList;
import org.essembeh.rtfm.model.gen.filesystem.v1.TProject;
import org.essembeh.rtfm.model.gen.filesystem.v1.TResourceList;
import org.essembeh.rtfm.model.gen.filesystem.v1.TResourceList.Resource;

public class ProjectReaderScannerExtension implements IScannerExtension {

	private final static List<String> SKIPPED_ATTRIBUTES = Arrays.asList(Attributes.FILEHANDLER_KEY,
			Attributes.ERROR_KEY);

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
				// Do not update all attributes
				if (!SKIPPED_ATTRIBUTES.contains(attribute.getName())) {
					resource.getAttributes().setValue(attribute.getName(), attribute.getValue());
				} else if (Attributes.ERROR_KEY.equals(attribute.getName())) {
					resource.getAttributes().updateError(attribute.getValue());
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
