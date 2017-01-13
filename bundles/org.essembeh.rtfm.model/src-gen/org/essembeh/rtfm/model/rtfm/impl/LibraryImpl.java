/**
 */
package org.essembeh.rtfm.model.rtfm.impl;

import java.net.URI;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.RtfmFactory;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl#getConfigurationUri <em>Configuration Uri</em>}</li>
 *   <li>{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl#getLastScan <em>Last Scan</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryImpl extends MinimalEObjectImpl.Container implements Library {
	/**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
	protected Node root;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getConfigurationUri() <em>Configuration Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationUri()
	 * @generated
	 * @ordered
	 */
	protected static final URI CONFIGURATION_URI_EDEFAULT = (URI)RtfmFactory.eINSTANCE.createFromString(RtfmPackage.eINSTANCE.getURI(), "rtfm:default");

	/**
	 * The cached value of the '{@link #getConfigurationUri() <em>Configuration Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurationUri()
	 * @generated
	 * @ordered
	 */
	protected URI configurationUri = CONFIGURATION_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getLastScan() <em>Last Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastScan()
	 * @generated
	 * @ordered
	 */
	protected static final long LAST_SCAN_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getLastScan() <em>Last Scan</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastScan()
	 * @generated
	 * @ordered
	 */
	protected long lastScan = LAST_SCAN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RtfmPackage.Literals.LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(Node newRoot, NotificationChain msgs) {
		Node oldRoot = root;
		root = newRoot;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RtfmPackage.LIBRARY__ROOT, oldRoot, newRoot);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(Node newRoot) {
		if (newRoot != root) {
			NotificationChain msgs = null;
			if (root != null)
				msgs = ((InternalEObject)root).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RtfmPackage.LIBRARY__ROOT, null, msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RtfmPackage.LIBRARY__ROOT, null, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.LIBRARY__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.LIBRARY__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI getConfigurationUri() {
		return configurationUri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConfigurationUri(URI newConfigurationUri) {
		URI oldConfigurationUri = configurationUri;
		configurationUri = newConfigurationUri;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.LIBRARY__CONFIGURATION_URI, oldConfigurationUri, configurationUri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getLastScan() {
		return lastScan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastScan(long newLastScan) {
		long oldLastScan = lastScan;
		lastScan = newLastScan;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RtfmPackage.LIBRARY__LAST_SCAN, oldLastScan, lastScan));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RtfmPackage.LIBRARY__ROOT:
				return basicSetRoot(null, msgs);
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
			case RtfmPackage.LIBRARY__ROOT:
				return getRoot();
			case RtfmPackage.LIBRARY__PATH:
				return getPath();
			case RtfmPackage.LIBRARY__CONFIGURATION_URI:
				return getConfigurationUri();
			case RtfmPackage.LIBRARY__LAST_SCAN:
				return getLastScan();
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
			case RtfmPackage.LIBRARY__ROOT:
				setRoot((Node)newValue);
				return;
			case RtfmPackage.LIBRARY__PATH:
				setPath((String)newValue);
				return;
			case RtfmPackage.LIBRARY__CONFIGURATION_URI:
				setConfigurationUri((URI)newValue);
				return;
			case RtfmPackage.LIBRARY__LAST_SCAN:
				setLastScan((Long)newValue);
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
			case RtfmPackage.LIBRARY__ROOT:
				setRoot((Node)null);
				return;
			case RtfmPackage.LIBRARY__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case RtfmPackage.LIBRARY__CONFIGURATION_URI:
				setConfigurationUri(CONFIGURATION_URI_EDEFAULT);
				return;
			case RtfmPackage.LIBRARY__LAST_SCAN:
				setLastScan(LAST_SCAN_EDEFAULT);
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
			case RtfmPackage.LIBRARY__ROOT:
				return root != null;
			case RtfmPackage.LIBRARY__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case RtfmPackage.LIBRARY__CONFIGURATION_URI:
				return CONFIGURATION_URI_EDEFAULT == null ? configurationUri != null : !CONFIGURATION_URI_EDEFAULT.equals(configurationUri);
			case RtfmPackage.LIBRARY__LAST_SCAN:
				return lastScan != LAST_SCAN_EDEFAULT;
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
		result.append(" (path: ");
		result.append(path);
		result.append(", configurationUri: ");
		result.append(configurationUri);
		result.append(", lastScan: ");
		result.append(lastScan);
		result.append(')');
		return result.toString();
	}

} //LibraryImpl
