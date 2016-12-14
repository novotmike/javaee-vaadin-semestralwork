package cz.novotm60.views;

import com.vaadin.data.Item;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.dao.CustomerDao;
import cz.novotm60.model.entity.Customer;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CustomerType;

import javax.inject.Inject;
import java.math.BigInteger;

public class ClientsView extends MyView{

    @Inject
    CustomerDao customerDao;

    @Inject
    AppService appService;

    private Table table;

    @Override
    public Component generateBodyContent() {

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("100%");
        verticalLayout.setSpacing(true);

        verticalLayout.addComponent(new Label("<h3>Seznam klientů</h3>", ContentMode.HTML));

        table = new Table();
        table.setWidth("100%");
        table.setPageLength(10);
        table.addContainerProperty("first", String.class, null);
        table.addContainerProperty("last", String.class, null);
        table.addContainerProperty("id", String.class, null);
        table.addContainerProperty("change", Button.class, null);
        table.setColumnHeaders(new String[]{"Jméno", "Příjmení", "ID Klienta", "Nový změnový požadavek"});
        table.setColumnReorderingAllowed(false);

        verticalLayout.addComponent(table);
        for(CustomerType c : appService.getAllCustomers(BigInteger.ONE, BigInteger.TEN)) {
            addCustomerToDatabase(c);
        }

        return verticalLayout;
    }

    public void addCustomerToDatabase(CustomerType customer) {
        Object newItemId = table.addItem();
        Item row = table.getItem(newItemId);

        row.getItemProperty("first").setValue(customer.getFirstName());
        row.getItemProperty("last").setValue(customer.getSurname());
        row.getItemProperty("id").setValue(customer.getId().toString());
        Button addNewButton = new Button("Vytvořit nový změnový požadavek");
        addNewButton.setWidth("100%");
        row.getItemProperty("change").setValue(addNewButton);
    }

}
