package com.mycompany.myapp.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Randonnee;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandonneeService {

    public ArrayList<Randonnee> randonneeArrayList; 
    public static RandonneeService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public RandonneeService() {
        req = new ConnectionRequest();
    }

    public static RandonneeService getInstance() {
        if (instance == null) {
            instance = new RandonneeService();
        }
        return instance;
    }

    public ArrayList<Randonnee> parseRandonnee(String jsonText) { //Parsing Issues with id and date type
        try {
            randonneeArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> randonneeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) randonneeListJson.get("root");
            for (Map<String, Object> obj : list) {
                Randonnee randonnee = new Randonnee();
                randonnee.setId((int) Float.parseFloat(obj.get("id").toString()));
                randonnee.setDate(obj.get("date").toString().substring(0, 10));
                randonnee.setPlace(obj.get("lieu").toString());
                randonnee.setStartHour(obj.get("heureDepart").toString());
                randonnee.setEndHour(obj.get("heureRetour").toString());
                randonnee.setPlaces((int) Float.parseFloat(obj.get("nbrPlaces").toString()));
                randonnee.setImage(obj.get("photo").toString());
                randonnee.setPrix(Float.parseFloat(obj.get("prix").toString()));
                randonneeArrayList.add(randonnee);
            }
        } catch (IOException ex) {
            System.out.println("error");
        }
        return randonneeArrayList;
    }

    public ArrayList<Randonnee> showAll() {
        // String url = Statics.BASE_URL + "displayRandonnee"; // Add Symfony URL Here // ELMOCHKLA FEL BASE URL IDONT KNOW WHY 
        String url = "http://127.0.0.1:8000/displayRandonnee"; // Add Symfony URL Here
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                randonneeArrayList = parseRandonnee(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return randonneeArrayList;
    }
}
