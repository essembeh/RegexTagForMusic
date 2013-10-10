package org.essembeh.rtfm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.config.RtfmProperties;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.TextUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.impl.WorkflowSupportCondition;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.util.ConditionUtils;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.dialog.JobDialogCustom;
import org.essembeh.rtfm.ui.model.AttributeModel;
import org.essembeh.rtfm.ui.model.ConditionModel;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.ResourceModel;
import org.essembeh.rtfm.ui.model.WorkflowModel;
import org.essembeh.rtfm.ui.panel.StatusBar;
import org.essembeh.rtfm.ui.renderers.AlternatibeRenderer;
import org.essembeh.rtfm.ui.renderers.ResourceRenderer;
import org.essembeh.rtfm.ui.renderers.WorkflowRenderer;
import org.essembeh.rtfm.ui.utils.ImageUtils;
import org.essembeh.rtfm.ui.utils.SelectionTool;

import com.google.inject.Inject;

public class RtfmController extends RtfmUI {

	private static final long serialVersionUID = -6158596439845992908L;
	private static final File DEFAULT_FOLDER = new File(".");

	private final static Logger LOGGER = Logger.getLogger(RtfmController.class);

	private final Application app;
	private final ConfigurationTool configurationTool;
	private File lastLibrary;

	private final ConditionModel conditionModel;
	private final ResourceModel resourceModel;
	private final AttributeModel attributesModel;
	private final WorkflowModel workflowModel;

	private final JButton showHideAttributesButton;
	private final StatusBar statusBar;

	@Inject
	public RtfmController(Application application, ConfigurationTool configurationTool, RtfmProperties properties) {
		super();
		this.app = application;
		this.configurationTool = configurationTool;
		this.lastLibrary = null;

		// Model
		conditionTree.setModel(conditionModel = new ConditionModel(new ExplorerNodeUtils(app), true));
		resourceTable.setModel(resourceModel = new ResourceModel(app, conditionTree, properties));
		attributeTable.setModel(attributesModel = new AttributeModel(resourceModel, resourceTable, conditionModel));

		//	 Size & style
		resourceTable.getColumnModel().getColumn(0).setMinWidth(100);
		resourceTable.getColumnModel().getColumn(0).setMaxWidth(200);
		resourceTable.getColumnModel().getColumn(0).setPreferredWidth(100);

		attributeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		attributeTable.getColumnModel().getColumn(1).setPreferredWidth(200);

		// Renderes
		resourceTable.setDefaultRenderer(Object.class, new ResourceRenderer());
		attributeTable.setDefaultRenderer(Object.class, new AlternatibeRenderer());

		// Add actions
		actionPanel.add(new JButton(new DefaultRtfmAction("Scan", ImageUtils.SCAN_FOLDER, new Runnable() {
			@Override
			public void run() {
				scanFolder();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Open", ImageUtils.OPEN_LIBRARY, new Runnable() {
			@Override
			public void run() {
				openProject();
			}
		})));
		actionPanel.add(new JButton(new DefaultRtfmAction("Save", ImageUtils.SAVE_LIBRARY, new Runnable() {
			@Override
			public void run() {
				saveProject();
			}
		})));

		actionPanel.add(showHideAttributesButton = new JButton(new DefaultRtfmAction("Attributes",
				ImageUtils.ATTRIBUTES, new Runnable() {
					@Override
					public void run() {
						setAttributesPanelVisible(!splitPaneCenterRight.getRightComponent().isVisible());
					}
				})));

		final JComboBox<Object> comboBox = new JComboBox<>(workflowModel = new WorkflowModel(app, resourceModel,
				resourceTable));
		actionPanel.add(comboBox);
		comboBox.setRenderer(new WorkflowRenderer());
		comboBox.setSelectedIndex(0);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object selectedWorkflow = comboBox.getSelectedItem();
				if (selectedWorkflow != null && selectedWorkflow instanceof IWorkflow) {
					executeWorkflow((IWorkflow) selectedWorkflow);
				}
				comboBox.setSelectedIndex(0);

			}
		});

		// Panels
		setAttributesPanelVisible(false);
		contentPane.add(statusBar = new StatusBar(), BorderLayout.SOUTH);

	}

	protected void executeWorkflow(IWorkflow workflow) {
		try {
			IJob job = app.getWorkflowManager()
					.createJob(
							workflow,
							ConditionUtils.filter(workflowModel.getSelectedResources(), new WorkflowSupportCondition(
									workflow)));
			JobDialogCustom dialog = new JobDialogCustom(workflow, job);
			dialog.setVisible(true);
			resourceModel.refresh(SelectionTool.getSelectedCondition(conditionTree));
		} catch (TaskInstanciationException e1) {
			statusBar.printError(e1.getMessage());
		}
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
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				IProject project = app.scanFolder(fileChooser.getSelectedFile());
				statusBar.printMessage(TextUtils.plural(project.getRootFolder().countResources(), "resource")
						+ " found");
			} catch (FileSystemException e) {
				statusBar.printError(e.getMessage());
			}
			conditionModel.refresh();
		}
	}

	protected void openProject() {
		// Create a file chooser to select an XML File
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("XML library", "xml"));
		fileChooser.setDialogTitle("Open your library");
		fileChooser.setCurrentDirectory(getFileChooserFolder());
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				IProject project = app.loadProject(lastLibrary = fileChooser.getSelectedFile());
				statusBar.printMessage(TextUtils.plural(project.getRootFolder().countResources(), "resource")
						+ " found");
			} catch (FileSystemException | FileNotFoundException | JAXBException e) {
				statusBar.printError(e.getMessage());
			}
			conditionModel.refresh();
		}
	}

	protected void saveProject() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("XML library", "xml"));
		fileChooser.setDialogTitle("Save your current library");
		fileChooser.setCurrentDirectory(getFileChooserFolder());
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				app.saveProject(lastLibrary = fileChooser.getSelectedFile());
				statusBar.printMessage("Library saved: " + fileChooser.getSelectedFile().getAbsolutePath());
			} catch (FileNotFoundException | JAXBException e) {
				statusBar.printError(e.getMessage());
			}
		}
	}

	private File getFileChooserFolder() {
		File out = DEFAULT_FOLDER;
		if (lastLibrary != null) {
			out = lastLibrary;
		} else if (app.getProject() != null) {
			out = app.getProject().getRootFolder().getFile();
		}
		return out;
	}

	public void loadDefaultConfiguration() {
		try {
			File f;
			if ((f = configurationTool.getCustomConfigurationFile()) != null) {
				LOGGER.info("Reading custom configuration: " + f.getAbsolutePath());
				app.loadConfiguration(new FileInputStream(f));
				statusBar.printMessage("Custom configuration loaded: " + f.getAbsolutePath());
			} else if ((f = configurationTool.getUserConfigurationFile()) != null) {
				LOGGER.info("Reading user configuration: " + f.getAbsolutePath());
				app.loadConfiguration(new FileInputStream(f));
				statusBar.printMessage("User configuration loaded: " + f.getAbsolutePath());
			} else {
				LOGGER.info("Reading embedded configuration");
				app.loadConfiguration(configurationTool.getEmbeddedConfiguration());
				statusBar.printMessage("User embedded configuration");
			}
		} catch (MissingTaskException | FileNotFoundException | JAXBException e) {
			statusBar.printError("Cannot load configuration");
			LOGGER.error("Cannot load configuration", e);
		}
	}
}
