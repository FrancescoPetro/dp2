//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:21 AM CET 
//


package it.polito.dp2.NFV.sol3.service.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}host" maxOccurs="unbounded"/>
 *         &lt;element ref="{}performances"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "host",
    "performances"
})
@XmlRootElement(name = "in")
public class In {

    @XmlElement(required = true)
    protected List<Host> host;
    @XmlElement(required = true)
    protected Performances performances;

    /**
     * Gets the value of the host property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the host property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHost().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Host }
     * 
     * 
     */
    public List<Host> getHost() {
        if (host == null) {
            host = new ArrayList<Host>();
        }
        return this.host;
    }

    /**
     * Recupera il valore della proprietà performances.
     * 
     * @return
     *     possible object is
     *     {@link Performances }
     *     
     */
    public Performances getPerformances() {
        return performances;
    }

    /**
     * Imposta il valore della proprietà performances.
     * 
     * @param value
     *     allowed object is
     *     {@link Performances }
     *     
     */
    public void setPerformances(Performances value) {
        this.performances = value;
    }

}
