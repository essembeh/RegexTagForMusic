package org.essembeh.rtfm.core.utils.list;

public class DefaultIdentifier<T> implements Identifier<T> {

	@Override
	public String getId(T o) {
		return o.toString();
	}
}
