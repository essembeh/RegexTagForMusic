package org.essembeh.rtfm.gui.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.essembeh.rtfm.gui.controller.GuiController;

public class TableSelectionListener implements ListSelectionListener {

	GuiController controller;

	public TableSelectionListener(GuiController controller) {
		this.controller = controller;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}

}
