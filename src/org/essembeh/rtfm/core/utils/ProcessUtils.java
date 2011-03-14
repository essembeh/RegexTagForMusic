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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ProcessUtils {

	static Logger logger = Logger.getLogger(ProcessUtils.class);

	/**
	 * 
	 * @param p
	 * @param getSysErrInsteadOfSysOut
	 * @return
	 */
	public static String getProcessSysOut(Process p, boolean getSysErrInsteadOfSysOut) {
		StringBuilder out = new StringBuilder();
		if (p != null) {
			InputStream stream = null;
			if (getSysErrInsteadOfSysOut) {
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
			}
		}
		return out.toString();
	}

}
