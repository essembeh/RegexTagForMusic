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
import java.util.List;

import javax.swing.JPanel;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class Tab extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5606715267589764167L;

	/**
	 * The panel for filtering files
	 */
	protected FilterPane filterPane = null;

	/**
	 * The table that displays music files
	 */
	protected MusicTable table = null;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public Tab(RTFMController controller) {
		this(controller, Filter.ALL, false);
	}

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @param filter
	 * @param disableFilterEdition
	 */
	public Tab(RTFMController controller, Filter filter,
			boolean disableFilterEdition) {
		setLayout(new BorderLayout());
		this.table = new MusicTable(controller);
		this.filterPane = new FilterPane(controller, filter,
				disableFilterEdition);
		add(this.table, BorderLayout.CENTER);
		add(this.filterPane, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @return
	 */
	public Filter getFilter() {
		return this.filterPane.getFilter();
	}

	/**
	 * 
	 * @return
	 */
	public List<IMusicFile> getSelectionOfFiles() {
		return this.table.getSelectedMusicFiles();
	}

	/**
	 * 
	 * @return
	 */
	public List<IMusicFile> getAllFiles() {
		return this.table.getAllFiles();
	}
}
