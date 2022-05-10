import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SmartDeviceTest {

    @Test
    public void testSetOn() {
        SmartDevice smartDev1 = new SmartCamera();
        smartDev1.setOn(true);
        assertTrue(smartDev1.getOn());
        smartDev1.setOn(false);
        assertFalse(smartDev1.getOn());
    }

    @Test
    public void testSetID() {
        SmartDevice smartDev1 = new SmartCamera();
        smartDev1.setID("b2");
        assertEquals("b2", smartDev1.getID());
        smartDev1.setID("b1");
        assertEquals("b1",smartDev1.getID());
    }

    /*@Test
    public void testOnlyDigits() {
        SmartDevice sd1 = new SmartCamera("12345",true,213,435);
        assertTrue(sd1.onlyDigits(sd1.getID(), sd1.getID().length()));
        SmartDevice sd2 = new SmartCamera("123a45",true,213,435);
        assertFalse(sd2.onlyDigits(sd2.getID(), sd2.getID().length()));
    }*/
}
