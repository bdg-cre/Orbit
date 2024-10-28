package simulator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class SelectorTest {


    @Test
    public void testDefaultConstructor() {
        Selector selector = new Selector();
        assertNotNull(selector, "Selector instance should not be null.");
    }

}
