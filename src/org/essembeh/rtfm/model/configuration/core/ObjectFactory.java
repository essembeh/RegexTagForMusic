
package org.essembeh.rtfm.model.configuration.core;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.essembeh.rtfm.model.configuration.core package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConfigurationCore_QNAME = new QName("http://rtfm.essembeh.org/ConfigurationCore", "configuration-core");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.essembeh.rtfm.model.configuration.core
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TCoreConfiguration }
     * 
     */
    public TCoreConfiguration createTCoreConfiguration() {
        return new TCoreConfiguration();
    }

    /**
     * Create an instance of {@link TConditionTrue }
     * 
     */
    public TConditionTrue createTConditionTrue() {
        return new TConditionTrue();
    }

    /**
     * Create an instance of {@link TTaskList }
     * 
     */
    public TTaskList createTTaskList() {
        return new TTaskList();
    }

    /**
     * Create an instance of {@link TAction }
     * 
     */
    public TAction createTAction() {
        return new TAction();
    }

    /**
     * Create an instance of {@link TWorkflow }
     * 
     */
    public TWorkflow createTWorkflow() {
        return new TWorkflow();
    }

    /**
     * Create an instance of {@link TFileHandler }
     * 
     */
    public TFileHandler createTFileHandler() {
        return new TFileHandler();
    }

    /**
     * Create an instance of {@link TReference }
     * 
     */
    public TReference createTReference() {
        return new TReference();
    }

    /**
     * Create an instance of {@link TConditionVirtualPathMatchesVirtualFile }
     * 
     */
    public TConditionVirtualPathMatchesVirtualFile createTConditionVirtualPathMatchesVirtualFile() {
        return new TConditionVirtualPathMatchesVirtualFile();
    }

    /**
     * Create an instance of {@link TProperty }
     * 
     */
    public TProperty createTProperty() {
        return new TProperty();
    }

    /**
     * Create an instance of {@link TFileHandlerList }
     * 
     */
    public TFileHandlerList createTFileHandlerList() {
        return new TFileHandlerList();
    }

    /**
     * Create an instance of {@link TTask }
     * 
     */
    public TTask createTTask() {
        return new TTask();
    }

    /**
     * Create an instance of {@link TApplyOnList }
     * 
     */
    public TApplyOnList createTApplyOnList() {
        return new TApplyOnList();
    }

    /**
     * Create an instance of {@link TConditionTypeEqualsXFile }
     * 
     */
    public TConditionTypeEqualsXFile createTConditionTypeEqualsXFile() {
        return new TConditionTypeEqualsXFile();
    }

    /**
     * Create an instance of {@link TConditionAttributeExistsXFile }
     * 
     */
    public TConditionAttributeExistsXFile createTConditionAttributeExistsXFile() {
        return new TConditionAttributeExistsXFile();
    }

    /**
     * Create an instance of {@link TConditionGroupXFile }
     * 
     */
    public TConditionGroupXFile createTConditionGroupXFile() {
        return new TConditionGroupXFile();
    }

    /**
     * Create an instance of {@link TRegexAttribute }
     * 
     */
    public TRegexAttribute createTRegexAttribute() {
        return new TRegexAttribute();
    }

    /**
     * Create an instance of {@link TSubstitutionList }
     * 
     */
    public TSubstitutionList createTSubstitutionList() {
        return new TSubstitutionList();
    }

    /**
     * Create an instance of {@link TConditionExtensionVirtualFile }
     * 
     */
    public TConditionExtensionVirtualFile createTConditionExtensionVirtualFile() {
        return new TConditionExtensionVirtualFile();
    }

    /**
     * Create an instance of {@link TConditionGroupVirtualFile }
     * 
     */
    public TConditionGroupVirtualFile createTConditionGroupVirtualFile() {
        return new TConditionGroupVirtualFile();
    }

    /**
     * Create an instance of {@link TAttributeList }
     * 
     */
    public TAttributeList createTAttributeList() {
        return new TAttributeList();
    }

    /**
     * Create an instance of {@link TActionList }
     * 
     */
    public TActionList createTActionList() {
        return new TActionList();
    }

    /**
     * Create an instance of {@link TFixedAttribute }
     * 
     */
    public TFixedAttribute createTFixedAttribute() {
        return new TFixedAttribute();
    }

    /**
     * Create an instance of {@link TSubstitution }
     * 
     */
    public TSubstitution createTSubstitution() {
        return new TSubstitution();
    }

    /**
     * Create an instance of {@link TConditionAttributeValueMatchesXFile }
     * 
     */
    public TConditionAttributeValueMatchesXFile createTConditionAttributeValueMatchesXFile() {
        return new TConditionAttributeValueMatchesXFile();
    }

    /**
     * Create an instance of {@link TConditionAttributeValueEqualsXFile }
     * 
     */
    public TConditionAttributeValueEqualsXFile createTConditionAttributeValueEqualsXFile() {
        return new TConditionAttributeValueEqualsXFile();
    }

    /**
     * Create an instance of {@link TConditionFileOrFilderVirtualFile }
     * 
     */
    public TConditionFileOrFilderVirtualFile createTConditionFileOrFilderVirtualFile() {
        return new TConditionFileOrFilderVirtualFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCoreConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtfm.essembeh.org/ConfigurationCore", name = "configuration-core")
    public JAXBElement<TCoreConfiguration> createConfigurationCore(TCoreConfiguration value) {
        return new JAXBElement<TCoreConfiguration>(_ConfigurationCore_QNAME, TCoreConfiguration.class, null, value);
    }

}
