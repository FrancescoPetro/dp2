//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:21 AM CET 
//


package it.polito.dp2.NFV.sol3.service.jaxb;

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
 *         &lt;element ref="{}nffgs"/>
 *         &lt;element ref="{}in"/>
 *         &lt;element ref="{}vnfCatalog"/>
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
    "nffgs",
    "in",
    "vnfCatalog"
})
@XmlRootElement(name = "nfv")
public class Nfv {

    @XmlElement(required = true)
    protected Nffgs nffgs;
    @XmlElement(required = true)
    protected In in;
    @XmlElement(required = true)
    protected VnfCatalog vnfCatalog;

    /**
     * Recupera il valore della proprietà nffgs.
     * 
     * @return
     *     possible object is
     *     {@link Nffgs }
     *     
     */
    public Nffgs getNffgs() {
        return nffgs;
    }

    /**
     * Imposta il valore della proprietà nffgs.
     * 
     * @param value
     *     allowed object is
     *     {@link Nffgs }
     *     
     */
    public void setNffgs(Nffgs value) {
        this.nffgs = value;
    }

    /**
     * Recupera il valore della proprietà in.
     * 
     * @return
     *     possible object is
     *     {@link In }
     *     
     */
    public In getIn() {
        return in;
    }

    /**
     * Imposta il valore della proprietà in.
     * 
     * @param value
     *     allowed object is
     *     {@link In }
     *     
     */
    public void setIn(In value) {
        this.in = value;
    }

    /**
     * Recupera il valore della proprietà vnfCatalog.
     * 
     * @return
     *     possible object is
     *     {@link VnfCatalog }
     *     
     */
    public VnfCatalog getVnfCatalog() {
        return vnfCatalog;
    }

    /**
     * Imposta il valore della proprietà vnfCatalog.
     * 
     * @param value
     *     allowed object is
     *     {@link VnfCatalog }
     *     
     */
    public void setVnfCatalog(VnfCatalog value) {
        this.vnfCatalog = value;
    }

}
