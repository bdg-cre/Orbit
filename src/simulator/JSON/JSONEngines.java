package simulator.JSON;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import simulator.Engine;



/**
 * The class {@code JSONEngines} represents a container for handling a collection of rocket engines
 * as deserialized from a JSON structure.
 * <p>
 * This class uses the Jackson library to map JSON data to Java objects from the "engines" JSON
 * file. The primary purpose of this class is to encapsulate a list of {@link Engine} objects that
 * are parsed from JSON under the "engines" property.
 * </p>
 * <p>
 * The {@link JsonProperty} annotation is used to map JSON properties to the corresponding fields.
 * </p>
 */
public class JSONEngines {

    /**
     * The container for a list of rocket engines.
     * <p>
     * This field is mapped to the JSON property "engines" and holds the {@link Engines} object,
     * which contains a list of {@link Engine} objects.
     * </p>
     */
    @JsonProperty("engines")
    private Engines engines;

    /**
     * Gets the list of rocket engines contained in this {@code JSONEngines} instance.
     * <p>
     * This method retrieves the list of {@link Engine} objects from the inner {@code Engines}
     * class.
     * </p>
     * 
     * @return a list of {@link Engine} objects, retrieved from the inner {@code Engines} class.
     */
    public List<Engine> getEnginesList() {
        return engines.getEngines();
    }

    /**
     * The inner static class {@code Engines} represents the structure containing a list of rocket
     * engines.
     * <p>
     * This class is mapped to the JSON property "engines" and inclued a list of {@link Engine}
     * objects.
     * </p>
     */
    public static class Engines {

        /**
         * The list of rocket engines.
         * <p>
         * This field is mapped to the JSON property "engine" and holds a list of {@link Engine}
         * objects.
         * </p>
         */
        @JsonProperty("engine")
        private List<Engine> engineList;

        /**
         * Gets the list of rocket engines contained in this {@code Engines} instance.
         * 
         * @return a list of {@link Engine} objects.
         */
        public List<Engine> getEngines() {
            return engineList;
        }
    }


}
