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
 * <p>Classe Java per FastestLapType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FastestLapType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Time" type="{http://ergast.com/mrd/1.5}DurationType"/&gt;
 *         &lt;element name="AverageSpeed" type="{http://ergast.com/mrd/1.5}SpeedType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="rank" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="lap" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FastestLapType", propOrder = {
    "time",
    "averageSpeed"
})
public class FastestLapType {

    @XmlElement(name = "Time", required = true)
    protected DurationType time;
    @XmlElement(name = "AverageSpeed")
    protected SpeedType averageSpeed;
    @XmlAttribute(name = "rank")
    protected BigInteger rank;
    @XmlAttribute(name = "lap", required = true)
    protected BigInteger lap;

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
     * Recupera il valore della propriet� averageSpeed.
     * 
     * @return
     *     possible object is
     *     {@link SpeedType }
     *     
     */
    public SpeedType getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Imposta il valore della propriet� averageSpeed.
     * 
     * @param value
     *     allowed object is
     *     {@link SpeedType }
     *     
     */
    public void setAverageSpeed(SpeedType value) {
        this.averageSpeed = value;
    }

    /**
     * Recupera il valore della propriet� rank.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRank() {
        return rank;
    }

    /**
     * Imposta il valore della propriet� rank.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRank(BigInteger value) {
        this.rank = value;
    }

    /**
     * Recupera il valore della propriet� lap.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLap() {
        return lap;
    }

    /**
     * Imposta il valore della propriet� lap.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLap(BigInteger value) {
        this.lap = value;
    }

}
