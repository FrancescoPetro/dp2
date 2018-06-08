//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.02.23 alle 10:22:41 AM CET 
//


package it.polito.dp2.NFV.sol3.client2.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="host1" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="host2" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="latency" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="throughput" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "connectionPerformance")
public class ConnectionPerformance {

    @XmlAttribute(name = "host1", required = true)
    protected String host1;
    @XmlAttribute(name = "host2", required = true)
    protected String host2;
    @XmlAttribute(name = "latency", required = true)
    protected int latency;
    @XmlAttribute(name = "throughput", required = true)
    protected float throughput;

    /**
     * Recupera il valore della proprietà host1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost1() {
        return host1;
    }

    /**
     * Imposta il valore della proprietà host1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost1(String value) {
        this.host1 = value;
    }

    /**
     * Recupera il valore della proprietà host2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost2() {
        return host2;
    }

    /**
     * Imposta il valore della proprietà host2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost2(String value) {
        this.host2 = value;
    }

    /**
     * Recupera il valore della proprietà latency.
     * 
     */
    public int getLatency() {
        return latency;
    }

    /**
     * Imposta il valore della proprietà latency.
     * 
     */
    public void setLatency(int value) {
        this.latency = value;
    }

    /**
     * Recupera il valore della proprietà throughput.
     * 
     */
    public float getThroughput() {
        return throughput;
    }

    /**
     * Imposta il valore della proprietà throughput.
     * 
     */
    public void setThroughput(float value) {
        this.throughput = value;
    }

}
