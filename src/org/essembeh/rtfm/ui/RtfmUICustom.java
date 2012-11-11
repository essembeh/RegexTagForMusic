package org.essembeh.rtfm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction.ICallback;
import org.essembeh.rtfm.ui.controller.MainController;
import org.essembeh.rtfm.ui.renderers.MusicFileRenderer;
import org.essembeh.rtfm.ui.renderers.ThreeStatesBooleanRenderer;
import org.essembeh.rtfm.ui.renderers.WorkflowRenderer;
import org.essembeh.rtfm.ui.utils.Image;

public class RtfmUICustom extends RtfmUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6158596439845992908L;
	private final JButton showHideAttributesButton;
	private final MainController mainController;

	public RtfmUICustom(MainController controller) {
		super();

		this.mainController = controller;

		// Model
		explorerTree.setModel(mainController.getFiltersModel());
		fileTable.setModel(mainController.getMusicFilesModel());
		attributeTable.setModel(mainController.getAttributesModel());

		// Size & style
		fileTable.getColumnModel().getColumn(0).setMinWidth(100);
		fileTable.getColumnModel().getColumn(0).setMaxWidth(100);
		fileTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		attributeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		attributeTable.getColumnModel().getColumn(1).setPreferredWidth(200);

		// Renderes
		fileTable.setDefaultRenderer(Boolean.class, new ThreeStatesBooleanRenderer());
		fileTable.setDefaultRenderer(IMusicFile.class, new MusicFileRenderer());

		// Listeners
		mainController.getFiltersSelection().listen(explorerTree);
		mainController.getMusicFilesSelection().listen(fileTable);

		// Add actions
		actionPanel.add(new JButton(new DefaultRtfmAction("Scan", Image.SCAN_FOLDER, new ICallback() {
			@Override
			public void execute() {
				mainController.scanFolder();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Open", Image.OPEN_LIBRARY, new ICallback() {
			@Override
			public void execute() {
				mainController.loadDatabase();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Save", Image.SAVE_LIBRARY, new ICallback() {
			@Override
			public void execute() {
				mainController.saveDatabase();
			}
		})));

		actionPanel.add(showHideAttributesButton = new JButton(new DefaultRtfmAction("Attributes", Image.ATTRIBUTES, new ICallback() {
			@Override
			public void execute() {
				setAttributesPanelVisible(!splitPaneCenterRight.getRightComponent().isVisible());
			}
		})));

		final JComboBox comboBox = new JComboBox(mainController.getWorkflowModel());
		actionPanel.add(comboBox);
		comboBox.setRenderer(new WorkflowRenderer());
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedWorkflow = comboBox.getSelectedItem();
				if (selectedWorkflow != null && selectedWorkflow instanceof IWorkflowIdentifier) {
					mainController.executeWorkFlow((IWorkflowIdentifier) selectedWorkflow);
				}
				comboBox.setSelectedIndex(0);

			}
		});

		// Panels
		setAttributesPanelVisible(false);
		contentPane.add(mainController.getStatusPanel(), BorderLayout.SOUTH);

		// Configurations
		final JComboBox configurationComboBox = new JComboBox(mainController.getConfigurationModel());
		actionPanel.add(configurationComboBox);
		configurationComboBox.setSelectedIndex(0);
		configurationComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selection = configurationComboBox.getSelectedItem();
				if (selection != null && selection instanceof String) {
					mainController.loadConfiguration((String) selection);
				}
			}
		});

	}

	protected void setAttributesPanelVisible(boolean visible) {
		splitPaneCenterRight.getRightComponent().setVisible(visible);
		if (visible) {
			splitPaneCenterRight.setDividerLocation(0.6);
			splitPaneCenterRight.setResizeWeight(0.8);
			showHideAttributesButton.setText("Hide attributes");
		} else {
			splitPaneCenterRight.setDividerLocation(1.0);
			splitPaneCenterRight.setResizeWeight(1.0);
			showHideAttributesButton.setText("Show attributes");
		}
	}
}
