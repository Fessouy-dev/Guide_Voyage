package com.monTour.guidevoyage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;

@Entity
public class Activite extends Endroit {
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private TypeActivie typeActivie;

    // Constructeur pour les propositions d'utilisateurs
    public Activite() {
        super();
        super.setNValide(false); // Par défaut, non validé
    }

    // Constructeur pour les ajouts directs par l'admin
    public Activite(long id, String nom, String description, String localisation, String photo, TypeActivie typeActivie) {
        super(id, nom, description, localisation, photo, true); // Directement validé
        this.typeActivie = typeActivie;
    }

    // Constructeur complet
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

    // Méthode pour approuver une proposition
    public void approuver() {
        super.setNValide(true);
    }

    // Méthode pour rejeter une proposition
    public void rejeter() {
        super.setNValide(false);
    }
}
