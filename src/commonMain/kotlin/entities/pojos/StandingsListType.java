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
 * <p>Classe Java per StandingsListType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="StandingsListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DriverStanding" type="{http://ergast.com/mrd/1.5}DriverStandingType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="ConstructorStanding" type="{http://ergast.com/mrd/1.5}ConstructorStandingType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="season" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="round" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StandingsListType", propOrder = {
    "driverStanding",
    "constructorStanding"
})
public class StandingsListType {

    @XmlElement(name = "DriverStanding")
    protected List<DriverStandingType> driverStanding;
    @XmlElement(name = "ConstructorStanding")
    protected List<ConstructorStandingType> constructorStanding;
    @XmlAttribute(name = "season", required = true)
    protected BigInteger season;
    @XmlAttribute(name = "round")
    protected BigInteger round;

    /**
     * Gets the value of the driverStanding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the driverStanding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDriverStanding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DriverStandingType }
     * 
     * 
     */
    public List<DriverStandingType> getDriverStanding() {
        if (driverStanding == null) {
            driverStanding = new ArrayList<DriverStandingType>();
        }
        return this.driverStanding;
    }

    /**
     * Gets the value of the constructorStanding property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constructorStanding property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstructorStanding().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConstructorStandingType }
     * 
     * 
     */
    public List<ConstructorStandingType> getConstructorStanding() {
        if (constructorStanding == null) {
            constructorStanding = new ArrayList<ConstructorStandingType>();
        }
        return this.constructorStanding;
    }

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

}
