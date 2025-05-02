package com.monTour.guidevoyage.model;

import jakarta.persistence.Entity;

@Entity
public class Activite extends Endroit {
    private TypeActivie typeActivie;

    public Activite() {

    }

    public Activite(long id, String nom, String description, String localisation, String photo, Boolean nValide, TypeActivie typeActivie) {
        super(id, nom, description, localisation, photo, nValide);
        this.typeActivie = typeActivie;
    }

    public TypeActivie getTypeActivie() {
        return typeActivie;
    }

    public void setTypeActivie(TypeActivie typeActivie) {
        this.typeActivie = typeActivie;
    }
}
