package simulator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class {@code Impulse} represents the total impulse the rocket's engine has.
 * <p>
 * The impulse is a measure of the total momentum charge provided by the engine.
 * </p>
 */
public class Impulse {

    /**
     * The total impulse of the rocket's engine.
     * <p>
     * This field is annotated with {@link JsonProperty} to specify the JSON property "totalimpulse"
     * during serialization and deserialization.
     * </p>
     */
    @JsonProperty("totalimpulse")
    private double totalImpulse;

    /**
     * Default constructor initializing the total impulse with a default value of zero.
     */
    public Impulse() {
        this.totalImpulse = 0;
    };

    /**
     * Parameterized constructor to initialize the {@code Impulse} using a specific total impulse
     * value.
     * 
     * @param totalImpulse the total impulse of the rocket's engine.
     */
    public Impulse(double totalImpulse) {
        this.totalImpulse = totalImpulse;

    }

    /**
     * Gets the total impulse of the rocket's engine.
     * 
     * @return the total impulse of the rocket's engine.
     */
    public double getTotalImpulse() {
        return totalImpulse;
    }

}
