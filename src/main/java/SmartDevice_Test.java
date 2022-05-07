import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SmartDevice_Test {

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
}
