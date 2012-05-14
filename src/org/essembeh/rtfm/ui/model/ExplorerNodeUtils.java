package org.essembeh.rtfm.ui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import org.essembeh.rtfm.core.filter.CommonFilters;
import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.FileUtils;

public class ExplorerNodeUtils {

	/**
	 * Attributes
	 */
	private final Library library;
	private final DefaultMutableTreeNode root;

	/**
	 * Constructor
	 * 
	 * @param library
	 */
	public ExplorerNodeUtils(Library library) {
		this.library = library;
		root = newNode("Filters");
	}

	/**
	 * 
	 * @return
	 */
	public TreeNode buildRoot() {
		root.removeAllChildren();
		root.add(newNode("All files", CommonFilters.noFilter()));
		root.add(newNode("Non tagged", CommonFilters.filterOnAttribute("rtfm:tagged", "false")));
		root.add(fileSystem());
		root.add(byType());
		root.add(byAttribute("Artist", "tag:artist"));
		root.add(byAttribute("Album", "tag:album"));
		root.add(byAttribute("Year", "tag:year"));
		return root;
	}

	/**
	 * 
	 * @return
	 */
	private MutableTreeNode fileSystem() {
		File rootFolder = library.getRootFolder();
		DefaultMutableTreeNode root = newNode("Filesystem");
		if (rootFolder != null) {
			root.add(folderToNode(rootFolder, rootFolder));
		}
		return root;
	}

	/**
	 * 
	 * @param folder
	 * @param root
	 * @return
	 */
	private DefaultMutableTreeNode folderToNode(File folder, File root) {
		DefaultMutableTreeNode node = newNode(folder.getName(), CommonFilters.virtualPathStartsWith(FileUtils.extractRelativePath(folder, root)),
				true);

		for (File sub : folder.listFiles()) {
			if (sub.isDirectory()) {
				node.add(folderToNode(sub, root));
			}
		}
		return node;
	}

	/**
	 * 
	 * @return
	 */
	private MutableTreeNode byType() {
		DefaultMutableTreeNode root = newNode("Type");
		for (FileType type : FileType.getAllFileTypes()) {
			root.add(newNode(type.getIdentifier(), CommonFilters.filterOnType(type.getIdentifier())));
		}
		return root;
	}

	/**
	 * 
	 * @param nodeName
	 * @param attributeName
	 * @return
	 */
	private MutableTreeNode byAttribute(String nodeName, String attributeName) {
		DefaultMutableTreeNode root = newNode(nodeName);
		List<String> values = new ArrayList<String>();
		for (IMusicFile musicFile : library.getAllFiles()) {
			Attribute att = musicFile.getAttributeList().get(attributeName);
			if (att != null && !values.contains(att.getValue())) {
				values.add(att.getValue());
			}
		}
		Collections.sort(values);
		for (String string : values) {
			root.add(newNode(string, CommonFilters.filterOnAttribute(attributeName, string)));
		}
		return root;
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	private DefaultMutableTreeNode newNode(String name) {
		return newNode(name, null);
	}

	/**
	 * 
	 * @param name
	 * @param filter
	 * @return
	 */
	private DefaultMutableTreeNode newNode(String name, Filter filter) {
		return newNode(name, filter, filter == null);
	}

	/**
	 * 
	 * @param name
	 * @param filter
	 * @param hasChildren
	 * @return
	 */
	private DefaultMutableTreeNode newNode(String name, Filter filter, boolean hasChildren) {
		return new DefaultMutableTreeNode(new NamedFilter(name, filter), hasChildren);
	}

	/**
	 * 
	 * @author smassot
	 * 
	 */
	public class NamedFilter {
		private final String name;
		private final Filter filter;

		public NamedFilter(String name, Filter filter) {
			super();
			this.name = name;
			this.filter = filter;
		}

		public String getName() {
			return name;
		}

		public Filter getFilter() {
			return filter;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
