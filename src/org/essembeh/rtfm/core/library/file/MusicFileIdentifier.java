package org.essembeh.rtfm.core.library.file;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class MusicFileIdentifier implements Identifier<MusicFile> {
	@Override
	public String getId(MusicFile o) {
		return o.getVirtualPath();
	}
}
