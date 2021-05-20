package com.mycompany.myapp.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reservation;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {

    public ArrayList<Reservation> reservationArrayList;
    public static ReservationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ReservationService() {
        req = new ConnectionRequest();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public ArrayList<Reservation> parseReservation(String jsonText) { //Parsing Issues with id and date type
        try {
            reservationArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reservation reservation = new Reservation();
                reservation.setId((int) Float.parseFloat(obj.get("id").toString()));
                reservation.setDate(obj.get("date").toString().substring(0,10));
                reservation.setPlaces((int) Float.parseFloat(obj.get("nbrPlaces").toString()));
                reservation.setStatus(obj.get("etat").toString());
                reservation.setIdUser(Statics.ID_LOGGED_USER);
                // MUST GET ID RANDO
                reservationArrayList.add(reservation);
            }
        } catch (IOException ex) {
        }
        return reservationArrayList;
    }

    public ArrayList<Reservation> showAll() {
        String url = "http://127.0.0.1:8000/reservation/api/myReservation?idUser="+Statics.ID_LOGGED_USER; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationArrayList = parseReservation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservationArrayList;
    }
    public boolean addReservation(Reservation reservation){
        String url = "http://127.0.0.1:8000/reservation/api/addReservation?idUser="+reservation.getIdUser()
                +"&idRandonnee="+reservation.getIdRandonnee()
                +"&places="+reservation.getPlaces();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public boolean deleteReservation(int id){
        String url = "http://127.0.0.1:8000/reservation/api/delete?id="+id ; // MUST CHANGE THIS
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
