
package org.essembeh.rtfm.model.configuration.core;

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
 *         &lt;element name="conditions" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionGroup_XFile"/>
 *         &lt;element name="workflow" type="{http://rtfm.essembeh.org/ConfigurationCore}TWorkflow"/>
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
@XmlType(name = "TAction", namespace = "http://rtfm.essembeh.org/ConfigurationCore", propOrder = {
    "conditions",
    "workflow"
})
public class TAction {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore", required = true)
    protected TConditionGroupXFile conditions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore", required = true)
    protected TWorkflow workflow;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "description", required = true)
    protected String description;

    /**
     * Obtient la valeur de la propriété conditions.
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
     * Définit la valeur de la propriété conditions.
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
