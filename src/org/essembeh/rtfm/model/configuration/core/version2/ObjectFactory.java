//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2012.12.03 � 04:47:27 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.essembeh.rtfm.model.configuration.core.version2 package. 
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

    private final static QName _ConfigurationCore_QNAME = new QName("http://rtfm.essembeh.org/ConfigurationCore-v2", "configuration-core");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.essembeh.rtfm.model.configuration.core.version2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TCoreConfigurationV2 }
     * 
     */
    public TCoreConfigurationV2 createTCoreConfigurationV2() {
        return new TCoreConfigurationV2();
    }

    /**
     * Create an instance of {@link TRegexAttribute }
     * 
     */
    public TRegexAttribute createTRegexAttribute() {
        return new TRegexAttribute();
    }

    /**
     * Create an instance of {@link TConditionOnType }
     * 
     */
    public TConditionOnType createTConditionOnType() {
        return new TConditionOnType();
    }

    /**
     * Create an instance of {@link TTaskList }
     * 
     */
    public TTaskList createTTaskList() {
        return new TTaskList();
    }

    /**
     * Create an instance of {@link TConditionOnVirtualPath }
     * 
     */
    public TConditionOnVirtualPath createTConditionOnVirtualPath() {
        return new TConditionOnVirtualPath();
    }

    /**
     * Create an instance of {@link TSubstitutionList }
     * 
     */
    public TSubstitutionList createTSubstitutionList() {
        return new TSubstitutionList();
    }

    /**
     * Create an instance of {@link TAction }
     * 
     */
    public TAction createTAction() {
        return new TAction();
    }

    /**
     * Create an instance of {@link TActionList }
     * 
     */
    public TActionList createTActionList() {
        return new TActionList();
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
     * Create an instance of {@link TConditionList }
     * 
     */
    public TConditionList createTConditionList() {
        return new TConditionList();
    }

    /**
     * Create an instance of {@link TApplyOnList }
     * 
     */
    public TApplyOnList createTApplyOnList() {
        return new TApplyOnList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TCoreConfigurationV2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", name = "configuration-core")
    public JAXBElement<TCoreConfigurationV2> createConfigurationCore(TCoreConfigurationV2 value) {
        return new JAXBElement<TCoreConfigurationV2>(_ConfigurationCore_QNAME, TCoreConfigurationV2 .class, null, value);
    }

}
