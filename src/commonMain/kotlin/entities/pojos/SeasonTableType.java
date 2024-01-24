//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.01.24 alle 01:08:53 PM CET 
//


package entities.pojos

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SeasonTableType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SeasonTableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ergast.com/mrd/1.5}TableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Season" type="{http://ergast.com/mrd/1.5}SeasonType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeasonTableType", propOrder = {
    "rest"
})
public class SeasonTableType
    extends TableType
{

    @XmlElementRef(name = "Season", namespace = "http://ergast.com/mrd/1.5", type = JAXBElement.class, required = false)
    protected List<JAXBElement<SeasonType>> rest;

    /**
     * Recupera il resto del modello di contenuto. 
     * 
     * <p>
     * Questa propriet� "catch-all" viene recuperata per il seguente motivo: 
     * Il nome di campo "Season" � usato da due diverse parti di uno schema. Vedere: 
     * riga 36 di file:/C:/Users/jcorina/Downloads/jaxb-ri-2.3.0/jaxb-ri/skema.xsd
     * riga 23 di file:/C:/Users/jcorina/Downloads/jaxb-ri-2.3.0/jaxb-ri/skema.xsd
     * <p>
     * Per eliminare questa propriet�, applicare una personalizzazione della propriet� a una 
     * delle seguenti due dichiarazioni per modificarne il nome: 
     * Gets the value of the rest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link SeasonType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<SeasonType>> getRest() {
        if (rest == null) {
            rest = new ArrayList<JAXBElement<SeasonType>>();
        }
        return this.rest;
    }

}
