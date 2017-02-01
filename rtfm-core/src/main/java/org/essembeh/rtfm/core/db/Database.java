package org.essembeh.rtfm.core.db;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.essembeh.rtfm.core.utils.JsonUtils;
import org.essembeh.rtfm.core.utils.ResourceUtils;

public class Database {

	public static class DatabaseEntry {
		private final Map<String, Date> workflows = new ConcurrentHashMap<>();

		public Optional<Date> getExecutionDate(String workflowId) {
			return Optional.ofNullable(workflows.get(workflowId));
		}

		public void logWorkflowSuccess(String workflowId) {
			workflows.put(workflowId, new Date());
		}
	}

	private final Map<String, DatabaseEntry> files = new ConcurrentHashMap<>();

	public Optional<DatabaseEntry> getEntry(Path in) {
		return Optional.ofNullable(files.get(ResourceUtils.getFullPath(in)));
	}

	public Optional<Date> getExecutionDate(Path in, String workflowId) {
		Optional<DatabaseEntry> entry = getEntry(in);
		if (entry.isPresent()) {
			return entry.get().getExecutionDate(workflowId);
		}
		return Optional.empty();
	}

	public void logWorkflowSuccess(Path in, String workflowId) {
		DatabaseEntry entry = files.get(ResourceUtils.getFullPath(in));
		if (entry == null) {
			files.put(ResourceUtils.getFullPath(in), entry = new DatabaseEntry());
		}
		entry.logWorkflowSuccess(workflowId);
	}

	public void load(Path in) throws IOException {
		Database db = JsonUtils.load(in, Database.class);
		files.clear();
		files.putAll(db.files);
	}

	public void save(Path out) throws IOException {
		JsonUtils.save(this, out);
	}
}
