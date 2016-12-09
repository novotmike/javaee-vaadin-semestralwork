package cz.novotm60.controllers;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import cz.novotm60.session.SessionHandler;
import cz.novotm60.util.Utils;
import cz.novotm60.views.ClientsView;
import cz.novotm60.views.LoginView;
import cz.novotm60.views.NewRequirementView;
import cz.novotm60.views.RequirementsView;
import cz.novotm60.views.LogoutView;

import javax.inject.Inject;

@CDIUI("")
@Theme("mytheme")
@Title("Semestral Work: Profinit")
public class IndexController extends UI {

    @Inject
    ClientsView clientsView;

    @Inject
    NewRequirementView newRequirementView;

    @Inject
    RequirementsView requirementsView;

    @Inject
    LoginView loginView;

    @Inject
    LogoutView logoutView;

    Navigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        if(SessionHandler.isLoggedIn()) {
            navigator = new Navigator(this, this);
            navigator.addView(Utils.DEFAULT_URI_FRAGMENT, clientsView);
            navigator.addView(Utils.CLIENT_URI_FRAGMENT, clientsView);
            navigator.addView(Utils.NEW_REQ_URI_FRAGMENT, newRequirementView);
            navigator.addView(Utils.REQ_URI_FRAGMENT, requirementsView);
            navigator.addView(Utils.LOGOUT_URI_FRAGMENT, logoutView);
            navigator.setErrorView(clientsView);
            //setContent(clientsView);
        }else {
            Utils.removeLogoutFragment();
            setContent(loginView);
        }

    }
}
