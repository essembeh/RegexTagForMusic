package org.essembeh.rtfm.ui.renderers;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JMenuItem;

import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.utils.Image;
import org.jdesktop.swingx.renderer.DefaultListRenderer;

public class WorkflowRenderer extends DefaultListRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3479421995917780195L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jdesktop.swingx.renderer.DefaultListRenderer#getListCellRendererComponent
	 * (javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(	JList list,
													Object value,
													int index,
													boolean isSelected,
													boolean cellHasFocus) {
		Component out;
		if (value != null && value instanceof IWorkflowIdentifier) {
			final IWorkflowIdentifier workflowIdentifier = (IWorkflowIdentifier) value;
			out = new JMenuItem(new DefaultRtfmAction(workflowIdentifier.getDescription(), Image.WORKFLOW));
		} else {
			out = new JMenuItem(new DefaultRtfmAction(value.toString(), Image.DIALOG_QUESTION));
		}
		return out;
	}

}
