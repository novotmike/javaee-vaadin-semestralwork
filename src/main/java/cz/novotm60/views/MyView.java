package cz.novotm60.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import cz.novotm60.views.handlers.NavigationHandler;

public abstract class MyView extends Panel implements View, IView{

    public MyView() {
        this.setSizeFull();
        VerticalLayout vl = new VerticalLayout();
        vl.addComponents(getHeader(), getBody(), getFooter());
        setContent(vl);
    }

    public Component getHeader() {
        Panel panel = new Panel();
        HorizontalLayout hr = new HorizontalLayout();
        hr.setSpacing(true);
        Button showClients = new Button("Seznam klientů", clickEvent -> new NavigationHandler().handleShowAllClients());
        Button showRequest = new Button("Seznam požadavků na změnu", clickEvent -> new NavigationHandler().handleShowAllRequests());
        Button newRequest = new Button("Nový požadavek na změnu", clickEvent -> new NavigationHandler().handleCreateNewRequest());
        Button logout = new Button("Odhlásit se", clickEvent -> new NavigationHandler().handleLogOut());
        showClients.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        showRequest.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        newRequest.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        logout.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        hr.addComponents(showClients, showRequest, newRequest, logout);
        hr.setComponentAlignment(showClients, Alignment.MIDDLE_CENTER);
        hr.setComponentAlignment(showRequest, Alignment.MIDDLE_CENTER);
        hr.setComponentAlignment(newRequest, Alignment.MIDDLE_CENTER);
        hr.setComponentAlignment(logout, Alignment.MIDDLE_CENTER);
        HorizontalLayout container = new HorizontalLayout();
        container.setMargin(true);
        container.setWidth("100%");
        hr.addStyleName("align-center");
        container.addComponent(hr);
        container.setComponentAlignment(hr, Alignment.MIDDLE_CENTER);
        panel.setContent(container);
        return panel;
    }

    public Component getBody() {
        HorizontalLayout container = new HorizontalLayout();
        container.addStyleName("align-center");
        container.setWidth("100%");
        container.setMargin(true);
        container.setSpacing(true);

        HorizontalLayout vl = new HorizontalLayout();
        vl.addStyleName("container");
        vl.addComponent(generateBodyContent());
        container.addComponent(vl);
        container.setComponentAlignment(vl, Alignment.MIDDLE_CENTER);
        return container;
    }

    public Component generateBodyContent() {
        return new Label("This is default content!");
    }

    public Component getFooter() {
        Label copyright = new Label("Copyright and Copy");
        copyright.setStyleName("align-center");
        HorizontalLayout hr = new HorizontalLayout();
        hr.setWidth("100%");
        hr.addComponent(copyright);
        hr.setComponentAlignment(copyright, Alignment.MIDDLE_CENTER);
        return hr;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
