package org.essembeh.rtfm.model.gen.filesystem.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour TResourceList complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TResourceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resource" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="attributes" type="{http://rtfm.essembeh.org/filesystem/1}TAttributeList" minOccurs="0"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="virtualpath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TResourceList", namespace = "http://rtfm.essembeh.org/filesystem/1", propOrder = { "resource" })
public class TResourceList {

	@XmlElement(namespace = "http://rtfm.essembeh.org/filesystem/1")
	protected List<TResourceList.Resource> resource;

	/**
	 * Gets the value of the resource property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
	 * not a <CODE>set</CODE> method for the resource property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getResource().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TResourceList.Resource }
	 * 
	 * 
	 */
	public List<TResourceList.Resource> getResource() {
		if (resource == null) {
			resource = new ArrayList<TResourceList.Resource>();
		}
		return this.resource;
	}

	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 * 
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="attributes" type="{http://rtfm.essembeh.org/filesystem/1}TAttributeList" minOccurs="0"/>
	 *       &lt;/sequence>
	 *       &lt;attribute name="virtualpath" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "attributes" })
	public static class Resource {

		@XmlElement(namespace = "http://rtfm.essembeh.org/filesystem/1")
		protected TAttributeList attributes;
		@XmlAttribute(name = "virtualpath", required = true)
		protected String virtualpath;

		/**
		 * Obtient la valeur de la propriété attributes.
		 * 
		 * @return possible object is {@link TAttributeList }
		 * 
		 */
		public TAttributeList getAttributes() {
			return attributes;
		}

		/**
		 * Définit la valeur de la propriété attributes.
		 * 
		 * @param value
		 *            allowed object is {@link TAttributeList }
		 * 
		 */
		public void setAttributes(TAttributeList value) {
			this.attributes = value;
		}

		/**
		 * Obtient la valeur de la propriété virtualpath.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getVirtualpath() {
			return virtualpath;
		}

		/**
		 * Définit la valeur de la propriété virtualpath.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setVirtualpath(String value) {
			this.virtualpath = value;
		}

	}

}
