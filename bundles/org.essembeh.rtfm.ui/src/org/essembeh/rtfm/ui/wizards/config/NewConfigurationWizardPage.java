package org.essembeh.rtfm.ui.wizards.config;

import java.net.URI;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.utils.RtfmModelIO;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (config).
 */

public class NewConfigurationWizardPage extends WizardPage {
	private Text containerText;

	private Text fileText;
	private ComboViewer templateCombo;

	private ISelection selection;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewConfigurationWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("Multi-page Editor File");
		setDescription("This wizard creates a new file with *.config extension that can be opened by a multi-page editor.");
		this.selection = selection;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(container);

		{
			Label label = new Label(container, SWT.NULL);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).applyTo(label);
			label.setText("&Container:");

			containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).span(1, 1).applyTo(containerText);
			containerText.addModifyListener(e -> dialogChanged());
			Button button = new Button(container, SWT.PUSH);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).span(1, 1).applyTo(button);
			button.setText("Browse...");
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					handleBrowse();
				}
			});
		}

		{
			Label label = new Label(container, SWT.NULL);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).applyTo(label);
			label.setText("File name:");

			fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).span(2, 1).applyTo(fileText);
			fileText.addModifyListener(e -> dialogChanged());
		}
		{
			Label label = new Label(container, SWT.NULL);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).applyTo(label);
			label.setText("Template:");

			templateCombo = new ComboViewer(container, SWT.READ_ONLY);
			GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).span(2, 1).applyTo(templateCombo.getControl());
			templateCombo.setContentProvider(ArrayContentProvider.getInstance());
		}

		initialize();
		dialogChanged();
		setControl(container);
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */

	private void initialize() {
		templateCombo.setInput(ConfigurationManager.INSTANCE.getAvailableTemplates());
		templateCombo.getCombo().select(0);

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
		fileText.setText("default." + RtfmModelIO.CONFIG_EXTENSION);
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
			if (ext.equalsIgnoreCase("config") == false) {
				updateStatus("File extension must be \"config\"");
				return;
			}
		}
		if (templateCombo.getStructuredSelection().getFirstElement() == null) {
			updateStatus("Invalid base configuration");
			return;
		}
		updateStatus(null);
	}

	public URI getTemplateId() {
		return (URI) templateCombo.getStructuredSelection().getFirstElement();
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
}