package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.ObjectUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.IStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class JobModel extends AbstractTableModel {

	private static final long serialVersionUID = 6137772141319616195L;
	private static final String[] TITLES = { "File", "Status" };
	private final boolean onlyKeepErrorFiles;
	private final Map<IResource, IStatus> statusMap;
	private final List<IResource> resources;

	public JobModel(IJob job, boolean onlyKeepErrorFiles) {
		this.onlyKeepErrorFiles = onlyKeepErrorFiles;
		this.statusMap = new HashMap<IResource, IStatus>();
		this.resources = new ArrayList<>(job.getResources());
		Collections.sort(resources);
	}

	@Override
	public String getColumnName(int column) {
		return TITLES[column];
	}

	@Override
	public int getRowCount() {
		return resources.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object out = null;
		IResource resource = resources.get(rowIndex);
		if (columnIndex == 0) {
			out = resource;
		} else {
			IStatus status = statusMap.get(resource);
			out = ObjectUtils.toString(status, "");
		}
		return out;
	}

	public void updateStatus(ExecutionStatus<IResource, SimpleStatus> s) {
		statusMap.put(s.getObject(), s);
		if (s.getSeverity().isOk() && onlyKeepErrorFiles) {
			int index = resources.indexOf(s.getObject());
			resources.remove(index);
			fireTableRowsDeleted(index, index);
		} else {
			fireTableCellUpdated(resources.indexOf(s.getObject()), 1);
		}
	}
}
