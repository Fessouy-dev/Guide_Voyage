package com.monTour.guidevoyage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class CircuitRequest {
    @NotNull(message = "La latitude est requise")
    private double latitude;

    @JsonProperty("lieux_exclus")
    private List<Long> lieuxExclus;
    
    @NotNull(message = "La longitude est requise")
    private double longitude;
    
    @NotNull(message = "Le rayon de recherche est requis")
    @Min(value = 1, message = "Le rayon de recherche doit être supérieur à 0")
    @JsonProperty("rayon_recherche")
    private double rayonRecherche;
    
    @NotNull(message = "L'heure de début est requise")
    @Min(value = 0, message = "L'heure de début doit être entre 0 et 23")
    @JsonProperty("heure_debut")
    private int heureDebut;
    
    @NotNull(message = "L'heure de fin est requise")
    @Min(value = 0, message = "L'heure de fin doit être entre 0 et 23")
    @JsonProperty("heure_fin")
    private int heureFin;
    
    @NotNull(message = "La liste des activités est requise")
    private List<String> activites;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRayonRecherche() {
        return rayonRecherche;
    }

    public void setRayonRecherche(double rayonRecherche) {
        this.rayonRecherche = rayonRecherche;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public List<String> getActivites() {
        return activites;
    }

    public void setActivites(List<String> activites) {
        this.activites = activites;
    }
    public List<Long> getLieuxExclus() {
        return lieuxExclus;
    }
    public void setLieuxExclus(List<Long> lieuxExclus) {
        this.lieuxExclus = lieuxExclus;
    }
}