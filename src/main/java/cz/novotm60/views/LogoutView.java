package cz.novotm60.views;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import cz.novotm60.session.SessionHandler;

public class LogoutView extends MyView {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        SessionHandler.logOutUser();
        SessionHandler.reloadPage("");
        UI.getCurrent().getPage().setUriFragment("");
    }
}
