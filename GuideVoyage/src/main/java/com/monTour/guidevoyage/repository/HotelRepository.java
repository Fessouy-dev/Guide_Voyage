package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findBynValideFalse();
    List<Hotel> findBynValideTrue();
}
