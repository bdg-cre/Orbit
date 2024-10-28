package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the class {@link Delay}.
 */
public class DelayTest {

    /**
     * Tests the default constructor of the {@link Delay} class.
     * <p>
     * Verifies that the created {@code Delay} object is not null and its default values are
     * correctly assigned.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Delay delay = new Delay();
        assertNotNull(delay, "Delay instance should not be null.");
        assertEquals(0, delay.getDelay(), 0.001);
    }

    /**
     * Tests the parameterized constructor of the {@link Delay} class.
     * <p>
     * Verifies that the created {@code Delay} object is not null and that the provided value is
     * correctly assigned.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Delay delay = new Delay(3.0);
        assertNotNull(delay, "Delay instance should not be null.");
        assertEquals(3.0, delay.getDelay(), 0.001);
    }

}
