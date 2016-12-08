package cz.novotm60.service.soap;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "from",
        "count"
})
@XmlRootElement(name = "ReadAllCustomers")
public class ReadAllCustomers {

    protected BigInteger from;
    protected BigInteger count;

    /**
     * Gets the value of the from property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setFrom(BigInteger value) {
        this.from = value;
    }

    /**
     * Gets the value of the count property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setCount(BigInteger value) {
        this.count = value;
    }

}
