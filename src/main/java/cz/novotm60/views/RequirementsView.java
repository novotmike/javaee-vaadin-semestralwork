package cz.novotm60.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class RequirementsView extends MyView implements View{

    public RequirementsView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Seznam požadavků na změnu</h3>", ContentMode.HTML);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        new Notification("All Requirements view!").show(Page.getCurrent());
    }
}
