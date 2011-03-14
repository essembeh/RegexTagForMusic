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

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlUtils {

	static Logger logger = Logger.getLogger(XmlUtils.class);

	/**
	 * 
	 * @param item
	 * @param xpathString
	 * @return
	 * @throws XPathExpressionException
	 */
	public static List<Element> getElementsByXPath(Object item, String xpathString) throws XPathExpressionException {
		List<Element> list = new ArrayList<Element>();
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression xPathExpression;
		xPathExpression = xpath.compile(xpathString);
		NodeList nodeListHandlers = (NodeList) xPathExpression.evaluate(item, XPathConstants.NODESET);
		for (int i = 0; i < nodeListHandlers.getLength(); i++) {
			list.add((Element) nodeListHandlers.item(i));
		}
		logger.debug("Found " + list.size() + " elements for xpath: " + xpathString);
		return list;
	}

	/**
	 * 
	 * @param item
	 * @param xpathString
	 * @return
	 * @throws XPathExpressionException
	 */
	public static Element getFirstElementByXPath(Object item, String xpathString) throws XPathExpressionException {
		Element element = null;
		List<Element> list;
		list = getElementsByXPath(item, xpathString);
		if (list.size() > 0) {
			element = list.get(0);
		}
		return element;
	}

}
