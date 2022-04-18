import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class Casa_Test {
    private Map<String, List<SmartDevice>> makeDivisoes() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        divisoes.put("Sala",SmartDevice.makeDevices());
        divisoes.put("Cozinha",SmartDevice.makeDevices());
        divisoes.put("Quarto",SmartDevice.makeDevices());
        return divisoes;
    }
    private Map<String, List<SmartDevice>> makeDivisoes2() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        divisoes.put("Sala",SmartDevice.makeDevices2());
        divisoes.put("Cozinha",SmartDevice.makeDevices2());
        divisoes.put("Quarto",SmartDevice.makeDevices2());
        return divisoes;
    }

    private boolean mapEquals (Map<String, List<SmartDevice>> first, Map<String, List<SmartDevice>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    @Test
    public void testContructor() {
        Map<String, List<SmartDevice>> divisoes = makeDivisoes();
        Casa casa1 = new Casa();
        assertNotNull(casa1);
        casa1 = new Casa(divisoes, "Jose Antonio", "24101987");
        assertNotNull(casa1);
    }

    @Test
    public void testGetProprietario() {
        Casa casa1 = new Casa();
        assertEquals("", casa1.getProprietario());
        casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        assertEquals("Maria", casa1.getProprietario());
    }

    @Test
    public void testSetProprietario() {
        Casa casa1 = new Casa(makeDivisoes(), "ANaa", "22222222");
        casa1.setProprietario("Carlos");
        assertEquals("Carlos", casa1.getProprietario());
        casa1.setProprietario("Andre");
        assertEquals("Andre",casa1.getProprietario());
    }

    @Test
    public void testGetNIF() {
        Casa casa1 = new Casa();
        assertEquals("", casa1.getNIF());
        casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        assertEquals("11111111", casa1.getNIF());
    }

    @Test
    public void testSetNIF() {
        Casa casa1 = new Casa(makeDivisoes(), "ANaa", "22222222");
        casa1.setNIF("2121212121");
        assertEquals("2121212121", casa1.getNIF());
        casa1.setNIF("33333333333");
        assertEquals("33333333333",casa1.getNIF());
    }

    @Test //organizar melhor
    public void testGetDivisoes() {
        Casa casa1 = new Casa();
        Map<String, List<SmartDevice>> divisoes = casa1.getDivisoes();
        Map<String, List<SmartDevice>> expected = new HashMap<>();
        assertTrue(mapEquals(divisoes, expected));

        casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        divisoes = casa1.getDivisoes();
        expected = makeDivisoes2();
        assertFalse(mapEquals(divisoes, expected));
    }

    @Test
    public void testSetDivisoes() {
        Casa casa1 = new Casa(makeDivisoes(), "ANaa", "22222222");
        Map<String, List<SmartDevice>> divisoes1 = casa1.getDivisoes();
        Map<String, List<SmartDevice>> divisoes2 = makeDivisoes2();
        casa1.setDivisoes(divisoes2);
        assertTrue(mapEquals(casa1.getDivisoes(), divisoes2));
        casa1.setDivisoes(divisoes1);
        assertTrue(mapEquals (casa1.getDivisoes(), divisoes1));
    }

    @Test
    public void testGetDevice() {
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        String divisao = "Sala";
        assertEquals(casa1.getDevice(divisao, "123456789"), sd);
    }

    @Test
    public void testSetDeviceOn() {
        //Casa casa1 = new Casa();
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        String divisao = "Sala";
        casa1.setDeviceOn(divisao, sd);
        assertTrue(casa1.getDevice(divisao, "123456789").getOn());
    }

    @Test
    public void testSetDeviceOff() {
        //Casa casa1 = new Casa();
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        String divisao = "Sala";
        casa1.setDeviceOff(divisao, sd);
        assertFalse(casa1.getDevice(divisao, "123456789").getOn());
    }

    @Test
    public void testSetDivisonOn() {
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.setDivisonOn(divisao);
        Map<String, List<SmartDevice>> divisoes = casa1.getDivisoes();
        assertTrue(divisoes.get(divisao).stream().allMatch(SmartDevice::getOn));
    }

    @Test
    public void testSetDivisonOff() {
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.setDivisonOff(divisao);
        Map<String, List<SmartDevice>> divisoes = casa1.getDivisoes();
        assertFalse(divisoes.get(divisao).stream().allMatch(SmartDevice::getOn));
    }

    @Test
    public void testHasRoom() {
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        assertTrue(casa1.hasRoom(divisao));
    }

    @Test
    public void testAddRoom() {
        String divisao = "WC";
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartSpeaker());
        devices.add(new SmartCamera());
        devices.add(new SmartDevice("1234", true));
        devices.add(new SmartSpeaker("1111", false,15, "RFM", "Marshall"));
        devices.add(new SmartCamera("2222", false, 155.0, 33.0));
        devices.add(new SmartBulb("12345", false, SmartBulb.WARM, 2.0));

        casa1.addRoom(divisao,devices);
        assertTrue(casa1.hasRoom(divisao));
    }

    @Test
    public void testRoomHasDevice() {
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        assertTrue(casa1.roomHasDevice(divisao, "2222"));
    }

    @Test
    public void testAddToRoom() {
        SmartDevice sd = new SmartBulb("999", false, SmartBulb.NEUTRAL, 2.0);
        Casa casa1 = new Casa(makeDivisoes(), "Maria", "11111111");
        String divisao = "Sala";
        casa1.addToRoom(divisao,sd);
        assertTrue(casa1.roomHasDevice(divisao, "999"));
    }
}
