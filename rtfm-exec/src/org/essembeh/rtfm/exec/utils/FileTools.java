package org.essembeh.rtfm.exec.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author seb
 * 
 */
public class FileTools {

	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(FileTools.class);
	private final static String SEPARATOR = "/";

	/**
	 * 
	 * @param folder
	 * @param listHidden
	 * @return
	 */
	public static List<File> searchFilesInFolder(File folder, boolean listHidden) {
		List<File> list = new ArrayList<File>();
		if (folder.isDirectory() && folder.exists()) {
			File[] ls = folder.listFiles();
			for (File file : ls) {
				if (listHidden || !file.isHidden()) {
					list.add(file);
					if (file.isDirectory()) {
						list.addAll(searchFilesInFolder(file, listHidden));
					}
				}
			}
		}
		return list;
	}

	/**
	 * Make standard path, a Unix path.
	 * 
	 * @param path
	 * @return
	 */
	public static String makeNormalPath(String path) {
		String cleanPath = path.replace("\\", SEPARATOR);
		return cleanPath;
	}

	/**
	 * 
	 * @param child
	 * @param parent
	 * @return
	 */
	public static String extractRelativePath(File child, File parent) {
		byte[] parentPath = parent.getAbsolutePath().getBytes();
		byte[] childPath = child.getAbsolutePath().getBytes();
		int i = 0;
		while (i < parentPath.length && i < childPath.length) {
			if (parentPath[i] != childPath[i]) {
				break;
			}
			i++;
		}
		String relative = makeNormalPath(child.getAbsolutePath().substring(i));
		if (child.isDirectory() && !relative.endsWith(SEPARATOR)) {
			relative += SEPARATOR;
		}
		return relative;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getResourceAsFile(String filename) throws FileNotFoundException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource(filename);
		if (resource == null) {
			throw new FileNotFoundException("Cannot find file: " + filename);
		}
		return new File(resource.getFile());
	}

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	public static InputStream getResourceAsStream(String filename) throws FileNotFoundException {
		InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		if (resource == null) {
			throw new FileNotFoundException("Cannot find file: " + filename);
		}
		logger.debug("Found resource: " + filename);
		return resource;
	}

}
