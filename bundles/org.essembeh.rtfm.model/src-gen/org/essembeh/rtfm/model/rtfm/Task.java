/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="org.essembeh.rtfm.model.rtfm.IStatus" monitorDataType="org.essembeh.rtfm.model.rtfm.IProgressMonitor"
	 * @generated
	 */
	IStatus execute(Node node, IProgressMonitor monitor);

} // Task
