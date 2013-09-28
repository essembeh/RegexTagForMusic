package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour TAttributeList complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TAttributeList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribute" type="{http://rtfm.essembeh.org/Configuration/1}TFixedAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="regex-attribute" type="{http://rtfm.essembeh.org/Configuration/1}TRegexAttribute" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAttributeList", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = { "attribute", "regexAttribute" })
public class TAttributeList {

	protected List<TFixedAttribute> attribute;
	@XmlElement(name = "regex-attribute")
	protected List<TRegexAttribute> regexAttribute;

	/**
	 * Gets the value of the attribute property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
	 * not a <CODE>set</CODE> method for the attribute property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAttribute().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TFixedAttribute }
	 * 
	 * 
	 */
	public List<TFixedAttribute> getAttribute() {
		if (attribute == null) {
			attribute = new ArrayList<TFixedAttribute>();
		}
		return this.attribute;
	}

	/**
	 * Gets the value of the regexAttribute property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
	 * not a <CODE>set</CODE> method for the regexAttribute property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getRegexAttribute().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TRegexAttribute }
	 * 
	 * 
	 */
	public List<TRegexAttribute> getRegexAttribute() {
		if (regexAttribute == null) {
			regexAttribute = new ArrayList<TRegexAttribute>();
		}
		return this.regexAttribute;
	}

}
