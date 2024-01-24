//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.24 alle 01:08:53 PM CET 
//


package entities.pojos

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per QualifyingResultType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="QualifyingResultType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Driver" type="{http://ergast.com/mrd/1.5}DriverType"/&gt;
 *           &lt;element name="DriverIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Constructor" type="{http://ergast.com/mrd/1.5}ConstructorType"/&gt;
 *           &lt;element name="ConstructorIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="Q1" type="{http://ergast.com/mrd/1.5}DurationType"/&gt;
 *         &lt;element name="Q2" type="{http://ergast.com/mrd/1.5}DurationType" minOccurs="0"/&gt;
 *         &lt;element name="Q3" type="{http://ergast.com/mrd/1.5}DurationType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QualifyingResultType", propOrder = {
    "driver",
    "driverIdRef",
    "constructor",
    "constructorIdRef",
    "q1",
    "q2",
    "q3"
})
public class QualifyingResultType {

    @XmlElement(name = "Driver")
    protected DriverType driver;
    @XmlElement(name = "DriverIdRef")
    protected String driverIdRef;
    @XmlElement(name = "Constructor")
    protected ConstructorType constructor;
    @XmlElement(name = "ConstructorIdRef")
    protected String constructorIdRef;
    @XmlElement(name = "Q1", required = true)
    protected DurationType q1;
    @XmlElement(name = "Q2")
    protected DurationType q2;
    @XmlElement(name = "Q3")
    protected DurationType q3;
    @XmlAttribute(name = "number")
    protected BigInteger number;
    @XmlAttribute(name = "position", required = true)
    protected BigInteger position;

    /**
     * Recupera il valore della propriet� driver.
     * 
     * @return
     *     possible object is
     *     {@link DriverType }
     *     
     */
    public DriverType getDriver() {
        return driver;
    }

    /**
     * Imposta il valore della propriet� driver.
     * 
     * @param value
     *     allowed object is
     *     {@link DriverType }
     *     
     */
    public void setDriver(DriverType value) {
        this.driver = value;
    }

    /**
     * Recupera il valore della propriet� driverIdRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverIdRef() {
        return driverIdRef;
    }

    /**
     * Imposta il valore della propriet� driverIdRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverIdRef(String value) {
        this.driverIdRef = value;
    }

    /**
     * Recupera il valore della propriet� constructor.
     * 
     * @return
     *     possible object is
     *     {@link ConstructorType }
     *     
     */
    public ConstructorType getConstructor() {
        return constructor;
    }

    /**
     * Imposta il valore della propriet� constructor.
     * 
     * @param value
     *     allowed object is
     *     {@link ConstructorType }
     *     
     */
    public void setConstructor(ConstructorType value) {
        this.constructor = value;
    }

    /**
     * Recupera il valore della propriet� constructorIdRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructorIdRef() {
        return constructorIdRef;
    }

    /**
     * Imposta il valore della propriet� constructorIdRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructorIdRef(String value) {
        this.constructorIdRef = value;
    }

    /**
     * Recupera il valore della propriet� q1.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getQ1() {
        return q1;
    }

    /**
     * Imposta il valore della propriet� q1.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setQ1(DurationType value) {
        this.q1 = value;
    }

    /**
     * Recupera il valore della propriet� q2.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getQ2() {
        return q2;
    }

    /**
     * Imposta il valore della propriet� q2.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setQ2(DurationType value) {
        this.q2 = value;
    }

    /**
     * Recupera il valore della propriet� q3.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getQ3() {
        return q3;
    }

    /**
     * Imposta il valore della propriet� q3.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setQ3(DurationType value) {
        this.q3 = value;
    }

    /**
     * Recupera il valore della propriet� number.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Imposta il valore della propriet� number.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Recupera il valore della propriet� position.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPosition() {
        return position;
    }

    /**
     * Imposta il valore della propriet� position.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPosition(BigInteger value) {
        this.position = value;
    }

}
