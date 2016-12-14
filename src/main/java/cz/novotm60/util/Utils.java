package cz.novotm60.util;

import com.vaadin.ui.UI;
import cz.novotm60.model.entity.Customer;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;

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

    public static boolean checkPassword(String password, String hash) {
        try {
            if(Utils.shaHash(password).equals(hash)) {
                return true;
            }else {
                return false;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String shaHash(String input) throws NoSuchAlgorithmException {
        return DigestUtils.sha256Hex(input);
    }

    public static String getCustomerFullName(Customer customer) {
        String name = "";
        for(String fname : customer.getFirstName()) {
            name+=fname+" ";
        }

        for(int i = 0; i<customer.getSurName().size(); i++) {
            if(i == customer.getSurName().size()-1) {
                name+=customer.getSurName().get(i);
            }else {
                name+=customer.getSurName().get(i)+" ";
            }
        }
        return name;
    }


}
