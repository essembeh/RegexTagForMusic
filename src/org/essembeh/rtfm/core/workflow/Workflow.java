package org.essembeh.rtfm.core.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IXFile;

public class Workflow {

	private final String id;
	private final String description;
	private final List<String> taskIds;
	private final ICondition<IXFile> condition;

	public Workflow(String id, String description, ICondition<IXFile> condition, List<String> taskIds) {
		this.id = id;
		this.description = description;
		this.condition = condition;
		this.taskIds = new ArrayList<String>(taskIds);
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public boolean supportFile(IXFile file) {
		return condition.isTrue(file);
	}

	public List<String> getTaskIds() {
		return Collections.unmodifiableList(taskIds);
	}
}
