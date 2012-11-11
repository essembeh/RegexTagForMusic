package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.core.utils.listener.IListenable;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils.NamedFilter;
import org.essembeh.rtfm.ui.utils.ChangeListenerContainer;

public class FiltersSelection implements TreeSelectionListener, IListenable<ChangeListener> {

	/**
	 * Attributes
	 */
	private final ChangeListenerContainer listeners;
	private final List<Filter> currentFilters;
	private volatile JTree jtree;

	/**
	 * Constructor
	 */
	public FiltersSelection() {
		this.currentFilters = new ArrayList<Filter>();
		this.listeners = new ChangeListenerContainer();
		this.jtree = null;
	}

	/**
	 * Listens to a JTree for selection
	 * 
	 * @param newTree
	 */
	public void listen(JTree newTree) {
		currentFilters.clear();
		if (jtree != null) {
			jtree.getSelectionModel().removeTreeSelectionListener(this);
		}
		if (newTree != null) {
			jtree = newTree;
			jtree.getSelectionModel().addTreeSelectionListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		currentFilters.clear();
		TreePath[] selectedPaths = jtree.getSelectionPaths();
		if (selectedPaths != null) {
			for (TreePath treePath : selectedPaths) {
				Object lastPathElement = treePath.getLastPathComponent();
				if (lastPathElement instanceof DefaultMutableTreeNode) {
					Object userObject = ((DefaultMutableTreeNode) lastPathElement).getUserObject();
					if (userObject instanceof NamedFilter) {
						Filter filter = ((NamedFilter) userObject).getFilter();
						if (filter != null) {
							currentFilters.add(filter);
						}
					}
				}
			}
		}
		listeners.stateChanged(new ChangeEvent(this));
	}

	/**
	 * Returns the list of selected filters
	 * 
	 * @return
	 */
	public List<Filter> getCurrentFilters() {
		return currentFilters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(ChangeListener listener) {
		listeners.addListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(ChangeListener listener) {
		listeners.removeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeAllListener()
	 */
	@Override
	public void removeAllListener() {
		listeners.removeAllListener();
	}

}
