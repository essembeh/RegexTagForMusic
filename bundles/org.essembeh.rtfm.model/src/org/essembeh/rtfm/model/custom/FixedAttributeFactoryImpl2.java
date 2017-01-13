package org.essembeh.rtfm.model.custom;

import org.essembeh.rtfm.model.rtfm.impl.FixedAttributeFactoryImpl;

public class FixedAttributeFactoryImpl2 extends FixedAttributeFactoryImpl {
	@Override
	public String getAttributeValue(String virtualPath) {
		return getValue();
	}
}
