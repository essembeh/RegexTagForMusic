package org.essembeh.rtfm.fs.content;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.mutable.MutableInt;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.ResourceAlreadyExistsException;
import org.essembeh.rtfm.fs.util.ProjectVisitor;

public class FolderImpl extends ResourceImpl implements IFolder {

	private final Map<String, IResource> resources;

	public FolderImpl(File file) {
		this(file, null);
	}

	protected FolderImpl(File file, VirtualPath virtualPath) {
		super(file, virtualPath);
		this.resources = new TreeMap<>();
	}

	@Override
	public Collection<IResource> getResources() {
		return Collections.unmodifiableCollection(resources.values());
	}

	@Override
	public int countResources() {
		final MutableInt count = new MutableInt();
		new ProjectVisitor() {
			@Override
			protected void onEachResource(IResource resource) {
				count.increment();
			}
		}.visitFolder(this);
		return count.intValue() - 1;
	}

	@Override
	public void addResource(IResource resource) throws ResourceAlreadyExistsException {
		String key = resource.getName();
		if (resources.containsKey(key)) {
			throw new ResourceAlreadyExistsException(resource, this);
		}
		resources.put(key, resource);
		resource.setParentFolder(this);
	}

	@Override
	public List<IResource> getFilteredResources(final ICondition condition) {
		final List<IResource> out = new ArrayList<>();
		new ProjectVisitor() {
			@Override
			protected void onEachResource(IResource resource) {
				if (condition == null || condition.isTrue(resource)) {
					out.add(resource);
				}
			}
		}.visitFolder(this);
		return out;
	}

}
