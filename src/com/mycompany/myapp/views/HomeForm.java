package com.mycompany.myapp.views;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;

public class HomeForm extends Form {
    Form current;
    public HomeForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("WELCOME");
        setLayout(BoxLayout.y());
        setScrollableY(false);
        /* *** *YOUR CODE GOES HERE* *** */
        addAll(new Label("Choose an option from side menu"));

        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home", FontImage.createMaterial(FontImage.MATERIAL_HOME, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new HomeForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("Randonnee", FontImage.createMaterial(FontImage.MATERIAL_MOVIE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new RandonneeForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("My Reservation", FontImage.createMaterial(FontImage.MATERIAL_ARCHIVE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ReservationForm().show();
        });
    }
}
