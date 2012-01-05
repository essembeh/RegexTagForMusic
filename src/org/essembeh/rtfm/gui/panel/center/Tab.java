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

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.gui.controller.GuiController;

public class Tab extends JPanel {

	private static final long serialVersionUID = -5606715267589764167L;

	Filter filter;

	MusicTablePane table;

	public Tab(GuiController controller, Filter filter) {
		this.filter = filter;
		this.table = new MusicTablePane(controller);
		setLayout(new BorderLayout());
		add(this.table, BorderLayout.CENTER);
	}

	public Filter getFilter() {
		return this.filter;
	}

	public List<MusicFile> getSelectionOfFiles() {
		return this.table.getSelectedMusicFiles();
	}

	public List<MusicFile> getAllFiles() {
		return this.table.getAllFiles();
	}
}
