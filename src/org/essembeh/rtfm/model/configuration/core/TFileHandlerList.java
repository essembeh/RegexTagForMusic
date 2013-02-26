
package org.essembeh.rtfm.model.configuration.core;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TFileHandlerList complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TFileHandlerList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="filehandler" type="{http://rtfm.essembeh.org/ConfigurationCore}TFileHandler" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFileHandlerList", namespace = "http://rtfm.essembeh.org/ConfigurationCore", propOrder = {
    "filehandler"
})
public class TFileHandlerList {

    @XmlElement(namespace = "http://rtfm.essembeh.org/ConfigurationCore")
    protected List<TFileHandler> filehandler;

    /**
     * Gets the value of the filehandler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filehandler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilehandler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TFileHandler }
     * 
     * 
     */
    public List<TFileHandler> getFilehandler() {
        if (filehandler == null) {
            filehandler = new ArrayList<TFileHandler>();
        }
        return this.filehandler;
    }

}
