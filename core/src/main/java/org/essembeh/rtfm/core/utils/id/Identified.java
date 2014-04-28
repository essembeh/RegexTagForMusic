package org.essembeh.rtfm.core.utils.id;

import org.apache.commons.lang3.ObjectUtils;

public abstract class Identified implements Identifiable {

	private final String id;

	public Identified(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int compareTo(Identifiable o) {
		return ObjectUtils.compare(id, o.getId());
	}
}
