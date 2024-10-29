package simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import simulator.CSV.CSVConverter;

/**
 * The class {@code Engine} represents the attributes required to define a rocket engine.
 * <p>
 * This class uses annotations from the Jackson and OpenCSV libraries for serialization and
 * deserialization:
 * <ul>
 * <li>{@link JsonProperty} - Maps JSON properties to fields in this class.</li>
 * <li>{@link CsvBindByName} - Maps CSV columns to fields in this class.</li>
 * <li>{@link CsvCustomBindByName} - Maps CSV columns to fields in this class using custom
 * converters for specific data types.</li>
 * </ul>
 * </p>
 */
public class Engine {

    /**
     * The class of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "class" and the CSV column "engine_class".
     * </p>
     */
    @JsonProperty("class")
    @CsvBindByName(column = "engine_class")
    private String engineClass;

    /**
     * The description (name) of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "description" and the CSV column "description".
     * </p>
     */
    @JsonProperty("description")
    @CsvBindByName(column = "description")
    private String description;

    /**
     * The model (number) of the rocket-'s engine. It's formatted as a {@code String} to preserve
     * leading zeroes.
     * <p>
     * This field is mapped to the JSON property "model" and the CSV column "model".
     * </p>
     */
    @JsonProperty("model")
    @CsvBindByName(column = "model")
    private String model;

    /**
     * The delay of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "delay" and the CSV column "delay". Uses a custom
     * converter {@link CSVConverter.DelayConverter} for conversion.
     * </p>
     */
    @JsonProperty("delay")
    @CsvCustomBindByName(converter = CSVConverter.DelayConverter.class, column = "delay")
    private Delay delay;

    /**
     * The impulse of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "impulse" and the CSV column "totalimpulse". Uses a
     * custom converter {@link CSVConverter.ImpulseConverter} for conversion.
     * </p>
     */
    @JsonProperty("impulse")
    @CsvCustomBindByName(converter = CSVConverter.ImpulseConverter.class, column = "totalimpulse")
    private Impulse impulse;

    /**
     * The thrust of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "thrust" and the CSV column "peakthrust". Uses a
     * custom converter {@link CSVConverter.ThrustConverter} for conversion.
     * </p>
     */
    @JsonProperty("thrust")
    @CsvCustomBindByName(converter = CSVConverter.ThrustConverter.class, column = "peakthrust")
    private Thrust thrust;

    /**
     * The mass of the propellant used in the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "propellantmass" and the CSV column
     * "propellantmass".
     * </p>
     */
    @JsonProperty("propellantmass")
    @CsvBindByName(column = "propellantmass")
    private double propellantMass;

    /**
     * The total mass of the rocket's engine.
     * <p>
     * This field is mapped to the JSON property "totalmass" and the CSV column "totalmass".
     * </p>
     */
    @JsonProperty("totalmass")
    @CsvBindByName(column = "totalmass")
    private double totalEngineMass;

    /**
     * Default constructor initializing the rocket's engine with default values.
     */
    public Engine() {
        this.engineClass = "none";
        this.description = "none";
        this.model = "00000";
        this.delay = new Delay();
        this.impulse = new Impulse();
        this.thrust = new Thrust();
        this.propellantMass = 0;
        this.totalEngineMass = 0;

    }

    /**
     * Parameterized constructor to initialize the rocket's engine using specific values.
     * 
     * @param engineClass the class of the rocket's engine
     * @param description the description (name) of the rocket's engine
     * @param model the model of the rocket's engine
     * @param delay the delay of the rocket's engine
     * @param impulse the impulse of the rocket's engine
     * @param thrust the thrust of the rocket's engine
     * @param propellantMass the propellant's mass of the rocket's engine
     * @param totalEngineMass the total mass of the rocket's engine
     */
    public Engine(String engineClass, String description, String model, Delay delay,
            Impulse impulse, Thrust thrust, double propellantMass, double totalEngineMass) {
        this.engineClass = engineClass;
        this.description = description;
        this.model = model;
        this.delay = delay;
        this.impulse = impulse;
        this.thrust = thrust;
        this.propellantMass = propellantMass;
        this.totalEngineMass = totalEngineMass;

    }

    /**
     * Gets the class of the rocket's engine.
     * 
     * @return the class of the rocket's engine.
     */
    public String getEngineClass() {
        return engineClass;
    }

    /**
     * Gets the description of the rocket's engine.
     * 
     * @return the description of the rocket's engine.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the model of the rocket's engine.
     * 
     * @return the model of the rocket's engine.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the delay of the rocket's engine.
     * 
     * @return the delay of the rocket's engine.
     */
    public Delay getDelay() {
        return delay;
    }

    /**
     * Gets the impulse of the rocket's engine.
     * 
     * @return the impulse of the rocket's engine.
     */
    public Impulse getImpulse() {
        return impulse;
    }

    /**
     * Gets the thrust of the rocket's engine.
     * 
     * @return the thrust of the rocket's engine.
     */
    public Thrust getThrust() {
        return thrust;
    }

    /**
     * Gets the propellant's mass of the rocket's engine.
     * 
     * @return the propellant's mass of the rocket's engine.
     */
    public double getPropellantMass() {
        return propellantMass;
    }

    /**
     * Gets the total mass of the rocket's engine.
     * 
     * @return the total mass of the rocket's engine.
     */
    public double getTotalEngineMass() {
        return totalEngineMass;
    }

}
