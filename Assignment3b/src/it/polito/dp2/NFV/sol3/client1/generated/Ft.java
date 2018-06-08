//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:42 AM CET 
//


package it.polito.dp2.NFV.sol3.client1.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ft.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ft">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="cache"/>
 *     &lt;enumeration value="dpi"/>
 *     &lt;enumeration value="fw"/>
 *     &lt;enumeration value="mail_client"/>
 *     &lt;enumeration value="mail_server"/>
 *     &lt;enumeration value="nat"/>
 *     &lt;enumeration value="spam"/>
 *     &lt;enumeration value="vpn"/>
 *     &lt;enumeration value="web_client"/>
 *     &lt;enumeration value="web_server"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ft")
@XmlEnum
public enum Ft {

    @XmlEnumValue("cache")
    CACHE("cache"),
    @XmlEnumValue("dpi")
    DPI("dpi"),
    @XmlEnumValue("fw")
    FW("fw"),
    @XmlEnumValue("mail_client")
    MAIL_CLIENT("mail_client"),
    @XmlEnumValue("mail_server")
    MAIL_SERVER("mail_server"),
    @XmlEnumValue("nat")
    NAT("nat"),
    @XmlEnumValue("spam")
    SPAM("spam"),
    @XmlEnumValue("vpn")
    VPN("vpn"),
    @XmlEnumValue("web_client")
    WEB_CLIENT("web_client"),
    @XmlEnumValue("web_server")
    WEB_SERVER("web_server");
    private final String value;

    Ft(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Ft fromValue(String v) {
        for (Ft c: Ft.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
