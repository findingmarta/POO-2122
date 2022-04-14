import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SmartSpeaker_Test {
    @Test
    public void testConstructor() {
        SmartSpeaker smartSpe1 = new SmartSpeaker();
        assertTrue(true);
        smartSpe1 = new SmartSpeaker("65478", true,5,"RUM", "JBL");
        assertTrue(true);
    }

    @Test
    public void testGetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("5431",true,5,"RUM", "JBL");
        assertEquals(5, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker("321", true,SmartSpeaker.MAX,"RUM", "JBL");
        assertEquals(20, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker("6666", false, -10,"RUM", "JBL");
        assertEquals(0, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker("2222", true,32,"RUM", "JBL");
        assertEquals(20, smartSpe1.getVolume());
        smartSpe1 = new SmartSpeaker();
        assertEquals(0, smartSpe1.getVolume());
    }

    @Test
    public void testSetVolume() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("7777", true, 5,"RUM", "JBL");
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
        SmartSpeaker smartSpe1 = new SmartSpeaker("9879", true,5,"RUM", "JBL");
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker("98700",false,5,"s2", "XPTO");
        assertEquals("s2", smartSpe1.getChannel());
        smartSpe1 = new SmartSpeaker();
        assertEquals("", smartSpe1.getChannel());
    }

    @Test
    public void testSetChannel() {
        //SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("77777", true,5,"", "JBL");
        smartSpe1.setChannel("RUM");
        assertEquals("RUM", smartSpe1.getChannel());
        smartSpe1.setChannel("XPTO");
        assertEquals("XPTO", smartSpe1.getChannel());
    }

    @Test
    public void testGetMarca() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("98799", true,5,"RUM", "JBL");
        assertEquals("JBL", smartSpe1.getMarca());
        smartSpe1 = new SmartSpeaker("7658",true,5,"s2", "Branca");
        assertEquals("Branca", smartSpe1.getMarca());
        smartSpe1 = new SmartSpeaker();
        assertEquals("", smartSpe1.getMarca());
    }

    @Test
    public void testSetMarca() {
        //SmartSpeaker smartSpe1 = new SmartSpeaker("s1");
        SmartSpeaker smartSpe1 = new SmartSpeaker("98756",true,5,"RUM", "");
        smartSpe1.setMarca("JBL");
        assertEquals("JBL", smartSpe1.getMarca());
        smartSpe1.setMarca("Branca");
        assertEquals("Branca", smartSpe1.getMarca());
    }

    @Test
    public void testConsumoEnergia() {
        SmartSpeaker smartSpe1 = new SmartSpeaker("90000",true,5,"RUM", "JBL");
        assertEquals(10,  smartSpe1.consumoEnergia());
        smartSpe1 = new SmartSpeaker("78888",false,10,"s2", "XPTO");
        assertEquals(6, smartSpe1.consumoEnergia());
        smartSpe1 = new SmartSpeaker("6544", true,25,"s2", "XPTO");
        assertEquals(26, smartSpe1.consumoEnergia());
    }
}
