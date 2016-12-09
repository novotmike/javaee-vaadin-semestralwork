package cz.novotm60.service.soap;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "status",
        "customer"
})
@XmlRootElement(name = "ReadCustomerDetailsResponse1")
public class ReadCustomerDetailsResponse1 {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected CustomerDetailType customer;

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
     * Gets the value of the status property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStatus(String value) {
        this.status = value;
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
