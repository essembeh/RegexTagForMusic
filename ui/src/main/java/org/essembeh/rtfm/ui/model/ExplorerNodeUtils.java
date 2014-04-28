package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.essembeh.rtfm.core.Application;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.ConditionUtils;

public class ExplorerNodeUtils {

	private final Application application;
	private final DefaultMutableTreeNode root;

	public ExplorerNodeUtils(Application application) {
		this.application = application;
		root = newNode("Filters");
	}

	public TreeNode buildRoot() {
		root.removeAllChildren();
		root.add(newNode("All files", ConditionUtils.alwaysTrue()));
		root.add(newNode("Non tagged", ConditionUtils.attributeValueEquals("music:tagged?", "false")));
		root.add(newNode("With error", ConditionUtils.attributeExists(Attributes.ERROR_KEY, true)));
		if (application.getProject() != null) {
			root.add(newResources());
			root.add(fileSystem());
			root.add(byType());
			root.add(byAttribute("Artist", "music:artist"));
			root.add(byAttribute("Album", "music:album"));
			root.add(byAttribute("Year", "music:year"));
		}
		return root;
	}

	private MutableTreeNode fileSystem() {
		IFolder rootFolder = application.getProject().getRootFolder();
		DefaultMutableTreeNode out = newNode("Filesystem");
		for (IResource r : rootFolder.getResources()) {
			if (r instanceof IFolder) {
				out.add(folderToNode((IFolder) r));
			}
		}
		return out;
	}

	private DefaultMutableTreeNode folderToNode(IFolder folder) {
		String escapedVirtualPath = Pattern.quote(folder.getVirtualPath().toString());
		DefaultMutableTreeNode out = newNode(folder.getName(),
				ConditionUtils.virtualPathMatches(Pattern.compile("^" + escapedVirtualPath + ".*")), true);
		for (IResource r : folder.getResources()) {
			if (r instanceof IFolder) {
				out.add(folderToNode((IFolder) r));
			}
		}
		return out;
	}

	private MutableTreeNode newResources() {
		String date = application.getProject().getScanDate();
		return newNode("New resources", ConditionUtils.attributeValueEquals(Attributes.DATE_KEY, date));
	}

	private MutableTreeNode byType() {
		return byAttribute("Type", Attributes.FILEHANDLER_KEY);
	}

	private MutableTreeNode byAttribute(String nodeName, String attributeName) {
		DefaultMutableTreeNode root = newNode(nodeName);
		List<String> values = new ArrayList<String>();
		for (IResource r : application.getProject().getRootFolder().getAllResources()) {
			String attributeValue = r.getAttributes().getValue(attributeName);
			if (attributeValue != null && !values.contains(attributeValue)) {
				values.add(attributeValue);
			}
		}
		Collections.sort(values);
		for (String string : values) {
			root.add(newNode(string, ConditionUtils.attributeValueEquals(attributeName, string)));
		}
		return root;
	}

	private DefaultMutableTreeNode newNode(String name) {
		return newNode(name, null);
	}

	private DefaultMutableTreeNode newNode(String name, ICondition condition) {
		return newNode(name, condition, condition == null);
	}

	private DefaultMutableTreeNode newNode(String name, ICondition condition, boolean hasChildren) {
		return new DefaultMutableTreeNode(new NamedFilter(name, condition), hasChildren);
	}

	public class NamedFilter {
		private final String name;
		private final ICondition condition;

		public NamedFilter(String name, ICondition condition) {
			super();
			this.name = name;
			this.condition = condition;
		}

		public String getName() {
			return name;
		}

		public ICondition getCondition() {
			return condition;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
