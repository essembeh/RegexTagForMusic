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

public class TagData {

	Logger logger = Logger.getLogger(getClass());

	protected String artist;
	protected String album;
	protected String year;
	protected String trackNumber;
	protected String trackName;
	protected String comment;
	protected String genre;

	/**
	 * 
	 * @param artist
	 * @param year
	 * @param album
	 * @param trackNumber
	 * @param trackName
	 * @param comment
	 * @param genre
	 */
	public TagData(String artist, String year, String album, String trackNumber, String trackName, String comment,
			String genre) {
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.trackNumber = trackNumber;
		this.trackName = trackName;
		this.comment = comment;
		this.genre = genre;
	}

	/**
	 * Default constructor
	 */
	public TagData() {
		// Does nothing, but exists.
		// And that is something ...
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO un peu porc quand meme non :)
		this.logger.debug("Test equality: " + this.toString());
		this.logger.debug("          and: " + obj.toString());
		return toString().equals(obj.toString());
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return this.album;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return this.artist;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * @return the trackName
	 */
	public String getTrackName() {
		return this.trackName;
	}

	/**
	 * @return the trackNumber
	 */
	public String getTrackNumber() {
		return this.trackNumber;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return this.year;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @param trackName
	 *            the trackName to set
	 */
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	/**
	 * @param trackNumber
	 *            the trackNumber to set
	 */
	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Information [artist=" + this.artist + ", album=" + this.album + ", year=" + this.year
				+ ", trackNumber=" + this.trackNumber + ", trackName=" + this.trackName + ", comment=" + this.comment
				+ ", genre=" + this.genre + "]";
	}
}
