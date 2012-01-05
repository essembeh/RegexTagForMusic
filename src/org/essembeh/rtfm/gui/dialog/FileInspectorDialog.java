/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.gui.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.gui.panel.sub.FileInformations;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;

public class FileInspectorDialog extends JDialog {

	private static final long serialVersionUID = -6412272907835657695L;

	public FileInspectorDialog(MusicFile file) {
		setLayout(new BorderLayout());
		add(new FileInformations(file), BorderLayout.CENTER);
		JButton closeButton = new JButton(Translator.get(StringId.buttonClose));
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(closeButton, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		setTitle(Translator.get(StringId.titleInspect));
		pack();
	}
}
