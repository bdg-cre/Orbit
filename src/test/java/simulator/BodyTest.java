package simulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the class {@link Body}.
 */
public class BodyTest {

    /**
     * Tests the default constructor of the {@link Body} class.
     * <p>
     * Verifies that the created {@code Body} object is not null and its default values are
     * correctly assigned.
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        Body body = new Body();
        assertNotNull(body, "Body instance should not be null.");
        assertEquals("Default", body.getModel());
        assertEquals(0, body.getEmptyMass(), 0.001);
        assertEquals(0, body.getLength(), 0.001);
        assertEquals(0, body.getDiameter(), 0.001);
    }

    /**
     * Tests the parameterized constructor of the {@link Body} class.
     * <p>
     * Verifies that the created {@code Body} object is not null and that the provided values are
     * correctly assigned.
     * </p>
     */
    @Test
    public void testParameterizedConstructor() {
        Body body = new Body("ModelX", 5.5, 10.2, 1.3);
        assertNotNull(body);
        assertEquals("ModelX", body.getModel());
        assertEquals(5.5, body.getEmptyMass(), 0.001);
        assertEquals(10.2, body.getLength(), 0.001);
        assertEquals(1.3, body.getDiameter(), 0.001);
    }

}
