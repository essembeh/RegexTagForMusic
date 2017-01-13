/**
 */
package org.essembeh.rtfm.model.rtfm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.essembeh.rtfm.model.rtfm.RtfmFactory
 * @model kind="package"
 * @generated
 */
public interface RtfmPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rtfm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://rtfm.essembeh.org/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rtfm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RtfmPackage eINSTANCE = org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl <em>Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getConfiguration()
	 * @generated
	 */
	int CONFIGURATION = 0;

	/**
	 * The feature id for the '<em><b>File Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__FILE_HANDLERS = 0;

	/**
	 * The feature id for the '<em><b>Workflows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__WORKFLOWS = 1;

	/**
	 * The feature id for the '<em><b>Substitutions</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION__SUBSTITUTIONS = 2;

	/**
	 * The number of structural features of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Resolve Pattern</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION___RESOLVE_PATTERN__STRING = 0;

	/**
	 * The number of operations of the '<em>Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl <em>Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.LibraryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getLibrary()
	 * @generated
	 */
	int LIBRARY = 1;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__PATH = 1;

	/**
	 * The feature id for the '<em><b>Configuration Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__CONFIGURATION_URI = 2;

	/**
	 * The feature id for the '<em><b>Last Scan</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY__LAST_SCAN = 3;

	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIBRARY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.NodeImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Folder</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__FOLDER = 1;

	/**
	 * The feature id for the '<em><b>Root</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ROOT = 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__CONTENT = 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ATTRIBUTES = 4;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Get Path</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_PATH = 0;

	/**
	 * The operation id for the '<em>Get Virtual Path</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_VIRTUAL_PATH = 1;

	/**
	 * The operation id for the '<em>Get Resource</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_RESOURCE = 2;

	/**
	 * The operation id for the '<em>Find</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___FIND__IPATH = 3;

	/**
	 * The operation id for the '<em>Get Parent Node</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_PARENT_NODE = 4;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.NodeEntryImpl <em>Node Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.NodeEntryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getNodeEntry()
	 * @generated
	 */
	int NODE_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Node Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Node Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.StringEntryImpl <em>String Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.StringEntryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getStringEntry()
	 * @generated
	 */
	int STRING_ENTRY = 4;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl <em>File Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFileHandler()
	 * @generated
	 */
	int FILE_HANDLER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER__CONFIGURATION = 1;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER__PATTERN = 2;

	/**
	 * The feature id for the '<em><b>Factories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER__FACTORIES = 3;

	/**
	 * The number of structural features of the '<em>File Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER___ACCEPT__STRING = 0;

	/**
	 * The number of operations of the '<em>File Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_HANDLER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl <em>Abstract Attribute Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAbstractAttributeFactory()
	 * @generated
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY__NAME = 0;

	/**
	 * The feature id for the '<em><b>File Handler</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER = 1;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY = 2;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL = 3;

	/**
	 * The number of structural features of the '<em>Abstract Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Get Attribute Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING = 0;

	/**
	 * The number of operations of the '<em>Abstract Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ATTRIBUTE_FACTORY_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.FixedAttributeFactoryImpl <em>Fixed Attribute Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.FixedAttributeFactoryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFixedAttributeFactory()
	 * @generated
	 */
	int FIXED_ATTRIBUTE_FACTORY = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__NAME = ABSTRACT_ATTRIBUTE_FACTORY__NAME;

	/**
	 * The feature id for the '<em><b>File Handler</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__FILE_HANDLER = ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__STRATEGY = ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__OPTIONAL = ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__VALUE = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY__PATTERN = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Fixed Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY_FEATURE_COUNT = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Attribute Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING = ABSTRACT_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING;

	/**
	 * The number of operations of the '<em>Fixed Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_ATTRIBUTE_FACTORY_OPERATION_COUNT = ABSTRACT_ATTRIBUTE_FACTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.RegexAttributeFactoryImpl <em>Regex Attribute Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.RegexAttributeFactoryImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getRegexAttributeFactory()
	 * @generated
	 */
	int REGEX_ATTRIBUTE_FACTORY = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__NAME = ABSTRACT_ATTRIBUTE_FACTORY__NAME;

	/**
	 * The feature id for the '<em><b>File Handler</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__FILE_HANDLER = ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__STRATEGY = ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__OPTIONAL = ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__GROUP = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY__PATTERN = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Regex Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY_FEATURE_COUNT = ABSTRACT_ATTRIBUTE_FACTORY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Attribute Value</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING = ABSTRACT_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING;

	/**
	 * The number of operations of the '<em>Regex Attribute Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REGEX_ATTRIBUTE_FACTORY_OPERATION_COUNT = ABSTRACT_ATTRIBUTE_FACTORY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.WorkflowImpl <em>Workflow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.WorkflowImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getWorkflow()
	 * @generated
	 */
	int WORKFLOW = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__NAME = 0;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__CONFIGURATION = 1;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__PATTERN = 2;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__TASKS = 3;

	/**
	 * The feature id for the '<em><b>Filters</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW__FILTERS = 4;

	/**
	 * The number of structural features of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Execute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW___EXECUTE__ELIST_IPROGRESSMONITOR = 0;

	/**
	 * The operation id for the '<em>Accept</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW___ACCEPT__NODE = 1;

	/**
	 * The number of operations of the '<em>Workflow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKFLOW_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.TaskImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 10;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Execute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK___EXECUTE__NODE_IPROGRESSMONITOR = 0;

	/**
	 * The number of operations of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.ExternalProcessImpl <em>External Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.ExternalProcessImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getExternalProcess()
	 * @generated
	 */
	int EXTERNAL_PROCESS = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS__NAME = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Executable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS__EXECUTABLE = TASK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS__ARGUMENTS = TASK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Env</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS__ENV = TASK_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>External Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS_FEATURE_COUNT = TASK_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Execute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS___EXECUTE__NODE_IPROGRESSMONITOR = TASK___EXECUTE__NODE_IPROGRESSMONITOR;

	/**
	 * The number of operations of the '<em>External Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PROCESS_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl <em>Attribute Tool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAttributeTool()
	 * @generated
	 */
	int ATTRIBUTE_TOOL = 12;

	/**
	 * The feature id for the '<em><b>Attributes To Update</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE = TASK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attributes To Delete</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE = TASK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TOOL_FEATURE_COUNT = TASK_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Execute</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TOOL___EXECUTE__NODE_IPROGRESSMONITOR = TASK___EXECUTE__NODE_IPROGRESSMONITOR;

	/**
	 * The number of operations of the '<em>Attribute Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_TOOL_OPERATION_COUNT = TASK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy <em>Attribute Factory Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAttributeFactoryStrategy()
	 * @generated
	 */
	int ATTRIBUTE_FACTORY_STRATEGY = 13;

	/**
	 * The meta object id for the '<em>File</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.io.File
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFile()
	 * @generated
	 */
	int FILE = 14;

	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 15;

	/**
	 * The meta object id for the '<em>IPath</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IPath
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIPath()
	 * @generated
	 */
	int IPATH = 16;

	/**
	 * The meta object id for the '<em>IO Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.io.IOException
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIOException()
	 * @generated
	 */
	int IO_EXCEPTION = 17;

	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IStatus
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 18;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.net.URI
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getURI()
	 * @generated
	 */
	int URI = 19;

	/**
	 * The meta object id for the '<em>Interrupted Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.InterruptedException
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getInterruptedException()
	 * @generated
	 */
	int INTERRUPTED_EXCEPTION = 20;

	/**
	 * The meta object id for the '<em>Iterable</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Iterable
	 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIterable()
	 * @generated
	 */
	int ITERABLE = 21;


	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.Configuration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration
	 * @generated
	 */
	EClass getConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.essembeh.rtfm.model.rtfm.Configuration#getFileHandlers <em>File Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>File Handlers</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration#getFileHandlers()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_FileHandlers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.essembeh.rtfm.model.rtfm.Configuration#getWorkflows <em>Workflows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Workflows</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration#getWorkflows()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Workflows();

	/**
	 * Returns the meta object for the map '{@link org.essembeh.rtfm.model.rtfm.Configuration#getSubstitutions <em>Substitutions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Substitutions</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration#getSubstitutions()
	 * @see #getConfiguration()
	 * @generated
	 */
	EReference getConfiguration_Substitutions();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Configuration#resolvePattern(java.lang.String) <em>Resolve Pattern</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Resolve Pattern</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Configuration#resolvePattern(java.lang.String)
	 * @generated
	 */
	EOperation getConfiguration__ResolvePattern__String();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the containment reference '{@link org.essembeh.rtfm.model.rtfm.Library#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Library#getRoot()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_Root();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Library#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Library#getPath()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Library#getConfigurationUri <em>Configuration Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Configuration Uri</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Library#getConfigurationUri()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_ConfigurationUri();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Library#getLastScan <em>Last Scan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Scan</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Library#getLastScan()
	 * @see #getLibrary()
	 * @generated
	 */
	EAttribute getLibrary_LastScan();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Node#isFolder <em>Folder</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Folder</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node#isFolder()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Folder();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Node#isRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Root</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node#isRoot()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Root();

	/**
	 * Returns the meta object for the map '{@link org.essembeh.rtfm.model.rtfm.Node#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Content</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getContent()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Content();

	/**
	 * Returns the meta object for the map '{@link org.essembeh.rtfm.model.rtfm.Node#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getAttributes()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Attributes();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Node#getPath() <em>Get Path</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Path</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getPath()
	 * @generated
	 */
	EOperation getNode__GetPath();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Node#getVirtualPath() <em>Get Virtual Path</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Virtual Path</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getVirtualPath()
	 * @generated
	 */
	EOperation getNode__GetVirtualPath();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Node#getResource() <em>Get Resource</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Resource</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getResource()
	 * @generated
	 */
	EOperation getNode__GetResource();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Node#find(org.eclipse.core.runtime.IPath) <em>Find</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Find</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Node#find(org.eclipse.core.runtime.IPath)
	 * @generated
	 */
	EOperation getNode__Find__IPath();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Node#getParentNode() <em>Get Parent Node</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Parent Node</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Node#getParentNode()
	 * @generated
	 */
	EOperation getNode__GetParentNode();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Node Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.essembeh.rtfm.model.rtfm.Node" valueContainment="true"
	 * @generated
	 */
	EClass getNodeEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getNodeEntry()
	 * @generated
	 */
	EAttribute getNodeEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getNodeEntry()
	 * @generated
	 */
	EReference getNodeEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringEntry()
	 * @generated
	 */
	EAttribute getStringEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringEntry()
	 * @generated
	 */
	EAttribute getStringEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.FileHandler <em>File Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Handler</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler
	 * @generated
	 */
	EClass getFileHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getName()
	 * @see #getFileHandler()
	 * @generated
	 */
	EAttribute getFileHandler_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Configuration</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getConfiguration()
	 * @see #getFileHandler()
	 * @generated
	 */
	EReference getFileHandler_Configuration();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getPattern()
	 * @see #getFileHandler()
	 * @generated
	 */
	EAttribute getFileHandler_Pattern();

	/**
	 * Returns the meta object for the containment reference list '{@link org.essembeh.rtfm.model.rtfm.FileHandler#getFactories <em>Factories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Factories</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#getFactories()
	 * @see #getFileHandler()
	 * @generated
	 */
	EReference getFileHandler_Factories();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.FileHandler#accept(java.lang.String) <em>Accept</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Accept</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.FileHandler#accept(java.lang.String)
	 * @generated
	 */
	EOperation getFileHandler__Accept__String();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory <em>Abstract Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Attribute Factory</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory
	 * @generated
	 */
	EClass getAbstractAttributeFactory();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getName()
	 * @see #getAbstractAttributeFactory()
	 * @generated
	 */
	EAttribute getAbstractAttributeFactory_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler <em>File Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>File Handler</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getFileHandler()
	 * @see #getAbstractAttributeFactory()
	 * @generated
	 */
	EReference getAbstractAttributeFactory_FileHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getStrategy()
	 * @see #getAbstractAttributeFactory()
	 * @generated
	 */
	EAttribute getAbstractAttributeFactory_Strategy();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#isOptional()
	 * @see #getAbstractAttributeFactory()
	 * @generated
	 */
	EAttribute getAbstractAttributeFactory_Optional();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getAttributeValue(java.lang.String) <em>Get Attribute Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Attribute Value</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.AbstractAttributeFactory#getAttributeValue(java.lang.String)
	 * @generated
	 */
	EOperation getAbstractAttributeFactory__GetAttributeValue__String();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.FixedAttributeFactory <em>Fixed Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fixed Attribute Factory</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FixedAttributeFactory
	 * @generated
	 */
	EClass getFixedAttributeFactory();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.FixedAttributeFactory#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FixedAttributeFactory#getValue()
	 * @see #getFixedAttributeFactory()
	 * @generated
	 */
	EAttribute getFixedAttributeFactory_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.FixedAttributeFactory#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.FixedAttributeFactory#getPattern()
	 * @see #getFixedAttributeFactory()
	 * @generated
	 */
	EAttribute getFixedAttributeFactory_Pattern();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory <em>Regex Attribute Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Regex Attribute Factory</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.RegexAttributeFactory
	 * @generated
	 */
	EClass getRegexAttributeFactory();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Group</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getGroup()
	 * @see #getRegexAttributeFactory()
	 * @generated
	 */
	EAttribute getRegexAttributeFactory_Group();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.RegexAttributeFactory#getPattern()
	 * @see #getRegexAttributeFactory()
	 * @generated
	 */
	EAttribute getRegexAttributeFactory_Pattern();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.Workflow <em>Workflow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workflow</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow
	 * @generated
	 */
	EClass getWorkflow();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Workflow#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getName()
	 * @see #getWorkflow()
	 * @generated
	 */
	EAttribute getWorkflow_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.essembeh.rtfm.model.rtfm.Workflow#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Configuration</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getConfiguration()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Configuration();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.Workflow#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getPattern()
	 * @see #getWorkflow()
	 * @generated
	 */
	EAttribute getWorkflow_Pattern();

	/**
	 * Returns the meta object for the containment reference list '{@link org.essembeh.rtfm.model.rtfm.Workflow#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasks</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getTasks()
	 * @see #getWorkflow()
	 * @generated
	 */
	EReference getWorkflow_Tasks();

	/**
	 * Returns the meta object for the attribute list '{@link org.essembeh.rtfm.model.rtfm.Workflow#getFilters <em>Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Filters</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#getFilters()
	 * @see #getWorkflow()
	 * @generated
	 */
	EAttribute getWorkflow_Filters();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Workflow#execute(org.eclipse.emf.common.util.EList, org.eclipse.core.runtime.IProgressMonitor) <em>Execute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Execute</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#execute(org.eclipse.emf.common.util.EList, org.eclipse.core.runtime.IProgressMonitor)
	 * @generated
	 */
	EOperation getWorkflow__Execute__EList_IProgressMonitor();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Workflow#accept(org.essembeh.rtfm.model.rtfm.Node) <em>Accept</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Accept</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Workflow#accept(org.essembeh.rtfm.model.rtfm.Node)
	 * @generated
	 */
	EOperation getWorkflow__Accept__Node();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the '{@link org.essembeh.rtfm.model.rtfm.Task#execute(org.essembeh.rtfm.model.rtfm.Node, org.eclipse.core.runtime.IProgressMonitor) <em>Execute</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Execute</em>' operation.
	 * @see org.essembeh.rtfm.model.rtfm.Task#execute(org.essembeh.rtfm.model.rtfm.Node, org.eclipse.core.runtime.IProgressMonitor)
	 * @generated
	 */
	EOperation getTask__Execute__Node_IProgressMonitor();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess <em>External Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Process</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess
	 * @generated
	 */
	EClass getExternalProcess();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess#getName()
	 * @see #getExternalProcess()
	 * @generated
	 */
	EAttribute getExternalProcess_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getExecutable <em>Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Executable</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess#getExecutable()
	 * @see #getExternalProcess()
	 * @generated
	 */
	EAttribute getExternalProcess_Executable();

	/**
	 * Returns the meta object for the attribute list '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Arguments</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess#getArguments()
	 * @see #getExternalProcess()
	 * @generated
	 */
	EAttribute getExternalProcess_Arguments();

	/**
	 * Returns the meta object for the map '{@link org.essembeh.rtfm.model.rtfm.ExternalProcess#getEnv <em>Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Env</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.ExternalProcess#getEnv()
	 * @see #getExternalProcess()
	 * @generated
	 */
	EReference getExternalProcess_Env();

	/**
	 * Returns the meta object for class '{@link org.essembeh.rtfm.model.rtfm.AttributeTool <em>Attribute Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Tool</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeTool
	 * @generated
	 */
	EClass getAttributeTool();

	/**
	 * Returns the meta object for the map '{@link org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToUpdate <em>Attributes To Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes To Update</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToUpdate()
	 * @see #getAttributeTool()
	 * @generated
	 */
	EReference getAttributeTool_AttributesToUpdate();

	/**
	 * Returns the meta object for the attribute list '{@link org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToDelete <em>Attributes To Delete</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Attributes To Delete</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeTool#getAttributesToDelete()
	 * @see #getAttributeTool()
	 * @generated
	 */
	EAttribute getAttributeTool_AttributesToDelete();

	/**
	 * Returns the meta object for enum '{@link org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy <em>Attribute Factory Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Attribute Factory Strategy</em>'.
	 * @see org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy
	 * @generated
	 */
	EEnum getAttributeFactoryStrategy();

	/**
	 * Returns the meta object for data type '{@link java.io.File <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>File</em>'.
	 * @see java.io.File
	 * @model instanceClass="java.io.File"
	 * @generated
	 */
	EDataType getFile();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor <em>IProgress Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IPath <em>IPath</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IPath</em>'.
	 * @see org.eclipse.core.runtime.IPath
	 * @model instanceClass="org.eclipse.core.runtime.IPath"
	 * @generated
	 */
	EDataType getIPath();

	/**
	 * Returns the meta object for data type '{@link java.io.IOException <em>IO Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IO Exception</em>'.
	 * @see java.io.IOException
	 * @model instanceClass="java.io.IOException"
	 * @generated
	 */
	EDataType getIOException();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus"
	 * @generated
	 */
	EDataType getIStatus();

	/**
	 * Returns the meta object for data type '{@link java.net.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see java.net.URI
	 * @model instanceClass="java.net.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link java.lang.InterruptedException <em>Interrupted Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Interrupted Exception</em>'.
	 * @see java.lang.InterruptedException
	 * @model instanceClass="java.lang.InterruptedException"
	 * @generated
	 */
	EDataType getInterruptedException();

	/**
	 * Returns the meta object for data type '{@link java.lang.Iterable <em>Iterable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Iterable</em>'.
	 * @see java.lang.Iterable
	 * @model instanceClass="java.lang.Iterable"
	 * @generated
	 */
	EDataType getIterable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RtfmFactory getRtfmFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl <em>Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getConfiguration()
		 * @generated
		 */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
		 * The meta object literal for the '<em><b>File Handlers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__FILE_HANDLERS = eINSTANCE.getConfiguration_FileHandlers();

		/**
		 * The meta object literal for the '<em><b>Workflows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__WORKFLOWS = eINSTANCE.getConfiguration_Workflows();

		/**
		 * The meta object literal for the '<em><b>Substitutions</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURATION__SUBSTITUTIONS = eINSTANCE.getConfiguration_Substitutions();

		/**
		 * The meta object literal for the '<em><b>Resolve Pattern</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CONFIGURATION___RESOLVE_PATTERN__STRING = eINSTANCE.getConfiguration__ResolvePattern__String();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.LibraryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__ROOT = eINSTANCE.getLibrary_Root();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__PATH = eINSTANCE.getLibrary_Path();

		/**
		 * The meta object literal for the '<em><b>Configuration Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__CONFIGURATION_URI = eINSTANCE.getLibrary_ConfigurationUri();

		/**
		 * The meta object literal for the '<em><b>Last Scan</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIBRARY__LAST_SCAN = eINSTANCE.getLibrary_LastScan();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.NodeImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Folder</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__FOLDER = eINSTANCE.getNode_Folder();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ROOT = eINSTANCE.getNode_Root();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__CONTENT = eINSTANCE.getNode_Content();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__ATTRIBUTES = eINSTANCE.getNode_Attributes();

		/**
		 * The meta object literal for the '<em><b>Get Path</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___GET_PATH = eINSTANCE.getNode__GetPath();

		/**
		 * The meta object literal for the '<em><b>Get Virtual Path</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___GET_VIRTUAL_PATH = eINSTANCE.getNode__GetVirtualPath();

		/**
		 * The meta object literal for the '<em><b>Get Resource</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___GET_RESOURCE = eINSTANCE.getNode__GetResource();

		/**
		 * The meta object literal for the '<em><b>Find</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___FIND__IPATH = eINSTANCE.getNode__Find__IPath();

		/**
		 * The meta object literal for the '<em><b>Get Parent Node</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation NODE___GET_PARENT_NODE = eINSTANCE.getNode__GetParentNode();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.NodeEntryImpl <em>Node Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.NodeEntryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getNodeEntry()
		 * @generated
		 */
		EClass NODE_ENTRY = eINSTANCE.getNodeEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_ENTRY__KEY = eINSTANCE.getNodeEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_ENTRY__VALUE = eINSTANCE.getNodeEntry_Value();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.StringEntryImpl <em>String Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.StringEntryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getStringEntry()
		 * @generated
		 */
		EClass STRING_ENTRY = eINSTANCE.getStringEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ENTRY__KEY = eINSTANCE.getStringEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ENTRY__VALUE = eINSTANCE.getStringEntry_Value();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl <em>File Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFileHandler()
		 * @generated
		 */
		EClass FILE_HANDLER = eINSTANCE.getFileHandler();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_HANDLER__NAME = eINSTANCE.getFileHandler_Name();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_HANDLER__CONFIGURATION = eINSTANCE.getFileHandler_Configuration();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_HANDLER__PATTERN = eINSTANCE.getFileHandler_Pattern();

		/**
		 * The meta object literal for the '<em><b>Factories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILE_HANDLER__FACTORIES = eINSTANCE.getFileHandler_Factories();

		/**
		 * The meta object literal for the '<em><b>Accept</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FILE_HANDLER___ACCEPT__STRING = eINSTANCE.getFileHandler__Accept__String();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl <em>Abstract Attribute Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.AbstractAttributeFactoryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAbstractAttributeFactory()
		 * @generated
		 */
		EClass ABSTRACT_ATTRIBUTE_FACTORY = eINSTANCE.getAbstractAttributeFactory();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ATTRIBUTE_FACTORY__NAME = eINSTANCE.getAbstractAttributeFactory_Name();

		/**
		 * The meta object literal for the '<em><b>File Handler</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ATTRIBUTE_FACTORY__FILE_HANDLER = eINSTANCE.getAbstractAttributeFactory_FileHandler();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ATTRIBUTE_FACTORY__STRATEGY = eINSTANCE.getAbstractAttributeFactory_Strategy();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ATTRIBUTE_FACTORY__OPTIONAL = eINSTANCE.getAbstractAttributeFactory_Optional();

		/**
		 * The meta object literal for the '<em><b>Get Attribute Value</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_ATTRIBUTE_FACTORY___GET_ATTRIBUTE_VALUE__STRING = eINSTANCE.getAbstractAttributeFactory__GetAttributeValue__String();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.FixedAttributeFactoryImpl <em>Fixed Attribute Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.FixedAttributeFactoryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFixedAttributeFactory()
		 * @generated
		 */
		EClass FIXED_ATTRIBUTE_FACTORY = eINSTANCE.getFixedAttributeFactory();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_ATTRIBUTE_FACTORY__VALUE = eINSTANCE.getFixedAttributeFactory_Value();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_ATTRIBUTE_FACTORY__PATTERN = eINSTANCE.getFixedAttributeFactory_Pattern();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.RegexAttributeFactoryImpl <em>Regex Attribute Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.RegexAttributeFactoryImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getRegexAttributeFactory()
		 * @generated
		 */
		EClass REGEX_ATTRIBUTE_FACTORY = eINSTANCE.getRegexAttributeFactory();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGEX_ATTRIBUTE_FACTORY__GROUP = eINSTANCE.getRegexAttributeFactory_Group();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REGEX_ATTRIBUTE_FACTORY__PATTERN = eINSTANCE.getRegexAttributeFactory_Pattern();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.WorkflowImpl <em>Workflow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.WorkflowImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getWorkflow()
		 * @generated
		 */
		EClass WORKFLOW = eINSTANCE.getWorkflow();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW__NAME = eINSTANCE.getWorkflow_Name();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__CONFIGURATION = eINSTANCE.getWorkflow_Configuration();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW__PATTERN = eINSTANCE.getWorkflow_Pattern();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKFLOW__TASKS = eINSTANCE.getWorkflow_Tasks();

		/**
		 * The meta object literal for the '<em><b>Filters</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORKFLOW__FILTERS = eINSTANCE.getWorkflow_Filters();

		/**
		 * The meta object literal for the '<em><b>Execute</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation WORKFLOW___EXECUTE__ELIST_IPROGRESSMONITOR = eINSTANCE.getWorkflow__Execute__EList_IProgressMonitor();

		/**
		 * The meta object literal for the '<em><b>Accept</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation WORKFLOW___ACCEPT__NODE = eINSTANCE.getWorkflow__Accept__Node();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.TaskImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Execute</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TASK___EXECUTE__NODE_IPROGRESSMONITOR = eINSTANCE.getTask__Execute__Node_IProgressMonitor();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.ExternalProcessImpl <em>External Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.ExternalProcessImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getExternalProcess()
		 * @generated
		 */
		EClass EXTERNAL_PROCESS = eINSTANCE.getExternalProcess();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_PROCESS__NAME = eINSTANCE.getExternalProcess_Name();

		/**
		 * The meta object literal for the '<em><b>Executable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_PROCESS__EXECUTABLE = eINSTANCE.getExternalProcess_Executable();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL_PROCESS__ARGUMENTS = eINSTANCE.getExternalProcess_Arguments();

		/**
		 * The meta object literal for the '<em><b>Env</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXTERNAL_PROCESS__ENV = eINSTANCE.getExternalProcess_Env();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl <em>Attribute Tool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAttributeTool()
		 * @generated
		 */
		EClass ATTRIBUTE_TOOL = eINSTANCE.getAttributeTool();

		/**
		 * The meta object literal for the '<em><b>Attributes To Update</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_TOOL__ATTRIBUTES_TO_UPDATE = eINSTANCE.getAttributeTool_AttributesToUpdate();

		/**
		 * The meta object literal for the '<em><b>Attributes To Delete</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_TOOL__ATTRIBUTES_TO_DELETE = eINSTANCE.getAttributeTool_AttributesToDelete();

		/**
		 * The meta object literal for the '{@link org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy <em>Attribute Factory Strategy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.essembeh.rtfm.model.rtfm.AttributeFactoryStrategy
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getAttributeFactoryStrategy()
		 * @generated
		 */
		EEnum ATTRIBUTE_FACTORY_STRATEGY = eINSTANCE.getAttributeFactoryStrategy();

		/**
		 * The meta object literal for the '<em>File</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.io.File
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getFile()
		 * @generated
		 */
		EDataType FILE = eINSTANCE.getFile();

		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();

		/**
		 * The meta object literal for the '<em>IPath</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IPath
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIPath()
		 * @generated
		 */
		EDataType IPATH = eINSTANCE.getIPath();

		/**
		 * The meta object literal for the '<em>IO Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.io.IOException
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIOException()
		 * @generated
		 */
		EDataType IO_EXCEPTION = eINSTANCE.getIOException();

		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IStatus
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.net.URI
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '<em>Interrupted Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.InterruptedException
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getInterruptedException()
		 * @generated
		 */
		EDataType INTERRUPTED_EXCEPTION = eINSTANCE.getInterruptedException();

		/**
		 * The meta object literal for the '<em>Iterable</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Iterable
		 * @see org.essembeh.rtfm.model.rtfm.impl.RtfmPackageImpl#getIterable()
		 * @generated
		 */
		EDataType ITERABLE = eINSTANCE.getIterable();

	}

} //RtfmPackage
