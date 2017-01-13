/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Configuration#getFileHandlers <em>File Handlers</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Configuration#getWorkflows <em>Workflows</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Configuration#getSubstitutions <em>Substitutions</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject {
	/**
	 * Returns the value of the '<em><b>File Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.essembeh.rtfm.model.rtfm.FileHandler}.
	 * It is bidirectional and its opposite is '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Handlers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Handlers</em>' containment reference list.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getConfiguration_FileHandlers()
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration
	 * @model opposite="configuration" containment="true"
	 * @generated
	 */
	EList<FileHandler> getFileHandlers();

	/**
	 * Returns the value of the '<em><b>Workflows</b></em>' containment reference list.
	 * The list contents are of type {@link org.essembeh.rtfm.model.rtfm.Workflow}.
	 * It is bidirectional and its opposite is '{@link org.essembeh.rtfm.model.rtfm.Workflow#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workflows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workflows</em>' containment reference list.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getConfiguration_Workflows()
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getConfiguration
	 * @model opposite="configuration" containment="true"
	 * @generated
	 */
	EList<Workflow> getWorkflows();

	/**
	 * Returns the value of the '<em><b>Substitutions</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Substitutions</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Substitutions</em>' map.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getConfiguration_Substitutions()
	 * @model mapType="org.essembeh.rtfm.model.rtfm.StringEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getSubstitutions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String resolvePattern(String pattern);

} // Configuration
