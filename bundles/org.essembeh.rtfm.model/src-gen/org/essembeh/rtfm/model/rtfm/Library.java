/**
 */
package org.essembeh.rtfm.model.rtfm;

import java.net.URI;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Library#getRoot <em>Root</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Library#getPath <em>Path</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Library#getConfigurationUri <em>Configuration Uri</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.Library#getLastScan <em>Last Scan</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends EObject {
	/**
	 * Returns the value of the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' containment reference.
	 * @see #setRoot(Node)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getLibrary_Root()
	 * @model containment="true"
	 * @generated
	 */
	Node getRoot();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Library#getRoot <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' containment reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(Node value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getLibrary_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Library#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Configuration Uri</b></em>' attribute.
	 * The default value is <code>"rtfm:default"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration Uri</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration Uri</em>' attribute.
	 * @see #setConfigurationUri(URI)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getLibrary_ConfigurationUri()
	 * @model default="rtfm:default" dataType="org.essembeh.rtfm.model.rtfm.URI"
	 * @generated
	 */
	URI getConfigurationUri();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Library#getConfigurationUri <em>Configuration Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration Uri</em>' attribute.
	 * @see #getConfigurationUri()
	 * @generated
	 */
	void setConfigurationUri(URI value);

	/**
	 * Returns the value of the '<em><b>Last Scan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Scan</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Scan</em>' attribute.
	 * @see #setLastScan(long)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getLibrary_LastScan()
	 * @model
	 * @generated
	 */
	long getLastScan();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.Library#getLastScan <em>Last Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Scan</em>' attribute.
	 * @see #getLastScan()
	 * @generated
	 */
	void setLastScan(long value);

} // Library
