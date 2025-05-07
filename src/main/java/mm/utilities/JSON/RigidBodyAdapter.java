package mm.utilities.JSON;

import com.google.gson.*;
import mm.utilities.ObjectsConf.BallConf;
import mm.utilities.ObjectsConf.BoxConf;
import mm.utilities.ObjectsConf.ObjectConf;
import mm.utilities.PhysicsObjects.Ball;
import mm.utilities.PhysicsObjects.Box;
import mm.utilities.PhysicsObjects.RigidBody;

import java.lang.reflect.Type;

public class RigidBodyAdapter implements JsonDeserializer<ObjectConf>, JsonSerializer<ObjectConf> {

    @Override
    public ObjectConf deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        // Unterschiedliche RigidBody-Typen je nach "type" deserialisieren
        switch (type) {
            case "Ball":
                // Extrahiere die Werte aus dem JSON und rufe den Konstruktor für BallConf auf
                float ballX = jsonObject.get("x").getAsFloat();
                float ballY = jsonObject.get("y").getAsFloat();
                float radius = jsonObject.get("radius").getAsFloat();
                // BallConf-Konstruktor aufrufen und zurückgeben
                return new BallConf(ballX, ballY, radius);
            case "Box":
                // Extrahiere die Werte aus dem JSON und rufe den Konstruktor für BoxConf auf
                float boxX = jsonObject.get("x").getAsFloat();
                float boxY = jsonObject.get("y").getAsFloat();
                float width = jsonObject.get("width").getAsFloat();
                float height = jsonObject.get("height").getAsFloat();
                float angle = jsonObject.has("angle") ? jsonObject.get("angle").getAsFloat() : 0;
                float density = jsonObject.has("density") ? jsonObject.get("density").getAsFloat() : -1;
                float friction = jsonObject.has("friction") ? jsonObject.get("friction").getAsFloat() : -1;
                // BoxConf-Konstruktor aufrufen und zurückgeben
                return new BoxConf(boxX, boxY, width, height, angle, density, friction);
            default:
                throw new JsonParseException("Unbekannter Typ: " + type);
        }
    }

    @Override
    public JsonElement serialize(ObjectConf src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Wenn es ein Ball ist, serialisiere ihn entsprechend
        if (src instanceof BallConf) {
            jsonObject.addProperty("type", "Ball");
            jsonObject.add("x", new JsonPrimitive(((BallConf) src).x));
            jsonObject.add("y", new JsonPrimitive(((BallConf) src).y));
            jsonObject.add("radius", new JsonPrimitive(((BallConf) src).radius));
        }
        // Wenn es eine Box ist, serialisiere sie entsprechend
        else if (src instanceof BoxConf) {
            jsonObject.addProperty("type", "Box");
            jsonObject.add("x", new JsonPrimitive(((BoxConf) src).x));
            jsonObject.add("y", new JsonPrimitive(((BoxConf) src).y));
            jsonObject.add("width", new JsonPrimitive(((BoxConf) src).width));
            jsonObject.add("height", new JsonPrimitive(((BoxConf) src).height));
            jsonObject.add("angle", new JsonPrimitive(((BoxConf) src).angle));
            if (((BoxConf) src).density != -1) {
                jsonObject.add("density", new JsonPrimitive(((BoxConf) src).density));
            }
            if (((BoxConf) src).friction != -1) {
                jsonObject.add("friction", new JsonPrimitive(((BoxConf) src).friction));
            }
        }

        return jsonObject;
    }
}

