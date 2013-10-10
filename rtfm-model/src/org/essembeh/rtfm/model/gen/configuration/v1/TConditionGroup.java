
package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionGroup complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionGroup">
 *   &lt;complexContent>
 *     &lt;extension base="{http://rtfm.essembeh.org/Configuration/1}TConditionBase">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="true" type="{http://rtfm.essembeh.org/Configuration/1}TConditionTrue"/>
 *         &lt;element name="false" type="{http://rtfm.essembeh.org/Configuration/1}TConditionFalse"/>
 *         &lt;element name="attributeExists" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeExists"/>
 *         &lt;element name="attributeValueEquals" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeValueEquals"/>
 *         &lt;element name="attributeValueMatches" type="{http://rtfm.essembeh.org/Configuration/1}TConditionAttributeValueMatches"/>
 *         &lt;element name="virtualPathMatches" type="{http://rtfm.essembeh.org/Configuration/1}TConditionVirtualPathMatches"/>
 *         &lt;element name="fileOrFilder" type="{http://rtfm.essembeh.org/Configuration/1}TConditionFileOrFolder"/>
 *         &lt;element name="extension" type="{http://rtfm.essembeh.org/Configuration/1}TConditionExtension"/>
 *         &lt;element name="group" type="{http://rtfm.essembeh.org/Configuration/1}TConditionGroup"/>
 *       &lt;/choice>
 *       &lt;attribute name="logic" use="required" type="{http://rtfm.essembeh.org/Configuration/1}TGroupLogic" />
 *     &lt;/extension>
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
public class TConditionGroup
    extends TConditionBase
{

    @XmlElements({
        @XmlElement(name = "true", type = TConditionTrue.class),
        @XmlElement(name = "false", type = TConditionFalse.class),
        @XmlElement(name = "attributeExists", type = TConditionAttributeExists.class),
        @XmlElement(name = "attributeValueEquals", type = TConditionAttributeValueEquals.class),
        @XmlElement(name = "attributeValueMatches", type = TConditionAttributeValueMatches.class),
        @XmlElement(name = "virtualPathMatches", type = TConditionVirtualPathMatches.class),
        @XmlElement(name = "fileOrFilder", type = TConditionFileOrFolder.class),
        @XmlElement(name = "extension", type = TConditionExtension.class),
        @XmlElement(name = "group", type = TConditionGroup.class)
    })
    protected List<TConditionBase> trueOrFalseOrAttributeExists;
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
     * {@link TConditionTrue }
     * {@link TConditionFalse }
     * {@link TConditionAttributeExists }
     * {@link TConditionAttributeValueEquals }
     * {@link TConditionAttributeValueMatches }
     * {@link TConditionVirtualPathMatches }
     * {@link TConditionFileOrFolder }
     * {@link TConditionExtension }
     * {@link TConditionGroup }
     * 
     * 
     */
    public List<TConditionBase> getTrueOrFalseOrAttributeExists() {
        if (trueOrFalseOrAttributeExists == null) {
            trueOrFalseOrAttributeExists = new ArrayList<TConditionBase>();
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
