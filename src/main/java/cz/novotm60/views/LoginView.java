package cz.novotm60.views;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import cz.novotm60.session.SessionHandler;
import cz.novotm60.util.Utils;

public class LoginView extends MyView {

    public LoginView() {
        super();
    }

    @Override
    public Component getBody() {
        HorizontalLayout container = new HorizontalLayout();
        container.addStyleName("align-center");
        container.setWidth("100%");
        container.setMargin(true);
        container.setSpacing(true);
        container.addComponent(generateBodyContent());
        return container;
    }

    public Component getHeader() {
        Panel panel = new Panel();
        HorizontalLayout hr = new HorizontalLayout();
        hr.setSpacing(true);
        HorizontalLayout container = new HorizontalLayout();
        container.setMargin(true);
        container.setWidth("100%");
        hr.addStyleName("align-center");
        hr.addComponent(new Label("<h1>Login</h1>", ContentMode.HTML));
        container.addComponent(hr);
        container.setComponentAlignment(hr, Alignment.MIDDLE_CENTER);
        panel.setContent(container);
        return panel;
    }

    @Override
    public Component generateBodyContent() {

        HorizontalLayout hr = new HorizontalLayout();
        hr.setWidth("100%");
        hr.setMargin(true);
        VerticalLayout vl = new VerticalLayout();
        vl.setSpacing(true);
        vl.addStyleName("align-center");
        hr.addComponent(vl);
        hr.setComponentAlignment(vl, Alignment.MIDDLE_CENTER);
        vl.setWidth("300px");
        TextField usernameField = new TextField("Username");
        usernameField.setInputPrompt("Username");
        usernameField.setNullRepresentation("");
        usernameField.addValidator(new StringLengthValidator("Username must be longer than 3 characters!", 4, 255, true));
        usernameField.setRequired(true);
        usernameField.setWidth("100%");
        vl.addComponent(usernameField);
        PasswordField passwordField = new PasswordField("Password");
        passwordField.setInputPrompt("Password");
        passwordField.setNullRepresentation("");
        passwordField.addValidator(new StringLengthValidator("Password must be longer than 2 characters", 3, 255, true));
        passwordField.setRequired(true);
        passwordField.setWidth("100%");
        vl.addComponent(passwordField);
        Button login = new Button("Login", clickEvent -> {
            Utils.removeLogoutFragment();
            try {
                usernameField.validate();
                passwordField.validate();
                if (usernameField.isValid() && passwordField.isValid()) {
                    if (SessionHandler.logInUser(usernameField.getValue(), passwordField.getValue())) {
                        UI.getCurrent().getPage().reload();
                        new Notification("Přihlášení proběhlo úspěšně!").show(Page.getCurrent());
                    } else {
                        new Notification("Špatné uživatelské jméno nebo heslo!").show(Page.getCurrent());
                    }

                }
            }catch(Validator.InvalidValueException e) {
                new Notification("Zadané údaje nejsou validní!").show(Page.getCurrent());
            }
        });
        login.setWidth("100%");
        vl.addComponent(login);
        return hr;
    }
}
