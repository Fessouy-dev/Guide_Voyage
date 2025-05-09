package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Activite;
import com.monTour.guidevoyage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    List<Activite> findByUser(User user);
    List<Activite> findBynValideFalse();
    List<Activite> findBynValideTrue();
} 