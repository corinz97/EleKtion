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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TableType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="season" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="round" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="circuitId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="constructorId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="driverId" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="grid" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="statusId" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TableType")
@XmlSeeAlso({
    SeasonTableType.class,
    RaceTableType.class,
    DriverTableType.class,
    ConstructorTableType.class,
    StandingsTableType.class,
    CircuitTableType.class,
    StatusTableType.class
})
public abstract class TableType {

    @XmlAttribute(name = "season")
    protected BigInteger season;
    @XmlAttribute(name = "round")
    protected BigInteger round;
    @XmlAttribute(name = "circuitId")
    protected String circuitId;
    @XmlAttribute(name = "constructorId")
    protected String constructorId;
    @XmlAttribute(name = "driverId")
    protected String driverId;
    @XmlAttribute(name = "grid")
    protected BigInteger grid;
    @XmlAttribute(name = "position")
    protected BigInteger position;
    @XmlAttribute(name = "statusId")
    protected BigInteger statusId;

    /**
     * Recupera il valore della propriet� season.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSeason() {
        return season;
    }

    /**
     * Imposta il valore della propriet� season.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSeason(BigInteger value) {
        this.season = value;
    }

    /**
     * Recupera il valore della propriet� round.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRound() {
        return round;
    }

    /**
     * Imposta il valore della propriet� round.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRound(BigInteger value) {
        this.round = value;
    }

    /**
     * Recupera il valore della propriet� circuitId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCircuitId() {
        return circuitId;
    }

    /**
     * Imposta il valore della propriet� circuitId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCircuitId(String value) {
        this.circuitId = value;
    }

    /**
     * Recupera il valore della propriet� constructorId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConstructorId() {
        return constructorId;
    }

    /**
     * Imposta il valore della propriet� constructorId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConstructorId(String value) {
        this.constructorId = value;
    }

    /**
     * Recupera il valore della propriet� driverId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverId() {
        return driverId;
    }

    /**
     * Imposta il valore della propriet� driverId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverId(String value) {
        this.driverId = value;
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
     * Recupera il valore della propriet� statusId.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStatusId() {
        return statusId;
    }

    /**
     * Imposta il valore della propriet� statusId.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStatusId(BigInteger value) {
        this.statusId = value;
    }

}
