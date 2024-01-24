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
 * <p>Classe Java per ConstructorStandingType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ConstructorStandingType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;element name="Constructor" type="{http://ergast.com/mrd/1.5}ConstructorType"/&gt;
 *           &lt;element name="ConstructorIdRef" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "ConstructorStandingType", propOrder = {
    "constructor",
    "constructorIdRef"
})
public class ConstructorStandingType {

    @XmlElement(name = "Constructor")
    protected ConstructorType constructor;
    @XmlElement(name = "ConstructorIdRef")
    protected String constructorIdRef;
    @XmlAttribute(name = "position", required = true)
    protected BigInteger position;
    @XmlAttribute(name = "points", required = true)
    protected float points;
    @XmlAttribute(name = "wins", required = true)
    protected BigInteger wins;

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
