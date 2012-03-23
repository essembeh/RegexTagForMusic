package org.essembeh.rtfm.core.filehandler.dynamic;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class DynamicAttributeIdentifier implements Identifier<IDynamicAttribute> {

	@Override
	public String getId(IDynamicAttribute o) {
		return o.getName();
	}

}
