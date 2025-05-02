package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

@Entity

public  class PersonnalisationParVille extends Personnalisation {

    @ManyToOne
    private Ville villeCible;
    private double budget;

    public PersonnalisationParVille() {

    }

    public PersonnalisationParVille(int id, User user, Ville villeCible , double budget) {
        super(id, user);
        this.villeCible = villeCible;
        this.budget = budget;
    }

    public Ville getVilleCible() {
        return villeCible;
    }

    public void setVilleCible(Ville villeCible) {
        this.villeCible = villeCible;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
