package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    // Custom query methods can be defined here if needed
    // For example, find hotels by city, rating, etc.
}
