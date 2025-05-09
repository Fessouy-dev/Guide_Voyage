package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.model.Activite;
import com.monTour.guidevoyage.model.Hotel;
import com.monTour.guidevoyage.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public Hotel ajouterHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotelById(id);
        if (hotel != null) {
            hotel.setNom(hotelDetails.getNom());
            hotel.setDescription(hotelDetails.getDescription());
            hotel.setLocalisation(hotelDetails.getLocalisation());
            
            // Ne mettre à jour la photo que si une nouvelle photo est fournie
            if (hotelDetails.getPhoto() != null && !hotelDetails.getPhoto().isEmpty()) {
                hotel.setPhoto(hotelDetails.getPhoto());
            }
            
            hotel.setNombreEtoiles(hotelDetails.getNombreEtoiles());
            hotel.setVille(hotelDetails.getVille());

            // Mise à jour des photos additionnelles
            if (hotelDetails.getPhotos() != null) {
                hotel.setPhotos(hotelDetails.getPhotos());
            }

            return hotelRepository.save(hotel);
        }
        return null;
    }


}