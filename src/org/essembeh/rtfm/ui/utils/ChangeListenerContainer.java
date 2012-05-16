package org.essembeh.rtfm.ui.utils;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.essembeh.rtfm.core.utils.listener.ListenerContainer;

public class ChangeListenerContainer extends ListenerContainer<ChangeListener> implements ChangeListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	public void stateChanged(final ChangeEvent e) {
		forEachListener(new ListenerAction<ChangeListener>() {
			@Override
			public void execute(ChangeListener listener) {
				listener.stateChanged(e);
			}
		});

	}

}
