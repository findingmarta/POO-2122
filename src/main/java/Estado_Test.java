import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

public class Estado_Test {
    private Casa makeCasa(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes1());
        casa.setProprietario("EU");
        casa.setNIF("987654321");
        return casa;
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
    /*
    private Fornecedores makeFornecedor(){
        Fornecedores fornecedor = new Fornecedores();
        fornecedor.setImposto(19.9);
        fornecedor.setValor_base(1.11);
        return fornecedor;
    }*/

    private Map<String, HashSet<String>> makeDivisoes1() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();
        HashSet<String> devices = new HashSet<>();
        devices.add("33333");
        devices.add("12345");
        devices.add("11111");
        divisoes.put("Sala",devices);
        return divisoes;
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

    private boolean mapEqualsDivices (Map<String, SmartDevice> first, Map<String, SmartDevice> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    private boolean mapEqualsDivisoes (Map<String, HashSet<String>> first, Map<String, HashSet<String>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    @Test
    public void testContructor() {
        Estado estado = new Estado();
        assertNotNull(estado);

        List<Casa> casas = new ArrayList<>();
        casas.add(makeCasa());
        List<Fornecedores> fornecedores = new ArrayList<>();
        //fornecedores.add(makeFornecedor());
        estado = new Estado(casas, fornecedores);
        assertNotNull(estado);
    }

    @Test
    public void testSetCasas() {
        Estado estado = new Estado();
        List<Casa> casas = new ArrayList<>();
        casas.add(makeCasa());
        estado.setCasas(casas);
        assertEquals(casas, estado.getCasas());
    }

    @Test
    public void testGetCasas() {
        List<Fornecedores> fornecedores = new ArrayList<>();
        List<Casa> casas = new ArrayList<>();
        //fornecedores.add(makeFornecedor());
        casas.add(makeCasa());
        Estado estado = new Estado(casas,fornecedores);
        List<Casa> casas1 = estado.getCasas();
        assertEquals(casas, casas1);
    }

    @Test
    public void testSetFornecedores() {
        Estado estado = new Estado();
        List<Fornecedores> fornecedores = new ArrayList<>();
        //fornecedores.add(makeFornecedor());
        estado.setFornecedores(fornecedores);
        assertEquals(fornecedores, estado.getFornecedores());
    }

    @Test
    public void testGetFornecedores() {
        List<Fornecedores> fornecedores = new ArrayList<>();
        List<Casa> casas = new ArrayList<>();
        //fornecedores.add(makeFornecedor());
        casas.add(makeCasa());
        Estado estado = new Estado(casas,fornecedores);
        List<Fornecedores> fornecedores1 = estado.getFornecedores();
        assertEquals(fornecedores, fornecedores1);
    }

    @Test
    public void testLerFicheiro(){
        List<String> lines = Estado.lerFicheiro();
        assertEquals("Fornecedor:EDP", lines.get(0));
        assertEquals("Fornecedor:Jomar", lines.get(1));
        assertEquals("Casa:Joao Pedro Malheiro da Costa,707666276,EDP", lines.get(3));
        assertEquals("Divisao:Sala de Jantar", lines.get(4));
    }

    @Test
    public void testLoadEstado() {
        Estado estado = new Estado();
        estado.loadEstado();
        List<Casa> c = estado.getCasas();
        List<Fornecedores> f = estado.getFornecedores();
        Casa casa = makeCasa2();
        assertEquals(casa.getProprietario(),c.get(0).getProprietario());
        assertEquals(casa.getNIF(),c.get(0).getNIF());
        assertEquals(casa.getDevices(),c.get(0).getDevices());
        assertEquals(casa.getDivisoes(),c.get(0).getDivisoes());
        assertEquals(casa, c.get(0));
    }

    @Test
    public void testLoadEstadoObj(){
        Estado estado = new Estado();
        try {
            estado = estado.loadEstadoObj("src/main/java/Estado.obj");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals("Joao Pedro Malheiro da Costa",estado.getCasas().get(0).getProprietario());
        assertEquals("707666276",estado.getCasas().get(0).getNIF());
        assertTrue(mapEqualsDivices(estado.getCasas().get(0).getDevices(),makeDevices2()));
        assertTrue(mapEqualsDivisoes(estado.getCasas().get(0).getDivisoes(),makeDivisoes2()));
    }

    @Test
    public void testSaveEstado(){
        Estado estado = new Estado();
        estado.loadEstado();
        Casa casa = makeCasa2();
        try {
            estado.saveEstado("src/main/java/Estado.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(casa.getDevices(),estado.getCasas().get(0).getDevices());
        assertEquals(casa.getDivisoes(),estado.getCasas().get(0).getDivisoes());
    }
    //FALTA PARA OS FORNECEDORES
}
