package com.monTour.guidevoyage.controller;

import com.monTour.guidevoyage.model.Sex;
import com.monTour.guidevoyage.model.User;
import com.monTour.guidevoyage.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Affiche la page d'inscription
    @GetMapping("/inscription")
    public String afficherPageInscription(Model model) {
        model.addAttribute("sex", Sex.values());
        return "inscriptionUser"; // nom du template Thymeleaf (inscriptionUser.html)
    }

    // Traite le formulaire d'inscription
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
            return "redirect:/connexion";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("sex", Sex.values());
            return "inscriptionUser";
        }
    }
} 