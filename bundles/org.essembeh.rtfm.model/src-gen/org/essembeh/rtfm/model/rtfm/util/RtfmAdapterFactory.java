/**
 */
package org.essembeh.rtfm.model.rtfm.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory;
import org.essembeh.rtfm.model.rtfm.AttributeTool;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.ExternalProcess;
import org.essembeh.rtfm.model.rtfm.FileHandler;
import org.essembeh.rtfm.model.rtfm.FixedAttributeFactory;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.RegexAttributeFactory;
import org.essembeh.rtfm.model.rtfm.RtfmPackage;
import org.essembeh.rtfm.model.rtfm.Task;
import org.essembeh.rtfm.model.rtfm.Workflow;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.essembeh.rtfm.model.rtfm.RtfmPackage
 * @generated
 */
public class RtfmAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RtfmPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RtfmAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RtfmPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RtfmSwitch<Adapter> modelSwitch =
		new RtfmSwitch<Adapter>() {
			@Override
			public Adapter caseConfiguration(Configuration object) {
				return createConfigurationAdapter();
			}
			@Override
			public Adapter caseLibrary(Library object) {
				return createLibraryAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseNodeEntry(Map.Entry<String, Node> object) {
				return createNodeEntryAdapter();
			}
			@Override
			public Adapter caseStringEntry(Map.Entry<String, String> object) {
				return createStringEntryAdapter();
			}
			@Override
			public Adapter caseFileHandler(FileHandler object) {
				return createFileHandlerAdapter();
			}
			@Override
			public Adapter caseAbstractAttributeFactory(AbstractAttributeFactory object) {
				return createAbstractAttributeFactoryAdapter();
			}
			@Override
			public Adapter caseFixedAttributeFactory(FixedAttributeFactory object) {
				return createFixedAttributeFactoryAdapter();
			}
			@Override
			public Adapter caseRegexAttributeFactory(RegexAttributeFactory object) {
				return createRegexAttributeFactoryAdapter();
			}
			@Override
			public Adapter caseWorkflow(Workflow object) {
				return createWorkflowAdapter();
			}
			@Override
			public Adapter caseTask(Task object) {
				return createTaskAdapter();
			}
			@Override
			public Adapter caseExternalProcess(ExternalProcess object) {
				return createExternalProcessAdapter();
			}
			@Override
			public Adapter caseAttributeTool(AttributeTool object) {
				return createAttributeToolAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration
	 * @generated
	 */
	public Adapter createConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.Library
	 * @generated
	 */
	public Adapter createLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Node Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createNodeEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.FileHandler <em>File Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler
	 * @generated
	 */
	public Adapter createFileHandlerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory <em>Abstract Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory
	 * @generated
	 */
	public Adapter createAbstractAttributeFactoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.FixedAttributeFactory <em>Fixed Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.FixedAttributeFactory
	 * @generated
	 */
	public Adapter createFixedAttributeFactoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory <em>Regex Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.RegexAttributeFactory
	 * @generated
	 */
	public Adapter createRegexAttributeFactoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.Workflow <em>Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow
	 * @generated
	 */
	public Adapter createWorkflowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.Task
	 * @generated
	 */
	public Adapter createTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess <em>External Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess
	 * @generated
	 */
	public Adapter createExternalProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.essembeh.rtfm.model.rtfm.AttributeTool <em>Attribute Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeTool
	 * @generated
	 */
	public Adapter createAttributeToolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RtfmAdapterFactory
