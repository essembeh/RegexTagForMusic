package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour TTask complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="property" type="{http://rtfm.essembeh.org/Configuration/1}TProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="classname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TTask", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = { "property" })
public class TTask {

	protected List<TProperty> property;
	@XmlAttribute(name = "id", required = true)
	protected String id;
	@XmlAttribute(name = "classname", required = true)
	protected String classname;

	/**
	 * Gets the value of the property property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
	 * not a <CODE>set</CODE> method for the property property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getProperty().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TProperty }
	 * 
	 * 
	 */
	public List<TProperty> getProperty() {
		if (property == null) {
			property = new ArrayList<TProperty>();
		}
		return this.property;
	}

	/**
	 * Obtient la valeur de la propriété id.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété classname.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClassname() {
		return classname;
	}

	/**
	 * Définit la valeur de la propriété classname.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClassname(String value) {
		this.classname = value;
	}

}
