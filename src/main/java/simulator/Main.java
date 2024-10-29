package simulator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import simulator.JSON.JSONReader;
import simulator.JSON.JSONWriter;

/**
 * The class {@code Main} is the entry point of the simulation application.
 * <p>
 * This class handles user interaction, including enabling weather functionality, selecting rocket
 * components, and performing calculations based on the user's choices.
 * </p>
 */
public class Main {

    private static JSONReader jr = new JSONReader();
    private static JSONWriter jw = new JSONWriter();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try { // used to ensure correct Scanner handling

            /* Weather functionality integration */
            boolean weatherEnabled = false;
            while (true) {
                System.out.println("Do you want to enable the weather functionality? (Y/N)");
                String userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("Y")) {
                    jw.weatherJSONWriter(); // Write weather data from API to a JSON file
                    jr.getWeather(sc); // Ask user if they want to see the weather forecast
                    weatherEnabled = true;
                    break;
                } else if (userInput.equalsIgnoreCase("N")) {
                    System.out.println("Ignoring weather funtionality.");
                    break;
                } else {
                    System.out.println("Invalid response, please type 'Y' or 'N'.");
                    continue;
                }
            }
            /* End of weather functionality integration */

            /* Rocket creation and selection */
            Selector selector = new Selector(); // Create Selector instance for rocket creation
            selector.inputFileType(sc); // Ask user which file type to use
            while (true) {
                Rocket selectedRocket = selector.createRocket(sc); // Create rocket based on user input
                if (selectedRocket != null) { // Check if a rocket has been created
                    ArrayList<Map<String, Object>> rocketValues = getRocketValues(selectedRocket); // Get rocket values
                    System.out.println("Calculate with this selection? (Y/N)");
                    String userInput = sc.nextLine();
                    if (userInput.equalsIgnoreCase("Y")) {
                        calculate(rocketValues, weatherEnabled, sc); // Perform calculations
                        break;
                    } else if (userInput.equalsIgnoreCase("N")) {
                        System.out.println("Please select again:\n");
                    } else {
                        System.out.println("Invalid response, please type 'Y' or 'N'.");
                    }
                } else {
                    System.out.println("No Engine was selected.");
                }
            }
            /* End of Rocket creation */

        } finally {
            sc.close(); // Ensure Scanner is closed properly
        }

    }

    /**
     * Calculates weather-related variations on the altitude and returns a map with results.
     * <p>
     * This method uses weather data to adjust the calculated altitude based on air density, wind
     * effect, and precipitation effect.
     * </p>
     * 
     * @param cl the {@link Calculations} instance used for performing calculations
     * @param altitudeA the initially calculated altitude
     * @return a map with weather variation results
     */
    private static Map<String, Object> altitudeWeatherVariation(Calculations cl, double altitudeA) {
        ArrayList<Double> weatherValues = jr.setWeather(); // Get weather data

        // Calculate and display weather effects
        Double airDensity = cl.calculateAirDensity(weatherValues.get(0), weatherValues.get(1));
        System.out.println("Air density: " + airDensity + " kg/m^3");
        Double windEffect = cl.calculateWindEffect(weatherValues.get(2), weatherValues.get(3),
                weatherValues.get(4));
        System.out.println("Wind effect: " + windEffect);
        Double precipEffect = cl.calculatePrecipEffect(weatherValues.get(5));
        System.out.println("Precipitation effect: " + precipEffect);
        Double adjustedAltitude =
                cl.calculateAdjustedAltitude(altitudeA, airDensity, windEffect, precipEffect);
        System.out.println("Adjusted Altitude: " + adjustedAltitude + " m!");

        // Create and return a map with variation results
        Map<String, Object> variationResults = new LinkedHashMap<>();
        variationResults.put("Air density", airDensity);
        variationResults.put("Wind effect", windEffect);
        variationResults.put("Precipitation effect", precipEffect);
        variationResults.put("Adjusted altitude", adjustedAltitude);
        return variationResults;
    }

    /**
     * Retrieves the values for the selected rocket's body and engine and returns them as a list of
     * maps.
     * <p>
     * This method extracts values from the selected rocket's body and engine and stores them in
     * maps for easy access and further processing.
     * </p>
     * 
     * @param selectedRocket the {@link Rocket} object from which values are retrieved
     * @return a list containing maps of {@link Body} and {@link Engine} values
     */
    private static ArrayList<Map<String, Object>> getRocketValues(Rocket selectedRocket) {
        Body selectedBody = selectedRocket.getBody();
        Engine selectedEngine = selectedRocket.getEngine();

        // Map body values
        Map<String, Object> bodyValues = Map.of("modelDescription", selectedBody.getModel(),
                "emptyMass", selectedBody.getEmptyMass(), "length", selectedBody.getLength(),
                "diameter", selectedBody.getDiameter());

        // Map engine values
        Map<String, Object> engineValues = Map.of("engineClass", selectedEngine.getEngineClass(),
                "type", selectedEngine.getDescription(), "model", selectedEngine.getModel(),
                "delay", selectedEngine.getDelay().getDelay(), "totalImpulse",
                selectedEngine.getImpulse().getTotalImpulse(), "peakThrust",
                selectedEngine.getThrust().getPeakThrust(), "propellantMass",
                selectedEngine.getPropellantMass(), "totalEngineMass",
                selectedEngine.getTotalEngineMass());

        // Add maps to list and print
        ArrayList<Map<String, Object>> maps = new ArrayList<>(2);
        maps.add(bodyValues);
        maps.add(engineValues);

        printRocketValues(maps);
        return maps;
    }

    /**
     * Prints the values of the selected rocket's body and engine.
     * <p>
     * This method iterates over the provided list of maps, printing out the details of the body and
     * engine components.
     * </p>
     * 
     * @param maps a list of maps containing {@link Body} and {@link Engine} values
     */
    private static void printRocketValues(ArrayList<Map<String, Object>> maps) {
        Map<String, Object> bodyValues = maps.get(0);

        /* Printing out selected Body values */
        System.out.println();
        System.out.println("Selected Model: " + bodyValues.get("modelDescription"));
        System.out.println("Empty Body Mass: " + bodyValues.get("emptyMass") + " g");
        System.out.println("Body Length: " + bodyValues.get("length") + " cm");
        System.out.println("Body Diameter: " + bodyValues.get("diameter") + " mm");

        Map<String, Object> engineValues = maps.get(1);

        /* Printing out selected Engine values */
        System.out.println();
        System.out.println("Selected Engine Class: " + engineValues.get("engineClass"));
        System.out.println("Selected Engine: " + engineValues.get("type"));
        System.out.println("Model: " + engineValues.get("model"));
        System.out.println("Delay: " + engineValues.get("delay") + " s");
        System.out.println("Total Impulse: " + engineValues.get("totalImpulse") + " N-s");
        System.out.println("Peak Thrust: " + engineValues.get("peakThrust") + " N");
        System.out.println("Propellant Mass: " + engineValues.get("propellantMass") + " g");
        System.out.println("Total Engine Mass: " + engineValues.get("totalEngineMass") + " g");
        System.out.println();

    }

    /**
     * Performs various rocket calculations and writes the results to a JSON file.
     * <p>
     * This method calculates rocket performance metrics such as mass, area, wind resistance, and
     * altitude. It also includes optional weather adjustments if enabled.
     * </p>
     * 
     * @param rocketValues a list containing maps of {@link Body} and {@link Engine} values
     * @param weatherEnabled flag indicating if weather adjustments should be applied
     * @param sc a {@link Scanner} instance for user input
     */
    private static void calculate(ArrayList<Map<String, Object>> rocketValues,
            Boolean weatherEnabled, Scanner sc) {
        Calculations cl = new Calculations();
        DecimalFormat df = new DecimalFormat("#.######"); // Format for decimal output

        // Perform calculations
        double massM = cl.calculateMassM((double) rocketValues.get(0).get("emptyMass"),
                (double) rocketValues.get(1).get("totalEngineMass"));
        System.out.println("Mass: " + massM + " Kg");

        double areaA = cl.calculateAreaA((double) rocketValues.get(0).get("diameter"));
        String formattedArea = df.format(areaA);
        System.out.println("Area: " + formattedArea + " m^2");

        double windResFact = cl.calculateWindResistanceFactor(areaA);
        String formattedWRF = df.format(windResFact);
        System.out.println("Wind resistance factor: " + formattedWRF);

        double burnTimeT = cl.calculateBurnTime((double) rocketValues.get(1).get("totalImpulse"),
                (double) rocketValues.get(1).get("peakThrust"));
        System.out.println("Burn Time: " + burnTimeT + " sec");

        double gravForceG = cl.calculateGravForceG(massM);
        System.out.println("Gravitational Force: " + gravForceG + " newton");

        double q = cl.calculateQ((double) rocketValues.get(1).get("peakThrust"), gravForceG,
                windResFact);
        System.out.println("Q: " + q);

        double x = cl.calculateX(windResFact, q, massM);
        System.out.println("X: " + x);

        double velocityV = cl.calculateVelocityV(q, x, burnTimeT);
        System.out.println("Velocity: " + velocityV + " m/s");

        double yb = cl.calculateBoostPhaseYB(massM, windResFact,
                (double) rocketValues.get(1).get("peakThrust"), velocityV);
        System.out.println("YB: " + yb + " m");

        double yc = cl.calculateCoastPhaseYC(massM, windResFact, velocityV);
        System.out.println("YC: " + yc + " m");

        double altitudeA = cl.calculateAltitude(yb, yc);
        System.out.println("Altitude reached: " + altitudeA + " m!");

        // Create list of calculation results
        ArrayList<Object> calcValues = new ArrayList<>();
        calcValues.add(massM);
        calcValues.add(formattedArea);
        calcValues.add(formattedWRF);
        calcValues.add(burnTimeT);
        calcValues.add(gravForceG);
        calcValues.add(q);
        calcValues.add(x);
        calcValues.add(velocityV);
        calcValues.add(yb);
        calcValues.add(yc);
        calcValues.add(altitudeA);


        if (weatherEnabled == true) {
            while (true) {
                System.out.println(
                        "Would you like to add the weather variations to the results? (Y/N)");
                String userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("Y")) {
                    Map<String, Object> variationResults = altitudeWeatherVariation(cl, altitudeA);
                    Map<String, Object> calcResults = fillMap(calcValues);
                    variationResults.forEach(calcResults::put);
                    cl.passToJSONWriter(calcResults, sc); // Pass results to JSON writer
                    break;
                } else if (userInput.equalsIgnoreCase("N")) {
                    System.out.println("Not using weather data.");
                    Map<String, Object> calcResults = fillMap(calcValues);
                    cl.passToJSONWriter(calcResults, sc); // Pass results to JSON writer
                    break;
                } else {
                    System.out.println("Invalid response, please type 'Y' or 'N'.");
                }
            }
        } else {
            Map<String, Object> calcResults = fillMap(calcValues);
            cl.passToJSONWriter(calcResults, sc); // Pass results to JSON writer

        }
    }

    /**
     * Creates a map with calculation results from a list of values.
     * <p>
     * This method uses the provided list of calculation values to populate a map, ensuring that the
     * results are included in the specified order.
     * </p>
     * 
     * @param calcValues a {@link ArrayList} of calculation results
     * @return a {@link Map} containing the calculation results
     */
    private static Map<String, Object> fillMap(ArrayList<Object> calcValues) {
        Map<String, Object> calcResults = new LinkedHashMap<>();
        calcResults.put("Mass", calcValues.get(0));
        calcResults.put("Area", calcValues.get(1));
        calcResults.put("Wind Resistance Factor", calcValues.get(2));
        calcResults.put("Burn Time", calcValues.get(3));
        calcResults.put("Gravforce", calcValues.get(4));
        calcResults.put("Q", calcValues.get(5));
        calcResults.put("X", calcValues.get(6));
        calcResults.put("Velocity", calcValues.get(7));
        calcResults.put("Boost Phase Distance", calcValues.get(8));
        calcResults.put("Coast Phase Distance", calcValues.get(9));
        calcResults.put("Altitude", calcValues.get(10));
        return calcResults;
    }
}
