package cz.novotm60.factory;

import cz.novotm60.service.soap.AddressType;
import cz.novotm60.service.soap.CustomerDetailType;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.service.soap.PhoneType;
import cz.novotm60.service.soap.*;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateCustomerChangeOrderResponse1_QNAME = new QName("http://www.cvut.cz/FEL/", "CreateCustomerChangeOrderResponse1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReadAllCustomers }
     */
    public ReadAllCustomers createReadAllCustomers() {
        return new ReadAllCustomers();
    }

    /**
     * Create an instance of {@link ReadAllCustomersResponse1 }
     */
    public ReadAllCustomersResponse1 createReadAllCustomersResponse1() {
        return new ReadAllCustomersResponse1();
    }

    /**
     * Create an instance of {@link CustomerType }
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

    /**
     * Create an instance of {@link ReadCustomerDetails }
     */
    public ReadCustomerDetails createReadCustomerDetails() {
        return new ReadCustomerDetails();
    }

    /**
     * Create an instance of {@link ReadCustomerDetailsResponse1 }
     */
    public ReadCustomerDetailsResponse1 createReadCustomerDetailsResponse1() {
        return new ReadCustomerDetailsResponse1();
    }

    /**
     * Create an instance of {@link CustomerDetailType }
     */
    public CustomerDetailType createCustomerDetailType() {
        return new CustomerDetailType();
    }

    /**
     * Create an instance of {@link CreateCustomerChangeOrder }
     */
    public CreateCustomerChangeOrder createCreateCustomerChangeOrder() {
        return new CreateCustomerChangeOrder();
    }

    /**
     * Create an instance of {@link AddressType }
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link PhoneType }
     */
    public PhoneType createPhoneType() {
        return new PhoneType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.cvut.cz/FEL/", name = "CreateCustomerChangeOrderResponse1")
    public JAXBElement<Object> createCreateCustomerChangeOrderResponse1(Object value) {
        return new JAXBElement<Object>(_CreateCustomerChangeOrderResponse1_QNAME, Object.class, null, value);
    }

}
