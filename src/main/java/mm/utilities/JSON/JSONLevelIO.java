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

/**
 * Utility class for loading and saving Level objects to and from JSON files.
 * Uses Gson with a registered adapter for polymorphic serialization and deserialization
 * of physics object configurations (ObjectConf).
 */
public class JSONLevelIO {

    /**
     * Gson instance with registered adapter for ObjectConf polymorphism and pretty printing enabled.
     */
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ObjectConf.class, new RigidBodyAdapter()) // Adapter für RigidBody registrieren
            .setPrettyPrinting()
            .create();

    /**
     * Loads a Level object from a JSON file specified by the given file path.
     *
     * @param filePath the path to the JSON file to load the Level from
     * @return the deserialized Level object
     * @throws IOException if an I/O error occurs during reading the file
     */
    public static Level loadLevelFromFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Level.class); // Jetzt wird der RigidBodyAdapter verwendet
        }
    }

    /**
     * Saves a Level object to the specified file as JSON.
     *
     * @param level the Level object to save
     * @param file  the destination file
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public static void saveToFile(Level level, File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(level, writer); // Serialisiert das Level-Objekt und schreibt es in die Datei
        }
    }

    /**
     * Opens a directory chooser dialog for the user to select a directory,
     * then saves the given Level as a JSON file inside the selected directory.
     *
     * @param level       the Level object to save
     * @param ownerWindow the owner window for the directory chooser dialog
     * @throws IOException if an I/O error occurs during saving the file
     */
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

    /**
     * Opens a file chooser dialog for the user to select a JSON file
     * and loads a Level object from the selected file.
     *
     * @param window the owner window for the file chooser dialog
     * @return the loaded Level object, or null if loading fails or no file selected
     * @throws IOException if an I/O error occurs during loading the file
     */
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

