package org.essembeh.rtfm.ui.wizards.library;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.utils.RtfmModelIO;
import org.essembeh.rtfm.ui.SharedImages;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (library).
 */

public class NewLibraryWizardPage extends WizardPage {
	private static final String DEFAULT_OUTPUT_FILE = "default." + RtfmModelIO.LIBRARY_EXTENSION;

	private Text containerText;

	private Text fileText;
	private Text rootFolderText;
	private Text configurationUriText;

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewLibraryWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Multi-page Editor File");
		setDescription("This wizard creates a new file with *.library extension that can be opened by a multi-page editor.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).applyTo(container);
		{
			Label label = new Label(container, SWT.NONE);
			GridDataFactory.swtDefaults().applyTo(label);
			label.setText("Project:");

			containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(containerText);
			containerText.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
			Button button = new Button(container, SWT.PUSH);
			GridDataFactory.swtDefaults().applyTo(button);
			button.setText("Browse...");
			button.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					handleBrowse();
				}
			});

		}
		{
			Label label = new Label(container, SWT.NONE);
			GridDataFactory.swtDefaults().applyTo(label);
			label.setText("File name:");

			fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(fileText);
			fileText.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}
		{
			Label label = new Label(container, SWT.NONE);
			GridDataFactory.swtDefaults().applyTo(label);
			label.setText("Root folder:");

			rootFolderText = new Text(container, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(rootFolderText);
			rootFolderText.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
			Button button = new Button(container, SWT.PUSH);
			GridDataFactory.swtDefaults().applyTo(button);
			button.setText("Browse...");
			button.setImage(SharedImages.getImage(SharedImages.FOLDER));
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					handleBrowseRootFolder();
				}
			});
		}
		{
			Label label = new Label(container, SWT.NONE);
			GridDataFactory.swtDefaults().applyTo(label);
			label.setText("Configuration URI:");

			configurationUriText = new Text(container, SWT.BORDER | SWT.SINGLE);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).applyTo(configurationUriText);
			configurationUriText.addModifyListener(new ModifyListener() {
				@Override
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}

		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText.setText(container.getFullPath().toString());
			}
		}
		fileText.setText(DEFAULT_OUTPUT_FILE);
		configurationUriText.setText(ConfigurationManager.DEFAULT_URI.toString());
	}

	/**
	 * Uses the standard container selection dialog to choose the new value for
	 * the container field.
	 */

	private void handleBrowse() {
		ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Select new file container");
		if (dialog.open() == ContainerSelectionDialog.OK) {
			Object[] result = dialog.getResult();
			if (result.length == 1) {
				containerText.setText(((Path) result[0]).toString());
			}
		}
	}

	protected void handleBrowseRootFolder() {
		DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
		String result = dialog.open();
		if (StringUtils.isNotBlank(result)) {
			rootFolderText.setText(result);
			String basename = FilenameUtils.getBaseName(result);
			if (StringUtils.isNotBlank(basename) && fileText.getText().equals(DEFAULT_OUTPUT_FILE)) {
				fileText.setText(basename + "." + RtfmModelIO.LIBRARY_EXTENSION);
			}
		}
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(getContainerName()));
		String fileName = getFileName();

		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		if (container == null || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("library") == false) {
				updateStatus("File extension must be \"library\"");
				return;
			}
		}
		if (StringUtils.isBlank(rootFolderText.getText())) {
			updateStatus("Root folder must be specified");
			return;
		}
		if (!Files.isDirectory(Paths.get(rootFolderText.getText()))) {
			updateStatus("Root folder must be a valid folder");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	public String getContainerName() {
		return containerText.getText();
	}

	public String getFileName() {
		return fileText.getText();
	}

	public String getRootFolder() {
		return rootFolderText.getText();
	}

	public String getConfigurationUri() {
		return configurationUriText.getText();
	}
}