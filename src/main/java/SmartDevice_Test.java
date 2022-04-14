import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartDevice_Test {
    private List<SmartDevice> makeDevices() {
        List<SmartDevice> devices = new ArrayList<SmartDevice>();
        devices.add(new SmartSpeaker());
        devices.add(new SmartBulb());
        devices.add(new SmartCamera());
        devices.add(new SmartDevice("1234", true));
        devices.add(new SmartSpeaker("1111", false,15, "RFM", "Marshall"));
        devices.add(new SmartBulb("12345", true, SmartBulb.WARM, 2.0));
        return devices;
    }

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
        //smartDev1 = new SmartDevice("b1", true);
        //assertTrue(smartDev1.getOn());
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
    public void testTurnAllOn() {
        List<SmartDevice> devices = makeDevices();
        //turnAllOn(devices);
    }

    @Test
    public void testTurnAllOff() {
        List<SmartDevice> devices = makeDevices();
        for(SmartDevice sd : devices) {
            sd.setOn(false);
            assertFalse(sd.getOn());
        }
    }
}
