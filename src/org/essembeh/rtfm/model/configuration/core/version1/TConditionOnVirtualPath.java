//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2013.01.25 � 12:27:08 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionOnVirtualPath complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionOnVirtualPath">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="matches" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionOnVirtualPath", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1")
public class TConditionOnVirtualPath {

    @XmlAttribute(name = "matches", required = true)
    protected String matches;

    /**
     * Obtient la valeur de la propri�t� matches.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatches() {
        return matches;
    }

    /**
     * D�finit la valeur de la propri�t� matches.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatches(String value) {
        this.matches = value;
    }

}
