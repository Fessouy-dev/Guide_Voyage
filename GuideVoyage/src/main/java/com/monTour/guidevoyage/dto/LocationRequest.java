package com.monTour.guidevoyage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class LocationRequest {
    @JsonProperty("type_filtre")
    private String typeFiltre;

    @JsonProperty("ville_filtre")
    private String villeFiltre;

    @JsonProperty("texte_recherche")
    private String texteRecherche;

    @JsonProperty("lieux_exclus")
    private List<Long> lieuxExclus;

    public String getTypeFiltre() {
        return typeFiltre;
    }

    public void setTypeFiltre(String typeFiltre) {
        this.typeFiltre = typeFiltre;
    }

    public String getVilleFiltre() {
        return villeFiltre;
    }

    public void setVilleFiltre(String villeFiltre) {
        this.villeFiltre = villeFiltre;
    }

    public String getTexteRecherche() {
        return texteRecherche;
    }

    public void setTexteRecherche(String texteRecherche) {
        this.texteRecherche = texteRecherche;
    }

    public List<Long> getLieuxExclus() {
        return lieuxExclus;
    }

    public void setLieuxExclus(List<Long> lieuxExclus) {
        this.lieuxExclus = lieuxExclus;
    }
}