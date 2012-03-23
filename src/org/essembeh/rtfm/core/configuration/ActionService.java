package org.essembeh.rtfm.core.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Action;
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

import com.google.inject.Inject;

public class ActionService {

	private static Logger logger = Logger.getLogger(ActionService.class);

	private IdList<Action, Identifier<Action>> actions;

	@Inject
	public ActionService(ICoreConfigurationLoader coreConfigurationLoader) throws ConfigurationException {
		logger.debug("Init ActionService");
		actions = coreConfigurationLoader.getActions();
	}

	public void execute(String actionIdentifier, IMusicFile musicFile) throws ActionException {
		if (!actions.contains(actionIdentifier)) {
			throw new ActionException("Unknown action: " + actionIdentifier);
		}
		Action action = actions.get(actionIdentifier);
		logger.debug("Executing action: " + action + ", on file: " + musicFile);
		action.executeOn(musicFile);
	}

	public void addAction(Action action) {
		actions.add(action);
	}

	public Set<String> getAllActions() {
		return actions.keySet();
	}

	public List<String> getActionsForType(FileType fileType) {
		List<String> out = new ArrayList<String>();
		for (Action action : actions) {
			if (action.supportType(fileType)) {
				out.add(actions.getIdentifier().getId(action));
			}
		}
		return out;
	}

	public Action getActionByIdentifier(String identifier) {
		return actions.get(identifier);
	}
}
