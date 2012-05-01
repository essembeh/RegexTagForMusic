//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.21 at 02:13:37 PM CEST 
//


package org.essembeh.rtfm.model.configuration.core.version1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TFileHandler complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFileHandler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conditions" type="{http://rtfm.essembeh.org/ConfigurationCore-v1}TConditionList"/>
 *         &lt;element name="attributes" type="{http://rtfm.essembeh.org/ConfigurationCore-v1}TAttributeList"/>
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
@XmlType(name = "TFileHandler", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", propOrder = {
    "conditions",
    "attributes"
})
public class TFileHandler {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", required = true)
    protected TConditionList conditions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", required = true)
    protected TAttributeList attributes;
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Gets the value of the conditions property.
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
     * Sets the value of the conditions property.
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
     * Gets the value of the attributes property.
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
     * Sets the value of the attributes property.
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
     * Gets the value of the id property.
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
     * Sets the value of the id property.
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
