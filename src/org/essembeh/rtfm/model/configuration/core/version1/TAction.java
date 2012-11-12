//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2012.12.03 à 04:47:27 PM CET 
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
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété applyOn.
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
     * Définit la valeur de la propriété applyOn.
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
     * Obtient la valeur de la propriété workflow.
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
     * Définit la valeur de la propriété workflow.
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

    /**
     * Obtient la valeur de la propriété description.
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
     * Définit la valeur de la propriété description.
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
