package com.mycompany.myapp.views;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ReservationService;
import com.mycompany.myapp.utils.Statics;

public class RandonneeDetailForm extends Form {
    Form current;
    ReservationService reservationService = new ReservationService();
    public RandonneeDetailForm(Form previous, Randonnee randonnee) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Randonnee Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        /* *THIS CODE USED TO DISPLAY IMAGE* */
        int deviceWidth = Display.getInstance().getDisplayWidth();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        Image image = URLImage.createToStorage(encImage, ""+randonnee.getId(), "http://127.0.0.1:8000/uploads/" + randonnee.getImage(), URLImage.RESIZE_SCALE);
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.setImage(image.fill(deviceWidth, deviceWidth));

        Label placeLabel = new Label("Destination: "+randonnee.getPlace());
        Label dateLabel = new Label("Date: "+randonnee.getDate());
        Label startHourLabel = new Label("Debut: "+randonnee.getStartHour());
        Label endHourLabel = new Label("Fin: "+randonnee.getEndHour());
        Label prixLabel = new Label("Prix: "+randonnee.getPrix()+"DT");
        Label placesLabel = new Label("Nombre place: "+randonnee.getPlaces()+"");
        Label numberLabel = new Label("Combien de place voulez-vous reserver?");
        TextField placesTextField = new TextField("", "Nombre de places", 30, TextArea.NUMERIC);
        Button showButton = new Button("Reserver");
        showButton.addActionListener(evt -> {
            Reservation reservation = new Reservation((int) Float.parseFloat(placesTextField.getText()), Statics.ID_LOGGED_USER, randonnee.getId());
            reservationService.addReservation(reservation);
            new ReservationForm().show();
        });
        addAll(imageViewer, placeLabel, dateLabel, prixLabel,startHourLabel, endHourLabel, placesLabel, numberLabel, placesTextField,showButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Let's participate this!", new Message("Check out this Randonnee: " + randonnee.getPlace() + " it's: " + randonnee.getPrix() + " DT"));
        });
    }
}
