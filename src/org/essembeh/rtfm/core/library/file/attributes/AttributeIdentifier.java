package org.essembeh.rtfm.core.library.file.attributes;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class AttributeIdentifier implements Identifier<Attribute> {

	@Override
	public String getId(Attribute o) {
		return o.getName();
	}

}
