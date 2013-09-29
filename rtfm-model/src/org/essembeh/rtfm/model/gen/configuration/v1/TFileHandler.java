
package org.essembeh.rtfm.model.gen.configuration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TFileHandler complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TFileHandler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conditions" type="{http://rtfm.essembeh.org/Configuration/1}TConditionGroup"/>
 *         &lt;element name="attributes" type="{http://rtfm.essembeh.org/Configuration/1}TAttributeList"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFileHandler", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = {
    "conditions",
    "attributes"
})
public class TFileHandler {

    @XmlElement(required = true)
    protected TConditionGroup conditions;
    @XmlElement(required = true)
    protected TAttributeList attributes;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Obtient la valeur de la propriété conditions.
     * 
     * @return
     *     possible object is
     *     {@link TConditionGroup }
     *     
     */
    public TConditionGroup getConditions() {
        return conditions;
    }

    /**
     * Définit la valeur de la propriété conditions.
     * 
     * @param value
     *     allowed object is
     *     {@link TConditionGroup }
     *     
     */
    public void setConditions(TConditionGroup value) {
        this.conditions = value;
    }

    /**
     * Obtient la valeur de la propriété attributes.
     * 
     * @return
     *     possible object is
     *     {@link TAttributeList }
     *     
     */
    public TAttributeList getAttributes() {
        return attributes;
    }

    /**
     * Définit la valeur de la propriété attributes.
     * 
     * @param value
     *     allowed object is
     *     {@link TAttributeList }
     *     
     */
    public void setAttributes(TAttributeList value) {
        this.attributes = value;
    }

    /**
     * Obtient la valeur de la propriété id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
