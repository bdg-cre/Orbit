package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the class {@link Thrust}.
 */
public class ThrustTest {

    /**
     * Test the default constructor of the {@link Thrust} class,
     * <p>
     * Verifies that the created {@code Thrust} object is not null and its default values are
     * correctly assigned.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Thrust thrust = new Thrust();
        assertNotNull(thrust, "Thrust instance should not be null.");
        assertEquals(0, thrust.getPeakThrust(), 0.001);
    }

    /**
     * Tests the parameterized constructor of the {@link Thrust} class.
     * <p>
     * Verifies that the created {@code Thrust} object is not null and that the provided value is
     * correctly assigned.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Thrust thrust = new Thrust(8.5);
        assertNotNull(thrust, "Thrust instance should not be null");
        assertEquals(8.5, thrust.getPeakThrust(), 0);
    }
}
