package simulator;

import java.io.File;

public class ConfigManager {
    AppConfig config = AppConfig.getInstance();

    public File returnXMLEngines() {
        String xmlEnginesPath = config.getXMLEnginesFilePath();
        return new File(getClass().getClassLoader().getResource(xmlEnginesPath).getFile());
    }

    public File returnXMLBodies() {
        String xmlBodiesPath = config.getXMLBodiesFilePath();
        return new File(getClass().getClassLoader().getResource(xmlBodiesPath).getFile());
    }

    public File returnJSONEngines() {
        String jsonEnginesPath = config.getJSONEnginesFilePath();
        return new File(getClass().getClassLoader().getResource(jsonEnginesPath).getFile());
    }

    public File returnJSONBodies() {
        String jsonBodiesPath = config.getJSONBodiesFilePath();
        return new File(getClass().getClassLoader().getResource(jsonBodiesPath).getFile());
    }

}
