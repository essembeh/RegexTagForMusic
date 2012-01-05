package org.essembeh.rtfm.gui.menu;

import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import org.essembeh.rtfm.gui.controller.GuiController;

public class ContextualMenu extends JPopupMenu {

	private static final long serialVersionUID = -8784040148143087595L;

	GuiController controller;

	public ContextualMenu(GuiController controller) {
		this.controller = controller;
		add(new JLabel("test"));
	}
}
