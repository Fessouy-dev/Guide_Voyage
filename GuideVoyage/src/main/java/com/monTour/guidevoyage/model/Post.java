package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String Contenu ;
    private LocalDateTime Date ;
    private String Photo;
    @ManyToOne
    private User user;
    @ManyToOne
    private Endroit endroit;
    @OneToMany(mappedBy="post")
     private List<Commentaire> commentaires;
    public Post(){

    }

    public Post(int id, String contenu, LocalDateTime date, String photo) {
        this.id = id;
        Contenu = contenu;
        Date = date;
        Photo = photo;
    }
}
