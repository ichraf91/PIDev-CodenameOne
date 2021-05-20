package com.mycompany.myapp.entities;

import java.util.Comparator;

public class Reservation {
    private int id;
    private String date;
    private String status;
    private int places;
    private int idUser;
    private int idRandonnee;

    public Reservation() {
    }

    public Reservation(String date, String status, int places, int idUser, int idRandonnee) {
        this.date = date;
        this.status = status;
        this.places = places;
        this.idUser = idUser;
        this.idRandonnee = idRandonnee;
    }

    public Reservation(int id, String date, String status, int places, int idUser, int idRandonnee) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.places = places;
        this.idUser = idUser;
        this.idRandonnee = idRandonnee;
    }

    // Constrictor for show
    public Reservation(int id, String date, String status, int places, int idUser) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.places = places;
        this.idUser = idUser;
    }
    

    // Constructor for adding
    public Reservation(int places, int idUser, int idRandonnee) {
        this.places = places;
        this.idUser = idUser;
        this.idRandonnee = idRandonnee;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRandonnee() {
        return idRandonnee;
    }

    public void setIdRandonnee(int idRandonnee) {
        this.idRandonnee = idRandonnee;
    }
    public static Comparator<Reservation> statusComparator = new Comparator<Reservation>() {
        @Override
        public int compare(Reservation o1, Reservation o2) {
            return (int) (o1.getStatus().toLowerCase().compareTo(o2.getStatus().toLowerCase()));
        }
    };
}
