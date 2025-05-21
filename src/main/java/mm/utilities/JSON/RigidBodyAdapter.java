package mm.utilities.JSON;

import com.google.gson.*;
import mm.utilities.ObjectsConf.BallConf;
import mm.utilities.ObjectsConf.BoxConf;
import mm.utilities.ObjectsConf.ObjectConf;


import java.lang.reflect.Type;

/**
 * Custom Gson adapter for serializing and deserializing physics object configurations.
 *
 * This adapter handles polymorphic conversion between JSON and Java objects
 * by distinguishing between different types of physics objects such as BallConf and BoxConf,
 * based on the "type" property in the JSON.
 */
public class RigidBodyAdapter implements JsonDeserializer<ObjectConf>, JsonSerializer<ObjectConf> {

    /**
     * Deserialize a JSON element into an ObjectConf instance.
     * The type of the ObjectConf is determined by the "type" property in JSON.
     *
     * @param json the JSON data being deserialized
     * @param typeOfT the type of the Object to deserialize to
     * @param context the deserialization context
     * @return an instance of BallConf or BoxConf based on the JSON "type"
     * @throws JsonParseException if the type is unknown or JSON is invalid
     */
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
                float BallAngle = jsonObject.has("angle") ? jsonObject.get("angle").getAsFloat() : 0;
                float BallDensity = jsonObject.has("density") ? jsonObject.get("density").getAsFloat() : -1;
                float BallFriction = jsonObject.has("friction") ? jsonObject.get("friction").getAsFloat() : -1;
                // BallConf-Konstruktor aufrufen und zurückgeben
                return new BallConf(ballX, ballY, radius,BallAngle,BallDensity,BallFriction);
            case "Box":
                // Extrahiere die Werte aus dem JSON und rufe den Konstruktor für BoxConf auf
                float boxX = jsonObject.get("x").getAsFloat();
                float boxY = jsonObject.get("y").getAsFloat();
                float width = jsonObject.get("width").getAsFloat();
                float height = jsonObject.get("height").getAsFloat();
                float BoxAngle = jsonObject.has("angle") ? jsonObject.get("angle").getAsFloat() : 0;
                float BoxDensity = jsonObject.has("density") ? jsonObject.get("density").getAsFloat() : -1;
                float BoxFriction = jsonObject.has("friction") ? jsonObject.get("friction").getAsFloat() : -1;
                // BoxConf-Konstruktor aufrufen und zurückgeben
                return new BoxConf(boxX, boxY, width, height, BoxAngle, BoxDensity, BoxFriction);
            default:
                throw new JsonParseException("Unbekannter Typ: " + type);
        }
    }

    /**
     * Serialize an ObjectConf instance into a JSON element.
     * The JSON will contain a "type" property and fields specific to the subclass.
     *
     * @param src the ObjectConf object to serialize
     * @param typeOfSrc the actual type of the source object
     * @param context the serialization context
     * @return a JsonElement representing the serialized ObjectConf
     */
    @Override
    public JsonElement serialize(ObjectConf src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Wenn es ein Ball ist, serialisiere ihn entsprechend
        if (src instanceof BallConf) {
            jsonObject.addProperty("type", "Ball");
            jsonObject.add("x", new JsonPrimitive(((BallConf) src).x));
            jsonObject.add("y", new JsonPrimitive(((BallConf) src).y));
            jsonObject.add("radius", new JsonPrimitive(((BallConf) src).radius));
            if (((BallConf) src).density != -1) {
                jsonObject.add("density", new JsonPrimitive(((BallConf) src).density));
            }
            if (((BallConf) src).friction != -1) {
                jsonObject.add("friction", new JsonPrimitive(((BallConf) src).friction));
            }
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

