package org.essembeh.rtfm.exec.tasks;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ExecutionException;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.CommonExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;
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
 * For now this tag writer support ID3v2.3 and ID3v2.4 but no UTF8 encoding. Note that removing the tag does not remove data from mp3, it just wipes the ID3 header.
 * 
 * @author seb
 * 
 */
public class JAudioTagger extends CommonExecutable implements IExecutable {

	private final static String PROPERTY_VERSION_KEY = "version";

	private static Logger logger = Logger.getLogger(JAudioTagger.class);

	enum VERSION {
		ID3V2_3, ID3V2_4
	}

	private final String attributeArtist = "music:artist";
	private final String attributeAlbum = "music:album";
	private final String attributeYear = "music:year";
	private final String attributeTitle = "music:title";
	private final String attributeTrack = "music:track";
	private final String attributeComment = "music:comment";

	private VERSION version;

	/**
	 * Constructor
	 */
	public JAudioTagger() {
		version = VERSION.ID3V2_4;
	}

	@Override
	public int execute0(IResource resource) throws ExecutionException {
		logger.debug("Step1: Remove tags");
		removeTag(resource);
		logger.debug("Step2: Tag");
		tag(resource);
		return 0;
	}

	private void tag(IResource resource) throws ExecutionException {
		String versionProperty = getFirstProperty(PROPERTY_VERSION_KEY);
		if (versionProperty != null) {
			version = VERSION.valueOf(versionProperty);
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
			setIfNotNull(id3, AttributesHelper.get(resource, attributeArtist), FieldKey.ARTIST);
			setIfNotNull(id3, AttributesHelper.get(resource, attributeAlbum), FieldKey.ALBUM);
			setIfNotNull(id3, AttributesHelper.get(resource, attributeYear), FieldKey.YEAR);
			setIfNotNull(id3, AttributesHelper.get(resource, attributeTitle), FieldKey.TITLE);
			setIfNotNull(id3, AttributesHelper.get(resource, attributeTrack), FieldKey.TRACK);
			setIfNotNull(id3, AttributesHelper.get(resource, attributeComment), FieldKey.COMMENT);
		} catch (Exception e) {
			logger.error("Error creating ID3Tag");
			throw new ExecutionException(e.getMessage());
		}
		try {
			logger.debug("Saving tag to file : " + resource);
			MP3File file = new MP3File(resource.getFile());
			file.setID3v2Tag(id3);
			file.save();
		} catch (Exception e) {
			logger.error("Error writing ID3Tag to file: " + resource);
			throw new ExecutionException(e.getMessage());
		}
	}

	private void removeTag(IResource resource) throws ExecutionException {
		MP3File mp3File = null;
		try {
			mp3File = new MP3File(resource.getFile());
			logger.debug("Removing all tags from file: " + resource);
			if (mp3File.hasID3v1Tag()) {
				logger.debug("Removing tag v1 from file: " + resource);
				try {
					mp3File.delete(mp3File.getID3v1Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv1 on file: " + resource);
				}
			}
			if (mp3File.hasID3v2Tag()) {
				logger.debug("Removing tag v2 from file: " + resource);
				try {
					mp3File.delete(mp3File.getID3v2Tag());
				} catch (Exception e) {
					logger.error("Error while remove tagv2 on file: " + resource);
				}
			}
			mp3File.save();
		} catch (Exception e) {
			logger.error("Error while trying to open file fot tag removing: " + resource);
			throw new ExecutionException(e.getMessage());
		}
	}

	private void setIfNotNull(AbstractID3v2Tag tag, String value, FieldKey key) throws KeyNotFoundException,
			FieldDataInvalidException {
		if (value != null) {
			tag.setField(key, value);
		}
	}

}
