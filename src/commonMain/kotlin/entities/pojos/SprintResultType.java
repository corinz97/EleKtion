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
 * <p>Classe Java per SprintResultType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SprintResultType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Driver" type="{http://ergast.com/mrd/1.5}DriverType" maxOccurs="unbounded"/&gt;
 *           &lt;element name="DriverIdRef" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *         &lt;/choice&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Constructor" type="{http://ergast.com/mrd/1.5}ConstructorType"/&gt;
 *           &lt;element name="ConstructorIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="Grid" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Laps" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="Status" type="{http://ergast.com/mrd/1.5}StatusType"/&gt;
 *         &lt;element name="Time" type="{http://ergast.com/mrd/1.5}DurationType" minOccurs="0"/&gt;
 *         &lt;element name="FastestLap" type="{http://ergast.com/mrd/1.5}FastestLapType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="number" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="points" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SprintResultType", propOrder = {
    "driver",
    "driverIdRef",
    "constructor",
    "constructorIdRef",
    "grid",
    "laps",
    "status",
    "time",
    "fastestLap"
})
public class SprintResultType {

    @XmlElement(name = "Driver")
    protected List<DriverType> driver;
    @XmlElement(name = "DriverIdRef")
    protected List<String> driverIdRef;
    @XmlElement(name = "Constructor")
    protected ConstructorType constructor;
    @XmlElement(name = "ConstructorIdRef")
    protected String constructorIdRef;
    @XmlElement(name = "Grid", required = true)
    protected BigInteger grid;
    @XmlElement(name = "Laps", required = true)
    protected BigInteger laps;
    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "Time")
    protected DurationType time;
    @XmlElement(name = "FastestLap")
    protected FastestLapType fastestLap;
    @XmlAttribute(name = "number")
    protected BigInteger number;
    @XmlAttribute(name = "position", required = true)
    protected BigInteger position;
    @XmlAttribute(name = "points")
    protected Float points;

    /**
     * Gets the value of the driver property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the driver property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDriver().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DriverType }
     * 
     * 
     */
    public List<DriverType> getDriver() {
        if (driver == null) {
            driver = new ArrayList<DriverType>();
        }
        return this.driver;
    }

    /**
     * Gets the value of the driverIdRef property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the driverIdRef property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDriverIdRef().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDriverIdRef() {
        if (driverIdRef == null) {
            driverIdRef = new ArrayList<String>();
        }
        return this.driverIdRef;
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
     * Recupera il valore della propriet� grid.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGrid() {
        return grid;
    }

    /**
     * Imposta il valore della propriet� grid.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGrid(BigInteger value) {
        this.grid = value;
    }

    /**
     * Recupera il valore della propriet� laps.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLaps() {
        return laps;
    }

    /**
     * Imposta il valore della propriet� laps.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLaps(BigInteger value) {
        this.laps = value;
    }

    /**
     * Recupera il valore della propriet� status.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Imposta il valore della propriet� status.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Recupera il valore della propriet� time.
     * 
     * @return
     *     possible object is
     *     {@link DurationType }
     *     
     */
    public DurationType getTime() {
        return time;
    }

    /**
     * Imposta il valore della propriet� time.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationType }
     *     
     */
    public void setTime(DurationType value) {
        this.time = value;
    }

    /**
     * Recupera il valore della propriet� fastestLap.
     * 
     * @return
     *     possible object is
     *     {@link FastestLapType }
     *     
     */
    public FastestLapType getFastestLap() {
        return fastestLap;
    }

    /**
     * Imposta il valore della propriet� fastestLap.
     * 
     * @param value
     *     allowed object is
     *     {@link FastestLapType }
     *     
     */
    public void setFastestLap(FastestLapType value) {
        this.fastestLap = value;
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

    /**
     * Recupera il valore della propriet� points.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPoints() {
        return points;
    }

    /**
     * Imposta il valore della propriet� points.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPoints(Float value) {
        this.points = value;
    }

}
