package cz.novotm60.views.windows;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.dao.AddressDao;
import cz.novotm60.model.dao.ChangeOrderDao;
import cz.novotm60.model.dao.CustomerDao;
import cz.novotm60.model.dao.PhoneNumberDao;
import cz.novotm60.model.entity.PhoneNumber;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.AddressType;
import cz.novotm60.service.soap.CustomerDetailType;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.service.soap.PhoneType;

public class CustomerDetailWindow extends Window {

    public CustomerDetailWindow(CustomerType customerType, AppService appService, CustomerDao customerDao, AddressDao addressDao, PhoneNumberDao phoneNumberDao, ChangeOrderDao changeOrderDao) {
        this.setClosable(true);
        setModal(true);
        setDraggable(false);
        setWidth("60%");
        setHeight("95%");


        Button newChangeOrder = new Button("Nový změnový požadavek", clickEvent -> {
            this.close();
            UI.getCurrent().addWindow(new NewChangeOrderWindow(customerType, appService, customerDao, addressDao, phoneNumberDao, changeOrderDao));
        });
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("80%");
        horizontalLayout.setStyleName("align-center");

        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(true);
        verticalLayout.setMargin(true);

        horizontalLayout.addComponent(verticalLayout);
        verticalLayout.addComponent(newChangeOrder);
        horizontalLayout.setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);

        CustomerDetailType detailType = appService.getCustomerDetailType(customerType);

        verticalLayout.addComponent(new Label("<h3>Osobní informace</h3>", ContentMode.HTML));
        String name = "";
        for(String s : detailType.getFirstName()) {
            name+=s+" ";
        }
        for(String s: detailType.getSurname()) {
            name+=s+" ";
        }
        verticalLayout.addComponent(new Label("<strong>Jméno: </strong>"+name, ContentMode.HTML));
        verticalLayout.addComponent(new Label("<strong>Rodné číslo: </strong>"+detailType.getBirthNum(), ContentMode.HTML));
        verticalLayout.addComponent(new Label("<strong>Země: </strong>"+detailType.getCountryOfOrigin(), ContentMode.HTML));

        verticalLayout.addComponent(new Label("<h3>Kontaktní informace</h3>", ContentMode.HTML));
        int i = 1;
        for(AddressType address : detailType.getAddress()) {
            verticalLayout.addComponent(new Label("<strong>Adresa: "+i+"</strong>", ContentMode.HTML));
            verticalLayout.addComponent(new Label("<strong>Ulice: </strong>"+address.getStreetName()+" "+address.getStreetNum(), ContentMode.HTML));
            verticalLayout.addComponent(new Label("<strong>Kraj, Město: </strong>"+address.getCounty()+","+address.getCity()+" "+address.getCityPart(), ContentMode.HTML));
            verticalLayout.addComponent(new Label("<strong>PSČ, Země: </strong>"+address.getPostalCode()+", "+address.getCountry(), ContentMode.HTML));
        }

        int k = 1;
        for(PhoneType phone : detailType.getPhoneNum()) {
            PhoneNumber.PhoneType type = PhoneNumber.PhoneType.values()[phone.getPhoneNumberType().intValue()];
            verticalLayout.addComponent(new Label("<strong>Telefoní číslo: "+k+"</strong>", ContentMode.HTML));
            verticalLayout.addComponent(new Label("<strong>Typ: </strong>"+type.name(), ContentMode.HTML));
            verticalLayout.addComponent(new Label("<strong>Číslo (Země, Město, Číslo): </strong>"+phone.getCountryCode()+" "+phone.getCityCode()+" "+phone.getPhoneNum(), ContentMode.HTML));
        }
        this.setContent(horizontalLayout);
    }
}
