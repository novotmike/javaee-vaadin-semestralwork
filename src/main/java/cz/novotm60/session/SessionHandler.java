package cz.novotm60.session;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import cz.novotm60.model.dao.UserDao;
import cz.novotm60.model.entity.User;
import cz.novotm60.util.Utils;

import javax.inject.Inject;
import java.io.Serializable;

public class SessionHandler implements Serializable {

    public static boolean logInUser(User user, String password, String username) {
        if(user == null) {
            return false;
        }

        if(Utils.checkPassword(password, user.getPassword())) {
            VaadinSession.getCurrent().setAttribute("user", user);
            return true;
        }else {
            VaadinSession.getCurrent().setAttribute("user", null);
            return false;
        }
    }

    public static boolean isLoggedIn() {
        return ((User) VaadinSession.getCurrent().getAttribute("user")) != null;
    }

    public static void logOutUser() {
        VaadinSession.getCurrent().setAttribute("user", null);
        new Notification("Uživatel úspěšně odhlášen!").show(Page.getCurrent());
    }

    public static void reloadPage(String uriFragment) {
        UI.getCurrent().getPage().setUriFragment(uriFragment);
        UI.getCurrent().getPage().reload();
    }

}
