package cz.novotm60.service.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "customer"
})
@XmlRootElement(name = "ReadAllCustomersResponse1")
public class ReadAllCustomersResponse1 {

    protected List<CustomerType> customer;

    /**
     * Gets the value of the customer property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomer().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerType }
     *
     *
     */
    public List<CustomerType> getCustomer() {
        if (customer == null) {
            customer = new ArrayList<CustomerType>();
        }
        return this.customer;
    }

}

