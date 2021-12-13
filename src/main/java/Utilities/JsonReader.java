package Utilities;

import org.json.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class JsonReader {

    public List<String> getProperty(String prop) throws IOException {
        Logger logger = Logger.getLogger(JsonReader.class.getName());
        List<String> objectDetails = new ArrayList<>();
        String text = new String(Files.readAllBytes(Paths.get("src/test/resources/objectProperties.json")), StandardCharsets.UTF_8);
        JSONObject obj = new JSONObject(text);
        String property = obj.getJSONObject(prop).getString("ObjectProperty");
        String locator = obj.getJSONObject(prop).getString("LocatorType");
        objectDetails.add(property);
        objectDetails.add(locator);
        return objectDetails;
    }
}
