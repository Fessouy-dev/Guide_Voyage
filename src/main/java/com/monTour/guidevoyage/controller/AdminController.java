package com.monTour.guidevoyage.controller;

import com.monTour.guidevoyage.model.*;
import com.monTour.guidevoyage.service.ActiviteService;
import com.monTour.guidevoyage.service.HotelService;
import com.monTour.guidevoyage.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ActiviteService activiteService;

    @GetMapping("/admin/Activites/Ajouter")
    public String AfficherFormulaireAjoutActivite(Model model) {
        // Créer un nouvel objet Activite
        Activite activite = new Activite();
        activite.setVille(new Ville());
        model.addAttribute("activite", activite);
        model.addAttribute("typesActivite", TypeActivie.values());
        return "ajouterActivite";
    }

    @GetMapping("/admin/Hotels")
    public String afficherListeHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        model.addAttribute("activePage", "hotels");
        return "AfficherHotels";
    }

    @GetMapping("/api/hotels/{id}/photos")
    @ResponseBody
    public List<String> getHotelPhotos(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        List<String> allPhotos = new ArrayList<>();
        if (hotel != null) {
            allPhotos.add(hotel.getPhoto());
            if (hotel.getPhotos() != null) {
                allPhotos.addAll(hotel.getPhotos());
            }
        }
        return allPhotos;
    }
} 