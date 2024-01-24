//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.24 alle 01:08:53 PM CET 
//


package entities.pojos

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DriverStandingType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DriverStandingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Driver" type="{http://ergast.com/mrd/1.5}DriverType"/&gt;
 *           &lt;element name="DriverIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Constructor" type="{http://ergast.com/mrd/1.5}ConstructorType" maxOccurs="unbounded"/&gt;
 *           &lt;element name="ConstructorIdRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="points" use="required" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="wins" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DriverStandingType", propOrder = {
    "driver",
    "driverIdRef",
    "constructor",
    "constructorIdRef"
})
public class DriverStandingType {

    @XmlElement(name = "Driver")
    protected DriverType driver;
    @XmlElement(name = "DriverIdRef")
    protected String driverIdRef;
    @XmlElement(name = "Constructor")
    protected List<ConstructorType> constructor;
    @XmlElement(name = "ConstructorIdRef")
    protected List<String> constructorIdRef;
    @XmlAttribute(name = "position", required = true)
    protected BigInteger position;
    @XmlAttribute(name = "points", required = true)
    protected float points;
    @XmlAttribute(name = "wins", required = true)
    protected BigInteger wins;

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
     * Gets the value of the constructor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constructor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstructor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstructorType }
     * 
     * 
     */
    public List<ConstructorType> getConstructor() {
        if (constructor == null) {
            constructor = new ArrayList<ConstructorType>();
        }
        return this.constructor;
    }

    /**
     * Gets the value of the constructorIdRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constructorIdRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstructorIdRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getConstructorIdRef() {
        if (constructorIdRef == null) {
            constructorIdRef = new ArrayList<String>();
        }
        return this.constructorIdRef;
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

    /**
     * Recupera il valore della propriet� points.
     * 
     */
    public float getPoints() {
        return points;
    }

    /**
     * Imposta il valore della propriet� points.
     * 
     */
    public void setPoints(float value) {
        this.points = value;
    }

    /**
     * Recupera il valore della propriet� wins.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWins() {
        return wins;
    }

    /**
     * Imposta il valore della propriet� wins.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWins(BigInteger value) {
        this.wins = value;
    }

}
