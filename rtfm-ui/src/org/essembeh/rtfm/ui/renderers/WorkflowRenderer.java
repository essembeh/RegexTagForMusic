package org.essembeh.rtfm.ui.renderers;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JMenuItem;

import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.utils.Image;
import org.jdesktop.swingx.renderer.DefaultListRenderer;

public class WorkflowRenderer extends DefaultListRenderer {

	private static final long serialVersionUID = 3479421995917780195L;

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(	JList list,
													Object value,
													int index,
													boolean isSelected,
													boolean cellHasFocus) {
		Component out;
		if (value != null && value instanceof IWorkflow) {
			final IWorkflow workflow = (IWorkflow) value;
			out = new JMenuItem(new DefaultRtfmAction(workflow.getDescription(), Image.WORKFLOW));
		} else {
			out = new JMenuItem(new DefaultRtfmAction(value.toString(), Image.DIALOG_QUESTION));
		}
		return out;
	}

}
