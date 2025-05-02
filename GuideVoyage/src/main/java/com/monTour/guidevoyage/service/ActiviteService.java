package com.monTour.guidevoyage.service;

import com.monTour.guidevoyage.model.Activite;
import com.monTour.guidevoyage.repository.ActiviteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {
    private final ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }
    public Activite ajouterActivite(Activite activite) {
        return activiteRepository.save(activite);
    }
    public Activite getActiviteById(Long id) {
        return activiteRepository.findById(id).orElse(null);
    }
    public void DeleteActivite(Long id) {
        activiteRepository.deleteById(id);
    }
    public List<Activite> getAllActivites() {
        return activiteRepository.findAll();
    }
    public Activite updateActivite(Long id, Activite activiteDetails) {
        Activite activite = getActiviteById(id);
        if (activite != null) {
            activite.setNom(activiteDetails.getNom());
            activite.setDescription(activiteDetails.getDescription());
            activite.setLocalisation(activiteDetails.getLocalisation());
            
            // Ne mettre à jour la photo que si une nouvelle photo est fournie
            if (activiteDetails.getPhoto() != null && !activiteDetails.getPhoto().isEmpty()) {
                activite.setPhoto(activiteDetails.getPhoto());
            }
            
            activite.setTypeActivie(activiteDetails.getTypeActivie());
            activite.setVille(activiteDetails.getVille());

            // Mise à jour des photos additionnelles
            if (activiteDetails.getPhotos() != null) {
                activite.setPhotos(activiteDetails.getPhotos());
            }

            return activiteRepository.save(activite);
        }
        return null;
    }
}
