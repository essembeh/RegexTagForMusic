package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class JobModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6137772141319616195L;
	private static final String[] TITLES = { "File", "Status" };
	private final List<FileWithStatus> data;

	public JobModel(List<IMusicFile> files) {
		data = new CopyOnWriteArrayList<JobModel.FileWithStatus>();
		for (IMusicFile iMusicFile : files) {
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
		FileWithStatus fileWithStatus = data.get(rowIndex);
		String out = columnIndex == 0 ? fileWithStatus.getFile().getVirtualPath() : fileWithStatus.getStatus();
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

	public void jobFinished(IMusicFile file, ActionException e) {
		int i = 0;
		for (FileWithStatus fileWithStatus : data) {
			if (fileWithStatus.getFile().equals(file)) {
				if (e != null) {
					fileWithStatus.finishWithError(e);
				} else {
					fileWithStatus.finish();
				}
				fireTableRowsUpdated(i, i);
				break;
			}
			i++;
		}
	}

	private class FileWithStatus {
		private final IMusicFile musicFile;
		private boolean finished;
		private ActionException exception;

		public FileWithStatus(IMusicFile file) {
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

		public IMusicFile getFile() {
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
