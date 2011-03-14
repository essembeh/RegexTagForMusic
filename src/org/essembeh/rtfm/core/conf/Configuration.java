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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.essembeh.rtfm.core.checkers.FixedChecker;
import org.essembeh.rtfm.core.checkers.RegexChecker;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.tag.RegexTagProvider;
import org.essembeh.rtfm.core.tag.fields.FixedField;
import org.essembeh.rtfm.core.tag.fields.OptionalField;
import org.essembeh.rtfm.core.tag.fields.RegexField;
import org.essembeh.rtfm.core.utils.XmlUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.ITagField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class handle the configuration file. It also create the file handlers.
 * 
 * @author seb
 * 
 */
public class Configuration {

	/*************** SiNGLETON ***************/
	static protected Configuration instance = null;

	/**
	 * Get the singleton on the Configuration
	 * 
	 * @return
	 * @throws ConfigurationException
	 */
	public static Configuration instance() throws ConfigurationException {
		if (instance == null) {
			String filename = "config.xml";
			try {
				instance = new Configuration(filename);
			} catch (XPathExpressionException e) {
				logger.error("Error during XML parsing: " + e.toString());
				throw new ConfigurationException("Error while parsing the XML configuration file: " + filename);
			} catch (ParserConfigurationException e) {
				logger.error("Error during XML parsing: " + e.toString());
				throw new ConfigurationException("Error while parsing the XML configuration file: " + filename);
			} catch (SAXException e) {
				logger.error("Error during XML parsing: " + e.toString());
				throw new ConfigurationException("Error while parsing the XML configuration file: " + filename);
			} catch (IOException e) {
				logger.error("Error during XML opening: " + e.toString());
				throw new ConfigurationException("Error while accessing the XML configuration file: " + filename);
			}
		}
		return instance;
	}

	/*************** CONSTRUCTION ***************/

	/**
	 * Configuration content
	 */
	Map<String, String> properties;
	List<FileHandler> listOfFileHandlers;
	Map<String, Class<?>> mapOfShellCommands;
	static Logger logger = Logger.getLogger(Configuration.class);

	/**
	 * Constructor
	 * 
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	protected Configuration(String filename) throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		this.listOfFileHandlers = new ArrayList<FileHandler>();
		this.mapOfShellCommands = new HashMap<String, Class<?>>();
		this.properties = new HashMap<String, String>();
		logger.debug("Loading configuration file: " + filename);
		parseConfigurationFile(filename);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public FileHandler getHandlerForFile(File file) {
		FileHandler handler = null;
		for (FileHandler currentHandler : this.listOfFileHandlers) {
			if (currentHandler.doesApplyForFile(file)) {
				handler = currentHandler;
				break;
			}
		}
		return handler;
	}

	/**
	 * @return the mapOfShellCommands
	 */
	public List<String> getListOfShellCommands() {
		List<String> list = new ArrayList<String>();
		for (String string : this.mapOfShellCommands.keySet()) {
			list.add(string);
		}
		Collections.sort(list);
		return list;
	}

	/**
	 * 
	 * @param string
	 * @return
	 * @throws RTFMException
	 */
	public String getMandatoryProperty(String string) throws ConfigurationException {
		String value = this.properties.get(string);
		logger.debug("Read mandatory property: " + string + "=" + value);
		if (value == null) {
			throw new ConfigurationException("The mandatory property is not found in configuration: " + string);
		}
		return value;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public String getProperty(String string) {
		String value = this.properties.get(string);
		logger.debug("Using property: " + string + "=" + value);
		return value;
	}

	/**
	 * Construct the Tagfield with the givent XML Node
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
					logger.debug("Found group for regex: " + group);
					regexTagField = new RegexField(Pattern.compile(pattern), Integer.parseInt(group));
				} else {
					logger.debug("Did not found group for regex");
					regexTagField = new RegexField(Pattern.compile(pattern));
				}
				String optional = element.getAttribute("optional");
				if ("true".equals(optional)) {
					logger.debug("Optional field");
					field = new OptionalField(regexTagField);
				} else {
					logger.debug("Non optional field");
					field = regexTagField;
				}
			} else {
				logger.debug("Fixed value: " + value);
				field = new FixedField(value);
			}
		}
		logger.debug("Found tagfield: " + field);
		return field;
	}

	/*************** PUBLIC METHODS ***************/

	/**
	 * 
	 * @param command
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ICommand instantiateCommand(String command) throws InstantiationException, IllegalAccessException {
		ICommand handler = null;
		for (String currentKey : this.mapOfShellCommands.keySet()) {
			if (command.equals(currentKey)) {
				Class<?> clazz = this.mapOfShellCommands.get(currentKey);
				handler = (ICommand) clazz.newInstance();
				break;
			}
		}
		return handler;
	}

	/**
	 * 
	 * @param configFileName
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 */
	protected void parseConfigurationFile(String configFileName) throws ParserConfigurationException, SAXException,
			IOException, XPathExpressionException {

		InputStream configFile = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document;
		document = documentBuilder.parse(configFile);
		readProperties(document);
		readFileHandlers(document);
		readShellCommands(document);
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readFileHandlers(Document document) throws XPathExpressionException {

		for (Element currentElementHandler : XmlUtils.getElementsByXPath(document, "//rtfm/handlers/*")) {
			if ((currentElementHandler != null) && currentElementHandler.getNodeName().equals("handler")) {
				// Mandatory attributes
				String id = currentElementHandler.getAttribute("id");
				String match = currentElementHandler.getAttribute("match");
				FileHandler currentHandler = new FileHandler(id, Pattern.compile(match));
				this.listOfFileHandlers.add(currentHandler);
				logger.debug("Found handler: " + currentHandler);

				// Optional attribute: Checker
				Element elementChecker = XmlUtils.getFirstElementByXPath(currentElementHandler, "checker");
				if (elementChecker != null) {
					String value = elementChecker.getAttribute("value");
					if ((value != null) && (value.length() > 0)) {
						currentHandler.setChecker(new FixedChecker(Boolean.parseBoolean(value)));
					} else {
						String pattern = elementChecker.getAttribute("pattern");
						currentHandler.setChecker(new RegexChecker(Pattern.compile(pattern)));
					}
					logger.debug("Found checker: " + currentHandler.getChecker());
				} else {
					logger.debug("No checker for current handler");
				}

				// Optional attribute: Tagwritter
				Element elementTagger = XmlUtils.getFirstElementByXPath(currentElementHandler, "tagwritter");
				if (elementTagger != null) {
					String taggerClassname = elementTagger.getAttribute("classname");
					try {
						currentHandler.setTagWritter(taggerClassname);
						logger.debug("Setting tagger: " + taggerClassname + " for Filehandler: " + currentHandler);
						// Setting properties
						List<Element> listOfProperties = XmlUtils.getElementsByXPath(elementTagger, "property");
						for (Element elementProperty : listOfProperties) {
							String name = elementProperty.getAttribute("name");
							String value = elementProperty.getAttribute("value");
							currentHandler.getTagWritter().setProperty(name, value);
						}
					} catch (Exception e) {
						logger.error("Error while creating tagger: " + taggerClassname + " for Filehandler: "
								+ currentHandler);
					}
				} else {
					logger.debug("No tagger for current handler");
				}

				// Optional attribute: TagData
				Element elementTagdata = XmlUtils.getFirstElementByXPath(currentElementHandler, "tagdata");
				if (elementTagdata != null) {
					RegexTagProvider regexTagProvider = new RegexTagProvider();
					ITagField fieldArtist = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata, "artist"));
					ITagField fieldAlbum = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata, "album"));
					ITagField fieldYear = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata, "year"));
					ITagField fieldTracknumber = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata,
							"tracknumber"));
					ITagField fieldTrackname = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata, "trackname"));
					ITagField fieldComment = getTagField(XmlUtils.getFirstElementByXPath(elementTagdata, "comment"));
					regexTagProvider.setArtist(fieldArtist);
					regexTagProvider.setAlbum(fieldAlbum);
					regexTagProvider.setYear(fieldYear);
					regexTagProvider.setTracknumber(fieldTracknumber);
					regexTagProvider.setTrackname(fieldTrackname);
					regexTagProvider.setComment(fieldComment);
					currentHandler.setTagProvider(regexTagProvider);
					logger.debug("Found tagdata: " + currentHandler.getTagProvider());

				} else {
					logger.debug("No tag data for current handler");
				}
			}
		}
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readProperties(Document document) throws XPathExpressionException {

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression xPathExpression = xpath.compile("//rtfm/properties/*");
		NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
		logger.debug("Found " + nodeList.getLength() + " properties");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element currentElement = (Element) nodeList.item(i);
			if ((currentElement != null) && currentElement.getNodeName().equals("property")) {
				String name = currentElement.getAttribute("name");
				String value = currentElement.getAttribute("value");
				logger.debug("Found property: " + name + "=" + value);
				this.properties.put(name, value);
			}
		}
	}

	/**
	 * 
	 * @param document
	 * @throws XPathExpressionException
	 */
	protected void readShellCommands(Document document) throws XPathExpressionException {

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression xPathExpression = xpath.compile("//rtfm/shell/*");
		NodeList nodeList = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
		logger.debug("Found " + nodeList.getLength() + " handlers");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element currentElement = (Element) nodeList.item(i);
			if ((currentElement != null) && currentElement.getNodeName().equals("command")) {
				String id = currentElement.getAttribute("id");
				String classname = currentElement.getAttribute("classname");
				String alternative = currentElement.getAttribute("alternative");
				try {
					Class<?> handler = Class.forName(classname);
					logger.debug("Found command: " + classname + " for id: " + id);
					this.mapOfShellCommands.put(id, handler);
					if ((alternative != null) && (alternative.length() > 0)
							&& !this.mapOfShellCommands.containsKey(alternative)) {
						logger.debug("Adding alternative: " + alternative + " to: " + id);
						this.mapOfShellCommands.put(alternative, handler);
					}
				} catch (ClassNotFoundException e) {
					logger.warn("Cannot find command: " + classname);
				}
			}
		}
	}
}
