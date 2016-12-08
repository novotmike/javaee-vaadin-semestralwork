package cz.novotm60.model.entity;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneType", propOrder = {
        "phoneNumberType",
        "phoneNum",
        "cityCode",
        "countryCode"
})
public class PhoneType {

    @XmlElement(required = true)
    protected BigInteger phoneNumberType;
    @XmlElement(required = true)
    protected String phoneNum;
    protected String cityCode;
    protected String countryCode;

    /**
     * Gets the value of the phoneNumberType property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getPhoneNumberType() {
        return phoneNumberType;
    }

    /**
     * Sets the value of the phoneNumberType property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setPhoneNumberType(BigInteger value) {
        this.phoneNumberType = value;
    }

    /**
     * Gets the value of the phoneNum property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * Sets the value of the phoneNum property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhoneNum(String value) {
        this.phoneNum = value;
    }

    /**
     * Gets the value of the cityCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the value of the cityCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCityCode(String value) {
        this.cityCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

}
