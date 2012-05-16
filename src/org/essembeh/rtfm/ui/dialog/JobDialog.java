package org.essembeh.rtfm.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class JobDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	protected JTextField nameValue;
	protected JTextField descriptionValue;
	protected JButton submitButton;
	protected JButton cancelButton;
	protected JProgressBar progressBar;
	protected JTable table;
	protected JTextField statusValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JobDialog dialog = new JobDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JobDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(3, 2, 0, 0));
			{
				JLabel nameLabel = new JLabel("Workflow");
				panel.add(nameLabel);
			}
			{
				nameValue = new JTextField();
				panel.add(nameValue);
				nameValue.setColumns(10);
			}
			{
				JLabel descriptionLabel = new JLabel("Description");
				panel.add(descriptionLabel);
			}
			{
				descriptionValue = new JTextField();
				panel.add(descriptionValue);
				descriptionValue.setColumns(10);
			}
			{
				JLabel statusLabel = new JLabel("Status");
				panel.add(statusLabel);
			}
			{
				statusValue = new JTextField();
				panel.add(statusValue);
				statusValue.setColumns(10);
			}
		}
		{
			progressBar = new JProgressBar();
			contentPanel.add(progressBar, BorderLayout.SOUTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				submitButton = new JButton("Submit");
				submitButton.setActionCommand("OK");
				buttonPane.add(submitButton);
				getRootPane().setDefaultButton(submitButton);
			}
			{
				cancelButton = new JButton("Close");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
