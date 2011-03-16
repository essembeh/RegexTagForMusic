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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DatabaseException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWritterException;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MusicManager {

	/**
	 * The logger.
	 */
	protected static Logger logger = Logger.getLogger(MusicManager.class);

	/**
	 * The folder containing music.
	 */
	protected File rootFolder;

	/**
	 * THe list of all MusicFiles found in the Music root folder
	 */
	protected List<MusicFile> listOfFiles;

	/**
	 * Constructor, only initialize attributes.
	 */
	public MusicManager() {
		this.listOfFiles = new ArrayList<MusicFile>();
	}

	/**
	 * Return all files.
	 * 
	 * @return the listOfFiles
	 */
	public List<MusicFile> getAllFiles() {
		List<MusicFile> list = new ArrayList<MusicFile>();
		for (MusicFile musicFile : this.listOfFiles) {
			list.add(musicFile);
		}
		return list;
	}

	/**
	 * Return a list containing all files matching the filter
	 * 
	 * @param filter
	 * @return
	 */
	public List<MusicFile> getFilteredFiles(Filter filter) {
		return filter.filter(this.listOfFiles);
	}

	/**
	 * 
	 * @param database
	 * @param scanRootFolderInDatabase
	 * @throws DatabaseException
	 * @throws ConfigurationException
	 * @throws RTFMException
	 */
	public void readDatabase(File database, boolean scanRootFolderInDatabase) throws DatabaseException,
			ConfigurationException, RTFMException {
		logger.debug("Update with database: " + database.getAbsolutePath());
		Document document = null;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(database);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new DatabaseException("Error parsing the database: " + database.getAbsolutePath());
		}

		// Scan music
		try {
			if (scanRootFolderInDatabase) {
				logger.debug("Trying to scan database root music folder");
				Element musicElement = XmlUtils.getFirstElementByXPath(document, "//music");
				if (musicElement == null) {
					throw new DatabaseException("The database file is invalid");
				}
				String musicRootAttribute = musicElement.getAttribute("path");
				logger.debug("Found path: " + musicRootAttribute);
				scanMusicFolder(new File(musicRootAttribute));

			}
			List<Element> listOfFilesAsElements = XmlUtils.getElementsByXPath(document, "//music/*");
			logger.debug("Found " + listOfFilesAsElements.size() + " items in database");
			if (listOfFilesAsElements.size() > 0) {
				// Transform the list in map for performance
				Map<String, MusicFile> map = new HashMap<String, MusicFile>();
				for (MusicFile file : this.listOfFiles) {
					map.put(file.getVirtualPath(), file);
				}
				for (Element currentElement : listOfFilesAsElements) {
					String path = currentElement.getAttribute("path");
					String isTagged = currentElement.getAttribute("tagged");
					if (Boolean.TRUE.toString().equals(isTagged)) {
						logger.debug("Found a tagged item in database: " + path);
						// Search file
						MusicFile thefile = map.get(path);
						if (thefile != null) {
							logger.debug("The file exists in MusicManager, set as tagged");
							thefile.setTagged();
						} else {
							logger.debug("The file does not exist in MusicManager");
						}
					}
				}
			}
		} catch (XPathExpressionException e) {
			logger.error(e.toString());
			throw new DatabaseException("Error reading the database: " + database.getAbsolutePath());
		}
		logger.info("Database loaded: " + database.getAbsolutePath());
	}

	/**
	 * Remove all Music files from MusicManager instance
	 */
	public void removeAllMusicFiles() {
		if (this.listOfFiles.size() > 0) {
			logger.info("Remove all MusicFile from MusicManager");
		}
		// Clean the list of files
		this.listOfFiles.clear();
		this.rootFolder = null;
	}

	/**
	 * Scan the given folder and retrieve all MusicFiles in it.
	 * 
	 * @param theRootFolder
	 * @throws ConfigurationException
	 * @throws RTFMException
	 */
	public void scanMusicFolder(File theRootFolder) throws ConfigurationException, RTFMException {

		if (theRootFolder == null) {
			throw new RTFMException("The Music root folder has not been set");
		}
		if (!theRootFolder.exists() || !theRootFolder.isDirectory()) {
			throw new RTFMException("The root folder is invalid: " + theRootFolder.getAbsolutePath());
		}

		// Clean the previous musicfiles
		removeAllMusicFiles();

		// Set root folder
		this.rootFolder = theRootFolder;

		// Search all files
		String scanHiddenFiles = Configuration.instance().getProperty("scan.hidden.files");
		List<File> allFiles = FileUtils.searchFilesInFolder(this.rootFolder, Boolean.parseBoolean(scanHiddenFiles));

		for (File file : allFiles) {
			try {
				MusicFile musicFile = new MusicFile(file, theRootFolder);
				logger.debug("Adding: " + musicFile);
				this.listOfFiles.add(musicFile);
			} catch (ConfigurationException e) {
				logger.warn(e.toString());
			}
		}
		// Sort the list
		Collections.sort(this.listOfFiles);
		logger.info("Found " + this.listOfFiles.size() + " files in folder: " + this.rootFolder.getAbsolutePath());
	}

	/**
	 * 
	 * @param dryrun
	 * @return
	 */
	public int tagAllTaggableFiles(boolean dryrun) {
		return tagList(getFilteredFiles(Filter.TAGGABLE), dryrun);
	}

	/**
	 * 
	 * @param dryrun
	 * @return
	 */
	public int tagAllTaggableNonTaggedFiles(boolean dryrun) {
		return tagList(getFilteredFiles(Filter.NON_TAGGED), dryrun);
	}

	/**
	 * Tag all Music Files that are taggable and valid in the list given in
	 * argument.
	 * 
	 * @param listOfMF
	 * @param dryrun
	 * @return
	 */
	public int tagList(List<MusicFile> listOfMF, boolean dryrun) {
		Iterator<MusicFile> it = listOfMF.iterator();
		while (it.hasNext()) {
			MusicFile currentMusicFile = it.next();
			if (currentMusicFile.isTaggable()) {
				try {
					currentMusicFile.tagFile(dryrun);
					logger.info("File tagged: " + currentMusicFile.getVirtualPath());
					// Remove it from the list
					it.remove();
				} catch (TagWritterException e) {
					logger.error("Error while tagging file: " + currentMusicFile.getVirtualPath() + ": "
							+ e.getMessage());
				} catch (TagNotFoundException e) {
					logger.error("Error getting tag data for file: " + currentMusicFile.getVirtualPath() + ": "
							+ e.getMessage());
				} catch (RTFMException e) {
					logger.error("Error while tagging file: " + currentMusicFile.getVirtualPath() + ": "
							+ e.getMessage());
				}
			}

		}
		return listOfMF.size();
	}

	/**
	 * 
	 * @param database
	 * @throws DatabaseException
	 */
	public void writeDatabase(File database) throws DatabaseException {
		logger.debug("Write database: " + database.getAbsolutePath());
		Document document;
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new DatabaseException(e);
		}
		Element rootElement = document.createElement("music");
		rootElement.setAttribute("path", this.rootFolder.getAbsolutePath());
		// Iterate
		for (MusicFile file : getAllFiles()) {
			Element currentElement = document.createElement("file");
			String path = file.getVirtualPath();
			currentElement.setAttribute("path", path);
			String type = file.getType();
			currentElement.setAttribute("type", type);
			if (file.isTaggable()) {
				currentElement.setAttribute("tagged", new Boolean(file.isTagged()).toString());
			}
			rootElement.appendChild(currentElement);
		}
		document.appendChild(rootElement);
		// Prepare the DOM document for writing
		Source source = new DOMSource(document);
		// Prepare the output file
		Result result = new StreamResult(database);
		// Write the DOM document to the file
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.transform(source, result);
		} catch (Exception e) {
			logger.error(e.toString());
			throw new DatabaseException("Error writting database: " + database.getAbsolutePath());
		}
		logger.info("Database written in: " + database.getAbsolutePath());
	}
}