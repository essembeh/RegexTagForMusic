/**
 */
package org.essembeh.rtfm.model.rtfm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Regex Attribute Factory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getGroup <em>Group</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getRegexAttributeFactory()
 * @model
 * @generated
 */
public interface RegexAttributeFactory extends AbstractAttributeFactory {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute.
	 * @see #setGroup(int)
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getRegexAttributeFactory_Group()
	 * @model
	 * @generated
	 */
	int getGroup();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getGroup <em>Group</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' attribute.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(int value);

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
	 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getRegexAttributeFactory_Pattern()
	 * @model
	 * @generated
	 */
	String getPattern();

	/**
	 * Sets the value of the '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getPattern <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' attribute.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(String value);

} // RegexAttributeFactory
