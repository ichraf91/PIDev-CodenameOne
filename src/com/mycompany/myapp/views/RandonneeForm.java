package com.mycompany.myapp.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.services.RandonneeService;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;
import java.util.Collections;

public class RandonneeForm extends Form {

    Form current;
    RandonneeService randonneeService = new RandonneeService();
    ArrayList<Randonnee> randonneeArrayList = new ArrayList<>();

    public RandonneeForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Randonnee List");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        randonneeArrayList = randonneeService.showAll();
        Collections.reverse(randonneeArrayList);
        fillData();
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Trier par destination", null, (evt) -> {
            removeAll();
             Collections.sort(randonneeArrayList, Randonnee.placeComparator);
            fillData();
        });
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

    public void fillData() {
        for (Randonnee randonnee : randonneeArrayList) {
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image image = URLImage.createToStorage(encImage, randonnee.getPlace() + randonnee.getId(), "http://127.0.0.1:8000/uploads/"+randonnee.getImage(), URLImage.RESIZE_SCALE);
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(randonnee.getPlace());
            multiButton.setTextLine2(randonnee.getDate());
            multiButton.setTextLine3("PRIX: " + randonnee.getPrix() + "DT");
            multiButton.setIcon(image);
            multiButton.setUIID(randonnee.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new RandonneeDetailForm(current, randonnee).show());
            add(multiButton);
        }
    }
}
