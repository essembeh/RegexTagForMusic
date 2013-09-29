package org.essembeh.rtfm.ui;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.FilterModel;
import org.essembeh.rtfm.ui.model.ResourceModel;
import org.essembeh.rtfm.ui.renderers.ResourceRenderer;
import org.essembeh.rtfm.ui.renderers.ThreeStatesBooleanRenderer;
import org.essembeh.rtfm.ui.utils.Image;

public class RtfmUICustom extends RtfmUI {

	private static final long serialVersionUID = -6158596439845992908L;
	private final JButton showHideAttributesButton;
	private final Application app;
	private final ResourceModel resourceModel;
	private final FilterModel filterModel;

	public RtfmUICustom() throws UnknownTaskException, FileNotFoundException, JAXBException {
		super();

		app = new Application();
		app.loadConfiguration(new File("../rtfm-test/resources/seb.xml"));

		// Model
		explorerTree.setModel(filterModel = new FilterModel(new ExplorerNodeUtils(app), true));
		fileTable.setModel(resourceModel = new ResourceModel(app, explorerTree));

		// Size & style
		//		fileTable.getColumnModel().getColumn(0).setMinWidth(100);
		//		fileTable.getColumnModel().getColumn(0).setMaxWidth(100);
		//		fileTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		//		attributeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		//		attributeTable.getColumnModel().getColumn(1).setPreferredWidth(200);

		// Renderes
		fileTable.setDefaultRenderer(Boolean.class, new ThreeStatesBooleanRenderer());
		fileTable.setDefaultRenderer(IResource.class, new ResourceRenderer());

		// Add actions
		actionPanel.add(new JButton(new DefaultRtfmAction("Scan", Image.SCAN_FOLDER, new Runnable() {
			@Override
			public void run() {
				scanFolder();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Open", Image.OPEN_LIBRARY, new Runnable() {
			@Override
			public void run() {
				openProject();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Save", Image.SAVE_LIBRARY, new Runnable() {
			@Override
			public void run() {
				saveProject();
			}
		})));

		actionPanel.add(showHideAttributesButton = new JButton(new DefaultRtfmAction("Attributes", Image.ATTRIBUTES,
				new Runnable() {
					@Override
					public void run() {
						setAttributesPanelVisible(!splitPaneCenterRight.getRightComponent().isVisible());
					}
				})));

		//		final JComboBox comboBox = new JComboBox(mainController.getWorkflowModel());
		//		actionPanel.add(comboBox);
		//		comboBox.setRenderer(new WorkflowRenderer());
		//		comboBox.setSelectedIndex(0);
		//		comboBox.addActionListener(new ActionListener() {
		//			@Override
		//			public void actionPerformed(ActionEvent e) {
		//				Object selectedWorkflow = comboBox.getSelectedItem();
		//				if (selectedWorkflow != null && selectedWorkflow instanceof IWorkflow) {
		//				}
		//				comboBox.setSelectedIndex(0);
		//
		//			}
		//		});

		// Panels
		setAttributesPanelVisible(false);
		//		contentPane.add(mainController.getStatusPanel(), BorderLayout.SOUTH);

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

	protected void scanFolder() {
		try {
			app.scanFolder(new File("../rtfm-test/resources/roots/seb"));
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
		filterModel.refresh();
	}

	protected void openProject() {
		try {
			app.loadProject(new File("../rtfm-test/save1.xml"));
		} catch (FileSystemException | FileNotFoundException | JAXBException e) {
			e.printStackTrace();
		}
		filterModel.refresh();
	}

	protected void saveProject() {
		try {
			app.saveProject(new File("../rtfm-test/save1.xml"));
		} catch (FileNotFoundException | JAXBException e) {
			e.printStackTrace();
		}
	}
}
