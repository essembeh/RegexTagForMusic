
package org.essembeh.rtfm.model.configuration.core;

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
 *         &lt;element name="true" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionTrue"/>
 *         &lt;element name="group" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionGroup_XFile"/>
 *         &lt;element name="typeEquals" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionTypeEquals_XFile"/>
 *         &lt;element name="attributeExists" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionAttributeExists_XFile"/>
 *         &lt;element name="attributeValueEquals" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionAttributeValueEquals_XFile"/>
 *         &lt;element name="attributeValueMatches" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionAttributeValueMatches_XFile"/>
 *         &lt;element name="virtualPathMatches" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionVirtualPathMatches_VirtualFile"/>
 *         &lt;element name="fileOrFilder" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionFileOrFilder_VirtualFile"/>
 *         &lt;element name="extension" type="{http://rtfm.essembeh.org/ConfigurationCore}TConditionExtension_VirtualFile"/>
 *       &lt;/choice>
 *       &lt;attribute name="logic" use="required" type="{http://rtfm.essembeh.org/ConfigurationCore}TGroupLogic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionGroup_XFile", namespace = "http://rtfm.essembeh.org/ConfigurationCore", propOrder = {
    "trueOrGroupOrTypeEquals"
})
public class TConditionGroupXFile {

    @XmlElements({
        @XmlElement(name = "true", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionTrue.class),
        @XmlElement(name = "group", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionGroupXFile.class),
        @XmlElement(name = "typeEquals", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionTypeEqualsXFile.class),
        @XmlElement(name = "attributeExists", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionAttributeExistsXFile.class),
        @XmlElement(name = "attributeValueEquals", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionAttributeValueEqualsXFile.class),
        @XmlElement(name = "attributeValueMatches", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionAttributeValueMatchesXFile.class),
        @XmlElement(name = "virtualPathMatches", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionVirtualPathMatchesVirtualFile.class),
        @XmlElement(name = "fileOrFilder", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionFileOrFilderVirtualFile.class),
        @XmlElement(name = "extension", namespace = "http://rtfm.essembeh.org/ConfigurationCore", type = TConditionExtensionVirtualFile.class)
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
     * {@link TConditionExtensionVirtualFile }
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
