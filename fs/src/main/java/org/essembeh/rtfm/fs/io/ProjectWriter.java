package org.essembeh.rtfm.fs.io;

import java.io.OutputStream;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;
import org.essembeh.rtfm.model.JaxbUtils;
import org.essembeh.rtfm.model.filesystem.ObjectFactory;
import org.essembeh.rtfm.model.filesystem.TAttributeList;
import org.essembeh.rtfm.model.filesystem.TProject;
import org.essembeh.rtfm.model.filesystem.TResourceList;

public class ProjectWriter {

	private final static ObjectFactory FACTORY = new ObjectFactory();

	public void write(IProject project, OutputStream outputStream) throws JAXBException {
		TProject tProject = projectToModel(project);
		JaxbUtils.writeJaxbObject(outputStream, FACTORY.createProject(tProject), TProject.class);
	}

	private TProject projectToModel(IProject project) {
		TProject out = FACTORY.createTProject();
		out.setName(project.getName());
		out.setPath(project.getRootFolder().getFile().getAbsolutePath());
		TResourceList resourceList = FACTORY.createTResourceList();
		resourceToModel(project.getRootFolder(), resourceList);
		out.setResources(resourceList);
		return out;
	}

	private void resourceToModel(IResource resource, TResourceList resources) {
		TResourceList.Resource resourceModel = FACTORY.createTResourceListResource();
		if (AttributesHelper.isExportable(resource)) {
			resourceModel.setVirtualpath(resource.getVirtualPath().toString());
			resourceModel.setAttributes(resourceToAttributes(resource));
			resources.getResource().add(resourceModel);
		}
		if (resource instanceof IFolder) {
			for (IResource subResource : ((IFolder) resource).getResources()) {
				resourceToModel(subResource, resources);
			}
		}
	}

	private TAttributeList resourceToAttributes(IResource resource) {
		if (resource.getAttributes().size() > 0) {
			TAttributeList out = FACTORY.createTAttributeList();
			for (String attributeName : AttributesHelper.sortedKeys(resource)) {
				TAttributeList.Attribute attributeModel = FACTORY.createTAttributeListAttribute();
				attributeModel.setName(attributeName);
				attributeModel.setValue(AttributesHelper.get(resource, attributeName));
				out.getAttribute().add(attributeModel);
			}
			return out;
		}
		return null;
	}
}
