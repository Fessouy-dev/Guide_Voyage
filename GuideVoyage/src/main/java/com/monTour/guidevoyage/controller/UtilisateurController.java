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

@Controller
@RequestMapping("/User")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private HotelService hotelService;

    @GetMapping("/Accueil")
    public String afficherPageAccueil(Model model) {

        model.addAttribute("activites", activiteService.getAllActivites());
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "accueil";
    }

    @GetMapping("/Activites")
    public String afficherActivites(Model model) {
        model.addAttribute("activites", activiteService.getAllActivites().stream()
                .filter(activite -> Boolean.TRUE.equals(activite.getNValide()))
                .toList());
        return "activites";
    }
    @GetMapping("/Activites/{id}/photos")
    @ResponseBody
    public List<String> getActivitePhotos(@PathVariable Long id){
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

    @GetMapping("/Restaurants")
    public String afficherRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants().stream()
                .filter(restaurant -> Boolean.TRUE.equals(restaurant.getNValide()))
                .toList());
        return "restaurants";
    }
    @GetMapping("/Restaurants/{id}/photos")
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

    @GetMapping("/Hotels")
    public String afficherHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels().stream()
                .filter(hotel -> Boolean.TRUE.equals(hotel.getNValide()))
                .toList());
        return "hotels";
    }
    @GetMapping("/Hotels/{id}/photos")
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

    @GetMapping("/connexion")
    public String afficherPageConnexion() {
        return "connexion";
    }

    @GetMapping("/inscription")
    public String afficherPageInscription(Model model) {
        model.addAttribute("sex", Sex.values());
        return "inscriptionUser";
    }

    @PostMapping("/inscription")
    public String inscrireUtilisateur(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String motDepasse,
                                    @RequestParam int age,
                                    @RequestParam Sex sex,
                                    Model model) {
        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setMotDepasse(motDepasse);
            user.setAge(age);
            user.setSex(sex);
            
            utilisateurService.inscrireUtilisateur(user);
            return "redirect:/User/connexion";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("sex", Sex.values());
            return "inscriptionUser";
        }
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

    @GetMapping("/RecomActivite")
    public String afficherformulaireAjouterActivite(Model model) {
        Activite activite = new Activite();
        model.addAttribute("typeActivite", TypeActivie.values());
        model.addAttribute("activite", activite);
        return "activiteRecommander";
    }

    @PostMapping("/RecomActivite")
    public String ajouterActivite(@ModelAttribute("activite") Activite activite
    , @RequestParam("photoFile")MultipartFile photoFile,
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
        activite.setNValide(false);
        activiteService.ajouterActivite(activite);
        return "redirect:/User/activites";

    }

    @GetMapping("/RecomHotel")
    public String afficherformulaireAjouterHotel(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "hotelRecommander";
    }

    @PostMapping("/RecomHotel")
    public String ajouterHotel(@ModelAttribute("hotel") Hotel hotel
            , @RequestParam("photoFile")MultipartFile photoFile,
                                  @RequestParam("additionalPhotos") List<MultipartFile> additionalPhotos) throws IOException {

        // Traitement de la photo principale
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = saveImage(photoFile, "Hotels");
            hotel.setPhoto(fileName);

        }
        if (additionalPhotos != null && !additionalPhotos.isEmpty()) {
            for (MultipartFile file : additionalPhotos) {
                if (file != null && !file.isEmpty()) {
                    String fileName = saveImage(file, "Hotels");
                    hotel.addPhoto(fileName);
                }
            }
        }
        hotel.setNValide(false);
        hotelService.ajouterHotel(hotel);
        return "redirect:/User/Hotels";

    }

    @GetMapping("/RecomRestaurant")
    public String afficherformulaireAjouterRestaurant(Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("specialiter", Specialiter.values());
        return "restaurantRecommander";
    }

    @PostMapping("/RecomRestaurant")
    public String ajouterRestaurant(@ModelAttribute("restaurant") Restaurant restaurant
            , @RequestParam("photoFile")MultipartFile photoFile,
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
        restaurant.setNValide(false);
        restaurantService.ajouterRestaurant(restaurant);
        return "redirect:/User/Restaurants";

    }
}
