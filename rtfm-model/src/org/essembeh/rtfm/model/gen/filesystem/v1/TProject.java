
package org.essembeh.rtfm.model.gen.filesystem.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TProject complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TProject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resources" type="{http://rtfm.essembeh.org/filesystem/1}TResourceList" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TProject", namespace = "http://rtfm.essembeh.org/filesystem/1", propOrder = {
    "resources"
})
public class TProject {

    @XmlElement(namespace = "http://rtfm.essembeh.org/filesystem/1")
    protected TResourceList resources;
    @XmlAttribute(name = "path", required = true)
    protected String path;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Obtient la valeur de la propriété resources.
     * 
     * @return
     *     possible object is
     *     {@link TResourceList }
     *     
     */
    public TResourceList getResources() {
        return resources;
    }

    /**
     * Définit la valeur de la propriété resources.
     * 
     * @param value
     *     allowed object is
     *     {@link TResourceList }
     *     
     */
    public void setResources(TResourceList value) {
        this.resources = value;
    }

    /**
     * Obtient la valeur de la propriété path.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Définit la valeur de la propriété path.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
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
