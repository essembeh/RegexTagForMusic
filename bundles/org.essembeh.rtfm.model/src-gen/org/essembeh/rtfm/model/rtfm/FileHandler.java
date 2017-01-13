/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.FileHandler#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration <em>Configuration</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.FileHandler#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.FileHandler#getFactories <em>Factories</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getFileHandler()
 * @model
 * @generated
 */
public interface FileHandler extends EObject {
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
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getFileHandler_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.essembeh.rtfm.model.rtfm.Configuration#getFileHandlers <em>File Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configuration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configuration</em>' container reference.
	 * @see #setConfiguration(Configuration)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getFileHandler_Configuration()
	 * @see org.essembeh.rtfm.model.rtfm.Configuration#getFileHandlers
	 * @model opposite="fileHandlers" transient="false"
	 * @generated
	 */
	Configuration getConfiguration();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration <em>Configuration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration</em>' container reference.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(Configuration value);

	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' attribute.
	 * @see #setPattern(String)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getFileHandler_Pattern()
	 * @model
	 * @generated
	 */
	String getPattern();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

	/**
	 * Returns the value of the '<em><b>Factories</b></em>' containment reference list.
	 * The list contents are of type {@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory}.
	 * It is bidirectional and its opposite is '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler <em>File Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factories</em>' containment reference list.
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getFileHandler_Factories()
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler
	 * @model opposite="fileHandler" containment="true"
	 * @generated
	 */
	EList<AbstractAttributeFactory> getFactories();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean accept(String virtualPath);

} // FileHandler
