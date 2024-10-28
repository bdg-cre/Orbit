package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the class {@link Engine}.
 */
public class EngineTest {


    /**
     * Tests the default constructor of the {@link Engine} class.
     * <p>
     * Verifies that the created {@code Engine} object is not null, its {@code delay},
     * {@code impulse} and {@code thrust} are not null and its default values are correctly
     * assigned.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Engine engine = new Engine();
        assertNotNull(engine, "Engine instance should not be null.");
        assertNotNull(engine.getDelay(), "Delay should not be null.");
        assertNotNull(engine.getImpulse(), "Impulse should not be null.");
        assertNotNull(engine.getThrust(), "Thrust should not be null.");
        assertEquals("none", engine.getDescription());
        assertEquals("none", engine.getEngineClass());
        assertEquals("00000", engine.getModel());
        assertEquals(0, engine.getPropellantMass(), 0.001);
        assertEquals(0, engine.getTotalEngineMass(), 0.001);
    }

    /**
     * Tests the parameterized constructor of the {@link Engine} class.
     * <p>
     * Verifies that the created {@code Engine} object is not null, correctly initializes its
     * {@code delay}, {@code impulse} and {@code thrust} with the provided instances and that
     * further provided values are correctly assigned.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Delay delay = new Delay();
        Impulse impulse = new Impulse();
        Thrust thrust = new Thrust();
        Engine engine = new Engine("A", "Test-1", "12345", delay, impulse, thrust, 15.12, 5);
        assertNotNull(engine, "Engine instance should not be null.");
        assertSame(engine, engine.getDelay(), "Delay should be the same as provided.");
        assertSame(engine, engine.getImpulse(), "Impulse should be the same as provided.");
        assertSame(engine, engine.getThrust(), "Thrust should be the same as provided.");
        assertEquals("Test-1", engine.getDescription());
        assertEquals("A", engine.getEngineClass());
        assertEquals("12345", engine.getModel());
        assertEquals(15.12, engine.getPropellantMass(), 0.001);
        assertEquals(5, engine.getTotalEngineMass(), 0.001);
    }


}
