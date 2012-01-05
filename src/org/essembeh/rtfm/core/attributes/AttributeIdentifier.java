package org.essembeh.rtfm.core.attributes;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class AttributeIdentifier implements Identifier<Attribute> {

	@Override
	public String getId(Attribute o) {
		return o.getName();
	}

}
