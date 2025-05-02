package com.monTour.guidevoyage.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Ville {
    @Id
    @GeneratedValue
    private int id;
    private String nom;
    private String pays;

    @OneToMany(mappedBy = "ville", cascade = CascadeType.ALL)
    private List<Endroit> endroits = new ArrayList<>();
    public Ville (){

    }
    public Ville(String nom, String pays) {
        this.nom = nom;
        this.pays = pays;
    }


}
