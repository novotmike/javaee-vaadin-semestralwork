package cz.novotm60.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {
        "streetName",
        "streetNum",
        "postalCode",
        "cityPart",
        "city",
        "county",
        "country"
})
public class AddressType {

    @XmlElement(required = true)
    protected String streetName;
    @XmlElement(required = true)
    protected String streetNum;
    @XmlElement(required = true)
    protected String postalCode;
    protected String cityPart;
    @XmlElement(required = true)
    protected String city;
    protected String county;
    @XmlElement(required = true)
    protected String country;

    /**
     * Gets the value of the streetName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the streetNum property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStreetNum() {
        return streetNum;
    }

    /**
     * Sets the value of the streetNum property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setStreetNum(String value) {
        this.streetNum = value;
    }

    /**
     * Gets the value of the postalCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the cityPart property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCityPart() {
        return cityPart;
    }

    /**
     * Sets the value of the cityPart property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCityPart(String value) {
        this.cityPart = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the county property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCounty() {
        return county;
    }

    /**
     * Sets the value of the county property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCounty(String value) {
        this.county = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountry(String value) {
        this.country = value;
    }

}
