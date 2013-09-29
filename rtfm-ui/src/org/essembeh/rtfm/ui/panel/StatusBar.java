package org.essembeh.rtfm.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 2713289246117490437L;
	private final JLabel shortMessage;

	public StatusBar() {
		// setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLoweredBevelBorder());
		setLayout(new BorderLayout());

		// Short message
		this.shortMessage = new JLabel();
		this.shortMessage.setFont(new Font(Font.MONOSPACED, Font.BOLD, this.shortMessage.getFont().getSize() - 1));
		add(shortMessage, BorderLayout.NORTH);
	}

	public void printMessage(String line) {
		shortMessage.setForeground(Color.BLACK);
		shortMessage.setText(line);
		shortMessage.setVisible(true);
	}

	public void printError(String line) {
		shortMessage.setForeground(Color.RED);
		shortMessage.setText(line);
		shortMessage.setVisible(true);
	}

	public void printError(Exception e) {
		printError(e.getMessage());
	}

	public void hide() {
		shortMessage.setText("");
		shortMessage.setVisible(false);
	}
}
