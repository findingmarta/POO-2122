import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SmartBulbTest.
 */
public class SmartBulb_Test {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testContructor() {
        SmartBulb smartBul1 = new SmartBulb();
        assertTrue(smartBul1!=null);
        smartBul1 = new SmartBulb("b1");
        assertTrue(smartBul1!=null);
        smartBul1 = new SmartBulb("b1", true,SmartBulb.NEUTRAL, 5.23);
        assertTrue(smartBul1!=null);
    }

    @Test
    public void testGetTone() {
        SmartBulb smartBul1 = new SmartBulb("b1", true, SmartBulb.COLD, 32.0);
        assertEquals(0, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1", true,SmartBulb.NEUTRAL, 32.0);
        assertEquals(1, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1", true,SmartBulb.WARM, 32.0);
        assertEquals(2, smartBul1.getTone());
        smartBul1 = new SmartBulb("b1");
        assertEquals(SmartBulb.NEUTRAL, smartBul1.getTone());
    }

    @Test
    public void testSetTone() {
        SmartBulb smartBul1 = new SmartBulb("b1");
        smartBul1.setTone(2);
        assertEquals(SmartBulb.WARM, smartBul1.getTone());
        smartBul1.setTone(10);
        assertEquals(SmartBulb.WARM, smartBul1.getTone());
        smartBul1.setTone(-10);
        assertEquals(SmartBulb.COLD, smartBul1.getTone());
    }

    //TESTAR O RESTO DAS VARIAVEIS

}
