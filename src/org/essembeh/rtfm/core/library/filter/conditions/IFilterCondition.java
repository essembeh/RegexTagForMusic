package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface IFilterCondition {
	boolean isTrue(IMusicFile musicFile);
}
