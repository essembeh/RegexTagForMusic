package org.essembeh.rtfm.core.library.file;

import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface IMusicFile extends Comparable<IMusicFile> {

	FileType getType();

	String getVirtualPath();

	VirtualFile getFile();

	IdList<Attribute, Identifier<Attribute>> getAttributeList();

}
