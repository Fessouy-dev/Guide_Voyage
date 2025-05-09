package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<User, Long> {

   boolean existsByEmail(String email);

   Optional<User> findByEmail(String email);

   Optional<User> findByEmailAndMotDepasse(String email, String motDepasse);

   long count();
}
