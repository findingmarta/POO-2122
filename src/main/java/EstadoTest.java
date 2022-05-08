import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

public class EstadoTest {
    private static List<Fornecedores> nFornecedores = new ArrayList<>();
    private static List<Casa> nCasas = new ArrayList<>();
    static Estado estado = new Estado();

    @BeforeAll
    static void load(){
        estado.loadEstado("src/main/java/testes.txt");
        nCasas = estado.getCasas();
        nFornecedores = estado.getFornecedores();
    }

    private Casa makeCasa(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes1());
        casa.setProprietario("EU");
        casa.setNIF("987654321");
        return casa;
    }

    private Map<String, HashSet<String>> makeDivisoes1() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();
        HashSet<String> devices = new HashSet<>();
        devices.add("33333");
        devices.add("12345");
        devices.add("11111");
        divisoes.put("Sala",devices);
        return divisoes;
    }

    private Casa makeCasa2(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes2());
        casa.setDevices(makeDevices2());
        casa.setProprietario("Joao Pedro Malheiro da Costa");
        casa.setNIF("707666276");
        casa.setFornecedor(null);
        return casa;
    }

    private Map<String, SmartDevice> makeDevices2() {
        Map <String, SmartDevice> devices = new HashMap<>();

        devices.put("122", new SmartBulb("122",false,1, 60.0));
        devices.put("1161", new SmartBulb("1161",false,1, 14.0));

        devices.put("1163", new SmartSpeaker("1163",false,55, "RFM Oceano Pacifico", "Marshall"));
        devices.put("1514", new SmartBulb("1514",false,0, 40.0));
        devices.put("335", new SmartCamera("335",false,3840.0, 2160.0));

        devices.put("571", new SmartSpeaker("571",false,35, "RTP Antena 1 98.3 FM", "Marshall"));
        devices.put("47", new SmartBulb("47",false,2, 60.0));
        devices.put("1215", new SmartSpeaker("1215",false,42, "RFM", "Sennheiser"));
        devices.put("845", new SmartSpeaker("845",false,30, "M80 Radio", "LG"));
        devices.put("1530", new SmartSpeaker("1530",false,65, "RTP Antena 1 98.3 FM", "Sony"));
        devices.put("1093", new SmartSpeaker("1093",false,52, "Radio Renascenca", "LG"));
        devices.put("1626", new SmartSpeaker("1626",false,45, "MEGA HITS", "BOSE"));
        return devices;
    }

    private Map<String, HashSet<String>> makeDivisoes2() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();

        HashSet<String> ids1 = new HashSet<>();
        ids1.add("122");
        ids1.add("1161");

        HashSet<String> ids2 = new HashSet<>();
        ids2.add("1163");
        ids2.add("1514");
        ids2.add("335");

        HashSet<String> ids3 = new HashSet<>();
        ids3.add("571");
        ids3.add("47");
        ids3.add("1215");
        ids3.add("845");
        ids3.add("1530");
        ids3.add("1093");
        ids3.add("1626");

        divisoes.put("Sala de Jantar",ids1);
        divisoes.put("Garagem",ids2);
        divisoes.put("Jardim",ids3);
        return divisoes;
    }

    private List<Fornecedores> makeFornecedores(){
        List<Fornecedores> fornecedores = new ArrayList<>();
        fornecedores.add(new FornecEDP());
        fornecedores.add(new FornecEndesa());
        fornecedores.add(new FornecJomar());
        return fornecedores;
    }

    private List<Casa> makeCasas(){
        List<Casa> casas = new ArrayList<>();
        casas.add(makeCasa());
        casas.add(makeCasa2());
        return casas;
    }

    /*private boolean mapEqualsDivices (Map<String, SmartDevice> first, Map<String, SmartDevice> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    private boolean mapEqualsDivisoes (Map<String, HashSet<String>> first, Map<String, HashSet<String>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }*/

    @Test
    public void testContructor() {
        Estado estado = new Estado();
        assertTrue(estado.getCasas().isEmpty());
        assertTrue(estado.getFornecedores().isEmpty());

        estado = new Estado(nCasas,nFornecedores);
        assertEquals(nCasas, estado.getCasas());
        assertEquals(nFornecedores, estado.getFornecedores());

        Estado umEstado = new Estado(makeCasas(), makeFornecedores());
        estado = new Estado(umEstado);
        assertEquals(umEstado, estado);
    }

    @Test
    public void testGetCasas() {
        Estado estado = new Estado();
        assertTrue(estado.getCasas().isEmpty());

        estado = new Estado(nCasas,nFornecedores);
        List<Casa> casas1 = estado.getCasas();
        assertEquals(nCasas, casas1);
    }

    @Test
    public void testSetCasas() {
        Estado estado = new Estado();
        estado.setCasas(nCasas);
        assertEquals(nCasas, estado.getCasas());
        estado.setCasas(null);
        assertTrue(estado.getCasas().isEmpty());

        estado = new Estado(nCasas,makeFornecedores());
        estado.setCasas(makeCasas());
        assertEquals(makeCasas(), estado.getCasas());
    }

    @Test
    public void testGetFornecedores() {
        Estado estado = new Estado();
        assertTrue(estado.getFornecedores().isEmpty());

        estado = new Estado(nCasas,nFornecedores);
        List<Fornecedores> fornecedores1 = estado.getFornecedores();
        assertEquals(nFornecedores, fornecedores1);
    }

    @Test
    public void testSetFornecedores() {
        Estado estado = new Estado();
        estado.setFornecedores(nFornecedores);
        assertEquals(nFornecedores, estado.getFornecedores());
        estado.setFornecedores(null);
        assertTrue(estado.getFornecedores().isEmpty());

        estado = new Estado(makeCasas(),nFornecedores);
        estado.setFornecedores(makeFornecedores());
        assertEquals(makeFornecedores(), estado.getFornecedores());
    }

    @Test
    public void testLerFicheiro(){
        List<String> lines = Estado.lerFicheiro("src/main/java/logs.txt");
        assertEquals("Fornecedor:EDP", lines.get(0));
        assertEquals("Fornecedor:Jomar", lines.get(1));
        assertEquals("Casa:Joao Pedro Malheiro da Costa,707666276,EDP", lines.get(3));
        assertEquals("Divisao:Sala de Jantar", lines.get(4));
    }

    @Test
    public void testLoadEstado() {
        Estado estado = new Estado();
        estado.loadEstado("src/main/java/testes.txt");
        List<Casa> c = estado.getCasas();
        List<Fornecedores> f = estado.getFornecedores();
        assertEquals(nCasas, c);
        assertEquals(nFornecedores, f);
    }

    @Test
    public void testSaveEstado(){
        Estado estado = new Estado();
        estado.loadEstado("src/main/java/logs.txt");
        try {
            estado.saveEstado("src/main/java/Estado.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadEstadoObj() {
        Estado estadoObj = new Estado();
        Estado estadoTxt = new Estado();

        estadoObj.loadEstado("src/main/java/logs.txt");
        try {
            estadoObj.saveEstado("src/main/java/Estado.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }

        estadoTxt.loadEstado("src/main/java/logs.txt");

        try {
            estadoObj = estadoObj.loadEstadoObj("src/main/java/Estado.obj");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(estadoTxt.getCasas(), estadoObj.getCasas());
        assertEquals(estadoTxt.getFornecedores(), estadoObj.getFornecedores());
    }
}
