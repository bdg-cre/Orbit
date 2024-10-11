package simulator.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulator.Body;
import simulator.Engine;
import simulator.Rocket;

/**
 * Unit tests for the class {@link Rocket}.
 */
public class RocketTest {

    /**
     * Tests the default constructor of the {@link Rocket} class.
     * <p>
     * Verifies that the created {@code Rocket} object is not null and its {@code engine} and
     * {@code body} are also not null.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Rocket rocket = new Rocket();
        assertNotNull(rocket, "Rocket instance should not be null.");
        assertNotNull(rocket.getEngine(), "Engine should not be null.");
        assertNotNull(rocket.getBody(), "Body should not be null.");
    }

    /**
     * Tests the parameterized constructor of the {@link Rocket} class.
     * <p>
     * Verifies that the created {@code Rocket} object correctly initializes its {@code engine} and
     * {@code body} with the provided instances, as well as not being null.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Engine engine = new Engine();
        Body body = new Body();
        Rocket rocket = new Rocket(body, engine);
        assertNotNull(rocket, "Rocket instance should not be null");
        assertSame(engine, rocket.getEngine(), "Engine should be the same as provided.");
        assertSame(body, rocket.getBody(), "Body should be the same as provided.");
    }
}
