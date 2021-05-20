package com.mycompany.myapp.views;

import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.services.ReservationService;

public class ReservationDetailForm extends Form {
Form current;
    ReservationService reservationService = new ReservationService();
    public ReservationDetailForm(Form previous, Reservation reservation) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Reservation Details");
        setLayout(BoxLayout.y());
               /* *** *YOUR CODE GOES HERE* *** */
        Label dateLabel = new Label("Date: "+reservation.getDate());
        Label statusLabel = new Label("Status: "+reservation.getStatus());
        Label placeLabel = new Label("Nombre place: "+reservation.getPlaces());
        Button cancelButton = new Button("Cancel Reservation");
        
        if(!reservation.getStatus().equals("ENATTENTE"))
            cancelButton.setEnabled(false);
        else cancelButton.setEnabled(true);
        
        cancelButton.addActionListener(evt -> {
            // reservation action
            reservationService.deleteReservation(reservation.getId());
            previous.showBack();
        });
        addAll(dateLabel, statusLabel, placeLabel,cancelButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Do Not forget!", new Message("Do: " + reservation.getIdRandonnee() + " it's on: " + reservation.getDate()));
        });
    }
}
