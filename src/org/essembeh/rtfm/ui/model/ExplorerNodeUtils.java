package org.essembeh.rtfm.ui.model;

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

public class ExplorerNodeUtils {

	private final Library library;
	private final DefaultMutableTreeNode root;

	public ExplorerNodeUtils(Library library) {
		this.library = library;
		root = newNode("Filters");
		refreshTreeNode();
	}

	public void refreshTreeNode() {
		root.removeAllChildren();
		root.add(newNode("All files", CommonFilters.noFilter()));
		root.add(byType());
		root.add(byAttribute("By artist", "tag:artist"));
		root.add(byAttribute("By album", "tag:album"));
		root.add(byAttribute("By year", "tag:year"));
		root.add(byCommonAttributes());
	}

	private MutableTreeNode byCommonAttributes() {
		DefaultMutableTreeNode root = newNode("By attribute");
		root.add(byAttribute("rtfm:tagged", "rtfm:tagged"));
		return root;
	}

	private MutableTreeNode byType() {
		DefaultMutableTreeNode root = newNode("By type");
		for (FileType type : FileType.getAllFileTypes()) {
			root.add(newNode(type.getIdentifier(), CommonFilters.filterOnType(type.getIdentifier())));
		}
		return root;
	}

	public TreeNode getRoot() {
		return root;
	}

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

	private DefaultMutableTreeNode newNode(String name) {
		return new DefaultMutableTreeNode(newNode(name, null));
	}

	private DefaultMutableTreeNode newNode(String name, Filter filter) {
		return new DefaultMutableTreeNode(new NamedFilter(name, filter), filter == null);
	}

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

	public Library getLibrary() {
		return library;
	}
}
