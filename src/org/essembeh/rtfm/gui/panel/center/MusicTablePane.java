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

import javax.swing.JScrollPane;

import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.tables.MusicTable;

public class MusicTablePane extends JScrollPane {

	private static final long serialVersionUID = 5526679516189400115L;

	MusicTable table = null;

	public MusicTablePane(GuiController controller) {
		this.table = new MusicTable(controller);
		setViewportView(this.table);
	}

	public List<MusicFile> getSelectedMusicFiles() {
		List<MusicFile> list = new ArrayList<MusicFile>();
		int[] selectedRows = this.table.getSelectedRows();
		for (int i : selectedRows) {
			list.add((MusicFile) this.table.getValueAt(i, 1));
		}
		return list;
	}

	public List<MusicFile> getAllFiles() {
		List<MusicFile> list = new ArrayList<MusicFile>();
		int nbRows = this.table.getRowCount();
		for (int i = 0; i < nbRows; i++) {
			list.add((MusicFile) this.table.getValueAt(i, 1));
		}
		return list;
	}
}
