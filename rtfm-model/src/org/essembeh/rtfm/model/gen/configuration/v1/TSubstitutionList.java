package org.essembeh.rtfm.model.gen.configuration.v1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour TSubstitutionList complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TSubstitutionList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="substitution" type="{http://rtfm.essembeh.org/Configuration/1}TSubstitution" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSubstitutionList", namespace = "http://rtfm.essembeh.org/Configuration/1", propOrder = { "substitution" })
public class TSubstitutionList {

	protected List<TSubstitution> substitution;

	/**
	 * Gets the value of the substitution property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is
	 * not a <CODE>set</CODE> method for the substitution property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSubstitution().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TSubstitution }
	 * 
	 * 
	 */
	public List<TSubstitution> getSubstitution() {
		if (substitution == null) {
			substitution = new ArrayList<TSubstitution>();
		}
		return this.substitution;
	}

}
