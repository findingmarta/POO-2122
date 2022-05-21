import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class smartSpeakerTest {

    @Test
    public void testConstructor() {
        SmartSpeaker smartSpeaker = new SmartSpeaker();
        assertEquals("", smartSpeaker.getID());
        smartSpeaker.setID("3");
        assertEquals("3", smartSpeaker.getID());
        assertFalse(smartSpeaker.getOn());
        smartSpeaker.setOn(true);
        assertTrue(smartSpeaker.getOn());
        assertEquals(0, smartSpeaker.getVolume());
        assertEquals("", smartSpeaker.getChannel());
        assertEquals("", smartSpeaker.getMarca());

        smartSpeaker = new SmartSpeaker("65478", true,5,"RUM", "JBL");
        assertEquals("65478", smartSpeaker.getID());
        assertTrue(smartSpeaker.getOn());
        assertEquals(5, smartSpeaker.getVolume());
        assertEquals("RUM", smartSpeaker.getChannel());
        assertEquals("JBL", smartSpeaker.getMarca());

        SmartSpeaker umSS = new SmartSpeaker("12345", false,15,"RFM", "Marshall");
        smartSpeaker = new SmartSpeaker(umSS);
        assertEquals("12345", smartSpeaker.getID());
        assertFalse(smartSpeaker.getOn());
        assertEquals(15, smartSpeaker.getVolume());
        assertEquals("RFM", smartSpeaker.getChannel());
        assertEquals("Marshall", smartSpeaker.getMarca());
    }

    @Test
    public void testGetVolume() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("5431",true,5,"RUM", "JBL");
        assertEquals(5, smartSpeaker.getVolume());

        smartSpeaker = new SmartSpeaker("321", true,SmartSpeaker.MAX,"RUM", "JBL");
        assertEquals(100, smartSpeaker.getVolume());

        smartSpeaker = new SmartSpeaker("6666", false, -10,"RUM", "JBL");
        assertEquals(0, smartSpeaker.getVolume());

        smartSpeaker = new SmartSpeaker();
        assertEquals(0, smartSpeaker.getVolume());

        smartSpeaker = new SmartSpeaker("2222", true,32,"RUM", "JBL");
        assertEquals(32, smartSpeaker.getVolume());
    }

    @Test
    public void testSetVolume() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("7777", true, 5,"RUM", "JBL");
        smartSpeaker.volumeUp();
        smartSpeaker.volumeUp();
        assertEquals(7, smartSpeaker.getVolume());
        for (int i=0; i<25; i++) smartSpeaker.volumeUp();
        assertEquals(32, smartSpeaker.getVolume());
        for (int i=0; i<70; i++) smartSpeaker.volumeUp();
        assertEquals(100, smartSpeaker.getVolume());
        for (int i=0; i<30; i++) smartSpeaker.volumeDown();
        assertEquals(70, smartSpeaker.getVolume());
        for (int i=0; i<75; i++) smartSpeaker.volumeDown();
        assertEquals(0, smartSpeaker.getVolume());
    }

    @Test
    public void testGetChannel() {
        SmartSpeaker smartSpeaker = new SmartSpeaker();
        assertEquals("", smartSpeaker.getChannel());

        smartSpeaker = new SmartSpeaker("9879", true,5,"RUM", "JBL");
        assertEquals("RUM", smartSpeaker.getChannel());

        smartSpeaker = new SmartSpeaker("98700",false,5,"s2", "XPTO");
        assertEquals("s2", smartSpeaker.getChannel());
    }

    @Test
    public void testSetChannel() {
        SmartSpeaker smartSpeaker = new SmartSpeaker();
        smartSpeaker.setChannel("RUM");
        assertEquals("RUM", smartSpeaker.getChannel());

        smartSpeaker = new SmartSpeaker("77777", true,5,"RFM", "JBL");
        smartSpeaker.setChannel("XPTO");
        assertEquals("XPTO", smartSpeaker.getChannel());
        smartSpeaker.setChannel("Comercial");
        assertEquals("Comercial", smartSpeaker.getChannel());
    }

    @Test
    public void testGetMarca() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("98799", true,5,"RUM", "JBL");
        assertEquals("JBL", smartSpeaker.getMarca());

        smartSpeaker = new SmartSpeaker();
        assertEquals("", smartSpeaker.getMarca());
    }

    @Test
    public void testSetMarca() {
        SmartSpeaker smartSpeaker = new SmartSpeaker();
        smartSpeaker.setMarca("Sony");
        assertEquals("Sony", smartSpeaker.getMarca());

        smartSpeaker = new SmartSpeaker("98756",true,5,"RUM", "");
        smartSpeaker.setMarca("JBL");
        assertEquals("JBL", smartSpeaker.getMarca());
        smartSpeaker.setMarca(null);
        assertNull(smartSpeaker.getMarca());
        smartSpeaker.setMarca("Branca");
        assertEquals("Branca", smartSpeaker.getMarca());
    }

    @Test
    public void testConsumoEnergia() {
        SmartSpeaker smartSpeaker = new SmartSpeaker("90000",true,5,"RUM", "JBL");
        assertEquals(10,  smartSpeaker.consumoEnergia());

        smartSpeaker = new SmartSpeaker("78888",false,-10,"Comercial", "Marshall");
        assertEquals(10, smartSpeaker.consumoEnergia());

        smartSpeaker = new SmartSpeaker("6544", true,131,"RFM", "XPTO");
        assertEquals(106, smartSpeaker.consumoEnergia());

        smartSpeaker = new SmartSpeaker();
        assertEquals(0, smartSpeaker.consumoEnergia());
    }
}
