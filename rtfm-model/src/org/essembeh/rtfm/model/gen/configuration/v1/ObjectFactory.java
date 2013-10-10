
package org.essembeh.rtfm.model.gen.configuration.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.essembeh.rtfm.model.gen.configuration.v1 package. 
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

    private final static QName _Configuration_QNAME = new QName("http://rtfm.essembeh.org/Configuration/1", "configuration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.essembeh.rtfm.model.gen.configuration.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TWorkflow }
     * 
     */
    public TWorkflow createTWorkflow() {
        return new TWorkflow();
    }

    /**
     * Create an instance of {@link TConfiguration }
     * 
     */
    public TConfiguration createTConfiguration() {
        return new TConfiguration();
    }

    /**
     * Create an instance of {@link TConditionGroup }
     * 
     */
    public TConditionGroup createTConditionGroup() {
        return new TConditionGroup();
    }

    /**
     * Create an instance of {@link TConditionFileOrFolder }
     * 
     */
    public TConditionFileOrFolder createTConditionFileOrFolder() {
        return new TConditionFileOrFolder();
    }

    /**
     * Create an instance of {@link TConditionTrue }
     * 
     */
    public TConditionTrue createTConditionTrue() {
        return new TConditionTrue();
    }

    /**
     * Create an instance of {@link TRegexAttribute }
     * 
     */
    public TRegexAttribute createTRegexAttribute() {
        return new TRegexAttribute();
    }

    /**
     * Create an instance of {@link TTaskList }
     * 
     */
    public TTaskList createTTaskList() {
        return new TTaskList();
    }

    /**
     * Create an instance of {@link TConditionExtension }
     * 
     */
    public TConditionExtension createTConditionExtension() {
        return new TConditionExtension();
    }

    /**
     * Create an instance of {@link TConditionAttributeValueEquals }
     * 
     */
    public TConditionAttributeValueEquals createTConditionAttributeValueEquals() {
        return new TConditionAttributeValueEquals();
    }

    /**
     * Create an instance of {@link TConditionVirtualPathMatches }
     * 
     */
    public TConditionVirtualPathMatches createTConditionVirtualPathMatches() {
        return new TConditionVirtualPathMatches();
    }

    /**
     * Create an instance of {@link TSubstitutionList }
     * 
     */
    public TSubstitutionList createTSubstitutionList() {
        return new TSubstitutionList();
    }

    /**
     * Create an instance of {@link TConditionFalse }
     * 
     */
    public TConditionFalse createTConditionFalse() {
        return new TConditionFalse();
    }

    /**
     * Create an instance of {@link TAttributeList }
     * 
     */
    public TAttributeList createTAttributeList() {
        return new TAttributeList();
    }

    /**
     * Create an instance of {@link TFixedAttribute }
     * 
     */
    public TFixedAttribute createTFixedAttribute() {
        return new TFixedAttribute();
    }

    /**
     * Create an instance of {@link TTaskRef }
     * 
     */
    public TTaskRef createTTaskRef() {
        return new TTaskRef();
    }

    /**
     * Create an instance of {@link TFileHandler }
     * 
     */
    public TFileHandler createTFileHandler() {
        return new TFileHandler();
    }

    /**
     * Create an instance of {@link TConditionAttributeExists }
     * 
     */
    public TConditionAttributeExists createTConditionAttributeExists() {
        return new TConditionAttributeExists();
    }

    /**
     * Create an instance of {@link TSubstitution }
     * 
     */
    public TSubstitution createTSubstitution() {
        return new TSubstitution();
    }

    /**
     * Create an instance of {@link TReference }
     * 
     */
    public TReference createTReference() {
        return new TReference();
    }

    /**
     * Create an instance of {@link TWorkflowList }
     * 
     */
    public TWorkflowList createTWorkflowList() {
        return new TWorkflowList();
    }

    /**
     * Create an instance of {@link TFileHandlerList }
     * 
     */
    public TFileHandlerList createTFileHandlerList() {
        return new TFileHandlerList();
    }

    /**
     * Create an instance of {@link TProperty }
     * 
     */
    public TProperty createTProperty() {
        return new TProperty();
    }

    /**
     * Create an instance of {@link TTask }
     * 
     */
    public TTask createTTask() {
        return new TTask();
    }

    /**
     * Create an instance of {@link TConditionAttributeValueMatches }
     * 
     */
    public TConditionAttributeValueMatches createTConditionAttributeValueMatches() {
        return new TConditionAttributeValueMatches();
    }

    /**
     * Create an instance of {@link TWorkflow.Tasks }
     * 
     */
    public TWorkflow.Tasks createTWorkflowTasks() {
        return new TWorkflow.Tasks();
    }

    /**
     * Create an instance of {@link TConfiguration.Properties }
     * 
     */
    public TConfiguration.Properties createTConfigurationProperties() {
        return new TConfiguration.Properties();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtfm.essembeh.org/Configuration/1", name = "configuration")
    public JAXBElement<TConfiguration> createConfiguration(TConfiguration value) {
        return new JAXBElement<TConfiguration>(_Configuration_QNAME, TConfiguration.class, null, value);
    }

}
