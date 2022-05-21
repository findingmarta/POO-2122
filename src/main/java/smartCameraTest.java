import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class smartCameraTest {
    @Test
    public void testConstructor(){
        SmartCamera smartCamera = new SmartCamera();
        assertEquals("",smartCamera.getID());
        smartCamera.setID("2");
        assertEquals("2",smartCamera.getID());
        assertFalse(smartCamera.getOn());
        smartCamera.setOn(true);
        assertTrue(smartCamera.getOn());
        assertEquals(0.0, smartCamera.getResolution());
        assertEquals(0.0, smartCamera.getSize());

        smartCamera = new SmartCamera("12345",true, 1080.0,200.30);
        assertEquals("12345",smartCamera.getID());
        assertTrue(smartCamera.getOn());
        assertEquals(1080.0, smartCamera.getResolution());
        assertEquals(200.30, smartCamera.getSize());

        SmartCamera umaSC = new SmartCamera("666", true, 1900.0, 2080.0);
        smartCamera = new SmartCamera(umaSC);
        assertEquals("666",smartCamera.getID());
        assertTrue(smartCamera.getOn());
        assertEquals(1900.0, smartCamera.getResolution());
        assertEquals(2080.0, smartCamera.getSize());
    }

    @Test
    public void testGetSize (){
        SmartCamera smartCamera = new SmartCamera("3542",true,1034.0,150.1);
        assertEquals(150.1, smartCamera.getSize());

        smartCamera = new SmartCamera();
        assertEquals(0.0, smartCamera.getSize());
    }

    @Test
    public void testSetSize (){
        SmartCamera smartCamera = new SmartCamera();
        smartCamera.setSize(654.2);
        assertEquals(654.2, smartCamera.getSize());

        smartCamera = new SmartCamera("6879",true,1034.1,150.1);
        smartCamera.setSize(200.37);
        assertEquals(200.37, smartCamera.getSize());
        smartCamera.setSize(152.6);
        assertEquals(152.6, smartCamera.getSize());
    }

    @Test
    public void testGetResolution (){
        SmartCamera smartCamera = new SmartCamera("3542",true,230.98,190.0);
        assertEquals(230.98, smartCamera.getResolution());

        smartCamera = new SmartCamera();
        assertEquals(0.0, smartCamera.getResolution());
    }

    @Test
    public void testSetResolution (){
        SmartCamera smartCamera = new SmartCamera();
        smartCamera.setResolution(654.2);
        assertEquals(654.2, smartCamera.getResolution());

        smartCamera = new SmartCamera("9872645",true, 1066.0,603.1);
        smartCamera.setResolution(603.3);
        assertEquals(603.3, smartCamera.getResolution());
        smartCamera.setResolution(200.2);
        assertEquals(200.2, smartCamera.getResolution());
    }

    @Test
    public void testConsumoEnergia (){
        SmartCamera smartCamera = new SmartCamera("56498",true,200.0,35.1);
        assertEquals(70.53, smartCamera.consumoEnergia());

        smartCamera = new SmartCamera("56498",true,-14,60.0);
        assertEquals(0, smartCamera.consumoEnergia());

        smartCamera = new SmartCamera("56498",true,140.2,-55);
        assertEquals(0, smartCamera.consumoEnergia());

        smartCamera = new SmartCamera();
        assertEquals(0, smartCamera.consumoEnergia());

        smartCamera = new SmartCamera("56498",true,140.2,60.0);
        assertEquals(60.059999999999995, smartCamera.consumoEnergia());
    }

}
