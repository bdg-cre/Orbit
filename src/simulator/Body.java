package simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;


/**
 * The class {@code Body} represents the attributes required to define a rocket body.
 * <p>
 * This class uses annotations from the Jackson and OpenCSV libraries for serialization and
 * deserialization:
 * <ul>
 * <li>{@link JsonProperty} - Maps JSON properties to fields in this class.</li>
 * <li>{@link CsvBindByName} - Maps CSV columns to fields in this class.</li>
 * </ul>
 * </p>
 */
public class Body {

    /**
     * The model of the rocket's body.
     * <p>
     * This field is mapped to the JSON property "model" and the CSV column "model".
     * </p>
     */
    @JsonProperty("model")
    @CsvBindByName(column = "model")
    private String model;

    /**
     * The empty mass of the rocket's body.
     * <p>
     * This field is mapped to the JSON property "emptymass" and the CSV column "emptymass".
     * </p>
     */
    @JsonProperty("emptymass")
    @CsvBindByName(column = "emptymass")
    private double emptyMass;

    /**
     * The length of the rocket's body.
     * <p>
     * This field is mapped to the JSON property "length" and the CSV column "length".
     * </p>
     */
    @JsonProperty("length")
    @CsvBindByName(column = "length")
    private double length;

    /**
     * The diameter of the rocket's body.
     * <p>
     * This field is mapped to the JSON property "diameter" and the CSV column "diameter".
     * </p>
     */
    @JsonProperty("diameter")
    @CsvBindByName(column = "diameter")
    private double diameter;

    /**
     * Default constructor initializing the rocket's body with default values.
     */
    public Body() {
        this.model = "Default";
        this.emptyMass = 0;
        this.length = 0;
        this.diameter = 0;
    }

    /**
     * Parameterized constructor to initialize the rocket's body using specific values.
     * 
     * @param model the model of the rocket's body
     * @param emptyMass the empty mass of the rocket's body
     * @param length the length of the rocket's body
     * @param diameter the diameter of the rocket's body
     */
    public Body(String model, double emptyMass, double length, double diameter) {
        this.model = model;
        this.emptyMass = emptyMass;
        this.length = length;
        this.diameter = diameter;
    }

    /**
     * Gets the model of the rocket's body.
     * 
     * @return the model of the rocket's body.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the empty mass of the rocket's body.
     * 
     * @return the empty mass of the rocket's body.
     */
    public double getEmptyMass() {
        return emptyMass;
    }

    /**
     * Gets the length of the rocket's body.
     * 
     * @return the length of the rocket's body.
     */
    public double getLength() {
        return length;
    }

    /**
     * Gets the diameter of the rocket's body.
     * 
     * @return the diameter of the rocket's body.
     */
    public double getDiameter() {
        return diameter;
    }

}
