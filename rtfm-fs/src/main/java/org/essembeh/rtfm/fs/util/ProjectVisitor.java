package org.essembeh.rtfm.fs.util;

import org.essembeh.rtfm.fs.content.interfaces.IFile;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public abstract class ProjectVisitor {

	protected void onEachFile(IFile file) {

	}

	protected void onEachFolder(IFolder folder) {

	}

	protected void onEachResource(IResource resource) {

	}

	final public void visitProject(IProject project) {
		visitResource(project.getRootFolder());
	}

	final public void visitFolder(IFolder folder) {
		visitResource(folder);
	}

	final private void visitResource(IResource resource) {
		onEachResource(resource);
		if (resource instanceof IFile) {
			visitFile0((IFile) resource);
		} else if (resource instanceof IFolder) {
			visitFolder0((IFolder) resource);
		}
	}

	final private void visitFolder0(IFolder folder) {
		onEachFolder(folder);
		for (IResource resource : folder.getResources()) {
			visitResource(resource);
		}
	}

	final private void visitFile0(IFile file) {
		onEachFile(file);
	}
}
