/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.gui.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.services.Services;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.UpdateFilter;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class TabManager extends JTabbedPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5563787269249312695L;

	/**
	 * The list of tabs
	 */
	protected List<Tab> listOfTab;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public TabManager(RTFMController controller) {
		this.listOfTab = new ArrayList<Tab>();

		this.listOfTab.add(new Tab(controller));
		add("All", this.listOfTab.get(0));

		for (int i = 0; i < Services.getTabService().getTabCount(); i++) {
			String name = Services.getTabService().getTabName(i);
			Filter filter = Services.getTabService().getTabFilter(i);
			this.listOfTab.add(new Tab(controller, filter, true));
			add(name, this.listOfTab.get(i + 1));
		}
		addChangeListener(new UpdateFilter(controller));
	}

	/**
	 * Returns the filter set in the current tab
	 * 
	 * @return
	 */
	public Filter getCurrentFilter() {
		int sel = this.getSelectedIndex();
		return this.listOfTab.get(sel).getFilter();
	}

	/**
	 * Returns the selected files in the current tab
	 * 
	 * @return
	 */
	public List<IMusicFile> getCurrentSelectionOfFiles() {
		int sel = this.getSelectedIndex();
		return this.listOfTab.get(sel).getSelectionOfFiles();
	}

	/**
	 * Return all files in the current tab
	 * 
	 * @return
	 */
	public List<IMusicFile> getAllFiles() {
		int sel = this.getSelectedIndex();
		return this.listOfTab.get(sel).getAllFiles();
	}
}
