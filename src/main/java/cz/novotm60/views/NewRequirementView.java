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
import cz.novotm60.views.forms.ChangeOrderForm;


import javax.inject.Inject;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class NewRequirementView extends MyView {

    @Inject
    AppService appService;

    @Inject
    AddressDao addressDao;

    @Inject
    CustomerDao customerDao;

    @Inject
    PhoneNumberDao phoneNumberDao;

    @Inject
    ChangeOrderDao changeOrderDao;

    private VerticalLayout layout;

    @Override
    public Component generateBodyContent() {
        layout = new VerticalLayout();
        generateContent();
        return layout;
    }

    public void generateContent() {
        layout.addComponent(new ChangeOrderForm(appService,customerDao,addressDao,phoneNumberDao, changeOrderDao));
    }


}
