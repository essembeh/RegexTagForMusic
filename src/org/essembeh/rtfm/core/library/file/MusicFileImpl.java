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

package org.essembeh.rtfm.core.library.file;

import java.util.Set;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.attributes.AttributeIdentifier;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.workflow.Engine;

/**
 * Represent a file in Music Folder. Not only MP3 but every file in the folder,
 * covers, playlists ...
 * 
 * @author seb
 * 
 */
public class MusicFileImpl implements MusicFile {

	protected static Logger logger = Logger.getLogger(MusicFileImpl.class);

	String type;

	VirtualFile file;

	Engine engine;

	IdList<Attribute, Identifier<Attribute>> attributeList;

	public MusicFileImpl(String type, VirtualFile virtualFile, Engine engine) {
		this.type = type;
		this.file = virtualFile;
		this.engine = engine;
		this.attributeList = new IdList<Attribute, Identifier<Attribute>>(new AttributeIdentifier());
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getVirtualPath() {
		return file.getVirtualPath();
	}

	@Override
	public void executeAction(String actionName) throws ActionException {
		engine.execute(actionName, this);
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("[").append(getType()).append("] ");
		out.append(getFile()).append(" ");
		out.append('{').append(attributeList).append("}");
		return out.toString();
	}

	@Override
	public int compareTo(MusicFile other) {
		return getVirtualPath().compareTo(other.getVirtualPath());
	}

	@Override
	public Set<String> getAllActions() {
		return this.engine.getActions();
	}

	@Override
	public VirtualFile getFile() {
		return file;
	}

	@Override
	public IdList<Attribute, Identifier<Attribute>> getAttributeList() {
		return attributeList;
	}
}
