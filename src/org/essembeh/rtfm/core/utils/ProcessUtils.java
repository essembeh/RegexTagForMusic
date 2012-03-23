/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.essembeh.rtfm.core.utils;

import java.io.BufferedReader;
import java.io.Closeable;
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

	static private Logger logger = Logger.getLogger(ProcessUtils.class);

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

}
