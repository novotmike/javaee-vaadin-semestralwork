package cz.novotm60.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import cz.novotm60.views.MyView;
import cz.novotm60.views.handlers.NavigationHandler;

public class LogoutView extends MyView {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        new NavigationHandler().handleLogOut();
        UI.getCurrent().getPage().setUriFragment("");
    }
}
