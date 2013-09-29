
package org.essembeh.rtfm.model.gen.configuration.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TRegexAttribute complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TRegexAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pattern" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="group" type="{http://www.w3.org/2001/XMLSchema}int" default="1" />
 *       &lt;attribute name="optional" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRegexAttribute", namespace = "http://rtfm.essembeh.org/Configuration/1")
public class TRegexAttribute {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "pattern", required = true)
    protected String pattern;
    @XmlAttribute(name = "group")
    protected Integer group;
    @XmlAttribute(name = "optional")
    protected Boolean optional;
    @XmlAttribute(name = "hidden")
    protected Boolean hidden;

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

    /**
     * Obtient la valeur de la propriété pattern.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Définit la valeur de la propriété pattern.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

    /**
     * Obtient la valeur de la propriété group.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getGroup() {
        if (group == null) {
            return  1;
        } else {
            return group;
        }
    }

    /**
     * Définit la valeur de la propriété group.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGroup(Integer value) {
        this.group = value;
    }

    /**
     * Obtient la valeur de la propriété optional.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isOptional() {
        if (optional == null) {
            return false;
        } else {
            return optional;
        }
    }

    /**
     * Définit la valeur de la propriété optional.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptional(Boolean value) {
        this.optional = value;
    }

    /**
     * Obtient la valeur de la propriété hidden.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isHidden() {
        if (hidden == null) {
            return false;
        } else {
            return hidden;
        }
    }

    /**
     * Définit la valeur de la propriété hidden.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHidden(Boolean value) {
        this.hidden = value;
    }

}
