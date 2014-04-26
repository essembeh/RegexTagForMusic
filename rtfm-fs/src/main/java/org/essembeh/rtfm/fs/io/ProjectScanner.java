package org.essembeh.rtfm.fs.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.fs.content.FileImpl;
import org.essembeh.rtfm.fs.content.FolderImpl;
import org.essembeh.rtfm.fs.content.ProjectImpl;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.exception.ResourceAlreadyExistsException;

public class ProjectScanner {

	private final List<IScannerExtension> extensions;
	private final String date;

	public ProjectScanner(String date) {
		this.date = date;
		this.extensions = new ArrayList<>();
	}

	public IProject scanFolder(File folder) throws FileSystemException {
		if (!folder.isDirectory() || !folder.exists()) {
			throw new IllegalArgumentException("Not a valid folder: " + folder.getAbsolutePath());
		}
		IProject out = new ProjectImpl(folder, date);
		updateResource(out.getRootFolder());
		scanFolder(out.getRootFolder());
		return out;
	}

	private IResource updateResource(IResource resource) {
		resource.getAttributes().setDate(date);
		for (IScannerExtension e : extensions) {
			e.postCreation(resource);
		}
		return resource;
	}

	private void scanFolder(IFolder folder) throws ResourceAlreadyExistsException {
		if (!folder.getFile().isDirectory() || !folder.getFile().exists()) {
			throw new IllegalArgumentException("Not a valid folder: " + folder.getFile().getAbsolutePath());
		}
		File[] ls = folder.getFile().listFiles();
		if (ls != null) {
			for (File file : folder.getFile().listFiles()) {
				boolean skip = false;
				for (IScannerExtension e : extensions) {
					if (e.skipResource(file)) {
						skip = true;
						break;
					}
				}
				if (!skip) {
					IResource resource;
					if (file.isDirectory()) {
						resource = new FolderImpl(file);
					} else {
						resource = new FileImpl(file);
					}
					folder.addResource(resource);
					updateResource(resource);
				}
			}
		}
		// Scan subfolders
		for (final IResource subResource : folder.getResources()) {
			if (subResource instanceof IFolder) {
				scanFolder((IFolder) subResource);
			}
		}
	}

	public void addExtension(IScannerExtension extension) {
		extensions.add(extension);
	}

	public void removeExtension(IScannerExtension extension) {
		extensions.remove(extension);
	}

	public String getDate() {
		return date;
	}
}
