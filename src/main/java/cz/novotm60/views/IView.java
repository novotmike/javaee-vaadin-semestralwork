package cz.novotm60.views;

import com.vaadin.ui.Component;

public interface IView {
    Component getHeader();
    Component getBody();
    Component generateBodyContent();
    Component getFooter();
}
