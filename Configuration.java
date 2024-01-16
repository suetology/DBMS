import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Configuration {
    private static final String configFilename = "config.json";
    private static JsonNode data;

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            data = objectMapper.readTree(new File(configFilename));
        } catch (IOException e) {
            System.out.println("Could not read a configuration file");
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        if (data != null && data.has(propertyName))
            return data.get(propertyName).asText();
        else
            return null;
    }
}
