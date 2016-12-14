package cz.novotm60.views.forms;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.entity.Customer;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CustomerType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.math.BigInteger;
import java.util.ArrayList;

public class ChangeOrderForm extends VerticalLayout{

    private CustomerType customerType;
    private ArrayList<CustomerType> customerTypes;
    private Window window;
    public ComboBox clientCombobox, choCombobox;
    public GridLayout form;

    public ChangeOrderForm() {
        this(null, null, null);
    }

    public void init() {
        generateContent();
    }

    public ChangeOrderForm(ArrayList<CustomerType> customerTypes) {
        this(null, customerTypes, null);
    }

    public ChangeOrderForm(CustomerType customerType, ArrayList<CustomerType> customerTypes, Window window) {
        this.customerType = customerType;
        this.customerTypes = customerTypes;
        this.window = window;
        //if(customerTypes != null)
          //  generateContent();
        generateContent();
    }

    public void generateContent() {
        this.setWidth("100%");
        this.setSpacing(true);
        this.setMargin(true);

        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setSizeFull();
        gridLayout.setSpacing(true);
        form = new GridLayout(2, 1);
        form.setWidth("100%");
        form.setSpacing(true);

        if(customerType == null) {
            clientCombobox = new ComboBox();
            for(CustomerType ct : customerTypes) {
                clientCombobox.addItem(ct);
                gridLayout.addComponent(clientCombobox);
            }
            clientCombobox.setCaption("Klient");
        }else {
            gridLayout.addComponent(new Label("<h3>Klient: "+ customerType.getFirstName()+" "+customerType.getSurname()+"</h3>", ContentMode.HTML));
        }

        choCombobox = new ComboBox();
        choCombobox.setCaption("Typ požadavku");
        for(Customer.RequestType rt : Customer.RequestType.values()) {
            if(clientCombobox == null && rt == Customer.RequestType.ACTIVE)
                continue;

            choCombobox.addItem(rt);
        }
        gridLayout.addComponent(choCombobox);
        this.addComponent(gridLayout);
        this.addComponent(form);
        if(clientCombobox != null)
            clientCombobox.addValueChangeListener(clickEvent -> clientChanged());
        choCombobox.addValueChangeListener(clickEvent -> typeChanged());
    }

    public void clientChanged() {
        if(clientCombobox.getValue() == null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
            showForm();
        }else if(clientCombobox.getValue() != null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
            showForm();
        }else {
            hideForm();
        }
    }

    public void typeChanged() {
        if(clientCombobox != null) {
            if (clientCombobox.getValue() == null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.ACTIVE) {
                showForm();
            } else if (clientCombobox.getValue() != null && choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                showForm();
            } else {
                hideForm();
            }
        }else {
            if(choCombobox.getValue() != null && choCombobox.getValue() == Customer.RequestType.INCHANGE) {
                showForm();
            }else {
                hideForm();
            }
        }
    }

    public void showForm() {
        form.addComponent(new Label("FORM!"));
    }

    public void hideForm() {
        form.removeAllComponents();
    }

}
