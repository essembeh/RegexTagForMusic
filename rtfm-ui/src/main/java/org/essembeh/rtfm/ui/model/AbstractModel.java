package org.essembeh.rtfm.ui.model;

import java.util.HashSet;
import java.util.Set;

import org.essembeh.rtfm.app.Application;

public class AbstractModel<L> {

	protected final Set<L> listeners = new HashSet<>();
	protected final Application app;

	public AbstractModel(Application app) {
		this.app = app;
	}

	void addListener(L l) {
		this.listeners.add(l);
	}

	void removeListener(L l) {
		this.listeners.remove(l);
	}

}
