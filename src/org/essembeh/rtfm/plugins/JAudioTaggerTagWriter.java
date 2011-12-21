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
package org.essembeh.rtfm.plugins;

import java.io.File;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagWriter;
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
public class JAudioTaggerTagWriter implements ITagWriter {

	/**
	 * To handle version
	 */
	protected enum VERSION {
		ID3V2_3, ID3V2_4
	}

	/**
	 * Attributes
	 */
	protected VERSION version = VERSION.ID3V2_4;

	/**
	 * Class logger
	 */
	static protected Logger logger = Logger.getLogger(JAudioTaggerTagWriter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File)
	 */
	@Override
	public void removeTag(File mp3) throws TagWriterException {
		MP3File theMp3File = null;
		try {
			theMp3File = new MP3File(mp3);
			logger.debug("Removing all tags from file: " + mp3.getAbsolutePath());
			if (theMp3File.hasID3v1Tag()) {
				logger.debug("Removing tag v1 from file: " + mp3.getAbsolutePath());
				try {
					theMp3File.delete(theMp3File.getID3v1Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv1 on file: " + mp3.getAbsoluteFile());
				}
			}
			if (theMp3File.hasID3v2Tag()) {
				logger.debug("Removing tag v2 from file: " + mp3.getAbsolutePath());
				try {
					theMp3File.delete(theMp3File.getID3v2Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv2 on file: " + mp3.getAbsoluteFile());
				}
			}
			theMp3File.save();
		} catch (Exception e) {
			logger.error("Error while trying to open file fot tag removing: " + mp3.getAbsolutePath());
			throw new TagWriterException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ITagWriter#setProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setProperty(String name, String value) {
		if ("version".equals(name)) {
			this.version = VERSION.valueOf(value);
			logger.debug("Setting tag version to: " + this.version.toString());
		}
		logger.warn("Invalid property for tagger: " + name + "=" + value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.core.tag.TagData)
	 */
	@Override
	public void tag(File mp3, TagData tag) throws TagWriterException {
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
			setIfNotNull(id3, tag.getArtist(), FieldKey.ARTIST);
			setIfNotNull(id3, tag.getAlbum(), FieldKey.ALBUM);
			setIfNotNull(id3, tag.getYear(), FieldKey.YEAR);
			setIfNotNull(id3, tag.getTrackName(), FieldKey.TITLE);
			setIfNotNull(id3, tag.getTrackNumber(), FieldKey.TRACK);
			setIfNotNull(id3, tag.getGenre(), FieldKey.GENRE);
			setIfNotNull(id3, tag.getComment(), FieldKey.COMMENT);
		} catch (Exception e) {
			logger.error("Error creating ID3TagV2.4");
			throw new TagWriterException(e);
		}
		try {
			logger.debug("Saving tag to file : " + mp3.getAbsolutePath());
			MP3File file = new MP3File(mp3);
			file.setID3v2Tag(id3);
			file.save();
		} catch (Exception e) {
			logger.error("Error writing ID3TagV2.4 to file: " + mp3.getAbsolutePath());
			throw new TagWriterException(e);
		}
	}

	/**
	 * 
	 * @param tag
	 * @param field
	 * @param key
	 * @throws KeyNotFoundException
	 * @throws FieldDataInvalidException
	 */
	protected void setIfNotNull(AbstractID3v2Tag tag, String field, FieldKey key) throws KeyNotFoundException,
			FieldDataInvalidException {
		if (field != null) {
			tag.setField(key, field);
		}
	}
}
