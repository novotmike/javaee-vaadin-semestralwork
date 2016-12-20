package cz.novotm60.views;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.dao.AddressDao;
import cz.novotm60.model.dao.ChangeOrderDao;
import cz.novotm60.model.dao.CustomerDao;
import cz.novotm60.model.dao.PhoneNumberDao;
import cz.novotm60.model.entity.Address;
import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.model.entity.Customer;
import cz.novotm60.model.entity.PhoneNumber;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.AddressType;
import cz.novotm60.service.soap.CustomerDetailType;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.service.soap.PhoneType;
import cz.novotm60.util.Utils;


import javax.inject.Inject;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class NewRequirementView extends MyView {

    @Inject
    AppService appService;

    @Inject
    AddressDao addresDao;

    @Inject
    CustomerDao customerDao;

    @Inject
    PhoneNumberDao phoneNumberDao;

    @Inject
    ChangeOrderDao changeOrderDao;

    private ComboBox clientCombobox, choCombobox;
    private CustomerType customerType = null;
    private GridLayout form;
    private VerticalLayout layout;
    private TextField firstNameField, lastNameField, birthNum, countryOO;
    private TextField streetName, streetNum, postalCode, city, cityCode, county, country;
    private TextField phoneNum, cityCodeP, countryCode;
    private ComboBox phoneTypeCombobox;

    @Override
    public Component generateBodyContent() {
        layout = new VerticalLayout();
        generateContent();
        return layout;
    }

    public void generateContent() {
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);

        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setSizeFull();
        gridLayout.setSpacing(true);
        form = new GridLayout(2, 1);
        form.setWidth("100%");
        form.setSpacing(true);

        ArrayList<CustomerType> customerTypes = (ArrayList<CustomerType>) appService.getAllCustomers(BigInteger.ZERO, BigInteger.TEN);

        if (customerType == null) {
            clientCombobox = new ComboBox();
            clientCombobox.setImmediate(true);
            for (CustomerType ct : customerTypes) {
                clientCombobox.addItem(ct);
                gridLayout.addComponent(clientCombobox);
            }
            clientCombobox.setCaption("Klient");
        } else {
            gridLayout.addComponent(new Label("<h3>Klient: " + customerType.getFirstName() + " " + customerType.getSurname() + "</h3>", ContentMode.HTML));
        }

        choCombobox = new ComboBox();
        choCombobox.setImmediate(true);
        choCombobox.setCaption("Typ požadavku");
        for (Customer.RequestType rt : Customer.RequestType.values()) {
            if (clientCombobox == null && rt == Customer.RequestType.ACTIVE)
                continue;

            choCombobox.addItem(rt);
        }
        gridLayout.addComponent(choCombobox);
        layout.addComponent(gridLayout);
        layout.addComponent(form);
        if (clientCombobox != null)
            clientCombobox.addValueChangeListener(clickEvent -> clientChanged());
        choCombobox.addValueChangeListener(clickEvent -> typeChanged());

        Button saveButton = new Button("Uložit", clickEvent -> {
            if (clientCombobox.getValue() != null && choCombobox.getValue() != null) {

                if (choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                    Address a = new Address();
                    a.setStreetName(streetName.getValue());
                    a.setStreetNum(streetNum.getValue());
                    a.setPostalCode(postalCode.getValue());
                    a.setCity(city.getValue());
                    a.setCityPart(cityCode.getValue());
                    a.setCounty(county.getValue());
                    a.setCountry(country.getValue());
                    addresDao.addNew(a);
                    PhoneNumber p = new PhoneNumber();
                    p.setCityCode(cityCodeP.getValue());
                    p.setCountryCode(countryCode.getValue());
                    p.setPhoneNum(phoneNum.getValue());
                    p.setPhoneType((PhoneNumber.PhoneType) phoneTypeCombobox.getValue());
                    phoneNumberDao.addNew(p);

                    Customer c = new Customer();
                    ArrayList<Address> addresses = new ArrayList<>();
                    addresses.add(a);
                    c.setAddress(addresses);
                    ArrayList<PhoneNumber> phones = new ArrayList<>();
                    phones.add(p);
                    c.setPhoneNumber(phones);
                    c.setBirthNum(birthNum.getValue());
                    c.setCountryOfOrigin(countryOO.getValue());
                    c.setFirstName(new ArrayList<String>(Arrays.asList(firstNameField.getValue().split("\\s+"))));
                    c.setSurName(new ArrayList<String>(Arrays.asList(lastNameField.getValue().split("\\s+"))));
                    c.setCustomerID(((CustomerType) clientCombobox.getValue()).getId());
                    c.setStatus((Customer.RequestType) choCombobox.getValue());
                    customerDao.addNew(c);

                    ChangeOrder cho = new ChangeOrder();
                    cho.setCustomer(c);
                    cho.setRequestType((Customer.RequestType) choCombobox.getValue());
                    cho.setSent(false);
                    changeOrderDao.addNew(cho);
                    UI.getCurrent().getNavigator().navigateTo(Utils.REQ_URI_FRAGMENT);
                } else {
                    CustomerDetailType customerDetail = appService.getCustomerDetailType((CustomerType) clientCombobox.getValue());
                    AddressType at = customerDetail.getAddress().get(customerDetail.getAddress().size() - 1);
                    PhoneType pt = customerDetail.getPhoneNum().get(customerDetail.getAddress().size() - 1);
                    Address a = new Address();
                    a.setStreetName(at.getStreetName());
                    a.setStreetNum(at.getStreetNum());
                    a.setPostalCode(at.getPostalCode());
                    a.setCity(at.getCity());
                    a.setCityPart(at.getCityPart());
                    a.setCounty(at.getCounty());
                    a.setCountry(at.getCountry());
                    addresDao.addNew(a);
                    PhoneNumber p = new PhoneNumber();
                    p.setCityCode(pt.getCityCode());
                    p.setCountryCode(pt.getCountryCode());
                    p.setPhoneNum(pt.getPhoneNum());
                    p.setPhoneType(PhoneNumber.PhoneType.values()[pt.getPhoneNumberType().intValue()]);
                    phoneNumberDao.addNew(p);

                    Customer c = new Customer();
                    ArrayList<Address> addresses = new ArrayList<>();
                    addresses.add(a);
                    c.setAddress(addresses);
                    ArrayList<PhoneNumber> phones = new ArrayList<>();
                    phones.add(p);
                    c.setPhoneNumber(phones);
                    c.setBirthNum(customerDetail.getBirthNum());
                    c.setCountryOfOrigin(customerDetail.getCountryOfOrigin());
                    c.setFirstName(customerDetail.getFirstName());
                    c.setSurName(customerDetail.getSurname());
                    c.setCustomerID(((CustomerType) clientCombobox.getValue()).getId());
                    c.setStatus((Customer.RequestType) choCombobox.getValue());
                    customerDao.addNew(c);
                    ChangeOrder cho = new ChangeOrder();
                    cho.setCustomer(c);
                    cho.setRequestType((Customer.RequestType) choCombobox.getValue());
                    cho.setSent(false);
                    changeOrderDao.addNew(cho);
                    UI.getCurrent().getNavigator().navigateTo(Utils.REQ_URI_FRAGMENT);
                }
            } else {
                new Notification("Není zvolen klient nebo typ změny.").show(Page.getCurrent());
            }
        });
        layout.addComponent(saveButton);
        layout.setComponentAlignment(saveButton, Alignment.MIDDLE_CENTER);
    }

    public void clientChanged() {
        if (clientCombobox.getValue() == null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
            showForm();
        } else if (clientCombobox.getValue() != null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
            showForm();
        } else if (clientCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
            choCombobox.setValue(null);
            hideForm();
        } else if (clientCombobox.getValue() == null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
            choCombobox.setValue(null);
            hideForm();
        } else {
            hideForm();
        }
    }

    public void typeChanged() {
        if (clientCombobox != null) {
            if (clientCombobox.getValue() == null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
                showForm();
            } else if (clientCombobox.getValue() != null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                showForm();
            } else if (clientCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
                choCombobox.setValue(null);
                hideForm();
            } else if (clientCombobox.getValue() == null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                choCombobox.setValue(null);
                hideForm();
            } else {
                hideForm();
            }
        } else {
            if (choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                showForm();
            } else {
                hideForm();
            }
        }
    }

    public void showForm() {
        firstNameField = new TextField();
        firstNameField.setInputPrompt("Jmeno/a");

        lastNameField = new TextField();
        lastNameField.setInputPrompt("Přijmení");

        birthNum = new TextField();
        birthNum.setInputPrompt("Rodné číslo");
        countryOO = new TextField();
        countryOO.setInputPrompt("Stát narození");

        //Address
        streetName = new TextField();
        streetName.setInputPrompt("Ulice");
        streetNum = new TextField();
        streetNum.setInputPrompt("Číslo popisné");
        postalCode = new TextField();
        postalCode.setInputPrompt("PSČ");
        city = new TextField();
        city.setInputPrompt("Město");
        cityCode = new TextField();
        cityCode.setInputPrompt("Část města");
        county = new TextField();
        county.setInputPrompt("Kraj");
        country = new TextField();
        country.setInputPrompt("Stát");

        //Phone
        phoneTypeCombobox = new ComboBox("Typ telefonu");
        phoneTypeCombobox.setImmediate(true);
        phoneTypeCombobox.addItem(PhoneNumber.PhoneType.MOBILE);
        phoneTypeCombobox.addItem(PhoneNumber.PhoneType.FAX);
        phoneNum = new TextField();
        phoneNum.setInputPrompt("Číslo");
        cityCodeP = new TextField();
        cityCodeP.setInputPrompt("Předčíslí města");
        countryCode = new TextField();
        countryCode.setInputPrompt("Předčíslí země");

        form.addComponents(firstNameField, lastNameField, birthNum, countryOO);
        form.addComponents(streetName, streetNum, postalCode, city, cityCode, county, country);
        form.addComponents(phoneTypeCombobox, phoneNum, cityCodeP, countryCode);

        //Bind selected entity
        if (clientCombobox.getValue() != null) {
            //Map values
            CustomerDetailType detailType = appService.getCustomerDetailType((CustomerType) clientCombobox.getValue());
            //Person
            for (String s : detailType.getFirstName()) {
                String name = s + " ";
                firstNameField.setValue(firstNameField.getValue() + name);
            }

            for (String s : detailType.getSurname()) {
                String name = s + " ";
                lastNameField.setValue(lastNameField.getValue() + name);
            }

            birthNum.setValue(detailType.getBirthNum());
            countryOO.setValue(detailType.getCountryOfOrigin());

            //Address
            if (!detailType.getAddress().isEmpty()) {
                AddressType addressType = detailType.getAddress().get(detailType.getAddress().size() - 1);
                streetName.setValue(addressType.getStreetName());
                streetNum.setValue(addressType.getStreetNum());
                postalCode.setValue(addressType.getPostalCode());
                city.setValue(addressType.getCity());
                cityCode.setValue(addressType.getCityPart());
                county.setValue(addressType.getCounty());
                country.setValue(addressType.getCountry());
            }

            if (!detailType.getPhoneNum().isEmpty()) {
                PhoneType phoneType = detailType.getPhoneNum().get(detailType.getPhoneNum().size() - 1);
                phoneNum.setValue(phoneType.getPhoneNum());
                cityCodeP.setValue(phoneType.getCityCode());
                countryCode.setValue(phoneType.getCountryCode());
                phoneTypeCombobox.select(PhoneNumber.PhoneType.values()[phoneType.getPhoneNumberType().intValue()]);
            }
        }
    }

    public void hideForm() {
        form.removeAllComponents();
    }

}
