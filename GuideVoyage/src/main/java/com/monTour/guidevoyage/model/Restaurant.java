package com.monTour.guidevoyage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity

public class Restaurant extends Endroit {
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private Specialiter specialiter;

    public Restaurant() {
        super();

    }

    public Restaurant(long id, String nom, String description, String localisation, String photo, Boolean nValide, Specialiter specialiter) {
        super(id, nom, description, localisation, photo, nValide);
        this.specialiter = specialiter;
    }
    public Specialiter getSpecialiter() {
        return specialiter;
    }
    public void setSpecialiter(Specialiter specialiter) {
        this.specialiter = specialiter;
    }

}
