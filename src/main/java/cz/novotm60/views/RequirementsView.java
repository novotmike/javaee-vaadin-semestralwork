package cz.novotm60.views;

import com.vaadin.data.Item;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.model.dao.ChangeOrderDao;
import cz.novotm60.model.entity.ChangeOrder;
import cz.novotm60.service.AppService;
import cz.novotm60.service.soap.CreateCustomerChangeOrder;
import cz.novotm60.service.soap.CustomerType;
import cz.novotm60.util.Converter;
import cz.novotm60.util.Utils;

import javax.inject.Inject;

public class RequirementsView extends MyView {

    @Inject
    ChangeOrderDao changeOrderDao;

    @Inject
    AppService appService;

    private Table table;

    @Override
    public Component generateBodyContent() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setWidth("100%");
        verticalLayout.setSpacing(true);

        verticalLayout.addComponent(new Label("<h3>Seznam požadavků na změnu</h3>", ContentMode.HTML));

        table = new Table();
        table.setWidth("100%");
        table.setPageLength(10);
        table.addContainerProperty("client", String.class, null);
        table.addContainerProperty("changeOrderType", String.class, null);
        table.addContainerProperty("sent", String.class, null);
        table.setColumnHeaders(new String[]{"Klient", "Změnový požadavek", "Odesláno"});
        table.setColumnReorderingAllowed(false);

        Button sendBatch = new Button("Odeslat požadavky", clickEvent -> {
            for(ChangeOrder ch : changeOrderDao.getAll()) {
                //Create Change Order Type
                CreateCustomerChangeOrder cho = Converter.createCustomerChangeOrder(ch);
                Object res = appService.sendChangeOrder(cho);
                if(res != null) {
                    ch.setSent(true);
                    changeOrderDao.update(ch);
                }
            }
            new Notification("Odesláno!").show(Page.getCurrent());
            table.removeAllItems();
            for(ChangeOrder c : changeOrderDao.getAll()) {
                addChangeOrderToDB(c);
            }
        });
        verticalLayout.addComponent(sendBatch);

        verticalLayout.addComponent(table);
        for(ChangeOrder c : changeOrderDao.getAll()) {
            addChangeOrderToDB(c);
        }

        return verticalLayout;
    }

    public void addChangeOrderToDB(ChangeOrder order) {
        Object newItemId = table.addItem();
        Item row = table.getItem(newItemId);

        row.getItemProperty("client").setValue(Utils.getCustomerFullName(order.getCustomer()));
        row.getItemProperty("changeOrderType").setValue(order.getRequestType().name());
        row.getItemProperty("sent").setValue((order.isSent())? "ANO" : "NE");
    }


}
