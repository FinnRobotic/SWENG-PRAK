package mm.utilities.JSON;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import mm.MVC.start.StartView;
import mm.utilities.Level;
import mm.utilities.ObjectsConf.ObjectConf;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONLevelIO {

    // Gson-Instanz mit dem registrierten Adapter für RigidBody
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ObjectConf.class, new RigidBodyAdapter()) // Adapter für RigidBody registrieren
            .setPrettyPrinting()
            .create();

    // Methode zum Laden eines Levels aus einer JSON-Datei
    public static Level loadLevelFromFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Level.class); // Jetzt wird der RigidBodyAdapter verwendet
        }
    }

    // Methode zum Speichern eines Levels in eine JSON-Datei
    public static void saveToFile(Level level, File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(level, writer); // Serialisiert das Level-Objekt und schreibt es in die Datei
        }
    }

    public static void saveToFileWithDirectoryChooser(Level level, Window ownerWindow) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Speicherort für Level auswählen");

        // Optional: Standardverzeichnis setzen
        File initialDir = new File("user-levels");
        if (initialDir.exists()) {
            directoryChooser.setInitialDirectory(initialDir);
        }

        File selectedDirectory = directoryChooser.showDialog(ownerWindow);

        if (selectedDirectory != null) {
            String fileName = level.getName() + ".json";
            File fileToSave = new File(selectedDirectory, fileName);

            try (FileWriter writer = new FileWriter(fileToSave)) {
                gson.toJson(level, writer);
                System.out.println("Level gespeichert unter: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
                throw ex; // Optional: Re-throw für höhere Ebene
            }
        } else {
            System.out.println("Kein Speicherort ausgewählt.");
        }
    }

    public static Level loadLevelFromDirectory(Window window) throws IOException {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Benutzerdefiniertes Level auswählen");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("JSON Dateien", "*.json")
            );

            // Starte im user-level Ordner, optional
            File initialDir = new File("user-levels");
            if (initialDir.exists()) {
                fileChooser.setInitialDirectory(initialDir);
            }

            File selectedFile = fileChooser.showOpenDialog(window);

            if (selectedFile != null) {
                System.out.println("Ausgewähltes Level: " + selectedFile.getAbsolutePath());
                // hier kannst du das File ans Model/Controller übergeben oder speichern
            }
            return gson.fromJson(new FileReader(selectedFile), Level.class);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

