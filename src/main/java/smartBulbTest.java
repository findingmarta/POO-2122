import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * The test class SmartBulbTest.
 */
public class smartBulbTest {

    @Test
    public void testContructor() {
        SmartBulb smartBulb = new SmartBulb();
        assertEquals("", smartBulb.getID());
        smartBulb.setID("1");
        assertEquals("1", smartBulb.getID());
        assertFalse(smartBulb.getOn());
        smartBulb.setOn(true);
        assertTrue(smartBulb.getOn());
        assertEquals(1, smartBulb.getTone());
        assertEquals(0.0, smartBulb.getDimensao());

        smartBulb = new SmartBulb("97845", true, 2, 32.3);
        assertEquals("97845", smartBulb.getID());
        assertTrue(smartBulb.getOn());
        assertEquals(SmartBulb.WARM, smartBulb.getTone());
        assertEquals(32.3, smartBulb.getDimensao());

        SmartBulb umaSB = new SmartBulb("5435", false, 1, 15.0);
        smartBulb = new SmartBulb(umaSB);
        assertEquals("5435", smartBulb.getID());
        assertFalse(smartBulb.getOn());
        assertEquals(1, smartBulb.getTone());
        assertEquals(15.0, smartBulb.getDimensao());
    }

    @Test
    public void testGetTone() {
        SmartBulb smartBulb = new SmartBulb("6879", true, SmartBulb.COLD, 32.0);
        assertEquals(0, smartBulb.getTone());

        smartBulb = new SmartBulb("6879", true,SmartBulb.NEUTRAL, 32.0);
        assertEquals(1, smartBulb.getTone());

        smartBulb = new SmartBulb("6879", true,SmartBulb.WARM, 32.0);
        assertEquals(2, smartBulb.getTone());

        smartBulb = new SmartBulb();
        assertEquals(SmartBulb.NEUTRAL, smartBulb.getTone());
    }

    @Test
    public void testSetTone() {
        SmartBulb smartBulb = new SmartBulb();
        smartBulb.setTone(2);
        assertEquals(SmartBulb.WARM, smartBulb.getTone());

        smartBulb.setTone(10);
        assertEquals(SmartBulb.WARM, smartBulb.getTone());

        smartBulb.setTone(-10);
        assertEquals(SmartBulb.COLD, smartBulb.getTone());

        smartBulb = new SmartBulb("9873", true, 2, 13.13);
        smartBulb.setTone(-10);
        assertEquals(SmartBulb.COLD, smartBulb.getTone());

        smartBulb.setTone(1);
        assertEquals(SmartBulb.NEUTRAL, smartBulb.getTone());
    }

    @Test
    public void testGetDimensao() {
        SmartBulb smartBulb = new SmartBulb("6879", true, SmartBulb.COLD, 32.0);
        assertEquals(32.0, smartBulb.getDimensao());

        smartBulb = new SmartBulb("6879", true,SmartBulb.COLD, 5.0);
        assertEquals(5.0, smartBulb.getDimensao());

        smartBulb = new SmartBulb();
        assertEquals(0.0, smartBulb.getDimensao());
    }

    @Test
    public void testSetDimensao() {
        SmartBulb smartBul1 = new SmartBulb();
        smartBul1.setDimensao(29.1);
        assertEquals(29.1, smartBul1.getDimensao());

        smartBul1.setDimensao(78.3);
        assertEquals(78.3, smartBul1.getDimensao());

        smartBul1.setDimensao(-12.9);
        assertEquals(0.0, smartBul1.getDimensao());

        smartBul1 = new SmartBulb("1",true,2,32.0);
        smartBul1.setDimensao(15.2);
        assertEquals(15.2, smartBul1.getDimensao());
    }

    @Test
    public void testConsumoEnergia (){
        SmartBulb smartBulb = new SmartBulb("1325", false, SmartBulb.COLD, 23.7);
        assertEquals(24.7, smartBulb.consumoEnergia());

        smartBulb = new SmartBulb("1325", true,SmartBulb.WARM, -78.1);
        assertEquals(0.0, smartBulb.consumoEnergia());

        smartBulb = new SmartBulb();
        assertEquals(0.0, smartBulb.consumoEnergia());

        smartBulb = new SmartBulb("1325", true,SmartBulb.WARM, 78.1);
        assertEquals(81.1, smartBulb.consumoEnergia());
    }

}
