
package org.essembeh.rtfm.model.gen.filesystem.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.essembeh.rtfm.model.gen.filesystem.v1 package. 
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

    private final static QName _Project_QNAME = new QName("http://rtfm.essembeh.org/filesystem/1", "project");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.essembeh.rtfm.model.gen.filesystem.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TAttributeList }
     * 
     */
    public TAttributeList createTAttributeList() {
        return new TAttributeList();
    }

    /**
     * Create an instance of {@link TResourceList }
     * 
     */
    public TResourceList createTResourceList() {
        return new TResourceList();
    }

    /**
     * Create an instance of {@link TProject }
     * 
     */
    public TProject createTProject() {
        return new TProject();
    }

    /**
     * Create an instance of {@link TAttributeList.Attribute }
     * 
     */
    public TAttributeList.Attribute createTAttributeListAttribute() {
        return new TAttributeList.Attribute();
    }

    /**
     * Create an instance of {@link TResourceList.Resource }
     * 
     */
    public TResourceList.Resource createTResourceListResource() {
        return new TResourceList.Resource();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TProject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtfm.essembeh.org/filesystem/1", name = "project")
    public JAXBElement<TProject> createProject(TProject value) {
        return new JAXBElement<TProject>(_Project_QNAME, TProject.class, null, value);
    }

}
