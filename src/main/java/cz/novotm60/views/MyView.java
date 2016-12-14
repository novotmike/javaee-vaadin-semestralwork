package cz.novotm60.views;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import cz.novotm60.util.Utils;

import javax.annotation.PostConstruct;

@CDIView
public class MyView extends Panel implements View, IView{

    @PostConstruct
    public void init() {
        this.setSizeFull();
        VerticalLayout vl = new VerticalLayout();
        vl.addComponents(getHeader(), getBody(), getFooter());
        setContent(vl);
    }

    public Component getHeader() {
        Panel panel = new Panel();
        HorizontalLayout hr = new HorizontalLayout();
        hr.setSpacing(true);
        Button showClients = new Button("Seznam klientů", clickEvent -> UI.getCurrent().getNavigator().navigateTo(Utils.CLIENT_URI_FRAGMENT));
        Button showRequest = new Button("Seznam požadavků na změnu", clickEvent -> UI.getCurrent().getNavigator().navigateTo(Utils.REQ_URI_FRAGMENT));
        Button newRequest = new Button("Nový požadavek na změnu", clickEvent -> UI.getCurrent().getNavigator().navigateTo(Utils.NEW_REQ_URI_FRAGMENT));
        Button logout = new Button("Odhlásit se", clickEvent -> UI.getCurrent().getNavigator().navigateTo("logout"));
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
        HorizontalLayout vl = new HorizontalLayout();
        vl.addStyleName("container");
        vl.addStyleName("align-center");
        vl.setWidth("100%");
        vl.addComponent(generateBodyContent());
        return vl;
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
        this.setSizeFull();
        VerticalLayout vl = new VerticalLayout();
        vl.addComponents(getHeader(), getBody(), getFooter());
        setContent(vl);
    }
}
