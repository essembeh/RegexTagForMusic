package org.essembeh.rtfm.core.library.file;

import java.util.Set;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface MusicFile extends Comparable<MusicFile> {

	String getType();

	String getVirtualPath();

	void executeAction(String actionName) throws ActionException;

	Set<String> getAllActions();

	VirtualFile getFile();

	IdList<Attribute, Identifier<Attribute>> getAttributeList();

}
