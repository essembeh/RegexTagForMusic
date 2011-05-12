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

package org.essembeh.rtfm.core.conf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.FileHandler;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.tag.RegexTagProvider;
import org.essembeh.rtfm.core.tag.fields.FixedField;
import org.essembeh.rtfm.core.tag.fields.OptionalField;
import org.essembeh.rtfm.core.tag.fields.RegexField;
import org.essembeh.rtfm.core.utils.XmlUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.ITagField;
import org.essembeh.rtfm.interfaces.ITagProvider;
import org.essembeh.rtfm.interfaces.ITagWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class handle the configuration file.
 * 
 * @author seb
 * 
 */
public class Configuration {

	/**
	 * Class logger
	 */
	static protected Logger logger = Logger.getLogger(Configuration.class);

	/**
	 * Singleton
	 */
	static protected Configuration instance = null;

	/**
	 * Get the singleton on the Configuration
	 * 
	 * @return
	 */
	public static Configuration init() throws ConfigurationException {
		if (instance == null) {
			try {
				instance = new Configuration();
			} catch (Exception e) {
				throw new ConfigurationException("Error during Configuration init:" + e.toString());
			}
		}
		return instance;
	}

	/**
	 * Constructor
	 * 
	 * @throws ConfigurationException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 * 
	 */
	protected Configuration() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException,
			ConfigurationException {
		readTagWriters(openXMLFile(RTFMProperties.getMandatoryProperty("configuration.tagwriters.filename")));
		readTagProviders(openXMLFile(RTFMProperties.getMandatoryProperty("configuration.tagproviders.filename")));
		readFileHandlers(openXMLFile(RTFMProperties.getMandatoryProperty("configuration.filehandlers.filename")));
		readShellCommands(openXMLFile(RTFMProperties.getMandatoryProperty("configuration.shell.filename")));
	}

	/**
	 * Construct the Tagfield with the given XML Node
	 * 
	 * @param element
	 * @return
	 */
	protected ITagField getTagField(Element element) {
		ITagField field = null;
		if (element != null) {
			String value = element.getAttribute("value");
			if ((value == null) || (value.length() == 0)) {
				String pattern = element.getAttribute("pattern");
				String group = element.getAttribute("group");
				ITagField regexTagField = null;
				if (group != null && group.length() > 0) {
					regexTagField = new RegexField(Pattern.compile(pattern), Integer.parseInt(group));
				} else {
					regexTagField = new RegexField(Pattern.compile(pattern));
				}
				String optional = element.getAttribute("optional");
				if ("true".equals(optional)) {
					field = new OptionalField(regexTagField);
				} else {
					field = regexTagField;
				}
			} else {
				field = new FixedField(value);
			}
		}
		logger.debug("Found tagfield: " + field);
		return field;
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	protected Document openXMLFile(String filename) throws SAXException, IOException, ParserConfigurationException {
		InputStream configFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		if (configFile == null) {
			logger.error("Cannot find configuration file: " + filename);
			throw new FileNotFoundException(filename);
		}
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = documentBuilder.parse(configFile);
		return document;
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readTagWriters(Document document) throws XPathExpressionException {
		logger.debug("Reading Tag Writers");
		for (Element currentElement : XmlUtils.getElementsByXPath(document, "//rtfm/tagwriters/tagwriter")) {
			String id = currentElement.getAttribute("id");
			String classname = currentElement.getAttribute("classname");
			try {
				Class<?> clazz = Class.forName(classname);
				ITagWriter tagWriter = (ITagWriter) clazz.newInstance();
				logger.debug("Found tagwritter: " + classname);
				// Setting properties
				List<Element> listOfProperties = XmlUtils.getElementsByXPath(currentElement, "property");
				for (Element elementProperty : listOfProperties) {
					String name = elementProperty.getAttribute("name");
					String value = elementProperty.getAttribute("value");
					tagWriter.setProperty(name, value);
				}
				// Register the tag writer
				Services.instance().addTagWriter(id, tagWriter);
			} catch (Exception e) {
				logger.error("Error while creating tagger: " + classname);
			}
		}
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readTagProviders(Document document) throws XPathExpressionException {
		logger.debug("Reading Tag Providers");
		for (Element currentElement : XmlUtils.getElementsByXPath(document, "//rtfm/tagproviders/tagprovider")) {
			String id = currentElement.getAttribute("id");
			RegexTagProvider regexTagProvider = new RegexTagProvider();
			ITagField fieldArtist = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "artist"));
			ITagField fieldAlbum = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "album"));
			ITagField fieldYear = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "year"));
			ITagField fieldTracknumber = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "tracknumber"));
			ITagField fieldTrackname = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "trackname"));
			ITagField fieldComment = getTagField(XmlUtils.getFirstElementByXPath(currentElement, "comment"));
			regexTagProvider.setArtist(fieldArtist);
			regexTagProvider.setAlbum(fieldAlbum);
			regexTagProvider.setYear(fieldYear);
			regexTagProvider.setTracknumber(fieldTracknumber);
			regexTagProvider.setTrackname(fieldTrackname);
			regexTagProvider.setComment(fieldComment);
			logger.debug("Found tagprovider: " + regexTagProvider + " with id: " + id);
			// Register the tag provider
			Services.instance().addTagProvider(id, regexTagProvider);
		}
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readFileHandlers(Document document) throws XPathExpressionException {
		logger.debug("Reading File Handlers");
		for (Element currentElementHandler : XmlUtils.getElementsByXPath(document, "//rtfm/filehandlers/filehandler")) {
			// Mandatory attributes
			String id = currentElementHandler.getAttribute("id");
			String pattern = currentElementHandler.getAttribute("pattern");
			FileHandler fileHandler = new FileHandler(id, Pattern.compile(pattern));
			String exportToDB = currentElementHandler.getAttribute("database");
			if (exportToDB.length() > 0) {
				fileHandler.setExportableToDatabase(Boolean.parseBoolean(exportToDB));
			}

			// Optional attribute: TagProvider
			Element element0 = XmlUtils.getFirstElementByXPath(currentElementHandler, "tagprovider");
			if (element0 != null) {
				String ref = element0.getAttribute("ref");
				ITagProvider tagProvider = Services.instance().getTagProvider(ref);
				if (tagProvider == null) {
					logger.error("The tag provider cannot be found: " + ref);
				} else {
					logger.debug("Found tag provider: " + tagProvider);
					fileHandler.setTagProvider(tagProvider);
				}
			}

			// Optional attribute: TagWriterFactory
			Element element1 = XmlUtils.getFirstElementByXPath(currentElementHandler, "tagwriter");
			if (element1 != null) {
				String ref = element1.getAttribute("ref");
				ITagWriter tagWriter = Services.instance().getTagWriter(ref);
				if (tagWriter == null) {
					logger.error("The tag writer cannot be found: " + ref);
				} else {
					logger.debug("Found tag writer: " + tagWriter);
					fileHandler.setTagWriter(tagWriter);
				}
			}

			logger.debug("Found file handler: " + fileHandler);
			Services.instance().addFileHandler(fileHandler);

		}
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readShellCommands(Document document) throws XPathExpressionException {
		logger.debug("Reading Shell Commands");
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression xPathExpression = xpath.compile("//rtfm/shell/*");
		NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
		logger.debug("Found " + nodeList.getLength() + " commands");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element currentElement = (Element) nodeList.item(i);
			if ((currentElement != null) && currentElement.getNodeName().equals("command")) {
				String id = currentElement.getAttribute("id");
				String classname = currentElement.getAttribute("classname");
				String alternative = currentElement.getAttribute("alternative");
				try {
					Class<?> clazz = Class.forName(classname);
					ICommand shellCommand = (ICommand) clazz.newInstance();
					logger.debug("Found shell command: " + classname);
					Services.instance().addShellCommand(id, shellCommand, alternative);
				} catch (Exception e) {
					logger.error("Cannot create shell command: " + classname);
				}
			}
		}
	}
}
