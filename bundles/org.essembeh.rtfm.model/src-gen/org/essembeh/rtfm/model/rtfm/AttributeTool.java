/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Tool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToUpdate <em>Attributes To Update</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToDelete <em>Attributes To Delete</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAttributeTool()
 * @model
 * @generated
 */
public interface AttributeTool extends Task {
	/**
	 * Returns the value of the '<em><b>Attributes To Update</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes To Update</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes To Update</em>' map.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAttributeTool_AttributesToUpdate()
	 * @model mapType="org.essembeh.rtfm.model.rtfm.StringEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getAttributesToUpdate();

	/**
	 * Returns the value of the '<em><b>Attributes To Delete</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes To Delete</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes To Delete</em>' attribute list.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAttributeTool_AttributesToDelete()
	 * @model
	 * @generated
	 */
	EList<String> getAttributesToDelete();

} // AttributeTool
