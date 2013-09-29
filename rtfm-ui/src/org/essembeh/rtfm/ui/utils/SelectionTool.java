package org.essembeh.rtfm.ui.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.condition.MultipleCondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.ConditionUtils;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils.NamedFilter;
import org.essembeh.rtfm.ui.model.ResourceModel;

public class SelectionTool {

	public static ICondition getSelectedCondition(JTree conditionTree) {
		MultipleCondition condition = ConditionUtils.andCondition();
		TreePath[] selectedPaths = conditionTree.getSelectionPaths();
		if (selectedPaths != null) {
			for (TreePath treePath : selectedPaths) {
				Object lastPathElement = treePath.getLastPathComponent();
				if (lastPathElement instanceof DefaultMutableTreeNode) {
					Object userObject = ((DefaultMutableTreeNode) lastPathElement).getUserObject();
					if (userObject instanceof NamedFilter) {
						condition.addCondition(((NamedFilter) userObject).getCondition());
					}
				}
			}
		}
		return condition;
	}

	public static List<IResource> getSelectedResources(JTable resourceTable) {
		ResourceModel model = (ResourceModel) resourceTable.getModel();
		List<IResource> content = model.getContent();
		List<IResource> out = new ArrayList<>();
		for (int i : resourceTable.getSelectedRows()) {
			if (i < content.size()) {
				out.add(content.get(i));
			}
		}
		return out;
	}
}
