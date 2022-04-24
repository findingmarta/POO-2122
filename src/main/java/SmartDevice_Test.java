import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class SmartDevice_Test {
    @Test
    public void testContructor() {
        SmartDevice smartDev1 = new SmartDevice();
        assertTrue(smartDev1!=null);
        smartDev1 = new SmartDevice("b1");
        assertTrue(smartDev1!=null);
        smartDev1 = new SmartDevice("b1", true);
        assertTrue(smartDev1!=null);
    }

    @Test
    public void testGetID() {
        SmartDevice smartDev1 = new SmartDevice();
        assertEquals("", smartDev1.getID());
        smartDev1 = new SmartDevice("b1");
        assertEquals("b1", smartDev1.getID());
    }

    @Test
    public void testGetOn() {
        SmartDevice smartDev1 = new SmartDevice();
        assertFalse(smartDev1.getOn());
        smartDev1 = new SmartDevice("b1", false);
        assertFalse(smartDev1.getOn());
        smartDev1.setOn(true);
        assertTrue(smartDev1.getOn());
    }
    @Test
    public void testSetOn() {
        SmartDevice smartDev1 = new SmartDevice("b1", false);
        smartDev1.setOn(true);
        assertTrue(smartDev1.getOn());
        smartDev1.setOn(false);
        assertFalse(smartDev1.getOn());
    }

    @Test
    public void testSetID() {
        SmartDevice smartDev1 = new SmartDevice("b1", false);
        smartDev1.setID("b2");
        assertEquals("b2", smartDev1.getID());
        smartDev1.setID("b1");
        assertEquals("b1",smartDev1.getID());
    }

    @Test
    public void testTurnAllOn() {
        List<SmartDevice> devices = SmartDevice.makeDevices();
        //turnAllOn(devices);
    }

    @Test
    public void testTurnAllOff() {
        List<SmartDevice> devices = SmartDevice.makeDevices();
        for(SmartDevice sd : devices) {
            sd.setOn(false);
            assertFalse(sd.getOn());
        }
    }

   /* @Test
    public void testEquals() {
        SmartDevice smartDev1 = new SmartDevice("b1", false);
        SmartDevice smartDev2 = new SmartDevice("b1", false);
        assertTrue(smartDev1.equals(smartDev2));

        smartDev1.setOn(true);
        assertFalse(smartDev1.equals(smartDev2));

        smartDev1.setOn(false);
        smartDev2.setID("b2");
        assertFalse(smartDev1.equals(smartDev2));
    }*/
}
