package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.model.Restaurant;
import com.monTour.guidevoyage.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    public final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
    public Restaurant ajouterRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = getRestaurantById(id);
        if (restaurant != null) {
            restaurant.setNom(restaurantDetails.getNom());
            restaurant.setDescription(restaurantDetails.getDescription());
            restaurant.setLocalisation(restaurantDetails.getLocalisation());
            
            // Ne mettre à jour la photo que si une nouvelle photo est fournie
            if (restaurantDetails.getPhoto() != null && !restaurantDetails.getPhoto().isEmpty()) {
                restaurant.setPhoto(restaurantDetails.getPhoto());
            }
            
            restaurant.setSpecialiter(restaurantDetails.getSpecialiter());
            restaurant.setVille(restaurantDetails.getVille());

            // Mise à jour des photos additionnelles
            if (restaurantDetails.getPhotos() != null) {
                restaurant.setPhotos(restaurantDetails.getPhotos());
            }

            return restaurantRepository.save(restaurant);
        }
        return null;
    }
}
