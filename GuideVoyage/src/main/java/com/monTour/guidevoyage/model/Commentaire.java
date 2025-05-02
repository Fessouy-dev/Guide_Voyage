package com.monTour.guidevoyage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Commentaire {
    @Id
    @GeneratedValue
    private int id;
    private String Contenu;
    private LocalDateTime date ;
    @ManyToOne
    private Post post;
public Commentaire() {

}

    public Commentaire(int id, String contenu, LocalDateTime date, Post post) {
        this.id = id;
        Contenu = contenu;
        this.date = date;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String contenu) {
        Contenu = contenu;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
