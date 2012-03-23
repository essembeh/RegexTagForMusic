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
package org.essembeh.rtfm.gui.panel.center;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.filter.CommonFilters;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;

public class TabManager extends JTabbedPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5563787269249312695L;

	/**
	 * The tabs
	 */
	protected FilterableTab filterableTab = null;
	protected List<Tab> listOfTabs = null;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public TabManager(final GuiController controller) {
		this.listOfTabs = new ArrayList<Tab>();
		this.filterableTab = new FilterableTab(controller);
		createNewTab(filterableTab, Translator.get(StringId.tabAll));
		createNewTab(new Tab(controller, CommonFilters.filterOnAttribute("tagged", "false")),
				Translator.get(StringId.tabNew));
		createNewTab(new Tab(controller, CommonFilters.filterOnType("UNKNOWN")), Translator.get(StringId.tabUnknown));
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				controller.updateAfterTabChange();
			}
		});
	}

	protected void createNewTab(Tab tab, String title) {
		listOfTabs.add(tab);
		add(title, tab);
	}

	public Filter getCurrentFilter() {
		int sel = this.getSelectedIndex();
		return this.listOfTabs.get(sel).getFilter();
	}

	public List<IMusicFile> getCurrentSelectionOfFiles() {
		int sel = this.getSelectedIndex();
		return this.listOfTabs.get(sel).getSelectionOfFiles();
	}

	public List<IMusicFile> getAllFiles() {
		int sel = this.getSelectedIndex();
		return this.listOfTabs.get(sel).getAllFiles();
	}

	public void setTypeList(List<String> list) {
		this.filterableTab.setTypeList(list);
	}
}
