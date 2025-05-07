package mm.utilities.JSON;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
}
