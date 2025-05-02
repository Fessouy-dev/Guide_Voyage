package com.monTour.guidevoyage.model;

import jakarta.persistence.*;

import java.util.List;

import lombok.Data;
import java.util.List;
@Entity

@Table(name = "Utilisateur")
@Data
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String motDepasse;
    private Sex sex ;
    private int age;
    @OneToMany(mappedBy ="user")
    private List<Avis> avis;
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Personnalisation> personnalisations;
public User(){


}

    public User(int id, String name, String email, String motDepasse, Sex sex, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.motDepasse = motDepasse;
        this.sex = sex;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getMotDepasse() {
        return motDepasse;
    }

    public void setMotDepasse(String motDepasse) {
        this.motDepasse = motDepasse;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
