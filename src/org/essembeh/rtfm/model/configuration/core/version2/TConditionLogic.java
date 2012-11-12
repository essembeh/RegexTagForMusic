//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.6 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2012.12.03 à 04:47:27 PM CET 
//


package org.essembeh.rtfm.model.configuration.core.version2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TConditionLogic.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TConditionLogic">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OR"/>
 *     &lt;enumeration value="AND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TConditionLogic", namespace = "http://rtfm.essembeh.org/ConfigurationCore-v2")
@XmlEnum
public enum TConditionLogic {

    OR,
    AND;

    public String value() {
        return name();
    }

    public static TConditionLogic fromValue(String v) {
        return valueOf(v);
    }

}
