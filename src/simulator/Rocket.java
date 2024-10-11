package simulator;

/**
 * The class {@code Rocket} represents a rocket, combining an engine and a body. This class is used
 * by the {@code Selector} class to create a selected rocket configuration.
 * <p>
 * Instances of this class allow access to the rocket's engine and body.
 * </p>
 * 
 */
public class Rocket {

    /**
     * The engine of the rocket.
     */
    private Engine engine;
    /**
     * The body of the rocket.
     */
    private Body body;

    /**
     * Default constructor initializing with an empty body and engine.
     */
    public Rocket() {
        this.body = new Body();
        this.engine = new Engine();
    }

    /**
     * Parameterized constructor to initialize with a specified body and engine.
     * 
     * @param body the rocket's body
     * @param engine the rocket's engine
     */
    public Rocket(Body body, Engine engine) {
        this.body = body;
        this.engine = engine;
    }

    /**
     * Gets the engine of the rocket.
     * 
     * @return the rocket's engine
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Gets the body of the rocket.
     * 
     * @return the rocket's body
     */
    public Body getBody() {
        return body;
    }



}
