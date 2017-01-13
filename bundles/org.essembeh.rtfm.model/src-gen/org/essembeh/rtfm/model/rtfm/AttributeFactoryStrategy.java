/**
 */
package org.essembeh.rtfm.model.rtfm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Attribute Factory Strategy</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage#getAttributeFactoryStrategy()
 * @model
 * @generated
 */
public enum AttributeFactoryStrategy implements Enumerator {
	/**
	 * The '<em><b>CREATE OR UPDATE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_OR_UPDATE_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE_OR_UPDATE(0, "CREATE_OR_UPDATE", "CREATE_OR_UPDATE"),

	/**
	 * The '<em><b>CREATE ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE_ONLY(1, "CREATE_ONLY", "CREATE_ONLY"),

	/**
	 * The '<em><b>UPDATE ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UPDATE_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	UPDATE_ONLY(2, "UPDATE_ONLY", "UPDATE_ONLY"),

	/**
	 * The '<em><b>CREATE OR APPEND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_OR_APPEND_VALUE
	 * @generated
	 * @ordered
	 */
	CREATE_OR_APPEND(3, "CREATE_OR_APPEND", "CREATE_OR_APPEND");

	/**
	 * The '<em><b>CREATE OR UPDATE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CREATE OR UPDATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE_OR_UPDATE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_OR_UPDATE_VALUE = 0;

	/**
	 * The '<em><b>CREATE ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CREATE ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE_ONLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_ONLY_VALUE = 1;

	/**
	 * The '<em><b>UPDATE ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UPDATE ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UPDATE_ONLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UPDATE_ONLY_VALUE = 2;

	/**
	 * The '<em><b>CREATE OR APPEND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CREATE OR APPEND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE_OR_APPEND
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CREATE_OR_APPEND_VALUE = 3;

	/**
	 * An array of all the '<em><b>Attribute Factory Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AttributeFactoryStrategy[] VALUES_ARRAY =
		new AttributeFactoryStrategy[] {
			CREATE_OR_UPDATE,
			CREATE_ONLY,
			UPDATE_ONLY,
			CREATE_OR_APPEND,
		};

	/**
	 * A public read-only list of all the '<em><b>Attribute Factory Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AttributeFactoryStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Attribute Factory Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AttributeFactoryStrategy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeFactoryStrategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Factory Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AttributeFactoryStrategy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeFactoryStrategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Factory Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AttributeFactoryStrategy get(int value) {
		switch (value) {
			case CREATE_OR_UPDATE_VALUE: return CREATE_OR_UPDATE;
			case CREATE_ONLY_VALUE: return CREATE_ONLY;
			case UPDATE_ONLY_VALUE: return UPDATE_ONLY;
			case CREATE_OR_APPEND_VALUE: return CREATE_OR_APPEND;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AttributeFactoryStrategy(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //AttributeFactoryStrategy
