
package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionGroup complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="true" type="{http://rtfm.essembeh.org/Configuration/1}TConditionTrue"/>
 *         &lt;element name="false" type="{http://rtfm.essembeh.org/Configuration/1}TConditionTrue"/>
 *         &lt;element name="attributeExists" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeExists"/>
 *         &lt;element name="attributeValueEquals" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeValueEquals"/>
 *         &lt;element name="attributeValueMatches" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeValueMatches"/>
 *         &lt;element name="virtualPathMatches" type="{http://rtfm.essembeh.org/Configuration/1}TConditionVirtualPathMatches"/>
 *         &lt;element name="fileOrFilder" type="{http://rtfm.essembeh.org/Configuration/1}TConditionFileOrFolder"/>
 *         &lt;element name="extension" type="{http://rtfm.essembeh.org/Configuration/1}TConditionExtension"/>
 *         &lt;element name="group" type="{http://rtfm.essembeh.org/Configuration/1}TConditionGroup"/>
 *       &lt;/choice>
 *       &lt;attribute name="logic" use="required" type="{http://rtfm.essembeh.org/Configuration/1}TGroupLogic" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionGroup", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = {
    "trueOrFalseOrAttributeExists"
})
public class TConditionGroup {

    @XmlElementRefs({
        @XmlElementRef(name = "extension", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "false", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "attributeValueMatches", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "virtualPathMatches", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "true", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "group", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "attributeValueEquals", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "fileOrFilder", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "attributeExists", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> trueOrFalseOrAttributeExists;
    @XmlAttribute(name = "logic", required = true)
    protected TGroupLogic logic;

    /**
     * Gets the value of the trueOrFalseOrAttributeExists property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trueOrFalseOrAttributeExists property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrueOrFalseOrAttributeExists().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TConditionExtension }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionTrue }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionAttributeValueMatches }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionVirtualPathMatches }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionTrue }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionAttributeValueEquals }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionGroup }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionAttributeExists }{@code >}
     * {@link JAXBElement }{@code <}{@link TConditionFileOrFolder }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getTrueOrFalseOrAttributeExists() {
        if (trueOrFalseOrAttributeExists == null) {
            trueOrFalseOrAttributeExists = new ArrayList<JAXBElement<?>>();
        }
        return this.trueOrFalseOrAttributeExists;
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
