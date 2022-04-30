import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class Casa_Test {
    private Map<String, HashSet<String>> makeDivisoes() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();
        divisoes.put("Sala",SmartDevice.idDevices());
        divisoes.put("Cozinha",SmartDevice.idDevices());
        divisoes.put("Quarto",SmartDevice.idDevices());
        return divisoes;
    }
    private Map<String, HashSet<String>> makeDivisoes2() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();
        divisoes.put("Sala",SmartDevice.idDevices2());
        divisoes.put("Cozinha",SmartDevice.idDevices2());
        divisoes.put("Quarto",SmartDevice.idDevices2());
        return divisoes;
    }

    private Map<String, SmartDevice> makeDevices() {
        Map<String, SmartDevice> devices = new HashMap<>();
        devices.put ("1111", new SmartCamera("1111", false, 187.2, 301.1));
        devices.put("123456789", new SmartBulb ("123456789", false, 2, 2.0));
        devices.put("2222", new SmartSpeaker ("2222", true, 15, "COMERCIAL", "SONY"));
        return devices;
    }

    private boolean mapEquals (Map<String, HashSet<String>> first, Map<String, HashSet<String>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    @Test
    public void testContructor() {
        Map<String, SmartDevice> devices = makeDevices();
       Map<String, HashSet<String>> divisoes = makeDivisoes();
        Casa casa1 = new Casa();
        assertNotNull(casa1);
        casa1 = new Casa(devices, divisoes, "Jose Antonio", "24101987");
        assertNotNull(casa1);
    }

    @Test
    public void testGetProprietario() {
        Casa casa1 = new Casa();
        assertEquals("", casa1.getProprietario());
        casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        assertEquals("Maria", casa1.getProprietario());
    }

    @Test
    public void testSetProprietario() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "ANaa", "22222222");
        casa1.setProprietario("Carlos");
        assertEquals("Carlos", casa1.getProprietario());
        casa1.setProprietario("Andre");
        assertEquals("Andre",casa1.getProprietario());
    }

    @Test
    public void testGetNIF() {
        Casa casa1 = new Casa();
        assertEquals("", casa1.getNIF());
        casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        assertEquals("11111111", casa1.getNIF());
    }

    @Test
    public void testSetNIF() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "ANaa", "22222222");
        casa1.setNIF("2121212121");
        assertEquals("2121212121", casa1.getNIF());
        casa1.setNIF("33333333333");
        assertEquals("33333333333",casa1.getNIF());
    }

    @Test //organizar melhor
    public void testGetDivisoes() {
        Casa casa1 = new Casa();
        Map<String, HashSet<String>> divisoes = casa1.getDivisoes();
        Map<String, HashSet<String>> expected = new HashMap<>();
        assertTrue(mapEquals(divisoes, expected));

        casa1 = new Casa(makeDevices(),makeDivisoes(), "Maria", "11111111");
        divisoes = casa1.getDivisoes();
        expected = makeDivisoes2();
        assertFalse(mapEquals(divisoes, expected));
    }

    @Test
    public void testSetDivisoes() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "ANaa", "22222222");
        Map<String, HashSet<String>> divisoes1 = casa1.getDivisoes();
        Map<String, HashSet<String>> divisoes2 = makeDivisoes2();
        casa1.setDivisoes(divisoes2);
        assertTrue(mapEquals(casa1.getDivisoes(), divisoes2));
        casa1.setDivisoes(divisoes1);
        assertTrue(mapEquals (casa1.getDivisoes(), divisoes1));
    }

    @Test
    public void testGetDevice() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        assertEquals(casa1.getDevice("123456789"), sd);
    }

    @Test
    public void testSetDeviceOn() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        casa1.setDeviceOn(sd.getID());
        assertTrue(casa1.getDevice("123456789").getOn());
    }

    @Test
    public void testSetDeviceOff() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        casa1.setDeviceOff(sd.getID());
        assertFalse(casa1.getDevice("123456789").getOn());
    }

    // corrigir AQUIIIIII e na seguinte a cena abstrata
    @Test
    public void testSetDivisonOn() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.setDivisonOn(divisao);
        Map<String, HashSet<String>> divisoes = casa1.getDivisoes();
        Map<String, SmartDevice> devices = casa1.getDevices();
        HashSet<String> ids = divisoes.get(divisao);

        for(String id : ids){
            assertTrue(devices.values().stream().allMatch(sd->casa1.getDevice(id).getOn()));
        }
    }

    @Test
    public void testSetDivisonOff() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.setDivisonOff(divisao);
        Map<String, HashSet<String>> divisoes = casa1.getDivisoes();
        Map<String, SmartDevice> devices = casa1.getDevices();
        HashSet<String> ids = divisoes.get(divisao);

        for(String id : ids){
            assertFalse(devices.values().stream().allMatch(sd->casa1.getDevice(id).getOn()));
        }
    }

    @Test
    public void testHasRoom() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        assertTrue(casa1.hasRoom(divisao));
    }

    @Test
    public void testAddRoom() {
        String divisao = "WC";
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        casa1.addRoom(divisao);
        assertTrue(casa1.hasRoom(divisao));
    }

    @Test
    public void testRoomHasDevice() {
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        assertTrue(casa1.roomHasDevice(divisao, "2222"));
    }


    //ver aqui
    @Test
    public void addSmartDevice (){
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        SmartDevice ss = new SmartSpeaker(false,15, "RFM", "Marshall");
        casa1.addSmartDevice(ss);
        assertEquals(ss, casa1.getDevice(ss.getID()));
    }

    @Test
    public void testAddToRoom() {
        //SmartDevice sd = new SmartBulb("999", false, SmartBulb.NEUTRAL, 2.0);
        Casa casa1 = new Casa(makeDevices(), makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.addToRoom(divisao,"999");
        assertTrue(casa1.roomHasDevice(divisao, "999"));
    }
}
