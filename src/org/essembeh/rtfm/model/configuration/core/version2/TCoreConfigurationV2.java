//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.25 à 12:27:08 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TCoreConfigurationV2 complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TCoreConfigurationV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substitutions" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TSubstitutionList" minOccurs="0"/>
 *         &lt;element name="filehandlers" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TFileHandlerList" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TTaskList" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TActionList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCoreConfigurationV2", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "substitutions",
    "filehandlers",
    "tasks",
    "actions"
})
public class TCoreConfigurationV2 {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected TSubstitutionList substitutions;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected TFileHandlerList filehandlers;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected TTaskList tasks;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected TActionList actions;
    @XmlAttribute(name = "name", required = true)
    protected String name;

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
     * Obtient la valeur de la propriété actions.
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
     * Définit la valeur de la propriété actions.
     * 
     * @param value
     *     allowed object is
     *     {@link TActionList }
     *     
     */
    public void setActions(TActionList value) {
        this.actions = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
