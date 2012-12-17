package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IXFile;

public class JobModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137772141319616195L;
	private static final String[] TITLES = { "File", "Status" };
	private final List<FileWithStatus> data;
	private final boolean onlyKeepErrorFiles;

	public JobModel(List<IXFile> files, boolean onlyKeepErrorFiles) {
		data = new CopyOnWriteArrayList<JobModel.FileWithStatus>();
		this.onlyKeepErrorFiles = onlyKeepErrorFiles;
		for (IXFile iMusicFile : files) {
			data.add(new FileWithStatus(iMusicFile));
		}
	}

	@Override
	public String getColumnName(int column) {
		return TITLES[column];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String out = "";
		if (columnIndex < data.size()) {
			FileWithStatus fileWithStatus = data.get(rowIndex);
			out = columnIndex == 0 ? fileWithStatus.getFile().getVirtualPath() : fileWithStatus.getStatus();
		}
		return out;
	}

	public int getErrorCount() {
		int count = 0;
		for (FileWithStatus file : data) {
			if (file.getException() != null) {
				count++;
			}
		}
		return count;
	}

	public void jobFinished(IXFile file, ActionException e) {
		int index = 0;
		FileWithStatus theFile = null;
		for (FileWithStatus fileWithStatus : data) {
			if (fileWithStatus.getFile().equals(file)) {
				theFile = fileWithStatus;
				break;
			}
			index++;
		}
		theFile.finishWithError(e);
		if (e == null && onlyKeepErrorFiles) {
			data.remove(index);
			fireTableDataChanged();
		} else {
			fireTableRowsUpdated(index, index);
		}
	}

	/**
	 * 
	 * @author seb
	 * 
	 */
	private class FileWithStatus {
		private final IXFile musicFile;
		private boolean finished;
		private ActionException exception;

		public FileWithStatus(IXFile file) {
			musicFile = file;
			exception = null;
			finished = false;
		}

		public void finish() {
			finished = true;
		}

		public void finishWithError(ActionException e) {
			finish();
			exception = e;
		}

		public IXFile getFile() {
			return musicFile;
		}

		public ActionException getException() {
			return exception;
		}

		public String getStatus() {
			String out = "";
			if (finished) {
				out = exception == null ? "Succeeded" : exception.getMessage();
			}
			return out;
		}
	}

}
