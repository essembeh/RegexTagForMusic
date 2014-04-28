package org.essembeh.rtfm.fs.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

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

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getResourceAsFile(String filename) throws FileNotFoundException {
		URL out = Thread.currentThread().getContextClassLoader().getResource(filename);
		logger.debug("getResourceAsFile " + filename + " --> " + out);
		if (out == null) {
			throw new FileNotFoundException("Cannot find file: " + filename);
		}
		return new File(out.getFile());
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static InputStream getResourceAsStream(String filename) {
		InputStream out = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		logger.debug("getResourceAsStream " + filename + " --> " + out);
		return out;
	}

}
