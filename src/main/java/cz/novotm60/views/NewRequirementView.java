package cz.novotm60.views;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class NewRequirementView extends MyView{

    public NewRequirementView() {
        super();
    }

    @Override
    public Component generateBodyContent() {
        return new Label("<h3>Vytvořit nový požadavek na změnu</h3>", ContentMode.HTML);
    }

}
