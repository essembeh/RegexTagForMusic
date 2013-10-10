
package org.essembeh.rtfm.model.gen.configuration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionBase complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="inverse" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionBase", namespace = "http://rtfm.essembeh.org/Configuration/1")
@XmlSeeAlso({
    TConditionGroup.class,
    TConditionFileOrFolder.class,
    TConditionTrue.class,
    TConditionExtension.class,
    TConditionAttributeValueEquals.class,
    TConditionAttributeExists.class,
    TConditionVirtualPathMatches.class,
    TConditionFalse.class,
    TConditionAttributeValueMatches.class
})
public class TConditionBase {

    @XmlAttribute(name = "inverse")
    protected Boolean inverse;

    /**
     * Obtient la valeur de la propriété inverse.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isInverse() {
        if (inverse == null) {
            return false;
        } else {
            return inverse;
        }
    }

    /**
     * Définit la valeur de la propriété inverse.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInverse(Boolean value) {
        this.inverse = value;
    }

}
