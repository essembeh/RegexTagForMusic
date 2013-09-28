package org.essembeh.rtfm.fs.condition;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface ICondition {
	boolean isTrue(IResource resource);
}
