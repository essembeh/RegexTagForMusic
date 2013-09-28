
package org.essembeh.rtfm.model.configuration.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TCoreConfigurationV2 complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TCoreConfigurationV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substitutions" type="{http://rtfm.essembeh.org/ConfigurationCore}TSubstitutionList" minOccurs="0"/>
 *         &lt;element name="filehandlers" type="{http://rtfm.essembeh.org/ConfigurationCore}TFileHandlerList" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://rtfm.essembeh.org/ConfigurationCore}TTaskList" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://rtfm.essembeh.org/ConfigurationCore}TActionList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCoreConfigurationV2", namespace = "http://rtfm.essembeh.org/ConfigurationCore", propOrder = {
    "substitutions",
    "filehandlers",
    "tasks",
    "actions"
})
public class TCoreConfigurationV2 {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore")
    protected TSubstitutionList substitutions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore")
    protected TFileHandlerList filehandlers;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore")
    protected TTaskList tasks;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore")
    protected TActionList actions;

    /**
     * Obtient la valeur de la propri�t� substitutions.
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
     * D�finit la valeur de la propri�t� substitutions.
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
     * Obtient la valeur de la propri�t� filehandlers.
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
     * D�finit la valeur de la propri�t� filehandlers.
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
     * Obtient la valeur de la propri�t� tasks.
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
     * D�finit la valeur de la propri�t� tasks.
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
     * Obtient la valeur de la propri�t� actions.
     * 
     * @return
     *     possible object is
     *     {@link TActionList }
     *     
     */
    public TActionList getActions() {
        return actions;
    }

    /**
     * D�finit la valeur de la propri�t� actions.
     * 
     * @param value
     *     allowed object is
     *     {@link TActionList }
     *     
     */
    public void setActions(TActionList value) {
        this.actions = value;
    }

}
