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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.Filter.Status;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.UpdateFilter;

public class FilterPane extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -4784113417411996447L;

	/**
	 * String REGEX for ANYTHING
	 */
	private static final String ANYTHING = ".*";

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(FilterPane.class);

	/**
	 * Used to translate filter for UI.
	 */
	Map<Status, String> taggedStrings = new HashMap<Filter.Status, String>();
	Map<Status, String> taggableStrings = new HashMap<Filter.Status, String>();

	/**
	 * UI Items
	 */
	JComboBox taggable;
	JComboBox tagged;
	JTextField pathRegex;
	JTextField typeRegex;

	/**
	 * The controller
	 */
	RTFMController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            the controller
	 * @param filter
	 *            the filter to show
	 * @param disableFilterEdition
	 *            if true, the filter will be disabled for the user
	 */
	public FilterPane(final RTFMController controller, Filter filter, boolean disableFilterEdition) {

		this.controller = controller;

		// Strings to translate a filter for UI
		this.taggableStrings.put(Status.ENABLE, "Taggable");
		this.taggableStrings.put(Status.INVERSE, "Not taggable");
		this.taggableStrings.put(Status.NO_FILTER, "All");
		this.taggedStrings.put(Status.ENABLE, "Tagged");
		this.taggedStrings.put(Status.INVERSE, "Not tagged");
		this.taggedStrings.put(Status.NO_FILTER, "All");

		// Create UI
		this.taggable = new JComboBox();
		this.taggable.setEditable(false);
		this.taggable.addItem(this.taggableStrings.get(Status.NO_FILTER));
		this.taggable.addItem(this.taggableStrings.get(Status.ENABLE));
		this.taggable.addItem(this.taggableStrings.get(Status.INVERSE));
		this.tagged = new JComboBox();
		this.tagged.setEditable(false);
		this.tagged.addItem(this.taggedStrings.get(Status.NO_FILTER));
		this.tagged.addItem(this.taggedStrings.get(Status.ENABLE));
		this.tagged.addItem(this.taggedStrings.get(Status.INVERSE));
		this.pathRegex = new JTextField();
		this.typeRegex = new JTextField();

		setLayout(new GridLayout(2, 4));
		add(new JLabel("Taggable"));
		add(this.taggable);
		add(new JLabel("Tagged"));
		add(this.tagged);
		add(new JLabel("Regex on path"));
		add(this.pathRegex);
		add(new JLabel("Type"));
		add(this.typeRegex);

		setFilter(filter, disableFilterEdition);

		// Set listeners
		this.taggable.addActionListener(new UpdateFilter(controller));
		this.tagged.addActionListener(new UpdateFilter(controller));
		this.typeRegex.addActionListener(new UpdateFilter(controller));
		this.pathRegex.addKeyListener(new UpdateFilter(controller));
		// Anonymous listener because only does nothing interesting
		this.tagged.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (FilterPane.this.tagged.getSelectedIndex() > 0) {
					FilterPane.this.taggable.setSelectedIndex(1);
				}
			}
		});
	}

	/**
	 * Returns the filtering options set in the panel
	 * 
	 * @return the filter
	 */
	public Filter getFilter() {
		String pathPattern = ANYTHING;
		if (this.pathRegex.getText().length() > 0) {
			pathPattern = ANYTHING + this.pathRegex.getText() + ANYTHING;
		}
		String typePattern = ANYTHING;
		if (this.typeRegex.getText().length() > 0) {
			typePattern = this.typeRegex.getText();
		}
		Filter f = new Filter(getStatusFromCombobox(this.taggable, this.taggableStrings), getStatusFromCombobox(
				this.tagged, this.taggedStrings), Pattern.compile(typePattern), Pattern.compile(pathPattern));

		logger.debug("Filter: " + f);
		return f;
	}

	/**
	 * Used to translate filter for UI
	 * 
	 * @param combo
	 * @param stringValues
	 * @return
	 */
	protected Status getStatusFromCombobox(JComboBox combo, Map<Status, String> stringValues) {
		Status status = Status.NO_FILTER;
		String comboValue = combo.getSelectedItem().toString();
		for (Status key : stringValues.keySet()) {
			if (stringValues.get(key).equals(comboValue)) {
				status = key;
				break;
			}
		}
		return status;
	}

	/**
	 * Change the filtering options
	 * 
	 * @param filter
	 * @param disableFilterEdition
	 */
	public void setFilter(Filter filter, boolean disableFilterEdition) {
		this.taggable.setSelectedItem(this.taggableStrings.get(filter.getTaggable()));
		this.tagged.setSelectedItem(this.taggedStrings.get(filter.getTagged()));
		Pattern patternOnPath = filter.getPath();
		if (patternOnPath != null) {
			this.pathRegex.setText(patternOnPath.pattern());
		} else {
			this.pathRegex.setText("");
		}
		Pattern patternOnType = filter.getType();
		if (patternOnType != null) {
			this.typeRegex.setText(patternOnType.pattern());
		} else {
			this.typeRegex.setText("");
		}

		if (disableFilterEdition) {
			this.pathRegex.setEnabled(false);
			this.typeRegex.setEnabled(false);
			this.taggable.setEnabled(false);
			this.tagged.setEnabled(false);
		}
	}
}
