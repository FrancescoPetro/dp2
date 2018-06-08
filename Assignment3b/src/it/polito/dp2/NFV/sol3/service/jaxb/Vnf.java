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
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="functionalType" type="{}ft"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="requiredMemory" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="requiredDiskStorage" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "functionalType"
})
@XmlRootElement(name = "vnf")
public class Vnf {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected Ft functionalType;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "requiredMemory", required = true)
    protected int requiredMemory;
    @XmlAttribute(name = "requiredDiskStorage", required = true)
    protected int requiredDiskStorage;

    /**
     * Recupera il valore della proprietà functionalType.
     * 
     * @return
     *     possible object is
     *     {@link Ft }
     *     
     */
    public Ft getFunctionalType() {
        return functionalType;
    }

    /**
     * Imposta il valore della proprietà functionalType.
     * 
     * @param value
     *     allowed object is
     *     {@link Ft }
     *     
     */
    public void setFunctionalType(Ft value) {
        this.functionalType = value;
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
     * Recupera il valore della proprietà requiredMemory.
     * 
     */
    public int getRequiredMemory() {
        return requiredMemory;
    }

    /**
     * Imposta il valore della proprietà requiredMemory.
     * 
     */
    public void setRequiredMemory(int value) {
        this.requiredMemory = value;
    }

    /**
     * Recupera il valore della proprietà requiredDiskStorage.
     * 
     */
    public int getRequiredDiskStorage() {
        return requiredDiskStorage;
    }

    /**
     * Imposta il valore della proprietà requiredDiskStorage.
     * 
     */
    public void setRequiredDiskStorage(int value) {
        this.requiredDiskStorage = value;
    }

}
