/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Attribute Factory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler <em>File Handler</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAbstractAttributeFactory()
 * @model abstract="true"
 * @generated
 */
public interface AbstractAttributeFactory extends EObject {
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
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAbstractAttributeFactory_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>File Handler</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getFactories <em>Factories</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Handler</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Handler</em>' container reference.
	 * @see #setFileHandler(FileHandler)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAbstractAttributeFactory_FileHandler()
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getFactories
	 * @model opposite="factories" transient="false"
	 * @generated
	 */
	FileHandler getFileHandler();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler <em>File Handler</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Handler</em>' container reference.
	 * @see #getFileHandler()
	 * @generated
	 */
	void setFileHandler(FileHandler value);

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' attribute.
	 * The default value is <code>"CREATE_OR_UPDATE"</code>.
	 * The literals are from the enumeration {@link org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' attribute.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy
	 * @see #setStrategy(AttributeFactoryStrategy)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAbstractAttributeFactory_Strategy()
	 * @model default="CREATE_OR_UPDATE"
	 * @generated
	 */
	AttributeFactoryStrategy getStrategy();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getStrategy <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' attribute.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(AttributeFactoryStrategy value);

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAbstractAttributeFactory_Optional()
	 * @model default="false"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getAttributeValue(String virtualPath);

} // AbstractAttributeFactory
