
package org.essembeh.rtfm.model.library.version2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TLibraryV2 complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TLibraryV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rootFolder" type="{http://rtfm.essembeh.org/Library-v2}TRootFolder"/>
 *         &lt;element name="file" type="{http://rtfm.essembeh.org/Library-v2}TFile" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLibraryV2", namespace = "http://rtfm.essembeh.org/Library-v2", propOrder = {
    "rootFolder",
    "file"
})
public class TLibraryV2 {

    @XmlElement(namespace = "http://rtfm.essembeh.org/Library-v2", required = true)
    protected TRootFolder rootFolder;
    @XmlElement(namespace = "http://rtfm.essembeh.org/Library-v2")
    protected List<TFile> file;

    /**
     * Obtient la valeur de la propriété rootFolder.
     * 
     * @return
     *     possible object is
     *     {@link TRootFolder }
     *     
     */
    public TRootFolder getRootFolder() {
        return rootFolder;
    }

    /**
     * Définit la valeur de la propriété rootFolder.
     * 
     * @param value
     *     allowed object is
     *     {@link TRootFolder }
     *     
     */
    public void setRootFolder(TRootFolder value) {
        this.rootFolder = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the file property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TFile }
     * 
     * 
     */
    public List<TFile> getFile() {
        if (file == null) {
            file = new ArrayList<TFile>();
        }
        return this.file;
    }

}
