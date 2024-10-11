package simulator.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import simulator.Selector;

public class SelectorTest {


    @Test
    public void testDefaultConstructor() {
        Selector selector = new Selector();
        assertNotNull(selector, "Selector instance should not be null.");
    }

}
