package org.essembeh.rtfm.core.utils.identifiers;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.utils.list.Identifier;

public class WorkflowIdentifier implements Identifier<Workflow> {

	@Override
	public String getId(Workflow o) {
		return o.getIdentifier();
	}
}
