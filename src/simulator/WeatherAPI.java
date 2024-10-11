package simulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


/**
 * The class {@code WeatherAPI} is responsible for making API calls to the "tomorrow.io" weather
 * service.
 * <p>
 * It allows the user to input their location, units (metric or imperial), and API key to fetch
 * current weather data. The retrieved data is then returned as a JSON-formatted string.
 * </p>
 */
public class WeatherAPI {

    /**
     * Retrieves current weather data in JSON format for a specified location.
     * <p>
     * Asks the user for an API key, location, and units. Constructs a URL to call the "tomorrow.io"
     * API and process the HTTP response. If errors occur (e.g., bad request, unauthorized access),
     * it informs the user via the {@link #handleErrorResponse(int)} method and allows them to
     * retry.
     * </p>
     * 
     * @param sc the {@link Scanner} instance for user input
     * @return a {@code String} containing the JSON response from the weather API
     * @throws Exception if an error occurs during the API call
     */
    public String getWeatherData(Scanner sc) throws Exception {
        String response = "";
        while (true) {
            // Asks user for API key, location, and units
            System.out.println("Input API Key: ");
            String apiKey = sc.nextLine();
            System.out.println("Input Location: ");
            String location = sc.nextLine();
            System.out.println("Input Units (metric/imperial): ");
            String units = sc.nextLine().toLowerCase();

            // Validate units
            if (!units.equals("metric") && !units.equals("imperial")) {
                System.out.println("Please input either 'metric' or 'imperial'.");
                continue;
            }

            try {
                // Construct URL for API request
                URL url = new URL("https://api.tomorrow.io/v4/weather/realtime" + "?location="
                        + location + "&units=" + units + "&apikey=" + apiKey);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                // Check HTTP response code
                int status = con.getResponseCode();
                if (status != 200) {
                    handleErrorResponse(status);
                    continue;
                }

                // Read the API response
                BufferedReader in =
                        new BufferedReader(new InputStreamReader((con.getInputStream())));
                String inputLine;
                StringBuilder responseBuilder = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    responseBuilder.append(inputLine);
                }
                in.close();
                response = responseBuilder.toString();
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return response;
    }


    /**
     * Handles different types of HTTP error responses.
     * <p>
     * Provides user feedback based on the received HTTP status code.
     * </p>
     * 
     * @param status the HTTP response status code
     */
    private void handleErrorResponse(int status) {
        switch (status) {
            case 400:
                System.out.println("Bad request, please check your inputs.");
                break;
            case 401:
                System.out.println("Unauthorized, please check your API key.");
                break;
            case 403:
                System.out
                        .println("Forbidden. Your API key does not have access to this resource.");
                break;
            case 404:
                System.out.println("Not found. Please check your location parameter.");
                break;
            case 429:
                System.out.println("Too many requests. Try again later (usually an hour).");
                break;
            default:
                if (status >= 500) {
                    System.out.println("Server error. Please try again later.");
                } else {
                    System.out.println("Unexpected error. HTTP response code: " + status);
                }
        }
    }
}


