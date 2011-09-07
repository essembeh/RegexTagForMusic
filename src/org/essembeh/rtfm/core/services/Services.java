/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of rtfm.
 * 
 * rtfm is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * rtfm is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * rtfm. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.services;

/**
 * @author seb
 * 
 */
public class Services {

	/**
	 * Services
	 */
	static protected final FileHandlerService fileHandlerService = new FileHandlerService();
	static protected final TagProviderService tagProviderService = new TagProviderService();
	static protected final TagWriterService tagWriterService = new TagWriterService();
	static protected final ShellCommandService shellCommandService = new ShellCommandService();
	static protected final TabService tabService = new TabService();

	/**
	 * Getters
	 */
	public static FileHandlerService getFilehandlerService() {
		return fileHandlerService;
	}

	public static TagProviderService getTagProviderService() {
		return tagProviderService;
	}

	public static TagWriterService getTagWriterService() {
		return tagWriterService;
	}

	public static ShellCommandService getShellCommandService() {
		return shellCommandService;
	}

	public static TabService getTabService() {
		return tabService;
	}

}
