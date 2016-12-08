package cz.novotm60.views.handlers;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import cz.novotm60.session.SessionHandler;
import cz.novotm60.util.Utils;
import cz.novotm60.views.ClientsView;
import cz.novotm60.views.NewRequirementView;
import cz.novotm60.views.RequirementsView;

import javax.inject.Inject;

public class NavigationHandler {

    @Inject
    ClientsView clientsView;

    @Inject
    NewRequirementView newRequirementView;

    @Inject
    RequirementsView requirementsView;

    Navigator navigator;

    public NavigationHandler() {
        navigator = new Navigator(UI.getCurrent().getUI(), UI.getCurrent().getUI());
        navigator.addView(Utils.DEFAULT_URI_FRAGMENT, new ClientsView());
        navigator.addView(Utils.CLIENT_URI_FRAGMENT, new ClientsView());
        navigator.addView(Utils.NEW_REQ_URI_FRAGMENT, new NewRequirementView());
        navigator.addView(Utils.REQ_URI_FRAGMENT, new RequirementsView());
        navigator.addView(Utils.LOGOUT_URI_FRAGMENT, new LogoutView());
        navigator.setErrorView(new ClientsView());
    }

    public void handleShowAllClients() {
        navigator.navigateTo(Utils.CLIENT_URI_FRAGMENT);
    }

    public void handleShowAllRequests() {
        navigator.navigateTo(Utils.REQ_URI_FRAGMENT);
    }

    public  void handleCreateNewRequest() {
        navigator.navigateTo(Utils.NEW_REQ_URI_FRAGMENT);
    }

    public  void handleLogOut() {
        SessionHandler.logOutUser();
        SessionHandler.reloadPage("");
    }

}
