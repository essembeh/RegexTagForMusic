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
package org.essembeh.rtfm.core.tag;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.interfaces.ITagField;
import org.essembeh.rtfm.interfaces.ITagProvider;

public class RegexTagProvider implements ITagProvider {

	protected Logger logger = Logger.getLogger(getClass());

	protected ITagField artist = null;
	protected ITagField album = null;
	protected ITagField year = null;
	protected ITagField tracknumber = null;
	protected ITagField trackname = null;
	protected ITagField comment = null;

	/* (non-Javadoc)
	 * @see org.essembeh.rtfm.core.tag.ITagProvider#getTagData(java.lang.String)
	 */
	public TagData getTagData(String path) throws TagNotFoundException {
		this.logger.debug("Parse for tardata: " + path + " with fields: " + toString());
		TagData tag = new TagData();
		if (this.artist != null) {
			tag.setArtist(this.artist.getValue(path));
		}
		if (this.album != null) {
			tag.setAlbum(this.album.getValue(path));
		}
		if (this.year != null) {
			tag.setYear(this.year.getValue(path));
		}
		if (this.trackname != null) {
			tag.setTrackName(this.trackname.getValue(path));
		}
		if (this.tracknumber != null) {
			tag.setTrackNumber(this.tracknumber.getValue(path));
		}
		if (this.comment != null) {
			tag.setComment(this.comment.getValue(path));
		}
		return tag;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(ITagField album) {
		this.album = album;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(ITagField artist) {
		this.artist = artist;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(ITagField comment) {
		this.comment = comment;
	}

	/**
	 * @param trackname
	 *            the trackname to set
	 */
	public void setTrackname(ITagField trackname) {
		this.trackname = trackname;
	}

	/**
	 * @param tracknumber
	 *            the tracknumber to set
	 */
	public void setTracknumber(ITagField tracknumber) {
		this.tracknumber = tracknumber;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(ITagField year) {
		this.year = year;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegexTagProvider [artist=" + this.artist + ", album=" + this.album + ", year=" + this.year + ", tracknumber="
				+ this.tracknumber + ", trackname=" + this.trackname + ", comment=" + this.comment + "]";
	}
}
