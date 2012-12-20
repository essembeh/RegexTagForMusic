package org.essembeh.rtfm.core.condition.impl.virtualfile;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IVirtualFile;

public class AlwaysTrue<T extends IVirtualFile> implements ICondition<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T input) {
		return true;
	}

}
