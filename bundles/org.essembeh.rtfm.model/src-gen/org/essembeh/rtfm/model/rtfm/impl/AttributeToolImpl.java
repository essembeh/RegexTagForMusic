/**
 */
package org.essembeh.rtfm.model.rtfm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.essembeh.rtfm.model.rtfm.AttributeTool;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Tool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl#getAttributesToUpdate <em>Attributes To Update</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl#getAttributesToDelete <em>Attributes To Delete</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AttributeToolImpl extends TaskImpl implements AttributeTool {
	/**
	 * The cached value of the '{@link #getAttributesToUpdate() <em>Attributes To Update</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributesToUpdate()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> attributesToUpdate;

	/**
	 * The cached value of the '{@link #getAttributesToDelete() <em>Attributes To Delete</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributesToDelete()
	 * @generated
	 * @ordered
	 */
	protected EList<String> attributesToDelete;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeToolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RtfmPackage.Literals.ATTRIBUTE_TOOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getAttributesToUpdate() {
		if (attributesToUpdate == null) {
			attributesToUpdate = new EcoreEMap<String,String>(RtfmPackage.Literals.STRING_ENTRY, StringEntryImpl.class, this, RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE);
		}
		return attributesToUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getAttributesToDelete() {
		if (attributesToDelete == null) {
			attributesToDelete = new EDataTypeUniqueEList<String>(String.class, this, RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE);
		}
		return attributesToDelete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE:
				return ((InternalEList<?>)getAttributesToUpdate()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE:
				if (coreType) return getAttributesToUpdate();
				else return getAttributesToUpdate().map();
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE:
				return getAttributesToDelete();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE:
				((EStructuralFeature.Setting)getAttributesToUpdate()).set(newValue);
				return;
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE:
				getAttributesToDelete().clear();
				getAttributesToDelete().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE:
				getAttributesToUpdate().clear();
				return;
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE:
				getAttributesToDelete().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE:
				return attributesToUpdate != null && !attributesToUpdate.isEmpty();
			case RtfmPackage.ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE:
				return attributesToDelete != null && !attributesToDelete.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (attributesToDelete: ");
		result.append(attributesToDelete);
		result.append(')');
		return result.toString();
	}

} //AttributeToolImpl
