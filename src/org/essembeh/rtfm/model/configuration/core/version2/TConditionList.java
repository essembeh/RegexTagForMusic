//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2012.12.03 à 04:47:27 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionList complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TConditionList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="virtualpath" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionOnVirtualPath" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionOnType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="logic" type="{http://rtfm.essembeh.org/ConfigurationCore-v2}TConditionLogic" default="AND" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TConditionList", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2", propOrder = {
    "virtualpath",
    "type"
})
public class TConditionList {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected List<TConditionOnVirtualPath> virtualpath;
    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
    protected List<TConditionOnType> type;
    @XmlAttribute(name = "logic")
    protected TConditionLogic logic;

    /**
     * Gets the value of the virtualpath property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtualpath property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtualpath().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TConditionOnVirtualPath }
     * 
     * 
     */
    public List<TConditionOnVirtualPath> getVirtualpath() {
        if (virtualpath == null) {
            virtualpath = new ArrayList<TConditionOnVirtualPath>();
        }
        return this.virtualpath;
    }

    /**
     * Gets the value of the type property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the type property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TConditionOnType }
     * 
     * 
     */
    public List<TConditionOnType> getType() {
        if (type == null) {
            type = new ArrayList<TConditionOnType>();
        }
        return this.type;
    }

    /**
     * Obtient la valeur de la propriété logic.
     * 
     * @return
     *     possible object is
     *     {@link TConditionLogic }
     *     
     */
    public TConditionLogic getLogic() {
        if (logic == null) {
            return TConditionLogic.AND;
        } else {
            return logic;
        }
    }

    /**
     * Définit la valeur de la propriété logic.
     * 
     * @param value
     *     allowed object is
     *     {@link TConditionLogic }
     *     
     */
    public void setLogic(TConditionLogic value) {
        this.logic = value;
    }

}
