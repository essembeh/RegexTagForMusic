
package org.essembeh.rtfm.model.configuration.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TGroupLogic.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TGroupLogic">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OR"/>
 *     &lt;enumeration value="AND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TGroupLogic", namespace = "http://rtfm.essembeh.org/ConfigurationCore")
@XmlEnum
public enum TGroupLogic {

    OR,
    AND;

    public String value() {
        return name();
    }

    public static TGroupLogic fromValue(String v) {
        return valueOf(v);
    }

}
