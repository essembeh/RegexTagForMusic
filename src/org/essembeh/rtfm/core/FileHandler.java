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

package org.essembeh.rtfm.core;

import java.io.File;
import java.util.regex.Pattern;

import org.essembeh.rtfm.interfaces.IChecker;
import org.essembeh.rtfm.interfaces.ITagProvider;
import org.essembeh.rtfm.interfaces.ITagWritter;

public class FileHandler {
	private String id = null;
	private IChecker checker = null;
	private ITagWritter tagWritter = null;
	private ITagProvider regexTagProvider = null;
	private Pattern applyPattern;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param extension
	 */
	public FileHandler(String id, Pattern applyPattern) {
		this.id = id;
		this.applyPattern = applyPattern;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public boolean doesApplyForFile(File file) {
		String filename = file.getName();
		return this.applyPattern.matcher(filename).matches();
	}

	/**
	 * 
	 * @return the checker
	 */
	public IChecker getChecker() {
		return this.checker;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return the regexTagReader
	 */
	public ITagProvider getTagProvider() {
		return this.regexTagProvider;
	}

	/**
	 * @return the tagWritter
	 */
	public ITagWritter getTagWritter() {
		return this.tagWritter;
	}

	/**
	 * @param checker
	 *            the checker to set
	 */
	public void setChecker(IChecker checker) {
		this.checker = checker;
	}

	/**
	 * @param regexTagProvider
	 *            the regexTag to set
	 */
	public void setTagProvider(ITagProvider regexTagProvider) {
		this.regexTagProvider = regexTagProvider;
	}

	/**
	 * @param tagWritter
	 *            the tagWritter to set
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void setTagWritter(String taggerClassname) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Class<?> taggerClass = Class.forName(taggerClassname);
		this.tagWritter = (ITagWritter) taggerClass.newInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileHandler [id=" + this.id + ", checker=" + this.checker + ", tagWritter=" + this.tagWritter + "]";
	}
}
