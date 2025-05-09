package com.monTour.guidevoyage.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileStorageConfig {

    @PostConstruct
    public void init() {
        try {
            // Créer les dossiers pour le stockage des fichiers
            createDirectoryIfNotExists("uploads/Activites");
            createDirectoryIfNotExists("uploads/hotels");
            createDirectoryIfNotExists("uploads/Restaurants");
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer les dossiers de stockage des fichiers", e);
        }
    }

    private void createDirectoryIfNotExists(String directory) throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }
} 