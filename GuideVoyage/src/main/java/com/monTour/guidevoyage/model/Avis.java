package com.monTour.guidevoyage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Avis {
    @Id
    @GeneratedValue
    private int id;
    private String contenu;
    private int note;
    private LocalDateTime date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Endroit endroit;

    public Avis() {

    }

    public Avis(int id, String contenu, int note, LocalDateTime date) {
        this.id = id;
        this.contenu = contenu;
        this.note = note;
        this.date = date;

    }


}

