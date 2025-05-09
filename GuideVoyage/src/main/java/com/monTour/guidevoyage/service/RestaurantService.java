package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.model.Restaurant;
import com.monTour.guidevoyage.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant ajouterRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        if (restaurant != null) {
            restaurant.setNom(restaurantDetails.getNom());
            restaurant.setDescription(restaurantDetails.getDescription());
            restaurant.setLocalisation(restaurantDetails.getLocalisation());
            restaurant.setPhoto(restaurantDetails.getPhoto());
            restaurant.setSpecialiter(restaurantDetails.getSpecialiter());
            restaurant.setVille(restaurantDetails.getVille());
            return restaurantRepository.save(restaurant);
        }
        return null;
    }
}
