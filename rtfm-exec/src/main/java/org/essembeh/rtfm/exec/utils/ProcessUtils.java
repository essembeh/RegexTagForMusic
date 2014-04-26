package org.essembeh.rtfm.exec.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
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
				IOUtils.closeQuietly(br);
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
			IOUtils.closeQuietly(p.getOutputStream());
			IOUtils.closeQuietly(p.getInputStream());
			IOUtils.closeQuietly(p.getErrorStream());
			p.destroy();
		}
	}
}
