package org.essembeh.rtfm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.app.utils.TextUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.dialog.JobDialogCustom;
import org.essembeh.rtfm.ui.model.AttributesModel;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.FilterModel;
import org.essembeh.rtfm.ui.model.ResourceModel;
import org.essembeh.rtfm.ui.model.WorkflowModel;
import org.essembeh.rtfm.ui.panel.StatusBar;
import org.essembeh.rtfm.ui.renderers.AlternatibeRenderer;
import org.essembeh.rtfm.ui.renderers.ResourceRenderer;
import org.essembeh.rtfm.ui.renderers.WorkflowRenderer;
import org.essembeh.rtfm.ui.utils.Image;

public class RtfmController extends RtfmUI {

	private static final long serialVersionUID = -6158596439845992908L;
	private static final File DEFAULT_FOLDER = new File(".");

	private final JButton showHideAttributesButton;
	private final Application app;
	private final ResourceModel resourceModel;
	private final FilterModel filterModel;
	private final AttributesModel attributesModel;
	private final WorkflowModel workflowModel;
	private final StatusBar statusBar;
	private File lastLibrary = null;

	public RtfmController() throws UnknownTaskException, FileNotFoundException, JAXBException {
		super();

		app = new Application();
		app.loadConfiguration(new File("../rtfm-test/resources/seb.xml"));

		// Model
		explorerTree.setModel(filterModel = new FilterModel(new ExplorerNodeUtils(app), true));
		fileTable.setModel(resourceModel = new ResourceModel(app, explorerTree));
		attributeTable.setModel(attributesModel = new AttributesModel(resourceModel, fileTable, filterModel));

		//	 Size & style
		fileTable.getColumnModel().getColumn(0).setMinWidth(100);
		fileTable.getColumnModel().getColumn(0).setMaxWidth(100);
		fileTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		attributeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		attributeTable.getColumnModel().getColumn(1).setPreferredWidth(200);

		// Renderes
		fileTable.setDefaultRenderer(Object.class, new ResourceRenderer());
		attributeTable.setDefaultRenderer(Object.class, new AlternatibeRenderer());

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

		final JComboBox<Object> comboBox = new JComboBox<>(workflowModel = new WorkflowModel(app, resourceModel,
				fileTable));
		actionPanel.add(comboBox);
		comboBox.setRenderer(new WorkflowRenderer());
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedWorkflow = comboBox.getSelectedItem();
				if (selectedWorkflow != null && selectedWorkflow instanceof IWorkflow) {
					try {
						IJob job = app.getWorkflowManager().createJob((IWorkflow) selectedWorkflow,
								workflowModel.getSelectedResources());
						JobDialogCustom dialog = new JobDialogCustom((IWorkflow) selectedWorkflow, job);
						dialog.setVisible(true);
					} catch (TaskInstanciationException e1) {
						statusBar.printError(e1.getMessage());
					}
				}
				comboBox.setSelectedIndex(0);

			}
		});

		// Panels
		setAttributesPanelVisible(false);
		contentPane.add(statusBar = new StatusBar(), BorderLayout.SOUTH);

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
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Select a folder to scan");
		fileChooser.setCurrentDirectory(getFileChooserFolder());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				IProject project = app.scanFolder(fileChooser.getSelectedFile());
				statusBar.printMessage(TextUtils.plural(project.getRootFolder().countResources(), "resource")
						+ " found");
			} catch (FileSystemException e) {
				statusBar.printError(e.getMessage());
			}
			filterModel.refresh();
		}
	}

	protected void openProject() {// Create a file chooser to select an XML File
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// TODO: Filter only Database files (XML files)
		fileChooser.setDialogTitle("Open your library");
		fileChooser.setCurrentDirectory(getFileChooserFolder());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				IProject project = app.loadProject(lastLibrary = fileChooser.getSelectedFile());
				statusBar.printMessage(TextUtils.plural(project.getRootFolder().countResources(), "resource")
						+ " found");
			} catch (FileSystemException | FileNotFoundException | JAXBException e) {
				statusBar.printError(e.getMessage());
			}
			filterModel.refresh();
		}
	}

	protected void saveProject() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle("Save your current library");
		fileChooser.setCurrentDirectory(getFileChooserFolder());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				app.saveProject(fileChooser.getSelectedFile());
				statusBar.printMessage("Library saved: " + fileChooser.getSelectedFile().getAbsolutePath());
			} catch (FileNotFoundException | JAXBException e) {
				statusBar.printError(e.getMessage());
			}
		}
	}

	private File getFileChooserFolder() {
		return lastLibrary != null ? lastLibrary : (app.getProject() != null ? app.getProject().getRootFolder()
				.getFile() : DEFAULT_FOLDER);

	}
}
