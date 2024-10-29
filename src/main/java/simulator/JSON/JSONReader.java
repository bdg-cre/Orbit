package simulator.JSON;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import simulator.Body;
import simulator.Engine;



/**
 * The class {@code JSONReader} handles the reading of specific JSON Files, related to weather data,
 * rocket engines and rocket bodies.
 * <p>
 * It provides methods to access and parse JSON data from these sources:
 * <ul>
 * <li>Weather data: "weather_data.json"</li>
 * <li>Rocket engines: "engines.json"</li>
 * <li>Rocket bodies: "bodies.json"</li>
 * </ul>
 * </p>
 */
public class JSONReader {

    private static JSONReader jr = new JSONReader();

    /**
     * Asks the user to view the current weather forecast.
     * <p>
     * If the user confirms, this method reads the "weather_data.json" file and prints the current
     * weather conditions.
     * </p>
     * 
     * @param sc the {@link Scanner} instance for user input
     */
    public void getWeather(Scanner sc) {

        while (true) {
            System.out.println("Would you like to see the current forecast? (Y/N)");
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("Y")) {
                // Read weather data from JSON file
                JSONWeather jw = jr.readJSONFile("weather_data", JSONWeather.class);

                // Accessing inner class Data in JSONWeather
                JSONWeather.Data data = jw.getData();

                // Print weather details
                System.out
                        .println("\nTemperature:    " + data.getValues().getTemperature() + " C°");
                System.out.println("Humidity:       " + data.getValues().getHumidity() + " %");
                System.out.println("Wind-Speed:     " + data.getValues().getWindSpeed() + " m/s");
                System.out.println("Wind-Direction: " + data.getValues().getWindDirection() + " °");
                System.out.println("Wind-Gust:      " + data.getValues().getWindGust() + " m/s");
                System.out.println(
                        "Precipitation:  " + data.getValues().getPrecipProbability() + " %\n");
                break;
            } else if (userInput.equalsIgnoreCase("N")) {
                // Exit if user does not want the forecast
                return;
            } else {
                System.out.println("Invalid response, please type 'Y' or 'N'.");
                // Handle invalid input
                continue;
            }
        }
    }

    /**
     * Retrieves weather metrics from "weather_data.json" and returns them as a list.
     * <p>
     * This method reads the JSON file, extracts weather values, and stores them in an
     * {@link ArrayList}.
     * </p>
     * 
     * @return an {@link ArrayList} of weather metrics
     */
    public ArrayList<Double> setWeather() {
        // Read weather data from JSON file
        JSONWeather jw = jr.readJSONFile("weather_data", JSONWeather.class);
        JSONWeather.Data data = jw.getData();

        // Create and populate weather values list
        ArrayList<Double> weatherValues = new ArrayList<>();
        double temperature = data.getValues().getTemperature();
        double humidity = data.getValues().getHumidity();
        double windSpeed = data.getValues().getWindSpeed();
        double windDirection = data.getValues().getWindDirection();
        double windGust = data.getValues().getWindGust();
        double precipProbability = data.getValues().getPrecipProbability();
        weatherValues.add(temperature);
        weatherValues.add(humidity);
        weatherValues.add(windSpeed);
        weatherValues.add(windDirection);
        weatherValues.add(windGust);
        weatherValues.add(precipProbability);

        return weatherValues;
    }


    /**
     * Retrieves a list of rocket engines from "engines.json".
     * <p>
     * This method reads the JSON file and returns a list of {@link Engine} objects.
     * </p>
     * 
     * @return a {@link List} of {@link Engine} objects
     */
    public List<Engine> getEngines() {
        // Read engines data from JSON file
        JSONEngines je = jr.readJSONFile("engines", JSONEngines.class);
        List<Engine> engines = je.getEnginesList();
        return engines;

    }

    /**
     * Retrieves a list of rocket engines from "bodies.json".
     * <p>
     * This method reads the JSON file and returns a list of {@link Body} objects.
     * </p>
     * 
     * @return a {@link List} of {@link Body} objects.
     */
    public List<Body> getBodies() {
        // Read bodies data from JSON file
        JSONBodies jb = jr.readJSONFile("bodies", JSONBodies.class);
        List<Body> bodies = jb.getBodyList();
        return bodies;
    }

    /**
     * Reads a JSON file and maps its content to a Java object of the specified class.
     * <p>
     * This method uses Jackson's {@link ObjectMapper} to read and parse the JSON file.
     * 
     * @param <T> the type of Java object to map to
     * @param fileName the name of the JSON file (without extension)
     * @param clazz the class of the Java object
     * @return an instance of the specified class with the JSON data
     */
    private <T> T readJSONFile(String fileName, Class<T> clazz) {
        T result = null;
        ObjectMapper om = new ObjectMapper();
        try {
            // Enable source location information in JSON parsing
            om.enable(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
            // Read JSON data from the file and map it to the specified class
            result = om.readValue(new File("resources\\" + fileName + ".json"), clazz);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
