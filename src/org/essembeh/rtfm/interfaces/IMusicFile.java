package org.essembeh.rtfm.interfaces;

import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.tag.TagData;

public interface IMusicFile {

	/**
	 * If the file is valid, use the regex tag reader in the handler to read tag
	 * informations from the virtual path.
	 * 
	 * @return
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 */
	public TagData getTagData() throws TagNotFoundException, RTFMException;

	/**
	 * Return the ID of handler
	 * 
	 * @return
	 */
	public String getType();

	/**
	 * 
	 * @return
	 */
	public String getVirtualPath();

	/**
	 * Returns true if the file is taggable
	 * 
	 * @return
	 */
	public boolean isTaggable();

	/**
	 * Return true if the file is set as tagged.
	 * 
	 * @return
	 */
	public boolean isTagged();

	/**
	 * 
	 * @return
	 */
	public boolean isExportableToDatabase();

}
