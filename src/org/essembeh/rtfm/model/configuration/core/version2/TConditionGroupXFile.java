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
 * <p>Classe Java pour TConditionGroup_XFile complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionGroup_XFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="true" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionTrue"/>
 *         &lt;element name="group" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionGroup_XFile"/>
 *         &lt;element name="typeEquals" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionTypeEquals_XFile"/>
 *         &lt;element name="attributeExists" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionAttributeExists_XFile"/>
 *         &lt;element name="attributeValueEquals" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionAttributeValueEquals_XFile"/>
 *         &lt;element name="attributeValueMatches" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionAttributeValueMatches_XFile"/>
 *         &lt;element name="virtualPathMatches" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionVirtualPathMatches_VirtualFile"/>
 *         &lt;element name="fileOrFilder" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionFileOrFilder_VirtualFile"/>
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
@XmlType(name = "TConditionGroup_XFile", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "trueOrGroupOrTypeEquals"
})
public class TConditionGroupXFile {

    @XmlElements({
        @XmlElement(name = "true", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionTrue.class),
        @XmlElement(name = "group", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionGroupXFile.class),
        @XmlElement(name = "typeEquals", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionTypeEqualsXFile.class),
        @XmlElement(name = "attributeExists", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionAttributeExistsXFile.class),
        @XmlElement(name = "attributeValueEquals", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionAttributeValueEqualsXFile.class),
        @XmlElement(name = "attributeValueMatches", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionAttributeValueMatchesXFile.class),
        @XmlElement(name = "virtualPathMatches", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionVirtualPathMatchesVirtualFile.class),
        @XmlElement(name = "fileOrFilder", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", type = TConditionFileOrFilderVirtualFile.class)
    })
    protected List<Object> trueOrGroupOrTypeEquals;
    @XmlAttribute(name = "logic", required = true)
    protected TGroupLogic logic;

    /**
     * Gets the value of the trueOrGroupOrTypeEquals property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trueOrGroupOrTypeEquals property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrueOrGroupOrTypeEquals().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TConditionTrue }
     * {@link TConditionGroupXFile }
     * {@link TConditionTypeEqualsXFile }
     * {@link TConditionAttributeExistsXFile }
     * {@link TConditionAttributeValueEqualsXFile }
     * {@link TConditionAttributeValueMatchesXFile }
     * {@link TConditionVirtualPathMatchesVirtualFile }
     * {@link TConditionFileOrFilderVirtualFile }
     * 
     * 
     */
    public List<Object> getTrueOrGroupOrTypeEquals() {
        if (trueOrGroupOrTypeEquals == null) {
            trueOrGroupOrTypeEquals = new ArrayList<Object>();
        }
        return this.trueOrGroupOrTypeEquals;
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
