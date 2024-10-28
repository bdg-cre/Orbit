package simulator.JSON;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * The class {@code JSONWeather} represents a container for handling weather data as deserialized
 * from a JSON structure.
 * <p>
 * This class uses the Jackson library to map JSON data to Java objects. It contains fields for
 * weather data and location details (which are not used for now), mapped from the "weather_data"
 * JSON file.
 * </p>
 * <p>
 * The {@link JsonIgnoreProperties} annotation is used to ignore any properties in the JSON that are
 * not mapped to fields in this class, preventing errors during deserialization. The
 * {@link JsonProperty} annotation is used to map JSON properties to the corresponding fields.
 * </p>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONWeather {

    /**
     * The weather data.
     * <p>
     * This field is mapped from the JSON property "data" and holds the {@link Data} object
     * containing weather metrics.
     * </p>
     */
    @JsonProperty("data")
    private Data data;

    /**
     * The location of the forecast.
     * <p>
     * This field is mapped from the JSON property "location" and holds the {@link Location} object
     * with location details.
     * </p>
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Gets the weather data contained in this {@code JSONWeather} instance.
     * <p>
     * This method retrieves the {@link Data} object, which includes weather metrics and other
     * related information.
     * </p>
     * 
     * @return the weather data
     */
    public Data getData() {
        return data;
    }

    /**
     * Gets the location information contained in this {@code JSONWeather} instance.
     * <p>
     * This method retrieves the {@link Location} object, which includes details about the location
     * associated with the weather data.
     * </p>
     * 
     * @return the location information
     */
    public Location getLocation() {
        return location;
    }

    /**
     * The inner static class {@code Data} represents the weather metrics data.
     * <p>
     * This class is mapped from the JSON property "data", which includes various weather metrics
     * such as temperature, humidity and wind conditions.
     * </p>
     * <p>
     * The {@link JsonIgnoreProperties} annotation is used to ignore any properties in the JSON that
     * are not mapped to fields in this class, preventing errors during deserialization.
     * </p>
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        /**
         * The weather metrics values.
         * <p>
         * This field is mapped from the JSON property "values" and contains the {@link Values}
         * object with detailed weather metrics.
         */
        @JsonProperty("values")
        private Values values;

        /**
         * Gets the weather metrics values contained in this {@code Data} instance.
         * <p>
         * This method retrieves the {@link Values} object, which includes specific weather metrics
         * such as temperature, humidity, wind speed and precipitation probability.
         * </p>
         * 
         * @return the weather metrics values
         */
        public Values getValues() {
            return values;
        }

    }

    /**
     * The inner static class {@code Values} represents the detailed weather metrics.
     * <p>
     * This class is mapped from the JSON property "values" and includes metrics like temperature,
     * humidity, wind speed and precipitation probability.
     * </p>
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Values {

        /**
         * The temperature in degrees.
         * <p>
         * This field is mapped from the JSON property "temperature".
         * </p>
         */
        @JsonProperty("temperature")
        private double temperature;

        /**
         * The humidity percentage.
         * <p>
         * This field is mapped from the JSON property "humidity".
         */
        @JsonProperty("humidity")
        private double humidity;

        /**
         * The wind speed in meters per second.
         * <p>
         * This field is mapped from the JSON property "windSpeed".
         * </p>
         */
        @JsonProperty("windSpeed")
        private double windSpeed;

        /**
         * The wind direction in degrees.
         * <p>
         * This field is mapped from the JSON property "windDirection".
         * </p>
         */
        @JsonProperty("windDirection")
        private double windDirection;

        /**
         * The maximum wind gust in meters per second.
         * <p>
         * This field is mapped from the JSON property "windGust".
         * </p>
         */
        @JsonProperty("windGust")
        private double windGust;

        /**
         * The probability of perception as a percentage.
         * <p>
         * This field is mapped from the JSON property "precipitationProbability".
         * </p>
         */
        @JsonProperty("precipitationProbability")
        private double precipProbability;

        /**
         * Gets the temperature.
         * 
         * @return the temperature in degrees
         */
        public double getTemperature() {
            return temperature;
        }

        /**
         * Gets the humidity.
         * 
         * @return the humidity percentage
         */
        public double getHumidity() {
            return humidity;
        }

        /**
         * Gets the wind speed.
         * 
         * @return the wind speed in meters per second
         */
        public double getWindSpeed() {
            return windSpeed;
        }

        /**
         * Gets the wind direction.
         * 
         * @return the wind direction in degrees
         */
        public double getWindDirection() {
            return windDirection;
        }

        /**
         * Gets the wind gust.
         * 
         * @return the wind gust in meters per second
         */
        public double getWindGust() {
            return windGust;
        }

        /**
         * Gets the probability of precipitation.
         * 
         * @return the probability of precipitation as a percentage
         */
        public double getPrecipProbability() {
            return precipProbability;
        }

    }

    /**
     * The inner static class {@code Location} represents the location for which the weather data is
     * relevant.
     * <p>
     * This class is mapped from the JSON property "location" and includes details such as the name
     * and type of the location.
     * </p>
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {

        /**
         * The name of the location.
         * <p>
         * This field is mapped from the JSON property "name".
         */
        @JsonProperty("name")
        private String name;

        /**
         * The type of location (e.g. city, region).
         * <p>
         * This field is mapped from the JSON property "type".
         * </p>
         */
        @JsonProperty("type")
        private String type;

        /**
         * Gets the name of the location.
         * 
         * @return the location name
         */
        public String getName() {
            return name;
        }

    }


}
