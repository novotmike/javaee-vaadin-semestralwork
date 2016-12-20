package cz.novotm60.util;

import cz.novotm60.model.entity.Address;
import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.model.entity.Customer;
import cz.novotm60.model.entity.PhoneNumber;
import cz.novotm60.service.soap.*;

import java.math.BigInteger;
import java.util.ArrayList;

public class Converter {

    public static AddressType addressTypeFromAdress(Address address) {
        AddressType addressType = new AddressType();
        addressType.setCity(address.getCity());
        addressType.setCityPart(address.getCityPart());
        addressType.setCountry(address.getCountry());
        addressType.setCounty(address.getCounty());
        addressType.setPostalCode(address.getPostalCode());
        addressType.setStreetName(address.getStreetName());
        addressType.setStreetNum(address.getStreetNum());
        return addressType;
    }

    public static Address addressFromAdressType(AddressType addressType) {
        Address address = new Address();
        address.setCity(addressType.getCity());
        address.setCityPart(addressType.getCityPart());
        address.setCountry(addressType.getCountry());
        address.setCounty(addressType.getCounty());
        address.setPostalCode(addressType.getPostalCode());
        address.setStreetName(addressType.getStreetName());
        address.setStreetNum(addressType.getStreetNum());
        return address;
    }

    public static PhoneType phoneTypeFromPhone(PhoneNumber phoneNumber) {
        PhoneType phonetype = new PhoneType();
        phonetype.setCityCode(phoneNumber.getCityCode());
        phonetype.setCountryCode(phoneNumber.getCountryCode());
        phonetype.setPhoneNum(phoneNumber.getPhoneNum());
        phonetype.setPhoneNumberType(BigInteger.valueOf(phoneNumber.getPhoneType().ordinal()));
        return phonetype;
    }

    public static CustomerDetailType customerDetailTypeFromCustomer(Customer customer) {
        AddressType addressType = addressTypeFromAdress(customer.getAddress().get(customer.getAddress().size()-1));
        PhoneType phoneType = phoneTypeFromPhone(customer.getPhoneNumber().get(customer.getPhoneNumber().size()-1));
        CustomerDetailType detail = new CustomerDetailType();
        detail.getPhoneNum().add(phoneType);
        detail.getAddress().add(addressType);
        for(String fname : customer.getFirstName())
            detail.getFirstName().add(fname);
        for(String lname : customer.getSurName())
            detail.getSurname().add(lname);
        detail.setBirthNum(customer.getBirthNum());
        detail.setCountryOfOrigin(customer.getCountryOfOrigin());
        return detail;
    }


    public static CreateCustomerChangeOrder createCustomerChangeOrder(ChangeOrder changeOrder, Customer customer) {
        CreateCustomerChangeOrder create = new CreateCustomerChangeOrder();
        create.setRequestType(changeOrder.getRequestType().name());
        create.setId(BigInteger.valueOf(changeOrder.getId()));
        create.setCustomer(customerDetailTypeFromCustomer(customer));
        return create;
    }


}

