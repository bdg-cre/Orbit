package simulator.JSON;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import simulator.WeatherAPI;


/**
 * The class {@code JSONWriter} is responsible for writing specific data like weather values and
 * user data to JSON files.
 * <p>
 * This class provides methods to create and save JSON files for weather data calculation results.
 * </p>
 */
public class JSONWriter {

    // ObjectMapper instance configured to pretty-print JSON output
    private static ObjectMapper om = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    /**
     * Writes weather data to a JSON file.
     * <p>
     * This method fetches weather data from an API, parses the JSON response into a
     * {@link JSONWeather} object, and writes it to "weather_data.json".
     * </p>
     */
    public void weatherJSONWriter() {
        Scanner sc = new Scanner(System.in);
        WeatherAPI wa = new WeatherAPI();
        try {
            // Fetch weather data from the WeatherAPI class
            String jsonResponse = wa.getWeatherData(sc);

            // Parse the JSON response into a JSONWeather object
            JSONWeather weatherResponse = om.readValue(jsonResponse, JSONWeather.class);

            // Define the file path for the output JSON file
            File weatherFile = new File("resources\\weather_data.json");

            // Write the JSONWeather object to the file
            om.writeValue(weatherFile, weatherResponse);

            // Notify the user of succesful file creation
            System.out
                    .println("Succesfully created the JSON file: " + weatherFile.getAbsolutePath());
        } catch (Exception e) {
            // Handle and print any exceptions that occur during processing
            e.printStackTrace();
        }
    }

    /**
     * Asks the user to save calculation results to a JSON file.
     * <p>
     * If the user chooses to save, this method converts a {@link Map} of calculation results to
     * JSON and writes it to "calculation_results.json".
     * </p>
     * 
     * @param calcValues the {@link Map} containing calculation results to be saved
     * @param sc the {@link Scanner} instance for user input
     */
    public void saveResultsJSON(Map<String, Object> calcValues, Scanner sc) {
        System.out.println("Do you want to save your calculation results? (Y/N)");
        while (true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("Y")) {
                // Create a new ObjectNode to hold the calculation results
                ObjectNode on = om.createObjectNode();

                // Process and add each entry from the calculation results map to the ObjectNode
                for (Map.Entry<String, Object> entry : calcValues.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();

                    if (value instanceof String) {
                        // Cast value as String and add to ObjectNode
                        on.put(key, (String) value);
                    } else if (value instanceof Double) {
                        // Cast value as Double and add to ObjectNode
                        on.put(key, (Double) value);
                    }
                }

                // Define the file path for the output JSON file
                File calcFile = new File("resources\\calculation_results.json");

                try {
                    // Write the ObjectNode containing the calculation results to the file
                    om.writeValue(calcFile, on);

                    // Notify the user of succesful file creation
                    System.out.println(
                            "Succesfully created the JSON File: " + calcFile.getAbsolutePath());
                } catch (Exception e) {
                    // Handle and print any exceptions that occur during processing
                    e.printStackTrace();
                }
                break;
            } else if (userInput.equalsIgnoreCase("N")) {
                // Notify the user that the results have not been saved
                System.out.println("Results have not been saved.");
                return;
            } else {
                // Handle invalid user input
                System.out.println("Invalid Response, please type 'Y' or 'N'.");
            }
        }
    }
}
