package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.library.file.MusicFile;

public interface Condition {
	boolean isTrue(MusicFile musicFile);
}
