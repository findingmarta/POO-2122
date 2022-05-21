import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class CasaTest {
    private final List<Faturas> faturas = makeFaturas();
    private final List<Faturas> faturas2 = makeFaturas2();
    private final Map<String, SmartDevice> devices = makeDevices();
    private final Map<String, SmartDevice> devices2 = makeDevices2();
    private final Map<String, HashSet<String>> divisoes = makeDivisoes();
    private final Map<String, HashSet<String>> divisoes2 = makeDivisoes2();

    static List<Faturas> makeFaturas() {
        List<Faturas> faturas = new ArrayList<>();
        faturas.add (new Faturas(30.45, "01-01-2017","27-01-2017", 60.0));
        faturas.add(new Faturas());
        faturas.add(new Faturas(46516, "01-01-2018","25-12-2019", 126));
        return faturas;
    }

    static List<Faturas> makeFaturas2() {
        List<Faturas> faturas = new ArrayList<>();
        faturas.add (new Faturas(70.70, "01-01-2017","27-01-2017", 25.3));
        faturas.add(new Faturas());
        faturas.add(new Faturas(37.56, "01-01-2020","25-12-2021", 200.2));
        return faturas;
    }

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
        assertEquals(0.0,casa.getCustoInstalacao());
        assertTrue(casa.getDevices().isEmpty());
        assertTrue(casa.getDivisoes().isEmpty());
        assertTrue(casa.getFatura().isEmpty());
        assertNull(casa.getFornecedor());

        casa = new Casa(devices, divisoes, "Jose Antonio", "241019876", fornecedor);
        assertEquals("Jose Antonio",casa.getProprietario());
        assertEquals("241019876",casa.getNIF());
        assertEquals(devices, casa.getDevices());
        assertEquals(divisoes,casa.getDivisoes());
        assertEquals(fornecedor, casa.getFornecedor());
        assertTrue(casa.getFatura().isEmpty());
        assertEquals(0.0,casa.getCustoInstalacao());

        fornecedor = new FornecEDP();
        casa = new Casa("Marco", "11111", fornecedor);
        assertEquals("Marco",casa.getProprietario());
        assertEquals("11111",casa.getNIF());
        assertEquals(fornecedor, casa.getFornecedor());
        assertTrue(casa.getFatura().isEmpty());
        assertTrue(casa.getDevices().isEmpty());
        assertTrue(casa.getDivisoes().isEmpty());
        assertEquals(0.0,casa.getCustoInstalacao());

        umaCasa.setCustoInstalacao(10.2);
        umaCasa.setFatura(faturas);
        casa = new Casa(umaCasa);
        assertEquals(umaCasa.getFornecedor(), casa.getFornecedor());
        assertEquals(10.2,casa.getCustoInstalacao());
        assertEquals("Manel",casa.getProprietario());
        assertEquals("24131313",casa.getNIF());
        assertEquals(devices, casa.getDevices());
        assertEquals(divisoes,casa.getDivisoes());
        assertEquals(faturas,casa.getFatura());
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
        casa.setProprietario(null);
        assertEquals("", casa.getProprietario());
        casa.setProprietario("Carlos");
        assertEquals("Carlos", casa.getProprietario());
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
        assertEquals("44561373", casa.getNIF());
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

        forn = new FornecEndesa("formula2");
        casa = new Casa("Raquel Oliveira", "64485889", forn);
        assertEquals(forn, casa.getFornecedor());
        assertEquals(0.0, casa.getFornecedor().getVolumeFaturacao());

        forn = new FornecJomar(12345.3);
        casa = new Casa("Raquel Oliveira", "64485889", forn);
        assertTrue(casa.getFornecedor() instanceof FornecJomar);
        assertEquals(12345.3, casa.getFornecedor().getVolumeFaturacao());
        assertEquals("", casa.getFornecedor().getFormula());
    }

    @Test
    public void testSetFornecedor() {
        Casa casa = new Casa();
        Fornecedores forn = new FornecEDP();
        assertNull(casa.getFornecedor());
        casa.setFornecedor(forn);
        assertEquals(forn, casa.getFornecedor());

        casa.setFornecedor(null);
        assertEquals(forn, casa.getFornecedor());

        Fornecedores newForn = new FornecJomar();
        casa.setFornecedor(newForn);
        assertEquals(0.60, casa.getFornecedor().getImposto());
        assertEquals(0.148, casa.getFornecedor().getValorBase());
        assertEquals(0.0, casa.getFornecedor().getVolumeFaturacao());
        assertEquals("", casa.getFornecedor().getFormula());
        assertEquals(newForn,casa.getFornecedor());
    }

    @Test
    public void testGetDivisoes() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        assertTrue(casa.getDivisoes().isEmpty());

        casa = new Casa(devices,divisoes, "Maria", "871564897",fornecedor);
        assertEquals(divisoes,casa.getDivisoes());
    }

    @Test
    public void testSetDivisoes() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setDivisoes(divisoes);
        assertEquals(divisoes, casa.getDivisoes());

        casa = new Casa(devices, divisoes2, "Ana", "22222222", fornecedor);
        assertEquals(divisoes2,casa.getDivisoes());
        casa.setDivisoes(divisoes);
        assertEquals(divisoes,casa.getDivisoes());
        casa.setDivisoes(null);
        assertEquals(divisoes,casa.getDivisoes());
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
        casa.setDevices(null);
        assertEquals(devices,casa.getDevices());
    }

    @Test
    public void testGetFatura() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa umaCasa = new Casa("Carla", "124543", fornecedor);
        umaCasa.setFatura(faturas);

        Casa casa = new Casa();
        assertTrue(casa.getFatura().isEmpty());

        casa = new Casa(devices2,divisoes2, "Maria", "871564897",fornecedor);
        assertTrue(casa.getFatura().isEmpty());

        casa = new Casa("Maria", "871564897",fornecedor);
        assertTrue(casa.getFatura().isEmpty());

        casa = new Casa(umaCasa);
        assertEquals(faturas,casa.getFatura());
    }

    @Test
    public void testSetFatura() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa = new Casa();
        casa.setFatura(faturas);
        assertEquals(faturas, casa.getFatura());

        casa = new Casa("Ana", "22222222",fornecedor);
        casa.setFatura(faturas2);
        assertEquals(faturas2,casa.getFatura());
        casa.setFatura(faturas);
        assertEquals(faturas,casa.getFatura());
        casa.setFatura(null);
        assertEquals(faturas,casa.getFatura());
    }

    @Test
    public void testGetCustoInstalacao() {
        Fornecedores fornecedor = new FornecEndesa();
        Casa umaCasa = new Casa("Carla", "124543", fornecedor);
        umaCasa.setCustoInstalacao(32.3);

        Casa casa = new Casa();
        assertEquals(0.0, casa.getCustoInstalacao());

        casa = new Casa(devices, divisoes, "Maria", "21645889", fornecedor);
        assertEquals(0.0, casa.getCustoInstalacao());

        casa = new Casa("Raquel Oliveira", "64485889", new FornecEDP());
        assertEquals(0.0, casa.getCustoInstalacao());

        casa = new Casa(umaCasa);
        assertEquals(32.3, casa.getCustoInstalacao());
    }

    @Test
    public void testSetCustoInstalacao() {
        Casa casa = new Casa();
        casa.setCustoInstalacao(-23.1);
        assertEquals(0.0, casa.getCustoInstalacao());
        casa.setCustoInstalacao(2.35);
        assertEquals(2.35, casa.getCustoInstalacao());

        casa = new Casa("Raquel Oliveira", "64485889", new FornecEDP());
        casa.setCustoInstalacao(3.5);
        assertEquals(3.5, casa.getCustoInstalacao());
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

        casa.addToRoom(divisao,"12342");
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

        SmartDevice sd1 = new SmartBulb("",true,SmartBulb.NEUTRAL, 2.0);
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

    @Test
    public void testConsumoTotal2 () {
        Fornecedores fornecedor = new FornecEDP();
        Casa casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        assertEquals(21.0,casa.consumoTotal2());
        casa.setDeviceOn("123456789");
        casa.setDeviceOn("12342");
        assertEquals(172.49,casa.consumoTotal2());
    }

    @Test
    public void testConsumoTotal () {
        Fornecedores fornecedor = new FornecEDP("formula2");
        Casa casa = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        assertEquals(120.83904,casa.consumoTotal(fornecedor.getFormula()));
        casa.setDeviceOn("123456789");
        casa.setDeviceOn("12342");
        assertEquals(992.5488576,casa.consumoTotal(fornecedor.getFormula()));
        fornecedor.setFormula("formula3");
        assertEquals(882.2656512000001,casa.consumoTotal(fornecedor.getFormula()));
    }

    @Test
    public void testComparatorConsumo () {
        Fornecedores fornecedor = new FornecEDP();
        Casa casa1 = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        casa1.setFatura(faturas);

        Casa casa2 = new Casa(devices2, divisoes2, "Marco", "65468321",fornecedor);
        casa2.setFatura(faturas2);

        List<Casa> casas = new ArrayList<>();
        casas.add(casa1.clone());
        casas.add(casa2.clone());

        casas.sort(new Casa.ComparatorConsumo());
        assertEquals(casa2, casas.get(0));
        assertEquals(casa1, casas.get(1));
    }

    @Test
    public void testComparatorGasto () {
        Fornecedores fornecedor = new FornecEndesa();
        Casa casa1 = new Casa(devices, divisoes, "Maria", "11111111",fornecedor);
        casa1.setFatura(faturas);

        Casa casa2 = new Casa(devices2, divisoes2, "Marco", "65468321",fornecedor);
        casa2.setFatura(faturas2);

        List<Casa> casas = new ArrayList<>();
        casas.add(casa1.clone());
        casas.add(casa2.clone());

        casas.sort(new Casa.ComparatorGasto());
        assertEquals(casa1, casas.get(0));
        assertEquals(casa2, casas.get(1));
    }

    @Test
    public void testOnlyDigits(){
        assertFalse(Casa.onlyDigits("1234a234", 8));
        assertFalse(Casa.onlyDigits("1234 234", 8));
        assertTrue(Casa.onlyDigits("12341234", 8));
    }
}
