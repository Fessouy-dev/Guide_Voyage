package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
