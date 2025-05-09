package com.monTour.guidevoyage.controller;

import com.monTour.guidevoyage.model.*;
import com.monTour.guidevoyage.service.ActiviteService;
import com.monTour.guidevoyage.service.HotelService;
import com.monTour.guidevoyage.service.RestaurantService;
import com.monTour.guidevoyage.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class AdminController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/admin/Hotels/Ajouter")
    public String afficherFormulaireAjoutHotel(Model model) {
        Hotel hotel = new Hotel();
        hotel.setVille(new Ville());
        model.addAttribute("hotel", hotel);
        return "ajouterHotel";
    }

    @PostMapping("/admin/Hotels/Ajouter")
    public String ajouterHotel(
            @ModelAttribute("hotel") Hotel hotel,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {

        // Traitement de la photo principale
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = saveImage(photoFile, "hotels");
            hotel.setPhoto(fileName);
        }

        // Traitement des photos additionnelles
        if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
            for (MultipartFile file : additionalPhotos) {
                if (file != null && !file.isEmpty()) {
                    String fileName = saveImage(file, "hotels");
                    hotel.addPhoto(fileName);
                }
            }
        }

        hotel.setNValide(true);
        hotelService.ajouterHotel(hotel);
        return "redirect:/admin/home";
    }

    // Méthode utilitaire pour sauvegarder les images
    private String saveImage(MultipartFile file, String subdirectory) throws IOException {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + extension;

        // Définir le chemin où sauvegarder les images
        String uploadDir = "src/main/resources/static/images/" + subdirectory;
        Path uploadPath = Paths.get(uploadDir);

        // Créer le répertoire s'il n'existe pas
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Sauvegarder le fichier
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            // Retourner le chemin relatif pour être stocké dans la base de données
            return "/images/" + subdirectory + "/" + fileName;
        }
    }

    @GetMapping("/admin/Restaurants/Ajouter")
    public String afficherFormulaireAjoutRestaurant(Model model) {
        // Créer un nouvel objet Restaurant
        Restaurant restaurant = new Restaurant();
        model.addAttribute("specialites", Specialiter.values());
        restaurant.setVille(new Ville());
        model.addAttribute("restaurant", restaurant);
        return "ajouterRestaurant";
    }
    @PostMapping("/admin/Restaurants/Ajouter")
    public String ajouterRestaurant(
            @ModelAttribute("restaurant")Restaurant restaurant,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {
        // Traitement de la photo principale
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = saveImage(photoFile, "Restaurants");
            restaurant.setPhoto(fileName);

        }
        if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
            for (MultipartFile file : additionalPhotos) {
                if (file != null && !file.isEmpty()) {
                    String fileName = saveImage(file, "Restaurants");
                    restaurant.addPhoto(fileName);
                }
            }
        }
        restaurant.setNValide(true);
        restaurantService.ajouterRestaurant(restaurant);
        return "redirect:/admin/home";

    }
    @GetMapping("/admin/Activites/Ajouter")
    public String AfficherFormulaireAjoutActivite(Model model) {
        // Créer un nouvel objet Activite
        Activite activite = new Activite();
        model.addAttribute("typesActivite",TypeActivie.values());
        activite.setVille(new Ville());
        model.addAttribute("activite", activite);
        return "ajouterActivite";
    }
    @PostMapping("/admin/Activites/Ajouter")
    public String AjouterActivite(
            @ModelAttribute("activite")Activite activite,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {
        // Traitement de la photo principale
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = saveImage(photoFile, "Activites");
            activite.setPhoto(fileName);

        }
        if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
            for (MultipartFile file : additionalPhotos) {
                if (file != null && !file.isEmpty()) {
                    String fileName = saveImage(file, "Activites");
                    activite.addPhoto(fileName);
                }
            }
        }
        activite.setNValide(true);
        activiteService.ajouterActivite(activite);
        return "redirect:/admin/home";

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
    @GetMapping("/admin/supprimerHotel/{id}")
    public String supprimerHotel(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            // Supprimer la photo principale
            if (hotel.getPhoto() != null && !hotel.getPhoto().isEmpty()) {
                deleteImage(hotel.getPhoto());
            }

            // Supprimer les photos additionnelles
            if (hotel.getPhotos() != null) {
                for (String photo : hotel.getPhotos()) {
                    if (photo != null && !photo.isEmpty()) {
                        deleteImage(photo);
                    }
                }
            }

            hotelService.deleteHotel(id);
        }
        return "redirect:/admin/Hotels";
    }

    // Méthode utilitaire pour supprimer une image
    private void deleteImage(String imagePath) {
        try {
            // Convertir le chemin relatif en chemin absolu
            String uploadDir = "src/main/resources/static";
            Path filePath = Paths.get(uploadDir + imagePath);

            // Vérifier si le fichier existe et le supprimer
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression de l'image: " + imagePath);
            e.printStackTrace();
        }
    }

    @GetMapping("/admin/ModifierHotel/{id}")
    public String afficherFormulaireModifierHotel(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "ModifierHotel";
        }
        return "redirect:/admin/Hotels";
    }
    @PostMapping("/admin/ModifierHotel/{id}")
    public String modifierHotel(
            @PathVariable Long id,
            @ModelAttribute("hotel") Hotel hotel,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {

        Hotel existingHotel = hotelService.getHotelById(id);
        if (existingHotel != null) {
            // Préserver la photo existante si aucune nouvelle photo n'est fournie
            if (photoFile != null && !photoFile.isEmpty()) {
                String fileName = saveImage(photoFile, "Hotels");
                hotel.setPhoto(fileName);
            } else {
                hotel.setPhoto(existingHotel.getPhoto());
            }

            // Traitement des photos additionnelles
            if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
                for (MultipartFile file : additionalPhotos) {
                    if (file != null && !file.isEmpty()) {
                        String fileName = saveImage(file, "Hotels");
                        hotel.addPhoto(fileName);
                    }
                }
            } else {
                hotel.setPhotos(existingHotel.getPhotos());
            }

            hotelService.updateHotel(id, hotel);
        }
        return "redirect:/admin/Hotels";
    }
    @GetMapping("/admin/Restaurants")
    public String afficherRestaurants(Model model){
        List<Restaurant> restaurants= restaurantService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("activePage", "restaurants");
        return "AfficherRestaurants";
    }
    @GetMapping("/admin/restaurants/{id}/photos")
    @ResponseBody
    public List<String> getRestaurantPhotos(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<String> allPhotos = new ArrayList<>();
        if (restaurant != null) {
            allPhotos.add(restaurant.getPhoto());
            if (restaurant.getPhotos() != null) {
                allPhotos.addAll(restaurant.getPhotos());
            }
        }
        return allPhotos;
    }
    @GetMapping("/admin/ModifierRestaurant/{id}")
    public String afficherFormulaireModifierRestaurant(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            model.addAttribute("restaurant", restaurant);
            model.addAttribute("specialites", Specialiter.values());
            return "ModifierRestaurant";
        }


        return "redirect:/admin/Restaurants";
    }
    @PostMapping("/admin/ModifierRestaurant/{id}")
    public String modifierRestaurant(
            @PathVariable Long id,
            @ModelAttribute("restaurant") Restaurant restaurant,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {

        Restaurant existingRestaurant = restaurantService.getRestaurantById(id);
        if (existingRestaurant != null) {
            // Préserver la photo existante si aucune nouvelle photo n'est fournie
            if (photoFile != null && !photoFile.isEmpty()) {
                String fileName = saveImage(photoFile, "Restaurants");
                restaurant.setPhoto(fileName);
            } else {
                restaurant.setPhoto(existingRestaurant.getPhoto());
            }

            // Traitement des photos additionnelles
            if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
                for (MultipartFile file : additionalPhotos) {
                    if (file != null && !file.isEmpty()) {
                        String fileName = saveImage(file, "Restaurants");
                        restaurant.addPhoto(fileName);
                    }
                }
            } else {
                restaurant.setPhotos(existingRestaurant.getPhotos());
            }

            restaurantService.updateRestaurant(id, restaurant);
        }
        return "redirect:/admin/Restaurants";
    }
    @GetMapping("/admin/supprimerRestaurant/{id}")
    public String supprimerReestaurant(@PathVariable Long id){
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            // Supprimer la photo principale
            if (restaurant.getPhoto() != null && !restaurant.getPhoto().isEmpty()) {
                deleteImage(restaurant.getPhoto());
            }

            // Supprimer les photos additionnelles
            if (restaurant.getPhotos() != null) {
                for (String photo : restaurant.getPhotos()) {
                    if (photo != null && !photo.isEmpty()) {
                        deleteImage(photo);
                    }
                }
            }
            restaurantService.deleteRestaurant(id);

        }

        return "redirect:/admin/Restaurants";
    }
    @GetMapping("/admin/activites")
    public String afficherActivites(Model model){
        List<Activite> activites= activiteService.getAllActivites();
        model.addAttribute("activites", activites);
        model.addAttribute("activePage", "activites");
        return "AfficherActivites";
    }
    @GetMapping("/admin/activites/{id}/photos")
    @ResponseBody
    public List<String> getActivitePhotos(@PathVariable Long id) {
        Activite activite = activiteService.getActiviteById(id);
        List<String> allPhotos = new ArrayList<>();
        if (activite != null) {
            allPhotos.add(activite.getPhoto());
            if (activite.getPhotos() != null) {
                allPhotos.addAll(activite.getPhotos());
            }
        }
        return allPhotos;
    }
    @GetMapping("/admin/supprimerActivite/{id}")
    public String supprimerActivite(@PathVariable Long id){
        Activite activite = activiteService.getActiviteById(id);
        if(activite != null){
            // Supprimer la photo principale
            if (activite.getPhoto() != null && !activite.getPhoto().isEmpty()) {
                deleteImage(activite.getPhoto());
            }

            // Supprimer les photos additionnelles
            if (activite.getPhotos() != null) {
                for (String photo : activite.getPhotos()) {
                    if (photo != null && !photo.isEmpty()) {
                        deleteImage(photo);
                    }
                }
            }
            activiteService.DeleteActivite(id);
        }

        return "redirect:/admin/activites";
    }

    @GetMapping("/admin/ModifierActivite/{id}")
    public String afficherFormulaireModifierActivite(@PathVariable Long id, Model model) {
        Activite activite = activiteService.getActiviteById(id);
        if (activite != null) {
            model.addAttribute("activite", activite);
            model.addAttribute("Activiter", TypeActivie.values());
            return "ModifierActivite";
        }
        return "redirect:/admin/Activites";
    }
    @PostMapping("/admin/ModifierActivite/{id}")
    public String modifierActivite(
            @PathVariable Long id,
            @ModelAttribute("activite") Activite activite,
            @RequestParam("photoFile") MultipartFile photoFile,
            @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {

        Activite existingActivite = activiteService.getActiviteById(id);
        if (existingActivite != null) {
            // Préserver la photo existante si aucune nouvelle photo n'est fournie
            if (photoFile != null && !photoFile.isEmpty()) {
                String fileName = saveImage(photoFile, "Activites");
                activite.setPhoto(fileName);
            } else {
                activite.setPhoto(existingActivite.getPhoto());
            }

            // Traitement des photos additionnelles
            if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
                for (MultipartFile file : additionalPhotos) {
                    if (file != null && !file.isEmpty()) {
                        String fileName = saveImage(file, "Activites");
                        activite.addPhoto(fileName);
                    }
                }
            } else {
                activite.setPhotos(existingActivite.getPhotos());
            }

            activiteService.updateActivite(id, activite);
        }
        return "redirect:/admin/activites";
    }

    @GetMapping("/admin/home")
    public String home(Model model) {
        // Récupérer les statistiques
        long hotelCount = hotelService.getAllHotels().size();
        long restaurantCount = restaurantService.getAllRestaurants().size();
        long activityCount = activiteService.getAllActivites().size();
        long userCount = utilisateurService.countUtilisateurs();

        // Ajouter les statistiques au modèle
        model.addAttribute("hotelCount", hotelCount);
        model.addAttribute("restaurantCount", restaurantCount);
        model.addAttribute("activityCount", activityCount);
        model.addAttribute("userCount", userCount);

        // Ajouter l'URL active
        model.addAttribute("activePage", "home");

        return "home";
    }

    @GetMapping("/home")
    public String redirectToAdminHome() {
        return "redirect:/admin/home";
    }
    @GetMapping("/admin/ValiderActivites/{id}")
        public String validerActivite(@PathVariable Long id ) {
            Activite activite = activiteService.getActiviteById(id);
            if (activite != null) {
                activite.setNValide(true);
               activiteService.updateActivite(id, activite);
            }
            return "redirect:/admin/activites";
        }

        @GetMapping("/admin/ValiderActivites")
        
        public String afficherActivitesNonValidees(Model model) {
            List<Activite> activites = activiteService.getAllActivites()
                .stream()
                .filter(a -> a.getNValide() != null && !a.getNValide())
                .toList();
            model.addAttribute("activites", activites);
            return "validerActivites";
        }
        @GetMapping("/admin/ValiderHotels/{id}")
    public String validerHotel(@PathVariable Long id ) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            hotel.setNValide(true);
            hotelService.updateHotel(id, hotel);
        }
        return "redirect:/admin/Hotels";
    }
    @GetMapping("/admin/ValiderHotels")
    public String afficherHotelsNonValidees(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels()
                .stream()
                .filter(h -> h.getNValide() != null && !h.getNValide())
                .toList();
        model.addAttribute("hotels", hotels);
        return "validerHotels";
    }
    @GetMapping("/admin/ValiderRestaurants/{id}")
    public String ValiderRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            restaurant.setNValide(true);
            restaurantService.updateRestaurant(id, restaurant);
        }
        return "redirect:/admin/Restaurants";
    }
    @GetMapping("/admin/ValiderRestaurants")
    public String afficherRestaurantsNonValidees(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants()
                .stream()
                .filter(r -> r.getNValide() != null && !r.getNValide())
                .toList();
        model.addAttribute("restaurants", restaurants);
        return "validerRestaurants";
    }


  }

    

