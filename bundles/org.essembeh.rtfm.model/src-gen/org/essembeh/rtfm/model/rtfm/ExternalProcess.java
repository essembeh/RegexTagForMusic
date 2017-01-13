/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getExecutable <em>Executable</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getEnv <em>Env</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getExternalProcess()
 * @model
 * @generated
 */
public interface ExternalProcess extends Task {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getExternalProcess_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executable</em>' attribute.
	 * @see #setExecutable(String)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getExternalProcess_Executable()
	 * @model required="true"
	 * @generated
	 */
	String getExecutable();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getExecutable <em>Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executable</em>' attribute.
	 * @see #getExecutable()
	 * @generated
	 */
	void setExecutable(String value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arguments</em>' attribute list.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getExternalProcess_Arguments()
	 * @model
	 * @generated
	 */
	EList<String> getArguments();

	/**
	 * Returns the value of the '<em><b>Env</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Env</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Env</em>' map.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getExternalProcess_Env()
	 * @model mapType="org.essembeh.rtfm.model.rtfm.StringEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getEnv();

} // ExternalProcess
