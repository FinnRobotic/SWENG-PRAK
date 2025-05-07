package mm.utilities;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONLevelIO {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static Level loadFromFile(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, Level.class);
        }

    }


    public static void saveToFile(Level level, File file) throws IOException {


    }

}
