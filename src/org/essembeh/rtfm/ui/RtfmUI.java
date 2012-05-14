package org.essembeh.rtfm.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import org.essembeh.rtfm.ui.controller.MainController;

public class RtfmUI extends JFrame {

	private JPanel contentPane;

	protected final MainController mainController;
	protected JPanel actionPanel;
	protected JTree explorerTree;
	protected JTable fileTable;
	protected JTable attributeTable;
	protected JPanel workflowPanel;

	/**
	 * Create the frame.
	 * 
	 * @param controller
	 */
	public RtfmUI(MainController controller) {
		setSize(new Dimension(600, 400));
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

		JSplitPane splitPaneLeftCenter = new JSplitPane();
		contentPane.add(splitPaneLeftCenter, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPaneLeftCenter.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		actionPanel = new JPanel();
		panel.add(actionPanel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		explorerTree = new JTree();
		scrollPane.setViewportView(explorerTree);

		JSplitPane splitPaneCenterRight = new JSplitPane();
		splitPaneLeftCenter.setRightComponent(splitPaneCenterRight);

		JPanel panel_1 = new JPanel();
		splitPaneCenterRight.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		fileTable = new JTable();
		scrollPane_1.setViewportView(fileTable);

		JPanel panel_2 = new JPanel();
		splitPaneCenterRight.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		panel_2.add(scrollPane_2, BorderLayout.CENTER);

		attributeTable = new JTable();
		scrollPane_2.setViewportView(attributeTable);

		workflowPanel = new JPanel();
		panel_2.add(workflowPanel, BorderLayout.SOUTH);
		workflowPanel.setLayout(new GridLayout(0, 1, 0, 0));

	}
}
