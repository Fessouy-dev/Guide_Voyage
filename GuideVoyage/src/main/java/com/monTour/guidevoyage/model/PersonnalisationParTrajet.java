package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PersonnalisationParTrajet extends Personnalisation {

    @ManyToOne
    private Ville villeDepart;
    @ManyToOne
    private Ville villeArrivee;
    private double Budeget ;
    @Temporal(TemporalType.DATE)
    private Date dateDepart;
    @Temporal(TemporalType.DATE)
    private Date dateArrivee;

    public PersonnalisationParTrajet() {

    }

    public PersonnalisationParTrajet(int id, User user, Ville villeDepart, Ville villeArrivee, double budeget, Date dateDepart, Date dateArrivee) {

        super(id, user);
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        Budeget = budeget;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public double getBudeget() {
        return Budeget;
    }

    public void setBudeget(double budeget) {
        Budeget = budeget;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }
}
