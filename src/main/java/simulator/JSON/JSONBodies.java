package simulator.JSON;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import simulator.Body;

/**
 * The class {@code JSONBodies} represents a container for handling a collection of rocket bodies as
 * deserialized from a JSON structure.
 * <p>
 * This class uses the Jackson library to map JSON data to Java objects from the "bodies" JSON file.
 * The primary purpose of this class is to encapsulate a list of {@link Body} objects that are
 * parsed from JSON under the "bodies" property.
 * </p>
 * <p>
 * The {@link JsonProperty} annotation is used to map JSON properties to the corresponding fields.
 * </p>
 */
public class JSONBodies {

    /**
     * The container for a list of rocket bodies.
     * <p>
     * This field is mapped to the JSON property "bodies" and holds the {@link Bodies} object, which
     * contains a list of {@link Body} objects.
     * </p>
     */
    @JsonProperty("bodies")
    private Bodies bodies;

    /**
     * Gets the list of rocket bodies contained in this {@code JSONBodies} instance.
     * <p>
     * This method retrieves the list of {@link Body} objects from the inner {@code Bodies} class.
     * </p>
     * 
     * @return a list of {@link Body} objects
     */
    public List<Body> getBodyList() {
        return bodies.getBodies();
    }

    /**
     * The inner static class {@code Bodies} represents the structure containing a list of rocket
     * bodies.
     * <p>
     * This class is mapped to the JSON property "bodies" and includes a list of {@link Body}
     * objects.
     * </p>
     */
    public static class Bodies {

        /**
         * The list of rocket bodies.
         * <p>
         * This field is mapped to the JSON property "body" and holds a list of {@link Body}
         * objects.
         * </p>
         */
        @JsonProperty("body")
        private List<Body> bodyList;

        /**
         * Gets the list of rocket bodies contained in this {@code Bodies} instance.
         * 
         * @return a list of {@link Body} objects.
         */
        public List<Body> getBodies() {
            return bodyList;
        }
    }
}
