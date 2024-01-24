//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.24 alle 01:08:53 PM CET 
//


package entities.pojos

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per LocationType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="LocationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Locality" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="lat" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="long" type="{http://www.w3.org/2001/XMLSchema}float" /&gt;
 *       &lt;attribute name="alt" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocationType", propOrder = {
    "locality",
    "country"
})
public class LocationType {

    @XmlElement(name = "Locality", required = true)
    protected List<String> locality;
    @XmlElement(name = "Country", required = true)
    protected String country;
    @XmlAttribute(name = "lat")
    protected Float lat;
    @XmlAttribute(name = "long")
    protected Float _long;
    @XmlAttribute(name = "alt")
    protected Integer alt;

    /**
     * Gets the value of the locality property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locality property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocality().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLocality() {
        if (locality == null) {
            locality = new ArrayList<String>();
        }
        return this.locality;
    }

    /**
     * Recupera il valore della propriet� country.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Imposta il valore della propriet� country.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Recupera il valore della propriet� lat.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLat() {
        return lat;
    }

    /**
     * Imposta il valore della propriet� lat.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLat(Float value) {
        this.lat = value;
    }

    /**
     * Recupera il valore della propriet� long.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLong() {
        return _long;
    }

    /**
     * Imposta il valore della propriet� long.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLong(Float value) {
        this._long = value;
    }

    /**
     * Recupera il valore della propriet� alt.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAlt() {
        return alt;
    }

    /**
     * Imposta il valore della propriet� alt.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAlt(Integer value) {
        this.alt = value;
    }

}
