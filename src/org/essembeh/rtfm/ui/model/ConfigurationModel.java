package org.essembeh.rtfm.ui.model;

import javax.swing.DefaultComboBoxModel;

import org.essembeh.rtfm.core.configuration.ConfigurationHelper;

public class ConfigurationModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9074272636959433501L;
	private final ConfigurationHelper configurationHelper;

	/**
	 * 
	 * @param configurationHelper
	 */
	public ConfigurationModel(ConfigurationHelper configurationHelper) {
		super();
		this.configurationHelper = configurationHelper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultComboBoxModel#getSize()
	 */
	@Override
	public int getSize() {
		return configurationHelper.getConfigurationsList().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultComboBoxModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return configurationHelper.getConfigurationsList().get(index);
	}

}
