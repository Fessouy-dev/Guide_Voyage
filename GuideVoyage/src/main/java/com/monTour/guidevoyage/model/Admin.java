package com.monTour.guidevoyage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "admin",
        uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;


    @NotBlank(message="le nom est obligatoire")
    @Column(nullable = false)
    private String name ;
    @NotBlank
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    @Column(unique = true, nullable = false)
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Column(nullable = false)
    private String motDePasse;
    public Admin() {

    }

    public Admin(long id, String name, String email, String motDepasse) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.motDePasse = motDepasse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDepasse) {
        this.motDePasse = motDepasse;
    }
}
