import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SmartCamera_Test {
    @Test
    public void testConstructor(){
        SmartCamera smartCam1 = new SmartCamera();
        assertNotNull(smartCam1);
        smartCam1 = new SmartCamera("cam1",true, 1080.0,200.30);
        assertNotNull(smartCam1);
    }

    @Test
    public void testGetSize (){
        SmartCamera smartCam1 = new SmartCamera("cam1",true,1034.0,150.1);
        assertEquals(150.1, smartCam1.getSize());
        smartCam1 = new SmartCamera("cam1",true,230.98,190.0);
        assertEquals(190.0, smartCam1.getSize());
        smartCam1 = new SmartCamera("cam1",true, 230.98,190.0);
        assertEquals(190.0, smartCam1.getSize());
    }

    @Test
    public void testSetSize (){
        SmartCamera smartCam1 = new SmartCamera("cam1",true,1034.1,150.1);
        smartCam1.setSize(200.37);
        assertEquals(200.37, smartCam1.getSize());
        smartCam1.setSize(152.6);
        assertEquals(152.6, smartCam1.getSize());
    }

    @Test
    public void testGetResolution (){
        SmartCamera smartCam1 = new SmartCamera("cam1",true,2080.4,222.1);
        assertEquals(2080.4, smartCam1.getResolution());
        smartCam1 = new SmartCamera("cam1",true,109.7,99.12);
        assertEquals(109.7, smartCam1.getResolution());
    }

    @Test
    public void testSetResolution (){
        SmartCamera smartCam1 = new SmartCamera("cam1",true, 1066.0,603.1);
        smartCam1.setResolution(603.3);
        assertEquals(603.3, smartCam1.getResolution());
        smartCam1.setResolution(200.2);
        assertEquals(200.2, smartCam1.getResolution());
    }

    @Test
    public void testConsumoEnergia (){
        SmartCamera smartCam1 = new SmartCamera("cam1",true,200.0,35.1);
        assertEquals(7023, smartCam1.consumoEnergia());
        smartCam1 = new SmartCamera("cam1",true,140.2,60.0);
        assertEquals(8415, smartCam1.consumoEnergia());
    }

}
