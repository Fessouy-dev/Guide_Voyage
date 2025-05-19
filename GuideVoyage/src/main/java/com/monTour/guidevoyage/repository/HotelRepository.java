package com.monTour.guidevoyage.repository;

import com.monTour.guidevoyage.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findBynValideFalse();
    List<Hotel> findBynValideTrue();
}
