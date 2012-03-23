package org.essembeh.rtfm.core.library.file;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class MusicFileIdentifier implements Identifier<IMusicFile> {
	@Override
	public String getId(IMusicFile o) {
		return o.getVirtualPath();
	}
}
