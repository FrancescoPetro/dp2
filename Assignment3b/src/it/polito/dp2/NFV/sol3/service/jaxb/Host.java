//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:21 AM CET 
//


package it.polito.dp2.NFV.sol3.service.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element ref="{}hostNodes"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="availableMemory" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="availableStorage" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="maxVNF" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hostNodes"
})
@XmlRootElement(name = "host")
public class Host {

    @XmlElement(required = true)
    protected HostNodes hostNodes;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "availableMemory", required = true)
    protected int availableMemory;
    @XmlAttribute(name = "availableStorage", required = true)
    protected int availableStorage;
    @XmlAttribute(name = "maxVNF", required = true)
    protected int maxVNF;

    /**
     * Recupera il valore della proprietà hostNodes.
     * 
     * @return
     *     possible object is
     *     {@link HostNodes }
     *     
     */
    public HostNodes getHostNodes() {
        return hostNodes;
    }

    /**
     * Imposta il valore della proprietà hostNodes.
     * 
     * @param value
     *     allowed object is
     *     {@link HostNodes }
     *     
     */
    public void setHostNodes(HostNodes value) {
        this.hostNodes = value;
    }

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà availableMemory.
     * 
     */
    public int getAvailableMemory() {
        return availableMemory;
    }

    /**
     * Imposta il valore della proprietà availableMemory.
     * 
     */
    public void setAvailableMemory(int value) {
        this.availableMemory = value;
    }

    /**
     * Recupera il valore della proprietà availableStorage.
     * 
     */
    public int getAvailableStorage() {
        return availableStorage;
    }

    /**
     * Imposta il valore della proprietà availableStorage.
     * 
     */
    public void setAvailableStorage(int value) {
        this.availableStorage = value;
    }

    /**
     * Recupera il valore della proprietà maxVNF.
     * 
     */
    public int getMaxVNF() {
        return maxVNF;
    }

    /**
     * Imposta il valore della proprietà maxVNF.
     * 
     */
    public void setMaxVNF(int value) {
        this.maxVNF = value;
    }

}
