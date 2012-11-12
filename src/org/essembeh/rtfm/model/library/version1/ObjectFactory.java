//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2012.11.12 à 12:52:13 AM CET 
//


package org.essembeh.rtfm.model.library.version1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.essembeh.rtfm.model.library.version1 package. 
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

    private final static QName _Music_QNAME = new QName("http://rtfm.essembeh.org/Library-v1", "music");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.essembeh.rtfm.model.library.version1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TLibraryV1 }
     * 
     */
    public TLibraryV1 createTLibraryV1() {
        return new TLibraryV1();
    }

    /**
     * Create an instance of {@link TFile }
     * 
     */
    public TFile createTFile() {
        return new TFile();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TLibraryV1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rtfm.essembeh.org/Library-v1", name = "music")
    public JAXBElement<TLibraryV1> createMusic(TLibraryV1 value) {
        return new JAXBElement<TLibraryV1>(_Music_QNAME, TLibraryV1 .class, null, value);
    }

}
