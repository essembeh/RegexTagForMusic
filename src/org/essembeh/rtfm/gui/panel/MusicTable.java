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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.Inspect;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.jdesktop.swingx.JXTable;

public class MusicTable extends JScrollPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 5526679516189400115L;

	/**
	 * UI Elements
	 */
	protected JXTable table = null;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public MusicTable(RTFMController controller) {
		this.table = new JXTable(controller.getModel());
		this.table.addMouseListener(new Inspect(controller));
		setPreferredSize(new Dimension(580, 200));
		setViewportView(this.table);
		// Set columns width
		this.table.getColumn(1).setPreferredWidth(400);
	}

	/**
	 * Returns the selected Music Files
	 * 
	 * @return the list of Files
	 */
	public List<IMusicFile> getSelectedMusicFiles() {
		List<IMusicFile> list = new ArrayList<IMusicFile>();
		int[] selectedRows = this.table.getSelectedRows();
		for (int i : selectedRows) {
			list.add((IMusicFile) this.table.getValueAt(i, 1));
		}
		return list;
	}

	/**
	 * Returns all files of the table
	 * 
	 * @return the list of Files
	 */
	public List<IMusicFile> getAllFiles() {
		List<IMusicFile> list = new ArrayList<IMusicFile>();
		int nbRows = this.table.getRowCount();
		for (int i = 0; i < nbRows; i++) {
			list.add((IMusicFile) this.table.getValueAt(i, 1));
		}
		return list;
	}
}
