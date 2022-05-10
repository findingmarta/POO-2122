import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class CasaTest {
    private final Map<String, SmartDevice> devices = makeDevices();
    private final Map<String, SmartDevice> devices2 = makeDevices2();
    private final Map<String, HashSet<String>> divisoes = makeDivisoes();
    private final Map<String, HashSet<String>> divisoes2 = makeDivisoes2();

    static Map<String, HashSet<String>> makeDivisoes() {
        Map<String, HashSet<String>> divisoes = new HashMap<>();
        divisoes.put("Sala",idDevices());
        divisoes.put("Cozinha",idDevices());
        divisoes.put("Quarto",idDevices());
        return divisoes;
    }

    public static HashSet<String> idDevices() {
        HashSet<String> devices = new HashSet<>();
        devices.add("12342");
        devices.add("123456789");
        devices.add("141531");
        return devices;
    }

    static Map<String, SmartDevice> makeDevices() {
        Map<String, SmartDevice> devices = new HashMap<>();
        devices.put ("12342", new SmartCamera("12342", false, 187.2, 301.1));
        devices.put("123456789", new SmartBulb ("123456789", false, 2, 2.0));
        devices.put("141531", new SmartSpeaker ("141531", true, 15, "COMERCIAL", "SONY"));
        return devices;
    }

    private Map<String, HashSet<String>> makeDivisoes2() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();
        divisoes.put("WC",idDevices2());
        divisoes.put("Jardim",idDevices2());
        divisoes.put("Garagem",idDevices2());
        return divisoes;
    }

    public static HashSet<String> idDevices2() {
        HashSet<String> devices = new HashSet<>();
        devices.add("0000");
        devices.add("1111");
        devices.add("2222");
        return devices;
    }

    static Map<String, SmartDevice> makeDevices2() {
        Map<String, SmartDevice> devices = new HashMap<>();
        devices.put ("0000", new SmartCamera("0000", true, 546.98, 17.1));
        devices.put("1111", new SmartBulb ("1111", true, 0, 23.0));
        devices.put("2222", new SmartSpeaker ("2222", true, 10, "RFM", "JBL"));
        return devices;
    }

    private boolean mapEquals (Map<String, HashSet<String>> first, Map<String, HashSet<String>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    /**
     * Construtores
     */
    @Test
    public void testContructor() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa umaCasa = new Casa(devices,divisoes,"Manel", "24131313", fornecedor);
        Casa casa = new Casa();
        assertEquals("",casa.getProprietario());
        assertEquals("",casa.getNIF());
        assertTrue(casa.getDevices().isEmpty());
        assertTrue(casa.getDivisoes().isEmpty());
        assertNull(casa.getFornecedor());

        casa = new Casa(devices, divisoes, "Jose Antonio", "241019876", fornecedor);
        assertEquals("Jose Antonio",casa.getProprietario());
        assertEquals("241019876",casa.getNIF());
        assertEquals(devices, casa.getDevices());
        assertEquals(divisoes,casa.getDivisoes());
        assertEquals(fornecedor, casa.getFornecedor());

        fornecedor = new FornecEDP();
        casa = new Casa("Marco", "11111", fornecedor);
        assertEquals("Marco",casa.getProprietario());
        assertEquals("11111",casa.getNIF());
        assertEquals(fornecedor, casa.getFornecedor());

        casa = new Casa(umaCasa);
        assertEquals("Manel",casa.getProprietario());
        assertEquals("24131313",casa.getNIF());
        assertEquals(devices, casa.getDevices());
        assertEquals(divisoes,casa.getDivisoes());
        assertEquals(umaCasa.getFornecedor(), casa.getFornecedor());
    }

    /**
     * Getters e Setters
     */
    @Test
    public void testGetProprietario() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertEquals("", casa.getProprietario());
        casa = new Casa(devices, divisoes, "Maria", "21645889", fornecedor);
        assertEquals("Maria", casa.getProprietario());
        casa = new Casa("Raquel Oliveira", "64485889", new FornecEDP());
        assertEquals("Raquel Oliveira", casa.getProprietario());
    }

    @Test
    public void testSetProprietario() {
        Casa casa = new Casa();
        casa.setProprietario("Carlos");
        assertEquals("Carlos", casa.getProprietario());
        casa.setProprietario(null);
        assertNull(casa.getProprietario());
        casa.setProprietario("Miguel Oliveira");
        assertEquals("Miguel Oliveira", casa.getProprietario());
    }

    @Test
    public void testGetNIF() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertEquals("", casa.getNIF());
        casa = new Casa(devices, divisoes, "Maria", "21645889",fornecedor);
        assertEquals("21645889", casa.getNIF());
        casa = new Casa("Raquel Oliveira", "64485889", new FornecEDP());
        assertEquals("64485889", casa.getNIF());
    }

    @Test
    public void testSetNIF() {
        Casa casa = new Casa();
        casa.setNIF("44561373");
        assertEquals("44561373", casa.getNIF());
        casa.setNIF(null);
        assertNull(casa.getNIF());
        casa.setNIF("654922121");
        assertEquals("654922121", casa.getNIF());
    }

    @Test
    public void testGetFornecedor() {
        Casa casa = new Casa();
        assertNull(casa.getFornecedor());

        Fornecedores forn = new FornecEDP();
        casa = new Casa("Raquel Oliveira", "64485889", forn);
        assertEquals(forn, casa.getFornecedor());
    }

    @Test
    public void testSetFornecedor() {
        Casa casa = new Casa();
        Fornecedores forn = new FornecEDP();
        assertNull(casa.getFornecedor()); //espero o fornecedor a null
        casa.setFornecedor(forn);
        assertEquals(forn, casa.getFornecedor());
        casa.setFornecedor(null);
        assertNotNull(casa.getFornecedor()); //espero o fornecedor que tinha antes
        Fornecedores newForn = new FornecJomar();
        casa.setFornecedor(newForn);
        //assertEquals("Jomar", casa.getFornecedor().getNome());
        assertEquals(0.60, casa.getFornecedor().getImposto());
        assertEquals(0.148, casa.getFornecedor().getValorBase());
        assertEquals(newForn,casa.getFornecedor());
    }

    @Test
    public void testGetDivisoes() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertTrue(casa.getDivisoes().isEmpty());
        casa = new Casa(devices,divisoes, "Maria", "871564897",fornecedor);
        assertTrue(mapEquals(divisoes,casa.getDivisoes()));
    }

    @Test
    public void testSetDivisoes() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setDivisoes(divisoes);
        assertTrue(mapEquals(divisoes, casa.getDivisoes()));

        casa = new Casa(devices, divisoes2, "Ana", "22222222", fornecedor);
        assertTrue(mapEquals(divisoes2,casa.getDivisoes()));
        casa.setDivisoes(divisoes);
        assertTrue(mapEquals (divisoes,casa.getDivisoes()));
    }

    @Test
    public void testGetDevices() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertTrue(casa.getDevices().isEmpty());

        casa = new Casa(devices2,divisoes2, "Maria", "871564897",fornecedor);
        assertEquals(devices2,casa.getDevices());
    }

    @Test
    public void testSetDevices() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setDevices(devices);
        assertEquals(devices, casa.getDevices());

        casa = new Casa(devices2, divisoes2, "Ana", "22222222",fornecedor);
        assertEquals(devices2,casa.getDevices());
        casa.setDevices(devices);
        assertEquals(devices,casa.getDevices());
    }

    /**
     * Metodos
     */
    @Test
    public void testSetDeviceOn() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setDeviceOn("123");

        casa = new Casa(devices, divisoes, "Carlos", "9856461",fornecedor);
        casa.setDeviceOn("12342");
        assertTrue(casa.getDevice("12342").getOn());

        casa = new Casa(devices2,divisoes, "João Pedro", "6849816",fornecedor);
        casa.setDeviceOn("");
        casa.setDeviceOn("12342");
        casa.setDeviceOn("2222");
        assertTrue(casa.getDevice("2222").getOn());
    }

    @Test
    public void testSetDeviceOff() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setDeviceOff("123");

        casa = new Casa(devices, divisoes, "Carlos", "9856461",fornecedor);
        casa.setDeviceOff("12342");
        assertFalse(casa.getDevice("12342").getOn());

        casa = new Casa(devices2,divisoes, "João Pedro", "6849816",fornecedor);
        casa.setDeviceOff("");
        casa.setDeviceOff("12342");
        casa.setDeviceOff("2222");
        assertFalse(casa.getDevice("2222").getOn());
    }

    @Test
    public void testSetDivisonOn() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Sala";
        Casa casa = new Casa();
        casa.setDivisonOn(divisao);

        Map <String, HashSet<String>> emptyDivisions = new HashMap<>();
        emptyDivisions.put(divisao, new HashSet<>());
        casa = new Casa(devices, emptyDivisions, "Carlota", "9879798",fornecedor);
        casa.setDivisonOn("Quarto");
        casa.setDivisonOn(divisao);

        casa = new Casa(devices, divisoes, "Carlota", "9879798",fornecedor);
        HashSet<String> ids = casa.getDivisoes().get(divisao);
        casa.setDivisonOn(divisao);
        for(String id : ids){
            Casa finalCasa = casa;
            assertTrue(casa.getDevices().values().stream().allMatch(sd-> finalCasa.getDevice(id).getOn()));
        }
    }

    @Test
    public void testSetDivisonOff() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Cozinha";
        Casa casa = new Casa();
        casa.setDivisonOff(divisao);

        Map <String, HashSet<String>> emptyDivisions = new HashMap<>();
        emptyDivisions.put(divisao, new HashSet<>());
        casa = new Casa(devices, emptyDivisions, "Carlota", "9879798",fornecedor);
        casa.setDivisonOff("Quarto");
        casa.setDivisonOff(divisao);

        casa = new Casa(devices, divisoes, "Carlota", "9879798",fornecedor);
        HashSet<String> ids = casa.getDivisoes().get(divisao);
        casa.setDivisonOff(divisao);
        for(String id : ids){
            Casa finalCasa = casa;
            assertFalse(casa.getDevices().values().stream().allMatch(sd-> finalCasa.getDevice(id).getOn()));
        }
    }

    @Test
    public void testHasRoom() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Sala";
        Casa casa = new Casa();
        assertFalse(casa.hasRoom(divisao));

        Map <String, HashSet<String>> emptyDivisions = new HashMap<>();
        emptyDivisions.put(divisao, new HashSet<>());
        casa = new Casa(devices, emptyDivisions, "Carlota", "9879798",fornecedor);
        assertTrue(casa.hasRoom(divisao));
        assertFalse(casa.hasRoom("Jardim"));
    }

    @Test
    public void testRoomIsEmpty() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Cozinha";
        Casa casa = new Casa();
        assertTrue(casa.roomisEmpty(divisao));

        Map <String, HashSet<String>> emptyDivisions = new HashMap<>();
        emptyDivisions.put(divisao, new HashSet<>());
        casa = new Casa(devices, emptyDivisions, "Carlota", "9879798",fornecedor);
        assertTrue(casa.roomisEmpty(divisao));
        assertTrue(casa.roomisEmpty("Jardim"));

        casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        assertFalse(casa.roomisEmpty(divisao));
    }

    @Test
    public void testRoomHasDevice() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Sala";
        Casa casa = new Casa();
        assertFalse(casa.roomHasDevice(divisao, "12342"));

        casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        assertFalse(casa.roomHasDevice("Jardim", "12342"));
        assertFalse(casa.roomHasDevice(divisao, "0000"));
        assertTrue(casa.roomHasDevice(divisao, "12342"));
    }

    @Test
    public void testAddRoom() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "WC";
        Casa casa = new Casa();
        casa.addRoom(divisao);
        assertTrue(casa.hasRoom(divisao));
        assertTrue(casa.getDivisoes().get(divisao).isEmpty());

        casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        casa.addRoom("Sala");
        assertTrue(casa.hasRoom("Sala"));
        assertFalse(casa.getDivisoes().get("Sala").isEmpty());

        casa.addRoom(divisao);
        assertTrue(casa.hasRoom(divisao));
        assertTrue(casa.getDivisoes().get(divisao).isEmpty());
    }

    @Test
    public void testAddToRoom() {
        Fornecedores fornecedor = new FornecEndesa();
        String divisao = "Sala";
        Casa casa = new Casa();
        casa.addToRoom(divisao,"999");
        assertTrue(casa.getDivisoes().isEmpty());

        SmartDevice sd = new SmartBulb("9998",false,SmartBulb.NEUTRAL, 2.0);
        casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        casa.addSmartDevice(sd);
        casa.addToRoom(divisao,sd.getID());
        assertTrue(casa.roomHasDevice(divisao, "9998"));
    }

    @Test
    public void testHasDevice() {
        Fornecedores fornecedor = new FornecEndesa();
        SmartDevice sd = new SmartBulb("54321", true,SmartBulb.NEUTRAL, 2.0);
        Casa casa = new Casa();
        assertFalse(casa.hasDevice("1234"));
        casa.addSmartDevice(sd);
        assertTrue(casa.hasDevice("54321"));

        casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        assertFalse(casa.hasDevice("00000"));
        assertTrue(casa.hasDevice("12342"));
    }

    @Test
    public void testGetDevice() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertNull(casa.getDevice("123456789"));

        casa = new Casa(devices, divisoes, "Mario", "7965464",fornecedor);
        SmartDevice sd = new SmartBulb("123456789", false, SmartBulb.WARM, 2.0);
        assertEquals(sd,casa.getDevice(sd.getID()));

        SmartDevice sd2 = new SmartBulb ("1111", true, 0, 23.0);
        casa.setDevices(devices2);
        assertEquals(sd2,casa.getDevice(sd2.getID()));
        assertNull(casa.getDevice("555"));
    }

    @Test
    public void testAddSmartDevice (){
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);

        SmartDevice sd1 = new SmartBulb(SmartBulb.NEUTRAL, 2.0);
        casa.addSmartDevice(sd1);
        assertEquals(devices, casa.getDevices());
        assertEquals(3, casa.getDevices().size());

        SmartDevice sd2 = new SmartCamera("123", true, 1080, 720);
        casa.addSmartDevice(sd2);
        assertEquals(sd2, casa.getDevice("123"));
        assertEquals(4, casa.getDevices().size());

        SmartDevice sd3 = new SmartSpeaker("",false,30, "Comercial", "Marshall");
        casa.addSmartDevice(sd3);
        assertNull(casa.getDevice(""));
        assertEquals(4, casa.getDevices().size());

        SmartDevice sd4 = new SmartSpeaker("321",false,15, "RFM", "Marshall");
        SmartDevice sd5 = new SmartSpeaker("321",true,15, "RFM", "Marshall");
        SmartDevice sd6 = new SmartBulb("321",false,1, 32.12);
        SmartDevice sd7 = new SmartCamera("321",false, 2160, 1080);
        casa.addSmartDevice(sd4);
        assertEquals(sd4, casa.getDevice("321"));
        assertEquals(5, casa.getDevices().size());

        casa.addSmartDevice(sd5);
        assertNotEquals(sd5, casa.getDevice("321"));
        assertEquals(5, casa.getDevices().size());

        casa.addSmartDevice(sd6);
        assertNotEquals(sd6, casa.getDevice("321"));
        assertEquals(5, casa.getDevices().size());

        casa.addSmartDevice(sd7);
        assertNotEquals(sd7, casa.getDevice("321"));
        assertEquals(5, casa.getDevices().size());

        casa.addSmartDevice(sd4);
        assertEquals(sd4, casa.getDevice("321"));
        assertEquals(5, casa.getDevices().size());
    }
}
