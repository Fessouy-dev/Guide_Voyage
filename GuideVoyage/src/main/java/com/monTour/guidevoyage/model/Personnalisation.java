package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Personnalisation {
    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Personnalisation() {

    }

    public Personnalisation(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}