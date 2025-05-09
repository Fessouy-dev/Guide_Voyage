package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Endroit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String description;
    private String localisation;
    private String photo;
    @ElementCollection
    @CollectionTable(name = "endroit_photos",
            joinColumns = @JoinColumn(name = "endroit_id"))
    @Column(name = "photo_url")
    private List<String> photos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Boolean nValide;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "ville_id")
    private Ville ville ;
    @OneToMany (mappedBy = "endroit")
    private List<Avis> avis ;
public Endroit() {

}

    public Endroit(long id, String nom, String description, String localisation, String photo, Boolean nValide) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.localisation = localisation;
        this.photo = photo;
        this.nValide = nValide;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public Boolean getNValide() {
        return nValide;
    }

    public void setNValide(Boolean nValide) {
        this.nValide = nValide;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    // Méthode utilitaire pour ajouter une photo
    public void addPhoto(String photoUrl) {
        if (this.photos == null) {
            this.photos = new ArrayList<>();
        }
        this.photos.add(photoUrl);
    }
}
