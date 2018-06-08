//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:42 AM CET 
//


package it.polito.dp2.NFV.sol3.client1.generated;

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
 *         &lt;element name="srcNode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dstNode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="minThroughput" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="maxLatency" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "srcNode",
    "dstNode"
})
@XmlRootElement(name = "link")
public class Link {

    @XmlElement(required = true)
    protected String srcNode;
    @XmlElement(required = true)
    protected String dstNode;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "minThroughput")
    protected Float minThroughput;
    @XmlAttribute(name = "maxLatency")
    protected Integer maxLatency;

    /**
     * Recupera il valore della proprietà srcNode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcNode() {
        return srcNode;
    }

    /**
     * Imposta il valore della proprietà srcNode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcNode(String value) {
        this.srcNode = value;
    }

    /**
     * Recupera il valore della proprietà dstNode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDstNode() {
        return dstNode;
    }

    /**
     * Imposta il valore della proprietà dstNode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDstNode(String value) {
        this.dstNode = value;
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
     * Recupera il valore della proprietà minThroughput.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMinThroughput() {
        return minThroughput;
    }

    /**
     * Imposta il valore della proprietà minThroughput.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMinThroughput(Float value) {
        this.minThroughput = value;
    }

    /**
     * Recupera il valore della proprietà maxLatency.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxLatency() {
        return maxLatency;
    }

    /**
     * Imposta il valore della proprietà maxLatency.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxLatency(Integer value) {
        this.maxLatency = value;
    }

}
