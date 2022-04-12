import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SmartSpeakerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SmartSpeaker_Test {
    /**
     * Default constructor for test class SmartSpeakerTest
     */
    public SmartSpeaker_Test() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        SmartSpeaker smartSpe1 = new SmartSpeaker();
        assertTrue(smartSpe1!=null);
        //smartSpe1 = new SmartSpeaker("b1");
        assertTrue(smartSpe1!=null);
        smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        assertTrue(smartSpe1!=null);
    }

    @Test
    public void testGetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        assertEquals(5, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker(smartSpe1.MAX,"RUM", "JBL", 7.23);
        assertEquals(20, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker(-10,"RUM", "JBL", 7.23);
        assertEquals(0, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker();
        assertEquals(0, smartSpe1.getVolume());
    }

    @Test
    public void testSetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        smartSpe1.volumeUp();
        smartSpe1.volumeUp();
        assertEquals(7, smartSpe1.getVolume());
        for (int i=0; i<25; i++) smartSpe1.volumeUp();
        assertEquals(20, smartSpe1.getVolume());
        for (int i=0; i<30; i++) smartSpe1.volumeDown();
        assertEquals(0, smartSpe1.getVolume());
    }

    @Test
    public void testGetChannel() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker(5,"s2", "XPTO", 7.23);
        assertEquals("XPTO", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker();
        assertEquals("", smartSpe1.getChannel());
    }

    @Test
    public void testSetChannel() {
        //SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"", "JBL", 7.23);
        smartSpe1.setChannel("RUM");
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1.setChannel("XPTO");
        assertEquals("XPTO", smartSpe1.getChannel());
    }
/*
    @Test
    public void testGetMarca() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        assertEquals("JBL", smartSpe1.getMarca());
        smartSpe1 = new SmartSpeaker(5,"s2", "Branca", 7.23);
        assertEquals("Branca", smartSpe1.getMarca());
        smartSpe1 = new SmartSpeaker();
        assertEquals("", smartSpe1.getMarca());
    }

    @Test
    public void testSetMarca() {
        //SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "", 7.23);
        smartSpe1.setMarca("JBL");
        assertEquals("JBL", smartSpe1.getMarca());
        smartSpe1.setMarca("Branca");
        assertEquals("Branca", smartSpe1.getMarca());
    }

    @Test
    public void testGetConsumoDiario() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 7.23);
        assertEquals(7.23, smartSpe1.getConsumoDiario());
        smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 10.0);
        assertEquals(10.0, smartSpe1.getConsumoDiario());
        smartSpe1 = new SmartSpeaker();
        assertEquals(0.0, smartSpe1.getConsumoDiario());
    }

    @Test
    public void testSetConsumoDiario() {
        SmartSpeaker smartSpe1 = new SmartSpeaker(5,"RUM", "JBL", 0.0);
        smartSpe1.setConsumoDiario(32.12);
        assertEquals(32.12, smartSpe1.getConsumoDiario());
        smartSpe1.setConsumoDiario(1.11);
        assertEquals(1.11, smartSpe1.getConsumoDiario());
    }*/

}
