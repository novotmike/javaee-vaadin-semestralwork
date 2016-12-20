package cz.novotm60.views.windows;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import cz.novotm60.model.dao.AddressDao;
import cz.novotm60.model.dao.ChangeOrderDao;
import cz.novotm60.model.dao.CustomerDao;
import cz.novotm60.model.dao.PhoneNumberDao;
import cz.novotm60.model.entity.Address;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.views.forms.ChangeOrderForm;

import javax.inject.Inject;
import java.util.ArrayList;

public class NewChangeOrderWindow extends Window {

    public NewChangeOrderWindow(CustomerType customerType,AppService appService, CustomerDao customerDao, AddressDao addressDao, PhoneNumberDao phoneNumberDao, ChangeOrderDao changeOrderDao) {
        if(addressDao == null) {
            System.out.println("SHAME");
        }
        this.setClosable(true);
        setModal(true);
        setDraggable(false);
        setWidth("90%");
        setHeight("95%");
        this.setCaption("Nový požadavek na změnu");
        HorizontalLayout vl = new HorizontalLayout();
        vl.addStyleName("align-center");
        vl.setWidth("100%");
        ChangeOrderForm chf = new ChangeOrderForm(customerType, appService, customerDao, addressDao, phoneNumberDao, changeOrderDao);
        vl.addComponent(chf);
        vl.setComponentAlignment(chf, Alignment.MIDDLE_CENTER);
        setContent(vl);
    }
}
