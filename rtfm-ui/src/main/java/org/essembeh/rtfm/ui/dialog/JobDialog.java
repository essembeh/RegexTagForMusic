package org.essembeh.rtfm.ui.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import org.essembeh.rtfm.ui.utils.SpringUtilities;

public class JobDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3237723209016194172L;
	private final JPanel contentPanel = new JPanel();
	protected JTextField descriptionValue;
	protected JButton submitButton;
	protected JButton cancelButton;
	protected JProgressBar progressBar;
	protected JTable table;
	protected JTextField statusValue;
	protected JPanel descriptionPanel;

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
			descriptionPanel = new JPanel();
			contentPanel.add(descriptionPanel, BorderLayout.NORTH);
			// panel.setLayout(new GridLayout(0, 2, 0, 0));
			descriptionPanel.setLayout(new SpringLayout());
			{
				JLabel nameLabel = new JLabel("Workflow", JLabel.TRAILING);
				descriptionPanel.add(nameLabel);
			}
			{
				descriptionValue = new JTextField();
				descriptionPanel.add(descriptionValue);
				descriptionValue.setColumns(10);
			}
			{
				JLabel statusLabel = new JLabel("Status", JLabel.TRAILING);
				descriptionPanel.add(statusLabel);
			}
			{
				statusValue = new JTextField();
				descriptionPanel.add(statusValue);
				statusValue.setColumns(10);
			}
			SpringUtilities.makeCompactGrid(descriptionPanel, 2, 2, // rows,
																	// cols
					6, 6, // initX, initY
					6, 6); // xPad, yPad
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
