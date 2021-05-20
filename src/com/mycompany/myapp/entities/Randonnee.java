package com.mycompany.myapp.entities;

import java.util.Comparator;

public class Randonnee {
    private int id;
    private String date;
    private String place;
    private String startHour;
    private String endHour;
    private int places;
    private String image;
    private float prix;

        public Randonnee() {
    }

    public Randonnee(String date, String place, String startHour, String endHour, int places, String image, float prix) {
        this.date = date;
        this.place = place;
        this.startHour = startHour;
        this.endHour = endHour;
        this.places = places;
        this.image = image;
        this.prix = prix;
    }

    public Randonnee(int id, String date, String place, String startHour, String endHour, int places, String image, float prix) {
        this.id = id;
        this.date = date;
        this.place = place;
        this.startHour = startHour;
        this.endHour = endHour;
        this.places = places;
        this.image = image;
        this.prix = prix;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public static Comparator<Randonnee> placeComparator = new Comparator<Randonnee>() {
        @Override
        public int compare(Randonnee o1, Randonnee o2) {
            return (int) (o1.getPlace().toLowerCase().compareTo(o2.getPlace().toLowerCase()));
        }
    };
}
