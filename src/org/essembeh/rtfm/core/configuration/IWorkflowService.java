package org.essembeh.rtfm.core.configuration;

import java.util.List;

import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;

public interface IWorkflowService {

	/**
	 * Creates a Job to be submitted
	 * 
	 * @param workflowIdentifier
	 * @param musicFiles
	 * @return
	 * @throws ActionException
	 */
	IJob createJob(IWorkflowIdentifier workflowIdentifier, List<IXFile> musicFiles) throws ActionException;

	/**
	 * Get All Workflow Ids
	 * 
	 * @return
	 */
	List<IWorkflowIdentifier> getAllWorkflows();

	/**
	 * Get all compatible workflows for a given filetype
	 * 
	 * @param fileType
	 * @return
	 */
	List<IWorkflowIdentifier> getWorkflowsForType(FileType fileType);

}