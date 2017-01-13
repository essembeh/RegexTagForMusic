/**
 */
package org.essembeh.rtfm.model.rtfm;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Node#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Node#isFolder <em>Folder</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Node#isRoot <em>Root</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Node#getContent <em>Content</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Node#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject {
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
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Folder</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Folder</em>' attribute.
	 * @see #setFolder(boolean)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode_Folder()
	 * @model
	 * @generated
	 */
	boolean isFolder();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Node#isFolder <em>Folder</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Folder</em>' attribute.
	 * @see #isFolder()
	 * @generated
	 */
	void setFolder(boolean value);

	/**
	 * Returns the value of the '<em><b>Root</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' attribute.
	 * @see #setRoot(boolean)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode_Root()
	 * @model
	 * @generated
	 */
	boolean isRoot();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Node#isRoot <em>Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' attribute.
	 * @see #isRoot()
	 * @generated
	 */
	void setRoot(boolean value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.essembeh.rtfm.model.rtfm.Node},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' map.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode_Content()
	 * @model mapType="org.essembeh.rtfm.model.rtfm.NodeEntry<org.eclipse.emf.ecore.EString, org.essembeh.rtfm.model.rtfm.Node>"
	 * @generated
	 */
	EMap<String, Node> getContent();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getNode_Attributes()
	 * @model mapType="org.essembeh.rtfm.model.rtfm.StringEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.essembeh.rtfm.model.rtfm.IPath"
	 * @generated
	 */
	IPath getPath();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getVirtualPath();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="org.essembeh.rtfm.model.rtfm.File" exceptions="org.essembeh.rtfm.model.rtfm.IOException"
	 * @generated
	 */
	File getResource() throws IOException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model pathDataType="org.essembeh.rtfm.model.rtfm.IPath"
	 * @generated
	 */
	Node find(IPath path);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Node getParentNode();

} // Node
