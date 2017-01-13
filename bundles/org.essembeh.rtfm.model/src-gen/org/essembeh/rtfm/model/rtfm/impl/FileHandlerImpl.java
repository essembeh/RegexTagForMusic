/**
 */
package org.essembeh.rtfm.model.rtfm.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Handler</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl#getConfiguration <em>Configuration</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl#getFactories <em>Factories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FileHandlerImpl extends MinimalEObjectImpl.Container implements FileHandler {
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
	 * The default value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected static final String PATTERN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPattern() <em>Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPattern()
	 * @generated
	 * @ordered
	 */
	protected String pattern = PATTERN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFactories() <em>Factories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactories()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractAttributeFactory> factories;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FileHandlerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RtfmPackage.Literals.FILE_HANDLER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.FILE_HANDLER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Configuration getConfiguration() {
		if (eContainerFeatureID() != RtfmPackage.FILE_HANDLER__CONFIGURATION) return null;
		return (Configuration)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConfiguration(Configuration newConfiguration, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newConfiguration, RtfmPackage.FILE_HANDLER__CONFIGURATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfiguration(Configuration newConfiguration) {
		if (newConfiguration != eInternalContainer() || (eContainerFeatureID() != RtfmPackage.FILE_HANDLER__CONFIGURATION && newConfiguration != null)) {
			if (EcoreUtil.isAncestor(this, newConfiguration))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConfiguration != null)
				msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, RtfmPackage.CONFIGURATION__FILE_HANDLERS, Configuration.class, msgs);
			msgs = basicSetConfiguration(newConfiguration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.FILE_HANDLER__CONFIGURATION, newConfiguration, newConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPattern(String newPattern) {
		String oldPattern = pattern;
		pattern = newPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.FILE_HANDLER__PATTERN, oldPattern, pattern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractAttributeFactory> getFactories() {
		if (factories == null) {
			factories = new EObjectContainmentWithInverseEList<AbstractAttributeFactory>(AbstractAttributeFactory.class, this, RtfmPackage.FILE_HANDLER__FACTORIES, RtfmPackage.ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER);
		}
		return factories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean accept(String virtualPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetConfiguration((Configuration)otherEnd, msgs);
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFactories()).basicAdd(otherEnd, msgs);
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
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				return basicSetConfiguration(null, msgs);
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				return ((InternalEList<?>)getFactories()).basicRemove(otherEnd, msgs);
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
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				return eInternalContainer().eInverseRemove(this, RtfmPackage.CONFIGURATION__FILE_HANDLERS, Configuration.class, msgs);
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
			case RtfmPackage.FILE_HANDLER__NAME:
				return getName();
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				return getConfiguration();
			case RtfmPackage.FILE_HANDLER__PATTERN:
				return getPattern();
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				return getFactories();
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
			case RtfmPackage.FILE_HANDLER__NAME:
				setName((String)newValue);
				return;
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				setConfiguration((Configuration)newValue);
				return;
			case RtfmPackage.FILE_HANDLER__PATTERN:
				setPattern((String)newValue);
				return;
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				getFactories().clear();
				getFactories().addAll((Collection<? extends AbstractAttributeFactory>)newValue);
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
			case RtfmPackage.FILE_HANDLER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				setConfiguration((Configuration)null);
				return;
			case RtfmPackage.FILE_HANDLER__PATTERN:
				setPattern(PATTERN_EDEFAULT);
				return;
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				getFactories().clear();
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
			case RtfmPackage.FILE_HANDLER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case RtfmPackage.FILE_HANDLER__CONFIGURATION:
				return getConfiguration() != null;
			case RtfmPackage.FILE_HANDLER__PATTERN:
				return PATTERN_EDEFAULT == null ? pattern != null : !PATTERN_EDEFAULT.equals(pattern);
			case RtfmPackage.FILE_HANDLER__FACTORIES:
				return factories != null && !factories.isEmpty();
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
			case RtfmPackage.FILE_HANDLER___ACCEPT__STRING:
				return accept((String)arguments.get(0));
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
		result.append(", pattern: ");
		result.append(pattern);
		result.append(')');
		return result.toString();
	}

} //FileHandlerImpl
