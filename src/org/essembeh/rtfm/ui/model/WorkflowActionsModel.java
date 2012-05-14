package org.essembeh.rtfm.ui.model;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.TextUtils;
import org.essembeh.rtfm.ui.action.AbstractRtfmAction;
import org.essembeh.rtfm.ui.utils.Image;

public class WorkflowActionsModel {
	private final Library library;
	private final JPanel workflowPanel;

	public WorkflowActionsModel(Library library, JPanel panel) {
		workflowPanel = panel;
		this.library = library;
	}

	public void updateSelection(List<IMusicFile> selection) {
		final List<IMusicFile> list;
		if (selection == null) {
			list = library.getAllFiles();
		} else {
			list = selection;
		}

		final Map<IWorkflowIdentifier, Integer> out = new HashMap<IWorkflowIdentifier, Integer>();
		for (IMusicFile musicFile : selection) {
			List<IWorkflowIdentifier> worflows = library.getActionService().getWorkflowIdentifiersForType(
					musicFile.getType());
			for (IWorkflowIdentifier workflowIdentifier : worflows) {
				Integer count = out.get(workflowIdentifier);
				if (count == null) {
					out.put(workflowIdentifier, 1);
				} else {
					out.put(workflowIdentifier, count + 1);
				}
			}
		}

		workflowPanel.removeAll();
		for (final IWorkflowIdentifier workflowIdentifier : out.keySet()) {
			int count = out.get(workflowIdentifier);
			String name = workflowIdentifier.getDescription() + " (" + TextUtils.plural(count, "file") + ")";
			JButton button = new JButton(new AbstractRtfmAction(name, Image.RUN_ALL) {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						library.getActionService().createJob(workflowIdentifier, list).submit();

					} catch (ActionException e) {
						e.printStackTrace();
					}
				}
			});
			button.setHorizontalAlignment(SwingConstants.LEADING);
			workflowPanel.add(button);
		}
		workflowPanel.revalidate();
		workflowPanel.repaint();
	}
}
