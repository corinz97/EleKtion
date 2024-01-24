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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ConstructorTableType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ConstructorTableType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://ergast.com/mrd/1.5}TableType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Constructor" type="{http://ergast.com/mrd/1.5}ConstructorType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConstructorTableType", propOrder = {
    "constructor"
})
public class ConstructorTableType
    extends TableType
{

    @XmlElement(name = "Constructor")
    protected List<ConstructorType> constructor;

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

}
