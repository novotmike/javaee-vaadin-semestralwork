package cz.novotm60.service.soap;

import cz.novotm60.model.entity.CustomerDetailType;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "requestType",
        "id",
        "customer"
})
@XmlRootElement(name = "CreateCustomerChangeOrder")
public class CreateCustomerChangeOrder {

    @XmlElement(required = true)
    protected String requestType;
    @XmlElement(required = true)
    protected BigInteger id;
    protected CustomerDetailType customer;

    /**
     * Gets the value of the requestType property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRequestType(String value) {
        this.requestType = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the customer property.
     *
     * @return possible object is
     * {@link CustomerDetailType }
     */
    public CustomerDetailType getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     *
     * @param value allowed object is
     *              {@link CustomerDetailType }
     */
    public void setCustomer(CustomerDetailType value) {
        this.customer = value;
    }

}

