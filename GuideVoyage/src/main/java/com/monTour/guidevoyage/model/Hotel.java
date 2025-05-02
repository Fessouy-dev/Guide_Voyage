package com.monTour.guidevoyage.model;

import jakarta.persistence.Entity;




@Entity

public class Hotel extends Endroit{

    private int nombreEtoiles;

    public Hotel() {
        super();

    }

    public Hotel(long id, String nom, String description, String localisation, String photo, Boolean nValide, int nombreEtoiles) {
        super(id, nom, description, localisation, photo, nValide);
        this.nombreEtoiles = nombreEtoiles;
    }
    public int getNombreEtoiles() {
        return nombreEtoiles;
    }

    public void setNombreEtoiles(int nombreEtoiles) {
        this.nombreEtoiles = nombreEtoiles;
    }

}
