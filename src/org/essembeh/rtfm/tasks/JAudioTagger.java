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
import org.essembeh.rtfm.core.exception.WorkflowException;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.attributes.Attributes;
import org.essembeh.rtfm.core.utils.commoninterfaces.impl.AbstractConfigurable;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;

/**
 * Tag writer that use the JAudioTagger Java library. JAudioTagger Library website: http://www.jthink.net/jaudiotagger/
 * 
 * For now this tag writer support ID3v2.3 and ID3v2.4 but no UTF8 encoding. Note that removing the tag does not remove data from mp3, it just wipes the ID3
 * header.
 * 
 * @author seb
 * 
 */
public class JAudioTagger extends AbstractConfigurable implements IExecutable {

	private final static String PROPERTY_TAGGED_ATTRIBUTE_KEY = "tagged-attribute";
	private final static String PROPERTY_VERSION_KEY = "version";

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
	public void execute(IXFile mp3) throws WorkflowException {
		logger.debug("Step1: Remove tags");
		removeTag(mp3);
		setTagged(mp3, false);
		logger.debug("Step2: Tag");
		tag(mp3);
		setTagged(mp3, true);
	}

	private void tag(IXFile mp3) throws WorkflowException {
		if (getProperties().containsKey(PROPERTY_VERSION_KEY)) {
			version = VERSION.valueOf(getProperties().get(PROPERTY_VERSION_KEY));
		}
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
			Attributes attributes = mp3.getAttributes();
			setIfNotNull(id3, attributes.getAttributeValue(attributeArtist), FieldKey.ARTIST);
			setIfNotNull(id3, attributes.getAttributeValue(attributeAlbum), FieldKey.ALBUM);
			setIfNotNull(id3, attributes.getAttributeValue(attributeYear), FieldKey.YEAR);
			setIfNotNull(id3, attributes.getAttributeValue(attributeTitle), FieldKey.TITLE);
			setIfNotNull(id3, attributes.getAttributeValue(attributeTrack), FieldKey.TRACK);
			setIfNotNull(id3, attributes.getAttributeValue(attributeComment), FieldKey.COMMENT);
		} catch (Exception e) {
			logger.error("Error creating ID3Tag");
			throw new WorkflowException(e);
		}
		try {
			logger.debug("Saving tag to file : " + mp3.getFile());
			MP3File file = new MP3File(mp3.getFile());
			file.setID3v2Tag(id3);
			file.save();
		} catch (Exception e) {
			logger.error("Error writing ID3Tag to file: " + mp3.getFile());
			throw new WorkflowException(e);
		}
	}

	private void removeTag(IXFile mp3) throws WorkflowException {
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
			throw new WorkflowException(e);
		}
	}

	private void setIfNotNull(AbstractID3v2Tag tag, String value, FieldKey key) throws KeyNotFoundException, FieldDataInvalidException {
		if (value != null) {
			tag.setField(key, value);
		}
	}

	private void setTagged(IXFile musicFile, Boolean tagged) {
		logger.debug("Set tagged=" + tagged + ", file: " + musicFile);
		if (getProperties().containsKey(PROPERTY_TAGGED_ATTRIBUTE_KEY)) {
			String attributeName = getProperties().get(PROPERTY_TAGGED_ATTRIBUTE_KEY);
			if (!musicFile.getAttributes().updateIfExists(attributeName, tagged.toString())) {
				logger.warn("Cannot update attribute tagged, attribute not found");
			}
		} else {
			logger.warn("No tagged attribute to update: " + PROPERTY_TAGGED_ATTRIBUTE_KEY);
		}
	}

}
