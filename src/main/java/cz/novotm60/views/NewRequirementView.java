package cz.novotm60.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class NewRequirementView extends MyView implements View{

    public NewRequirementView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Vytvořit nový požadavek na změnu</h3>", ContentMode.HTML);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        new Notification("New Requirement!").show(Page.getCurrent());
    }
}
