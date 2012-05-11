package org.essembeh.rtfm.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.essembeh.rtfm.ui.controller.MainController;

public class RtfmUI extends JFrame {

	private JPanel contentPane;
	private JTable attributeTable;
	private JTable fileTable;

	private final MainController mainController;

	/**
	 * Create the frame.
	 * 
	 * @param controller
	 */
	public RtfmUI(MainController controller) {
		this.mainController = controller;
		setTitle("RegexTagForMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnLibrary = new JMenu("Library");
		menuBar.add(mnLibrary);

		JMenuItem mntmScanANew = new JMenuItem("Scan a new folder");
		mntmScanANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainController.scanFolder();
			}
		});
		mnLibrary.add(mntmScanANew);

		JMenuItem mntmOpenADatabase = new JMenuItem("Open database");
		mntmOpenADatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.loadDatabase();
			}
		});
		mnLibrary.add(mntmOpenADatabase);

		JMenuItem mntmSaveDatabase = new JMenuItem("Save database");
		mntmSaveDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainController.saveDatabase();
			}
		});
		mnLibrary.add(mntmSaveDatabase);

		JMenu mnAction = new JMenu("Actions");
		menuBar.add(mnAction);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPaneCenter = new JSplitPane();
		splitPaneCenter.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPaneCenter, BorderLayout.EAST);

		attributeTable = new JTable(mainController.getAttributesModel());
		splitPaneCenter.setLeftComponent(attributeTable);

		JPanel actionPanel = new JPanel();
		splitPaneCenter.setRightComponent(actionPanel);

		JSplitPane splitPaneLeft = new JSplitPane();
		contentPane.add(splitPaneLeft, BorderLayout.CENTER);

		JTree fileTree = new JTree();
		splitPaneLeft.setLeftComponent(fileTree);

		fileTable = new JTable(mainController.getFileModel());
		splitPaneLeft.setRightComponent(fileTable);
		fileTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int[] selectedRows = fileTable.getSelectedRows();
				mainController.updateSelection(selectedRows);
			}
		});
	}

}
