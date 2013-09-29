
package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConfiguration complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConfiguration">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="properties">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="property" type="{http://rtfm.essembeh.org/Configuration/1}TProperty" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="substitutions" type="{http://rtfm.essembeh.org/Configuration/1}TSubstitutionList" minOccurs="0"/>
 *         &lt;element name="filehandlers" type="{http://rtfm.essembeh.org/Configuration/1}TFileHandlerList" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://rtfm.essembeh.org/Configuration/1}TTaskList" minOccurs="0"/>
 *         &lt;element name="workflows" type="{http://rtfm.essembeh.org/Configuration/1}TWorkflowList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConfiguration", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = {
    "properties",
    "substitutions",
    "filehandlers",
    "tasks",
    "workflows"
})
public class TConfiguration {

    @XmlElement(required = true)
    protected TConfiguration.Properties properties;
    protected TSubstitutionList substitutions;
    protected TFileHandlerList filehandlers;
    protected TTaskList tasks;
    protected TWorkflowList workflows;

    /**
     * Obtient la valeur de la propriété properties.
     * 
     * @return
     *     possible object is
     *     {@link TConfiguration.Properties }
     *     
     */
    public TConfiguration.Properties getProperties() {
        return properties;
    }

    /**
     * Définit la valeur de la propriété properties.
     * 
     * @param value
     *     allowed object is
     *     {@link TConfiguration.Properties }
     *     
     */
    public void setProperties(TConfiguration.Properties value) {
        this.properties = value;
    }

    /**
     * Obtient la valeur de la propriété substitutions.
     * 
     * @return
     *     possible object is
     *     {@link TSubstitutionList }
     *     
     */
    public TSubstitutionList getSubstitutions() {
        return substitutions;
    }

    /**
     * Définit la valeur de la propriété substitutions.
     * 
     * @param value
     *     allowed object is
     *     {@link TSubstitutionList }
     *     
     */
    public void setSubstitutions(TSubstitutionList value) {
        this.substitutions = value;
    }

    /**
     * Obtient la valeur de la propriété filehandlers.
     * 
     * @return
     *     possible object is
     *     {@link TFileHandlerList }
     *     
     */
    public TFileHandlerList getFilehandlers() {
        return filehandlers;
    }

    /**
     * Définit la valeur de la propriété filehandlers.
     * 
     * @param value
     *     allowed object is
     *     {@link TFileHandlerList }
     *     
     */
    public void setFilehandlers(TFileHandlerList value) {
        this.filehandlers = value;
    }

    /**
     * Obtient la valeur de la propriété tasks.
     * 
     * @return
     *     possible object is
     *     {@link TTaskList }
     *     
     */
    public TTaskList getTasks() {
        return tasks;
    }

    /**
     * Définit la valeur de la propriété tasks.
     * 
     * @param value
     *     allowed object is
     *     {@link TTaskList }
     *     
     */
    public void setTasks(TTaskList value) {
        this.tasks = value;
    }

    /**
     * Obtient la valeur de la propriété workflows.
     * 
     * @return
     *     possible object is
     *     {@link TWorkflowList }
     *     
     */
    public TWorkflowList getWorkflows() {
        return workflows;
    }

    /**
     * Définit la valeur de la propriété workflows.
     * 
     * @param value
     *     allowed object is
     *     {@link TWorkflowList }
     *     
     */
    public void setWorkflows(TWorkflowList value) {
        this.workflows = value;
    }


    /**
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="property" type="{http://rtfm.essembeh.org/Configuration/1}TProperty" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "property"
    })
    public static class Properties {

        protected List<TProperty> property;

        /**
         * Gets the value of the property property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the property property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProperty().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link TProperty }
         * 
         * 
         */
        public List<TProperty> getProperty() {
            if (property == null) {
                property = new ArrayList<TProperty>();
            }
            return this.property;
        }

    }

}
