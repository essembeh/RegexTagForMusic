//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.25 à 12:27:08 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionGroup_VirtualFile complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionGroup_VirtualFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="true" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionTrue"/>
 *         &lt;element name="group" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionGroup_VirtualFile"/>
 *         &lt;element name="virtualPathMatches" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionVirtualPathMatches_VirtualFile"/>
 *         &lt;element name="fileOrFilder" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionFileOrFilder_VirtualFile"/>
 *         &lt;element name="extension" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionExtension_VirtualFile"/>
 *       &lt;/choice>
 *       &lt;attribute name="logic" use="required" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TGroupLogic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionGroup_VirtualFile", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "trueOrGroupOrVirtualPathMatches"
})
public class TConditionGroupVirtualFile {

    @XmlElements({
        @XmlElement(name = "true", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionTrue.class),
        @XmlElement(name = "group", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionGroupVirtualFile.class),
        @XmlElement(name = "virtualPathMatches", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionVirtualPathMatchesVirtualFile.class),
        @XmlElement(name = "fileOrFilder", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionFileOrFilderVirtualFile.class),
        @XmlElement(name = "extension", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionExtensionVirtualFile.class)
    })
    protected List<Object> trueOrGroupOrVirtualPathMatches;
    @XmlAttribute(name = "logic", required = true)
    protected TGroupLogic logic;

    /**
     * Gets the value of the trueOrGroupOrVirtualPathMatches property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trueOrGroupOrVirtualPathMatches property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrueOrGroupOrVirtualPathMatches().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TConditionTrue }
     * {@link TConditionGroupVirtualFile }
     * {@link TConditionVirtualPathMatchesVirtualFile }
     * {@link TConditionFileOrFilderVirtualFile }
     * {@link TConditionExtensionVirtualFile }
     * 
     * 
     */
    public List<Object> getTrueOrGroupOrVirtualPathMatches() {
        if (trueOrGroupOrVirtualPathMatches == null) {
            trueOrGroupOrVirtualPathMatches = new ArrayList<Object>();
        }
        return this.trueOrGroupOrVirtualPathMatches;
    }

    /**
     * Obtient la valeur de la propriété logic.
     * 
     * @return
     *     possible object is
     *     {@link TGroupLogic }
     *     
     */
    public TGroupLogic getLogic() {
        return logic;
    }

    /**
     * Définit la valeur de la propriété logic.
     * 
     * @param value
     *     allowed object is
     *     {@link TGroupLogic }
     *     
     */
    public void setLogic(TGroupLogic value) {
        this.logic = value;
    }

}
