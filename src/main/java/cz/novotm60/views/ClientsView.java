package cz.novotm60.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class ClientsView extends MyView{

    public ClientsView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Seznam klientů</h3>", ContentMode.HTML);
    }

}
