import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageController {

    // Méthode utilitaire pour sauvegarder les images
    private String saveImage(MultipartFile file, String subdirectory) throws IOException {
        // Récupérer l'extension originale du fichier
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        // Générer un nom de fichier unique avec l'extension originale
        String fileName = UUID.randomUUID().toString() + extension;

        // Définir le chemin où sauvegarder les images
        String uploadDir = "uploads/" + subdirectory;
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
            return "/uploads/" + subdirectory + "/" + fileName;
        }
    }
} 