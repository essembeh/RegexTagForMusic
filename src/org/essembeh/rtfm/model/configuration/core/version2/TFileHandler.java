//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2012.12.03 � 04:47:27 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TFileHandler complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TFileHandler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conditions" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionList"/>
 *         &lt;element name="attributes" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TAttributeList"/>
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
@XmlType(name = "TFileHandler", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "conditions",
    "attributes"
})
public class TFileHandler {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", required = true)
    protected TConditionList conditions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", required = true)
    protected TAttributeList attributes;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Obtient la valeur de la propri�t� conditions.
     * 
     * @return
     *     possible object is
     *     {@link TConditionList }
     *     
     */
    public TConditionList getConditions() {
        return conditions;
    }

    /**
     * D�finit la valeur de la propri�t� conditions.
     * 
     * @param value
     *     allowed object is
     *     {@link TConditionList }
     *     
     */
    public void setConditions(TConditionList value) {
        this.conditions = value;
    }

    /**
     * Obtient la valeur de la propri�t� attributes.
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
     * D�finit la valeur de la propri�t� attributes.
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
     * Obtient la valeur de la propri�t� id.
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
     * D�finit la valeur de la propri�t� id.
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
