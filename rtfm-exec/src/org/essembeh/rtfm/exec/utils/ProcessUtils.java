package org.essembeh.rtfm.exec.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * 
 * @author seb
 * 
 */
public class ProcessUtils {

	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(ProcessUtils.class);

	public enum STDOUT {
		stdout, stderr
	};

	/**
	 * 
	 * @param p
	 * @param what
	 * @return
	 */
	public static String getProcessSysOut(Process p, STDOUT what) {
		StringBuilder out = new StringBuilder();
		if (p != null) {
			InputStream stream = null;
			if (what == STDOUT.stderr) {
				stream = p.getErrorStream();
			} else {
				stream = p.getInputStream();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			try {
				while (br.ready()) {
					out.append(br.readLine()).append("\n");
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally {
				closeCloseable(br);
			}
		}
		return out.toString();
	}

	/**
	 * Try to close stdout, stderr and stdin
	 * 
	 * @param p
	 */
	public static void end(Process p) {
		if (p != null) {
			closeCloseable(p.getOutputStream());
			closeCloseable(p.getInputStream());
			closeCloseable(p.getErrorStream());
			p.destroy();
		}
	}

	/**
	 * Close the closeable if not null. Does not throw any exception.
	 * 
	 * @param c
	 */
	public static void closeCloseable(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				// Do nothing
			}
		}
	}

	/**
	 * 
	 * @param scriptName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String retrieveBinaryFullPath(String scriptName) throws FileNotFoundException {
		File script = FileTools.getResourceAsFile(scriptName);
		String binaryPath = null;
		if (!script.canExecute()) {
			logger.error("The binary is not executable: " + scriptName);
		} else {
			binaryPath = script.getAbsolutePath();
			logger.debug("Found script: " + binaryPath);
		}
		return binaryPath;
	}
}
