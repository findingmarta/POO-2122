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
        assertNotNull(smartBul1);
        smartBul1 = new SmartBulb("b1");
        assertNotNull(smartBul1);
        smartBul1 = new SmartBulb("b1", true,SmartBulb.NEUTRAL, 5.23);
        assertNotNull(smartBul1);
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
/*
    @Test
    public void testEquals() {
        SmartDevice smartDev1 = new SmartBulb("b1", false, 2, 23.0);
        SmartDevice smartDev2 = new SmartBulb("b1", false, 2, 23.0);
        assertTrue(smartDev1.equals(smartDev2));

        smartDev1.setOn(true);
        assertFalse(smartDev1.equals(smartDev2));

        smartDev1.setOn(false);
        smartDev2.setID("b2");
        assertFalse(smartDev1.equals(smartDev2));
    }*/

    @Test
    public void testGetDimensao() {
        SmartBulb smartBul1 = new SmartBulb("b1", true, SmartBulb.COLD, 50.2);
        assertEquals(50.2, smartBul1.getDimensao());
        smartBul1 = new SmartBulb("b1", true,SmartBulb.WARM, 120.1);
        assertEquals(120.1, smartBul1.getDimensao());
        smartBul1 = new SmartBulb("b1", true,SmartBulb.COLD, 48.0);
        assertEquals(48.0, smartBul1.getDimensao());
        smartBul1 = new SmartBulb("b1");
        assertEquals(0.0, smartBul1.getDimensao());
    }

    @Test
    public void testSetDimensao() {
        SmartBulb smartBul1 = new SmartBulb("b1");
        smartBul1.setDimensao(29.1);
        assertEquals(29.1, smartBul1.getDimensao());
        smartBul1.setDimensao(78.3);
        assertEquals(78.3, smartBul1.getDimensao());
    }

    @Test
    public void testConsumoEnergia (){
        SmartBulb smartBul1 = new SmartBulb("b1", false, SmartBulb.COLD, 23.7);
        assertEquals(24.7, smartBul1.consumoEnergia());
        smartBul1 = new SmartBulb("b1", true,SmartBulb.WARM, 78.1);
        assertEquals(81.1, smartBul1.consumoEnergia());
    }

}
