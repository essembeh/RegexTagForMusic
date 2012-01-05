package org.essembeh.rtfm.core.attributes.dynamic;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class DynamicAttributeIdentifier implements Identifier<DynamicAttribute> {

	@Override
	public String getId(DynamicAttribute o) {
		return o.getName();
	}

}
