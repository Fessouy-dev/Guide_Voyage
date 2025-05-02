package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.jaas.JaasAuthenticationCallbackHandler;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {
    // Custom query methods can be defined here if needed
    // For example, find activities by city, type, etc.
}
