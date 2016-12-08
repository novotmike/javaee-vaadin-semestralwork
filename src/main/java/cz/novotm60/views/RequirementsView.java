package cz.novotm60.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class RequirementsView extends MyView {

    public RequirementsView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Seznam požadavků na změnu</h3>", ContentMode.HTML);
    }

}
