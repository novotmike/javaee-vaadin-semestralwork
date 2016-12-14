package cz.novotm60.views;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.entity.Customer;
import cz.novotm60.model.entity.PhoneNumber;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CustomerDetailType;
import cz.novotm60.service.soap.CustomerType;


import javax.inject.Inject;
import java.math.BigInteger;
import java.util.ArrayList;

public class NewRequirementView extends MyView {

    @Inject
    AppService appService;

    private ComboBox clientCombobox, choCombobox;
    private CustomerType customerType = null;
    private GridLayout form;
    private VerticalLayout layout;

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
                System.out.println("SAVIG");
            }else {
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
        TextField firstNameField = new TextField();
        firstNameField.setInputPrompt("Jmeno/a");

        TextField lastNameField = new TextField();
        lastNameField.setInputPrompt("Přijmení");

        TextField birthNum = new TextField();
        birthNum.setInputPrompt("Rodné číslo");
        TextField countryOO = new TextField();
        countryOO.setInputPrompt("Stát narození");

        //Address
        TextField streetName = new TextField();
        streetName.setInputPrompt("Ulice");
        TextField streetNum = new TextField();
        streetNum.setInputPrompt("Číslo popisné");
        TextField postalCode = new TextField();
        postalCode.setInputPrompt("PSČ");
        TextField city = new TextField();
        city.setInputPrompt("Město");
        TextField cityCode = new TextField();
        cityCode.setInputPrompt("Část města");
        TextField county = new TextField();
        county.setInputPrompt("Kraj");
        TextField country = new TextField();
        country.setInputPrompt("Stát");

        //Phone
        ComboBox phoneTypeCombobox = new ComboBox("Typ telefonu");
        phoneTypeCombobox.setImmediate(true);
        phoneTypeCombobox.addItem(PhoneNumber.PhoneType.MOBILE);
        phoneTypeCombobox.addItem(PhoneNumber.PhoneType.FAX);
        TextField phoneNum = new TextField();
        phoneNum.setInputPrompt("Číslo");
        TextField cityCodeP = new TextField();
        cityCodeP.setInputPrompt("Předčíslí města");
        TextField countryCode = new TextField();
        countryCode.setInputPrompt("Předčíslí země");

        form.addComponents(firstNameField, lastNameField, birthNum, countryOO);
        form.addComponents(streetName, streetNum, postalCode, city, cityCode, county, country);
        form.addComponents(phoneTypeCombobox, phoneNum, cityCodeP, countryCode);

        //Bind selected entity
        if (clientCombobox.getValue() != null) {
            //Map values
            CustomerDetailType detailType = appService.getCustomerDetailType((CustomerType) clientCombobox.getValue());
        }

    }

    public void hideForm() {
        form.removeAllComponents();
    }

}
