package cz.novotm60.views.windows;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.views.forms.ChangeOrderForm;

import java.util.ArrayList;

public class NewChangeOrderWindow extends Window {

    public NewChangeOrderWindow(ArrayList<CustomerType> customerTypes, CustomerType customerType) {
        this.setClosable(true);
        setModal(true);
        setDraggable(false);
        setWidth("90%");
        setHeight("95%");
        this.setCaption("Nový požadavek na změnu");
        HorizontalLayout vl = new HorizontalLayout();
        vl.addStyleName("container");
        vl.addStyleName("align-center");
        vl.setWidth("100%");
        vl.addComponent(new ChangeOrderForm(customerType, customerTypes, this));
        setContent(vl);
    }
}
