package org.essembeh.rtfm.ui.renderers;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.ListCellRenderer;

import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.ui.action.DefaultRtfmAction;
import org.essembeh.rtfm.ui.utils.Image;

public class WorkflowRenderer implements ListCellRenderer<Object> {

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
