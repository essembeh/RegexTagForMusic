package org.essembeh.rtfm.fs.util;

import java.io.File;

import org.essembeh.rtfm.fs.content.VirtualPath;

/**
 * 
 * @author seb
 * 
 */
public class VirtualPathUtils {

	/**
	 * Make standard path, a Unix path.
	 * 
	 * @param path
	 * @return
	 */
	public static String makeNormalPath(String path) {
		String cleanPath = path.replace("\\", VirtualPath.SEPARATOR);
		return cleanPath;
	}

	/**
	 * 
	 * @param child
	 * @param parent
	 * @return
	 */
	public static String extractRelativePath(File child, File parent) {
		String childAbsolutePath = child.getAbsolutePath();
		String parentAbsolutePath = parent.getAbsolutePath();
		if (!childAbsolutePath.startsWith(parentAbsolutePath)) {
			throw new IllegalArgumentException(String.format("File '%s' is not relative to '%s'", childAbsolutePath, parentAbsolutePath));
		}

		byte[] parentPath = parentAbsolutePath.getBytes();
		byte[] childPath = childAbsolutePath.getBytes();
		int i = 0;
		while (i < parentPath.length && i < childPath.length) {
			if (parentPath[i] != childPath[i]) {
				break;
			}
			i++;
		}
		String relative = makeNormalPath(child.getAbsolutePath().substring(i));
		if (child.isDirectory() && !relative.endsWith(VirtualPath.SEPARATOR)) {
			relative += VirtualPath.SEPARATOR;
		}
		return relative;
	}

}
