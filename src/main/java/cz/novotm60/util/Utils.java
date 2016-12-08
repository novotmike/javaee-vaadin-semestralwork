package cz.novotm60.util;

import com.vaadin.ui.UI;

public class Utils {

    public static final String DEFAULT_URI_FRAGMENT = "";
    public static final String CLIENT_URI_FRAGMENT = "clients";
    public static final String REQ_URI_FRAGMENT = "requests";
    public static final String NEW_REQ_URI_FRAGMENT = "new-request";
    public static final String LOGOUT_URI_FRAGMENT = "logout";

    public static void removeLogoutFragment() {
        if(UI.getCurrent().getPage().getUriFragment() != null && UI.getCurrent().getPage().getUriFragment().equals("!logout")) {
            UI.getCurrent().getPage().setUriFragment("");
        }
    }


}
