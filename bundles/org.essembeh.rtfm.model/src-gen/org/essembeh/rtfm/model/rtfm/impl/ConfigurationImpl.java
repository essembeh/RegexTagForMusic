/**
 */
package org.essembeh.rtfm.model.rtfm.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;
import org.essembeh.rtfm.model.rtfm.Workflow;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl#getFileHandlers <em>File Handlers</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl#getWorkflows <em>Workflows</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl#getSubstitutions <em>Substitutions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationImpl extends MinimalEObjectImpl.Container implements Configuration {
	/**
	 * The cached value of the '{@link #getFileHandlers() <em>File Handlers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileHandlers()
	 * @generated
	 * @ordered
	 */
	protected EList<FileHandler> fileHandlers;

	/**
	 * The cached value of the '{@link #getWorkflows() <em>Workflows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkflows()
	 * @generated
	 * @ordered
	 */
	protected EList<Workflow> workflows;

	/**
	 * The cached value of the '{@link #getSubstitutions() <em>Substitutions</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubstitutions()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> substitutions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RtfmPackage.Literals.CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FileHandler> getFileHandlers() {
		if (fileHandlers == null) {
			fileHandlers = new EObjectContainmentWithInverseEList<FileHandler>(FileHandler.class, this, RtfmPackage.CONFIGURATION__FILE_HANDLERS, RtfmPackage.FILE_HANDLER__CONFIGURATION);
		}
		return fileHandlers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Workflow> getWorkflows() {
		if (workflows == null) {
			workflows = new EObjectContainmentWithInverseEList<Workflow>(Workflow.class, this, RtfmPackage.CONFIGURATION__WORKFLOWS, RtfmPackage.WORKFLOW__CONFIGURATION);
		}
		return workflows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getSubstitutions() {
		if (substitutions == null) {
			substitutions = new EcoreEMap<String,String>(RtfmPackage.Literals.STRING_ENTRY, StringEntryImpl.class, this, RtfmPackage.CONFIGURATION__SUBSTITUTIONS);
		}
		return substitutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String resolvePattern(String pattern) {
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFileHandlers()).basicAdd(otherEnd, msgs);
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWorkflows()).basicAdd(otherEnd, msgs);
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				return ((InternalEList<?>)getFileHandlers()).basicRemove(otherEnd, msgs);
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				return ((InternalEList<?>)getWorkflows()).basicRemove(otherEnd, msgs);
			case RtfmPackage.CONFIGURATION__SUBSTITUTIONS:
				return ((InternalEList<?>)getSubstitutions()).basicRemove(otherEnd, msgs);
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				return getFileHandlers();
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				return getWorkflows();
			case RtfmPackage.CONFIGURATION__SUBSTITUTIONS:
				if (coreType) return getSubstitutions();
				else return getSubstitutions().map();
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				getFileHandlers().clear();
				getFileHandlers().addAll((Collection<? extends FileHandler>)newValue);
				return;
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				getWorkflows().clear();
				getWorkflows().addAll((Collection<? extends Workflow>)newValue);
				return;
			case RtfmPackage.CONFIGURATION__SUBSTITUTIONS:
				((EStructuralFeature.Setting)getSubstitutions()).set(newValue);
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				getFileHandlers().clear();
				return;
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				getWorkflows().clear();
				return;
			case RtfmPackage.CONFIGURATION__SUBSTITUTIONS:
				getSubstitutions().clear();
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
			case RtfmPackage.CONFIGURATION__FILE_HANDLERS:
				return fileHandlers != null && !fileHandlers.isEmpty();
			case RtfmPackage.CONFIGURATION__WORKFLOWS:
				return workflows != null && !workflows.isEmpty();
			case RtfmPackage.CONFIGURATION__SUBSTITUTIONS:
				return substitutions != null && !substitutions.isEmpty();
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
			case RtfmPackage.CONFIGURATION___RESOLVE_PATTERN__STRING:
				return resolvePattern((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //ConfigurationImpl
