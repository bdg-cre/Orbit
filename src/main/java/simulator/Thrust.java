package simulator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The class {@code Thrust} represents the thrust characteristics of a rocket engine.
 * <p>
 * The thrust value is a measure of the maximum force the engine can produce to propel the rocket.
 * </p>
 */
public class Thrust {

    /**
     * The peak thrust value of the rocket's engine.
     * <p>
     * This field is annotated with {@link JsonProperty} to specify the JSON property "peakthrust"
     * during serialization and deserialization.
     * </p>
     */
    @JsonProperty("peakthrust")
    private double peakThrust;

    /**
     * Default constructor initializing the peak thrust to zero.
     */
    public Thrust() {
        this.peakThrust = 0;
    };

    /**
     * Parameterized constructor to initialize the {@code Thrust} with a specific peak thrust value.
     * 
     * @param peakThrust the peak thrust value to set
     */
    public Thrust(double peakThrust) {
        this.peakThrust = peakThrust;
    }

    /**
     * Gets the peak thrust value.
     * 
     * @return the peak thrust value
     */
    public double getPeakThrust() {
        return peakThrust;
    }
}
