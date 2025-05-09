package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.model.User;
import com.monTour.guidevoyage.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public User inscrireUtilisateur(User user) {
        if (utilisateurRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("L'email existe déjà");
        }
        return utilisateurRepository.save(user);
    }

    public User connecterUtilisateur(String email, String motDepasse) {
        return utilisateurRepository.findByEmailAndMotDepasse(email, motDepasse)
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
    }

    public long countUtilisateurs() {
        return utilisateurRepository.count();
    }
}




