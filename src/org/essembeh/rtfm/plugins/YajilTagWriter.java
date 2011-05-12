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
import java.io.IOException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagWriter;

import cn.cooper.id3.ID3v2Tag;
import cn.cooper.id3.MP3File;

/**
 * @author seb
 * 
 */
public class YajilTagWriter implements ITagWriter {

	/**
	 * Class logger
	 */
	static protected Logger logger = Logger.getLogger(YajilTagWriter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File,
	 * boolean)
	 */
	@Override
	public void removeTag(File mp3, boolean dryrun) throws TagWriterException {
		if (!dryrun) {
			MP3File theMp3File = null;
			try {
				// TODO doc about fast mode
				theMp3File = new MP3File(mp3, "rw", true);
				theMp3File.removeID3v1Tag();
				theMp3File.removeID3v2Tag();

			} catch (Exception e) {
				throw new TagWriterException(e);
			} finally {
				// Try to close the file
				if (theMp3File != null) {
					try {
						theMp3File.close();
					} catch (IOException e) {
						throw new TagWriterException(e);
					}
				}
			}

		} else {
			logger.debug("Dry run mode");
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
		logger.warn("Invalid property for tagger: " + name + "=" + value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.core.tag.TagData, boolean)
	 */
	@Override
	public boolean tag(File mp3, TagData tag, boolean dryrun) throws TagWriterException {
		if (dryrun) {
			logger.debug("Dry run mode");
		} else {
			try {
				// Set the tag content
				ID3v2Tag theTag = new ID3v2Tag();
				if (tag.getArtist() != null) {
					theTag.setArtist(tag.getArtist());
				}
				if (tag.getAlbum() != null) {
					theTag.setAlbum(tag.getAlbum());
				}
				if (tag.getYear() != null) {
					theTag.setYear(tag.getYear());
				}
				if (tag.getTrackNumber() != null) {
					theTag.setTrack(tag.getTrackNumber());
				}
				if (tag.getTrackName() != null) {
					theTag.setTitle(tag.getTrackName());
				}
				if (tag.getGenre() != null) {
					theTag.setGenre(tag.getGenre());
				}
				MP3File theMp3File = new MP3File(mp3, "rw", true);
				theMp3File.writeID3v2Tag(theTag);
			} catch (Exception e) {
				throw new TagWriterException(e);
			}
		}
		return true;
	}
}
