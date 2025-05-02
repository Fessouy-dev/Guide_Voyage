package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Custom query methods can be defined here if needed
    // For example, find restaurants by city, cuisine type, etc.
}
