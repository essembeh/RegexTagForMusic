//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2013.01.25 � 12:27:08 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

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
 *         &lt;element name="conditions" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionGroup_XFile"/>
 *         &lt;element name="workflow" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TWorkflow"/>
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
@XmlType(name = "TAction", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "conditions",
    "workflow"
})
public class TAction {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", required = true)
    protected TConditionGroupXFile conditions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", required = true)
    protected TWorkflow workflow;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "description", required = true)
    protected String description;

    /**
     * Obtient la valeur de la propri�t� conditions.
     * 
     * @return
     *     possible object is
     *     {@link TConditionGroupXFile }
     *     
     */
    public TConditionGroupXFile getConditions() {
        return conditions;
    }

    /**
     * D�finit la valeur de la propri�t� conditions.
     * 
     * @param value
     *     allowed object is
     *     {@link TConditionGroupXFile }
     *     
     */
    public void setConditions(TConditionGroupXFile value) {
        this.conditions = value;
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
