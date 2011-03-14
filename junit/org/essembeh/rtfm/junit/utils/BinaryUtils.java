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
package org.essembeh.rtfm.junit.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryUtils {
	/**
	 * Check that the file contains the bytes in its n first bytes.
	 * 
	 * @param f
	 * @param bytes
	 * @param headSize
	 * @return
	 * @throws IOException
	 */
	public static boolean checkFileContainsBytes(File f, byte[] bytes,
			int headSize) throws IOException {
		boolean result = false;
		FileInputStream fis = new FileInputStream(f);
		byte[] bytesFromFile = new byte[headSize];
		int count = fis.read(bytesFromFile);
		if (count > 0) {
			result = findSequenceInArray(bytesFromFile, bytes);
		}
		return result;
	}

	/**
	 * 
	 * @param buffer
	 * @param sequence
	 * @return
	 */
	public static boolean findSequenceInArray(byte[] buffer, byte[] sequence) {
		return findSequenceInArray(buffer, 0, sequence, 0);
	}

	/**
	 * 
	 * @param buffer
	 * @param bufferIndex
	 * @param sequence
	 * @param sequenceIndex
	 * @return
	 */
	protected static boolean findSequenceInArray(byte[] buffer, int bufferIndex,
			byte[] sequence, int sequenceIndex) {
		boolean result = false;
		if (sequenceIndex >= sequence.length) {
			// OK is sequence is totally read
			result = true;
		} else if (bufferIndex >= buffer.length) {
			// KO if end of buffer
			result = false;
		} else {
			if (buffer[bufferIndex] == sequence[sequenceIndex]) {
				// Try to find the end of sequence
				result = findSequenceInArray(buffer, bufferIndex + 1, sequence,
						sequenceIndex + 1);
			}
			if (!result) {
				// try to find sequence forward
				result = findSequenceInArray(buffer, bufferIndex + 1, sequence,
						0);
			}
		}
		return result;
	}
}
