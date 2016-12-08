package cz.novotm60.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class ClientsView extends MyView implements View{

    public ClientsView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Seznam klientů</h3>", ContentMode.HTML);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        new Notification("Clients view!").show(Page.getCurrent());
    }
}
