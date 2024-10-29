package simulator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ReaderSelectorTest {

    @Test
    public void testDefaultConstructor() {
        ReaderSelector rs = new ReaderSelector();
        assertNotNull(rs, "ReaderSelector instance should not be null.");
    }

}
