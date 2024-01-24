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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per RaceType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RaceType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RaceName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Circuit" type="{http://ergast.com/mrd/1.5}CircuitType"/&gt;
 *           &lt;element name="CircuitIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="Time" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/&gt;
 *         &lt;element name="FirstPractice" type="{http://ergast.com/mrd/1.5}DateTimeType" minOccurs="0"/&gt;
 *         &lt;element name="SecondPractice" type="{http://ergast.com/mrd/1.5}DateTimeType" minOccurs="0"/&gt;
 *         &lt;element name="ThirdPractice" type="{http://ergast.com/mrd/1.5}DateTimeType" minOccurs="0"/&gt;
 *         &lt;element name="Qualifying" type="{http://ergast.com/mrd/1.5}DateTimeType" minOccurs="0"/&gt;
 *         &lt;element name="Sprint" type="{http://ergast.com/mrd/1.5}DateTimeType" minOccurs="0"/&gt;
 *         &lt;element name="QualifyingList" type="{http://ergast.com/mrd/1.5}QualifyingListType" minOccurs="0"/&gt;
 *         &lt;element name="SprintList" type="{http://ergast.com/mrd/1.5}SprintListType" minOccurs="0"/&gt;
 *         &lt;element name="ResultsList" type="{http://ergast.com/mrd/1.5}ResultsListType" minOccurs="0"/&gt;
 *         &lt;element name="LapsList" type="{http://ergast.com/mrd/1.5}LapsListType" minOccurs="0"/&gt;
 *         &lt;element name="PitStopsList" type="{http://ergast.com/mrd/1.5}PitStopsListType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="season" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="round" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *       &lt;attribute name="url" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RaceType", propOrder = {
    "raceName",
    "circuit",
    "circuitIdRef",
    "date",
    "time",
    "firstPractice",
    "secondPractice",
    "thirdPractice",
    "qualifying",
    "sprint",
    "qualifyingList",
    "sprintList",
    "resultsList",
    "lapsList",
    "pitStopsList"
})
public class RaceType {

    @XmlElement(name = "RaceName", required = true)
    protected String raceName;
    @XmlElement(name = "Circuit")
    protected CircuitType circuit;
    @XmlElement(name = "CircuitIdRef")
    protected String circuitIdRef;
    @XmlElement(name = "Date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "Time")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(name = "FirstPractice")
    protected DateTimeType firstPractice;
    @XmlElement(name = "SecondPractice")
    protected DateTimeType secondPractice;
    @XmlElement(name = "ThirdPractice")
    protected DateTimeType thirdPractice;
    @XmlElement(name = "Qualifying")
    protected DateTimeType qualifying;
    @XmlElement(name = "Sprint")
    protected DateTimeType sprint;
    @XmlElement(name = "QualifyingList")
    protected QualifyingListType qualifyingList;
    @XmlElement(name = "SprintList")
    protected SprintListType sprintList;
    @XmlElement(name = "ResultsList")
    protected ResultsListType resultsList;
    @XmlElement(name = "LapsList")
    protected LapsListType lapsList;
    @XmlElement(name = "PitStopsList")
    protected PitStopsListType pitStopsList;
    @XmlAttribute(name = "season", required = true)
    protected BigInteger season;
    @XmlAttribute(name = "round", required = true)
    protected BigInteger round;
    @XmlAttribute(name = "url")
    protected String url;

    /**
     * Recupera il valore della propriet� raceName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaceName() {
        return raceName;
    }

    /**
     * Imposta il valore della propriet� raceName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaceName(String value) {
        this.raceName = value;
    }

    /**
     * Recupera il valore della propriet� circuit.
     * 
     * @return
     *     possible object is
     *     {@link CircuitType }
     *     
     */
    public CircuitType getCircuit() {
        return circuit;
    }

    /**
     * Imposta il valore della propriet� circuit.
     * 
     * @param value
     *     allowed object is
     *     {@link CircuitType }
     *     
     */
    public void setCircuit(CircuitType value) {
        this.circuit = value;
    }

    /**
     * Recupera il valore della propriet� circuitIdRef.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCircuitIdRef() {
        return circuitIdRef;
    }

    /**
     * Imposta il valore della propriet� circuitIdRef.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCircuitIdRef(String value) {
        this.circuitIdRef = value;
    }

    /**
     * Recupera il valore della propriet� date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Imposta il valore della propriet� date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Recupera il valore della propriet� time.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Imposta il valore della propriet� time.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Recupera il valore della propriet� firstPractice.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getFirstPractice() {
        return firstPractice;
    }

    /**
     * Imposta il valore della propriet� firstPractice.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setFirstPractice(DateTimeType value) {
        this.firstPractice = value;
    }

    /**
     * Recupera il valore della propriet� secondPractice.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getSecondPractice() {
        return secondPractice;
    }

    /**
     * Imposta il valore della propriet� secondPractice.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setSecondPractice(DateTimeType value) {
        this.secondPractice = value;
    }

    /**
     * Recupera il valore della propriet� thirdPractice.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getThirdPractice() {
        return thirdPractice;
    }

    /**
     * Imposta il valore della propriet� thirdPractice.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setThirdPractice(DateTimeType value) {
        this.thirdPractice = value;
    }

    /**
     * Recupera il valore della propriet� qualifying.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getQualifying() {
        return qualifying;
    }

    /**
     * Imposta il valore della propriet� qualifying.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setQualifying(DateTimeType value) {
        this.qualifying = value;
    }

    /**
     * Recupera il valore della propriet� sprint.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeType }
     *     
     */
    public DateTimeType getSprint() {
        return sprint;
    }

    /**
     * Imposta il valore della propriet� sprint.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeType }
     *     
     */
    public void setSprint(DateTimeType value) {
        this.sprint = value;
    }

    /**
     * Recupera il valore della propriet� qualifyingList.
     * 
     * @return
     *     possible object is
     *     {@link QualifyingListType }
     *     
     */
    public QualifyingListType getQualifyingList() {
        return qualifyingList;
    }

    /**
     * Imposta il valore della propriet� qualifyingList.
     * 
     * @param value
     *     allowed object is
     *     {@link QualifyingListType }
     *     
     */
    public void setQualifyingList(QualifyingListType value) {
        this.qualifyingList = value;
    }

    /**
     * Recupera il valore della propriet� sprintList.
     * 
     * @return
     *     possible object is
     *     {@link SprintListType }
     *     
     */
    public SprintListType getSprintList() {
        return sprintList;
    }

    /**
     * Imposta il valore della propriet� sprintList.
     * 
     * @param value
     *     allowed object is
     *     {@link SprintListType }
     *     
     */
    public void setSprintList(SprintListType value) {
        this.sprintList = value;
    }

    /**
     * Recupera il valore della propriet� resultsList.
     * 
     * @return
     *     possible object is
     *     {@link ResultsListType }
     *     
     */
    public ResultsListType getResultsList() {
        return resultsList;
    }

    /**
     * Imposta il valore della propriet� resultsList.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultsListType }
     *     
     */
    public void setResultsList(ResultsListType value) {
        this.resultsList = value;
    }

    /**
     * Recupera il valore della propriet� lapsList.
     * 
     * @return
     *     possible object is
     *     {@link LapsListType }
     *     
     */
    public LapsListType getLapsList() {
        return lapsList;
    }

    /**
     * Imposta il valore della propriet� lapsList.
     * 
     * @param value
     *     allowed object is
     *     {@link LapsListType }
     *     
     */
    public void setLapsList(LapsListType value) {
        this.lapsList = value;
    }

    /**
     * Recupera il valore della propriet� pitStopsList.
     * 
     * @return
     *     possible object is
     *     {@link PitStopsListType }
     *     
     */
    public PitStopsListType getPitStopsList() {
        return pitStopsList;
    }

    /**
     * Imposta il valore della propriet� pitStopsList.
     * 
     * @param value
     *     allowed object is
     *     {@link PitStopsListType }
     *     
     */
    public void setPitStopsList(PitStopsListType value) {
        this.pitStopsList = value;
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

    /**
     * Recupera il valore della propriet� url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Imposta il valore della propriet� url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
