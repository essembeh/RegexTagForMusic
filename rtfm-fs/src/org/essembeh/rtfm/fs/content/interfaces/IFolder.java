package org.essembeh.rtfm.fs.content.interfaces;

import java.util.Collection;
import java.util.List;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.exception.ResourceAlreadyExistsException;

public interface IFolder extends IResource {

	Collection<IResource> getResources();

	int countResources();

	void addResource(IResource resource) throws ResourceAlreadyExistsException;

	List<IResource> getFilteredResources(ICondition condition);

}
