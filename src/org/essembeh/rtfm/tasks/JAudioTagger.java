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
package org.essembeh.rtfm.tasks;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;

/**
 * Tag writer that use the JAudioTagger Java library. JAudioTagger Library
 * website: http://www.jthink.net/jaudiotagger/
 * 
 * For now this tag writer support ID3v2.3 and ID3v2.4 but no UTF8 encoding.
 * Note that removing the tag does not remove data from mp3, it just wipes the
 * ID3 header.
 * 
 * @author seb
 * 
 */
public class JAudioTagger implements ITask {

	private static Logger logger = Logger.getLogger(JAudioTagger.class);

	enum VERSION {
		ID3V2_3, ID3V2_4
	}

	private final String attributeArtist = "tag:artist";
	private final String attributeAlbum = "tag:album";
	private final String attributeYear = "tag:year";
	private final String attributeTitle = "tag:title";
	private final String attributeTrack = "tag:track";
	private final String attributeComment = "tag:comment";

	private VERSION version = VERSION.ID3V2_4;

	/**
	 * Constructor
	 */
	public JAudioTagger() {
	}

	@Override
	public void setProperty(String name, String value) {
		if ("version".equals(name)) {
			this.version = VERSION.valueOf(value);
			logger.debug("Setting tag version to: " + this.version.toString());
		} else {
			logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	@Override
	public void execute(IMusicFile mp3) throws ActionException {
		logger.debug("Step1: Remove tags");
		removeTag(mp3);
		setTagged(mp3, false);
		logger.debug("Step2: Tag");
		tag(mp3);
		setTagged(mp3, true);

	}

	private void setTagged(IMusicFile musicFile, Boolean tagged) {
		logger.debug("Set tagged=" + tagged + ", file: " + musicFile);
		Attribute taggedAttribute = musicFile.getAttributeList().get("rtfm:tagged");
		if (taggedAttribute != null) {
			taggedAttribute.setValue(tagged.toString());
		} else {
			logger.warn("Cannot update attribute tagged, attribute not found");
		}
	}

	private void tag(IMusicFile mp3) throws ActionException {
		// Set the tag content
		AbstractID3v2Tag id3 = null;
		if (this.version == VERSION.ID3V2_3) {
			logger.debug("Creating ID3v2.3 tag");
			id3 = new ID3v23Tag();
		} else {
			logger.debug("Creating ID3v2.4 tag");
			id3 = new ID3v24Tag();
		}
		try {
			logger.debug("Generating tag content");
			IdList<Attribute, Identifier<Attribute>> attributes = mp3.getAttributeList();
			setIfNotNull(id3, attributes.get(attributeArtist), FieldKey.ARTIST);
			setIfNotNull(id3, attributes.get(attributeAlbum), FieldKey.ALBUM);
			setIfNotNull(id3, attributes.get(attributeYear), FieldKey.YEAR);
			setIfNotNull(id3, attributes.get(attributeTitle), FieldKey.TITLE);
			setIfNotNull(id3, attributes.get(attributeTrack), FieldKey.TRACK);
			setIfNotNull(id3, attributes.get(attributeComment), FieldKey.COMMENT);
		} catch (Exception e) {
			logger.error("Error creating ID3Tag");
			throw new ActionException(e);
		}
		try {
			logger.debug("Saving tag to file : " + mp3.getFile());
			MP3File file = new MP3File(mp3.getFile());
			file.setID3v2Tag(id3);
			file.save();
		} catch (Exception e) {
			logger.error("Error writing ID3Tag to file: " + mp3.getFile());
			throw new ActionException(e);
		}
	}

	private void removeTag(IMusicFile mp3) throws ActionException {
		MP3File theMp3File = null;
		try {
			theMp3File = new MP3File(mp3.getFile());
			logger.debug("Removing all tags from file: " + mp3.getFile());
			if (theMp3File.hasID3v1Tag()) {
				logger.debug("Removing tag v1 from file: " + mp3.getFile());
				try {
					theMp3File.delete(theMp3File.getID3v1Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv1 on file: " + mp3.getFile());
				}
			}
			if (theMp3File.hasID3v2Tag()) {
				logger.debug("Removing tag v2 from file: " + mp3.getFile());
				try {
					theMp3File.delete(theMp3File.getID3v2Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv2 on file: " + mp3.getFile());
				}
			}
			theMp3File.save();
		} catch (Exception e) {
			logger.error("Error while trying to open file fot tag removing: " + mp3.getFile());
			throw new ActionException(e);
		}
	}

	private void setIfNotNull(AbstractID3v2Tag tag, Attribute field, FieldKey key) throws KeyNotFoundException,
			FieldDataInvalidException {
		if (field != null) {
			String value = field.getValue();
			if (value != null) {
				tag.setField(key, field.getValue());
			}
		}
	}
}
