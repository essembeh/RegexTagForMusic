//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2012.12.03 � 04:47:27 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TAction complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apply-on" type="{http://rtfm.essembeh.org/ConfigurationCore-v1}TApplyOnList"/>
 *         &lt;element name="workflow" type="{http://rtfm.essembeh.org/ConfigurationCore-v1}TWorkflow"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAction", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", propOrder = {
    "applyOn",
    "workflow"
})
public class TAction {

    @XmlElement(name = "apply-on", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", required = true)
    protected TApplyOnList applyOn;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v1", required = true)
    protected TWorkflow workflow;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "description", required = true)
    protected String description;

    /**
     * Obtient la valeur de la propri�t� applyOn.
     * 
     * @return
     *     possible object is
     *     {@link TApplyOnList }
     *     
     */
    public TApplyOnList getApplyOn() {
        return applyOn;
    }

    /**
     * D�finit la valeur de la propri�t� applyOn.
     * 
     * @param value
     *     allowed object is
     *     {@link TApplyOnList }
     *     
     */
    public void setApplyOn(TApplyOnList value) {
        this.applyOn = value;
    }

    /**
     * Obtient la valeur de la propri�t� workflow.
     * 
     * @return
     *     possible object is
     *     {@link TWorkflow }
     *     
     */
    public TWorkflow getWorkflow() {
        return workflow;
    }

    /**
     * D�finit la valeur de la propri�t� workflow.
     * 
     * @param value
     *     allowed object is
     *     {@link TWorkflow }
     *     
     */
    public void setWorkflow(TWorkflow value) {
        this.workflow = value;
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

    /**
     * Obtient la valeur de la propri�t� description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * D�finit la valeur de la propri�t� description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
