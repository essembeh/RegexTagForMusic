package org.essembeh.rtfm.fs.content;

import java.io.File;

import org.apache.commons.lang3.ObjectUtils;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public abstract class ResourceImpl implements IResource {

	private final File file;
	private final Attributes attributes;
	private VirtualPath virtualPath;
	private IFolder parentFolder;

	protected ResourceImpl(File file) {
		this(file, null);
	}

	protected ResourceImpl(File file, VirtualPath virtualPath) {
		this.file = file;
		this.virtualPath = virtualPath;
		this.parentFolder = null;
		this.attributes = new Attributes();
	}

	@Override
	public void setParentFolder(IFolder parentFolder) {
		this.parentFolder = parentFolder;
		if (parentFolder == null) {
			this.virtualPath = VirtualPath.ROOT;
		} else {
			this.virtualPath = new VirtualPath(parentFolder.getVirtualPath(), file);
		}
	}

	@Override
	public File getFile() {
		return file;
	}

	@Override
	public VirtualPath getVirtualPath() {
		return virtualPath;
	}

	@Override
	public Attributes getAttributes() {
		return attributes;
	}

	@Override
	public IFolder getParentFolder() {
		return parentFolder;
	}

	@Override
	public String getName() {
		if (virtualPath == VirtualPath.ROOT) {
			return virtualPath.toString();
		}
		return file.getName();
	}

	@Override
	public int compareTo(IResource o) {
		return ObjectUtils.compare(getVirtualPath(), o.getVirtualPath());
	}

	@Override
	public String toString() {
		return ObjectUtils.toString(virtualPath, "unknown");
	}
}
