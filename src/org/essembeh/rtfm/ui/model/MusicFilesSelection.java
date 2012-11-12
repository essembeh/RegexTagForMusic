package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.listener.IListenable;
import org.essembeh.rtfm.ui.utils.ChangeListenerContainer;

public class MusicFilesSelection implements ListSelectionListener, IListenable<ChangeListener> {

	/**
	 * Attributes
	 */
	private final ChangeListenerContainer changeListenerContainer;
	private final List<IXFile> currentSelection;
	private final MusicFilesModel musicFilesModel;
	private volatile JTable jtable;

	/**
	 * Constructor
	 */
	public MusicFilesSelection(MusicFilesModel model) {
		this.musicFilesModel = model;
		this.currentSelection = new ArrayList<IXFile>();
		this.changeListenerContainer = new ChangeListenerContainer();
		this.jtable = null;
	}

	/**
	 * Listens to a JTree for selection
	 * 
	 * @param JTable
	 */
	public void listen(JTable newTable) {
		currentSelection.clear();
		if (jtable != null) {
			jtable.getSelectionModel().removeListSelectionListener(this);
		}
		if (newTable != null) {
			jtable = newTable;
			jtable.getSelectionModel().addListSelectionListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		currentSelection.clear();
		int[] selectedRows = jtable.getSelectedRows();
		if (selectedRows != null) {
			List<IXFile> filteredFiles = musicFilesModel.getFilteredFiles();
			for (int i : selectedRows) {
				currentSelection.add(filteredFiles.get(i));
			}
		}
		changeListenerContainer.stateChanged(new ChangeEvent(this));
	}

	/**
	 * Returns the list of selected files
	 * 
	 * @return
	 */
	public List<IXFile> getSelectedFiles() {
		return currentSelection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(ChangeListener listener) {
		changeListenerContainer.addListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(ChangeListener listener) {
		changeListenerContainer.removeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeAllListener()
	 */
	@Override
	public void removeAllListener() {
		changeListenerContainer.removeAllListener();
	}

}
