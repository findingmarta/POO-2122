import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Fornecedores_Test {
        @Test
        public void testContructor(SmartDevice sd) {
            assertEquals("SmartDevice", sd.getClass());
        }
}
