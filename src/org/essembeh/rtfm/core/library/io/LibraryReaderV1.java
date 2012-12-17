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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.version.JaxbReader;
import org.essembeh.rtfm.model.library.version1.TFile;
import org.essembeh.rtfm.model.library.version1.TLibraryV1;

import com.google.inject.Inject;

public class LibraryReaderV1 extends JaxbReader<TLibraryV1> implements ILibraryProvider {
	/**
	 * Attributes
	 */
	private final static String RTFM_TAGGED = "rtfm:tagged";
	private final static Logger logger = Logger.getLogger(LibraryReaderV1.class);

	/**
	 * 
	 */
	@Inject
	public LibraryReaderV1() {
		super(TLibraryV1.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryLoader#fileExists(org.essembeh.rtfm.core.library.file.VirtualFile)
	 */
	@Override
	public boolean fileExists(VirtualFile virtualFile) {
		return findFile(virtualFile) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryLoader#getAttributesOfFile(org.essembeh.rtfm.core.library.file.VirtualFile)
	 */
	@Override
	public List<Attribute> getAttributesOfFile(VirtualFile virtualFile) {
		List<Attribute> out = new ArrayList<Attribute>();
		TFile modelFile = findFile(virtualFile);
		if (modelFile != null) {
			out.add(new Attribute(RTFM_TAGGED, modelFile.isTagged().toString()));
		}
		return out;
	}

	/**
	 * 
	 * @param virtualFile
	 * @return
	 */
	private TFile findFile(VirtualFile virtualFile) {
		TFile out = null;
		if (virtualFile != null && modelAvailable()) {
			for (TFile modelFile : getModel().getFile()) {
				if (ObjectUtils.equals(modelFile.getPath(), virtualFile.getVirtualPath())) {
					out = modelFile;
					break;
				}
			}
		}
		logger.debug("Finding file: " + virtualFile + ", found: " + out);
		return out;
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

}
