package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.Attributes;
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
			//		root.add(fileSystem());
			root.add(byType());
			root.add(byAttribute("Artist", "music:artist"));
			root.add(byAttribute("Album", "music:album"));
			root.add(byAttribute("Year", "music:year"));
		}
		return root;
	}

	//	private MutableTreeNode fileSystem() {
	//		File rootFolder = library.getRootFolder();
	//		DefaultMutableTreeNode root = newNode("Filesystem");
	//		if (rootFolder != null) {
	//			root.add(folderToNode(rootFolder, rootFolder));
	//		}
	//		return root;
	//	}

	//	private DefaultMutableTreeNode folderToNode(File folder, File root) {
	//		DefaultMutableTreeNode node = newNode(folder.getName(),
	//				CommonFilters.virtualPathStartsWith(FileUtils.extractRelativePath(folder, root)), true);
	//		List<File> ls = Arrays.asList(folder.listFiles());
	//		Collections.sort(ls, new Comparator<File>() {
	//			@Override
	//			public int compare(File a, File b) {
	//				return a.getAbsolutePath().compareTo(b.getAbsolutePath());
	//			}
	//		});
	//		for (File sub : ls) {
	//			if (sub.isDirectory()) {
	//				node.add(folderToNode(sub, root));
	//			}
	//		}
	//		return node;
	//	}

	private MutableTreeNode byType() {
		return byAttribute("Type", Attributes.FILEHANDLER_KEY);
	}

	private MutableTreeNode byAttribute(String nodeName, String attributeName) {
		DefaultMutableTreeNode root = newNode(nodeName);
		List<String> values = new ArrayList<String>();
		for (IResource r : application.getProject().getAllResources()) {
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
