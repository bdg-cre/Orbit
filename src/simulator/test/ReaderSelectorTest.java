package simulator.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulator.ReaderSelector;

public class ReaderSelectorTest {

    @Test
    public void testDefaultConstructor() {
        ReaderSelector rs = new ReaderSelector();
        assertNotNull(rs, "ReaderSelector instance should not be null.");
    }

}
