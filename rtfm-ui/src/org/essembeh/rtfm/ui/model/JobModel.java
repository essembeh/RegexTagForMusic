package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class JobModel extends AbstractTableModel {

	private static final long serialVersionUID = 6137772141319616195L;
	private static final String[] TITLES = { "File", "Status" };
	private final List<ResourceWithStatus> data;
	private final boolean onlyKeepErrorFiles;

	public JobModel(List<IResource> files, boolean onlyKeepErrorFiles) {
		data = new CopyOnWriteArrayList<JobModel.ResourceWithStatus>();
		this.onlyKeepErrorFiles = onlyKeepErrorFiles;
		for (IResource r : files) {
			data.add(new ResourceWithStatus(r));
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
		if (columnIndex < TITLES.length) {
			ResourceWithStatus resourceWithStatus = data.get(rowIndex);
			out = columnIndex == 0 ? resourceWithStatus.getResource().getVirtualPath().toString() : resourceWithStatus
					.getStatus();
		}
		return out;
	}

	public int getErrorCount() {
		int count = 0;
		for (ResourceWithStatus file : data) {
			if (file.getException() != null) {
				count++;
			}
		}
		return count;
	}

	public void jobFinished(IResource file, Exception e) {
		int index = 0;
		ResourceWithStatus theFile = null;
		for (ResourceWithStatus fileWithStatus : data) {
			if (fileWithStatus.getResource() == file) {
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
	private class ResourceWithStatus {
		private final IResource resource;
		private boolean finished;
		private Exception exception;

		public ResourceWithStatus(IResource resource) {
			this.resource = resource;
			exception = null;
			finished = false;
		}

		public void finish() {
			finished = true;
		}

		public void finishWithError(Exception e) {
			finish();
			exception = e;
		}

		public IResource getResource() {
			return resource;
		}

		public Exception getException() {
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
