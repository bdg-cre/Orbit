package simulator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// TO-DO:
// - Increase modularity by defining single methods for the specific file types.

public class AppConfig {
    private static AppConfig instance;
    private Properties properties;

    public AppConfig() {
        properties = new Properties();
        loadProperties();
    }

    // Single Instance Method
    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public void loadProperties() {
        try (InputStream input =
                getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Unable to find config.properties");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getXMLEnginesFilePath() {
        return properties.getProperty("xml.engines.path");
    }

    public String getXMLBodiesFilePath() {
        return properties.getProperty("xml.bodies.path");
    }

    public String getJSONEnginesFilePath() {
        return properties.getProperty("json.engines.path");
    }

    public String getJSONBodiesFilePath() {
        return properties.getProperty("json.bodies.path");
    }
}
