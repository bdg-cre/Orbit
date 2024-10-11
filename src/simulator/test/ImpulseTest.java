package simulator.test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulator.Impulse;

public class ImpulseTest {


    /**
     * Test the default constructor of the {@link Impulse} class,
     * <p>
     * Verifies that the created {@code Impulse} object is not null and its default values are
     * correctly assigned.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Impulse impulse = new Impulse();
        assertNotNull(impulse, "Impulse instance should not be null.");
        assertEquals(0, impulse.getTotalImpulse(), 0.001);
    }

    /**
     * Tests the parameterized constructor of the {@link Impulse} class.
     * <p>
     * Verifies that the created {@code Impulse} object is not null and that the provided value is
     * correctly assigned.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Impulse impulse = new Impulse(2.2);
        assertNotNull(impulse, "Impulse instance should not be null.");
        assertEquals(2.2, impulse.getTotalImpulse(), 0.001);
    }

}
