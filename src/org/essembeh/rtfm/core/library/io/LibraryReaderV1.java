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
package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.essembeh.rtfm.core.utils.version.JaxbReader;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.model.library.version1.TFile;
import org.essembeh.rtfm.model.library.version1.TLibraryV1;

import com.google.inject.Inject;

public class LibraryReaderV1 extends JaxbReader<TLibraryV1> implements ILibraryReader {
	/**
	 * Attributes
	 */
	private final static String RTFM_TAGGED = "music:tagged?";
	private final Map<String, TFile> cache;

	/**
	 * 
	 */
	@Inject
	public LibraryReaderV1() {
		super(TLibraryV1.class);
		this.cache = new HashMap<String, TFile>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.JaxbReader#read(java.io.InputStream)
	 */
	@Override
	public void read(InputStream inputStream) throws ReaderException {
		cache.clear();
		super.read(inputStream);
		for (TFile modelFile : getModel().getFile()) {
			cache.put(modelFile.getPath(), modelFile);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryProvider#getRootFolder()
	 */
	@Override
	public File getRootFolder() {
		File out = null;
		if (modelAvailable()) {
			out = new File(getModel().getPath());
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryReader#getListOfFiles()
	 */
	@Override
	public List<String> getListOfFiles() {
		return Arrays.asList(cache.keySet().toArray(new String[0]));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryReader#getAttributesForFile(java.lang.String)
	 */
	@Override
	public Map<String, String> getAttributesForFile(String virtualPath) {
		Map<String, String> out = null;
		if (cache.containsKey(virtualPath)) {
			out = new HashMap<String, String>();
			out.put(RTFM_TAGGED, cache.get(virtualPath).isTagged().toString());
		}
		return out;
	}

}
