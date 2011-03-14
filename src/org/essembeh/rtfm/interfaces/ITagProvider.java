package org.essembeh.rtfm.interfaces;

import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.tag.TagData;

public interface ITagProvider {

	/**
	 * 
	 * @param path
	 * @return
	 * @throws TagNotFoundException
	 */
	public TagData getTagData(String path) throws TagNotFoundException;

}