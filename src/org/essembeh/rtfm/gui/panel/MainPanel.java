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

import java.awt.BorderLayout;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.panel.center.TabManager;
import org.essembeh.rtfm.gui.panel.side.SidePanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = -8753385764638412756L;

	RTFMProperties properties;
	SidePanel sidePanel;
	TabManager tabManager;
	StatusBar statusBar;

	public MainPanel(GuiController controller, RTFMProperties properties) {
		setLayout(new BorderLayout());
		this.properties = properties;
		this.sidePanel = new SidePanel(controller);
		add(this.sidePanel, BorderLayout.EAST);
		this.tabManager = new TabManager(controller);
		add(this.tabManager, BorderLayout.CENTER);
		this.statusBar = new StatusBar(properties.getProperty("app.name") + properties.getProperty("app.version"));
		add(this.statusBar, BorderLayout.SOUTH);
	}

	public Filter getCurrentFilter() {
		return this.tabManager.getCurrentFilter();
	}

	public List<MusicFile> getAllFiles() {
		return this.tabManager.getAllFiles();
	}

	public List<MusicFile> getCurrentSelectionOfFiles() {
		return this.tabManager.getCurrentSelectionOfFiles();
	}

	public void updateInformationPanel(File rootFolder, int fileCount, int nonTaggedCount) {
		this.sidePanel.updateInformationPanel(rootFolder, fileCount, nonTaggedCount);
	}

	public void statusPrintMessage(String line) {
		this.statusBar.printMessage(line);
	}

	public void statusPrintError(String line) {
		this.statusBar.printError(line);
	}

	public StatusBar getStatusBar() {
		return this.statusBar;
	}

	public void setTypeList(List<String> list) {
		this.tabManager.setTypeList(list);
	}

	public void updateActions(Map<String, Integer> map) {
		this.sidePanel.updateActions(map);
	}

}
