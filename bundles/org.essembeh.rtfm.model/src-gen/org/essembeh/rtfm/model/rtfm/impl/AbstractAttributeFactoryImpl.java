/**
 */
package org.essembeh.rtfm.model.rtfm.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory;
import org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Attribute Factory</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl#getFileHandler <em>File Handler</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractAttributeFactoryImpl extends MinimalEObjectImpl.Container implements AbstractAttributeFactory {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected static final AttributeFactoryStrategy STRATEGY_EDEFAULT = AttributeFactoryStrategy.CREATE_OR_UPDATE;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected AttributeFactoryStrategy strategy = STRATEGY_EDEFAULT;

	/**
	 * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OPTIONAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOptional()
	 * @generated
	 * @ordered
	 */
	protected boolean optional = OPTIONAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractAttributeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RtfmPackage.Literals.ABSTRACT_ATTRIBUTE_FACTORY;
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
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FileHandler getFileHandler() {
		if (eContainerFeatureID() != RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER) return null;
		return (FileHandler)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFileHandler(FileHandler newFileHandler, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFileHandler, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileHandler(FileHandler newFileHandler) {
		if (newFileHandler != eInternalContainer() || (eContainerFeatureID() != RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER && newFileHandler != null)) {
			if (EcoreUtil.isAncestor(this, newFileHandler))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFileHandler != null)
				msgs = ((InternalEObject)newFileHandler).eInverseAdd(this, RtfmPackage.FILE_HANDLER__FACTORIES, FileHandler.class, msgs);
			msgs = basicSetFileHandler(newFileHandler, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER, newFileHandler, newFileHandler));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeFactoryStrategy getStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategy(AttributeFactoryStrategy newStrategy) {
		AttributeFactoryStrategy oldStrategy = strategy;
		strategy = newStrategy == null ? STRATEGY_EDEFAULT : newStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY, oldStrategy, strategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOptional() {
		return optional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptional(boolean newOptional) {
		boolean oldOptional = optional;
		optional = newOptional;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL, oldOptional, optional));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAttributeValue(String virtualPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFileHandler((FileHandler)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				return basicSetFileHandler(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				return eInternalContainer().eInverseRemove(this, RtfmPackage.FILE_HANDLER__FACTORIES, FileHandler.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__NAME:
				return getName();
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				return getFileHandler();
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY:
				return getStrategy();
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL:
				return isOptional();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__NAME:
				setName((String)newValue);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				setFileHandler((FileHandler)newValue);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY:
				setStrategy((AttributeFactoryStrategy)newValue);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL:
				setOptional((Boolean)newValue);
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
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				setFileHandler((FileHandler)null);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY:
				setStrategy(STRATEGY_EDEFAULT);
				return;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL:
				setOptional(OPTIONAL_EDEFAULT);
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
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER:
				return getFileHandler() != null;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY:
				return strategy != STRATEGY_EDEFAULT;
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL:
				return optional != OPTIONAL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING:
				return getAttributeValue((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", strategy: ");
		result.append(strategy);
		result.append(", optional: ");
		result.append(optional);
		result.append(')');
		return result.toString();
	}

} //AbstractAttributeFactoryImpl
