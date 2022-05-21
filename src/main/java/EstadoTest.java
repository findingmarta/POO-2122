import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.*;

public class EstadoTest {
    private static final List<Fornecedores> nFornecedores = makeFornecedores2();
    private static final List<Casa> nCasas = makeCasas2();

    private Casa makeCasa(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes1());
        casa.setProprietario("EU");
        casa.setNIF("987654321");
        casa.setFornecedor(new FornecEDP());
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
        casa.setFornecedor(new FornecEndesa());
        return casa;
    }

    private static Map<String, SmartDevice> makeDevices2() {
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

    private static Map<String, HashSet<String>> makeDivisoes2() {
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

    private static Casa makeCasa3(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes3());
        casa.setDevices(makeDevices3());
        casa.setProprietario("Marta Isabel da Silva e Sa");
        casa.setNIF("842037533");
        casa.setFornecedor(new FornecEDP("formula2"));
        return casa;
    }

    private static Map<String, SmartDevice> makeDevices3() {
        Map <String, SmartDevice> devices = new HashMap<>();

        devices.put("6549099", new SmartBulb("6549099",true,1, 0.21));
        devices.put("8154343", new SmartBulb("8154343",true,1, 17.94));
        devices.put("1417572", new SmartBulb("1417572",true,1, 14.68));

        devices.put("4651525", new SmartSpeaker("4651525",false,39, "TSF Radio Noticias", "Marshall"));
        devices.put("9297", new SmartSpeaker("9297",false,63, "Radio Comercial", "Sennheiser"));
        devices.put("25376", new SmartCamera("25376",false,1366, 768));

        devices.put("9752190", new SmartBulb("9752190",true,0, 20.63));
        devices.put("6105340", new SmartCamera("6105340",false,3840, 2160));
        devices.put("2451006", new SmartBulb("2451006",true,0, 10.19));
        return devices;
    }

    private static Map<String, HashSet<String>> makeDivisoes3() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();

        HashSet<String> ids1 = new HashSet<>();
        ids1.add("6549099");
        ids1.add("8154343");
        ids1.add("1417572");

        HashSet<String> ids2 = new HashSet<>();
        ids2.add("4651525");
        ids2.add("9297");
        ids2.add("25376");

        HashSet<String> ids3 = new HashSet<>();
        ids3.add("9752190");
        ids3.add("6105340");
        ids3.add("2451006");

        divisoes.put("Escritorio",ids1);
        divisoes.put("Sala de Jantar",ids2);
        divisoes.put("Cozinha",ids3);
        return divisoes;
    }

    private static Casa makeCasa4(){
        Casa casa = new Casa();
        casa.setDivisoes(makeDivisoes4());
        casa.setDevices(makeDevices4());
        casa.setProprietario("Joana Catarina Veloso Branco");
        casa.setNIF("127167894");
        casa.setFornecedor(new FornecEndesa("formula3"));
        return casa;
    }

    private static Map<String, SmartDevice> makeDevices4() {
        Map <String, SmartDevice> devices = new HashMap<>();

        devices.put("555056", new SmartBulb("555056",true,1, 10.44));
        devices.put("9494700", new SmartBulb("9494700",true,1, 16.30));
        devices.put("5321070", new SmartBulb("5321070",true,1, 20.47));

        devices.put("2178600", new SmartSpeaker("2178600",false,30, "RFM", "Bang&Olufsen"));
        devices.put("4810168", new SmartSpeaker("4810168",false,30, "M80 Radio", "Marshall"));
        devices.put("1809858", new SmartBulb("1809858",true,2, 10.00));

        devices.put("3993490", new SmartCamera("3993490",false,3840, 2160));
        devices.put("2811611", new SmartSpeaker("2811611",false,70, "RTP Antena 1 98.3 FM", "Philips"));
        devices.put("322356", new SmartSpeaker("322356",false,50, "M80 Radio", "Sennheiser"));
        devices.put("2382854", new SmartBulb("2382854",true,1, 14.67));
        return devices;
    }

    private static Map<String, HashSet<String>> makeDivisoes4() {
        Map <String, HashSet<String>> divisoes = new HashMap<>();

        HashSet<String> ids1 = new HashSet<>();
        ids1.add("555056");
        ids1.add("9494700");
        ids1.add("5321070");

        HashSet<String> ids2 = new HashSet<>();
        ids2.add("2178600");
        ids2.add("4810168");
        ids2.add("1809858");

        HashSet<String> ids3 = new HashSet<>();
        ids3.add("3993490");
        ids3.add("2811611");
        ids3.add("322356");
        ids3.add("2382854");

        divisoes.put("Quarto",ids1);
        divisoes.put("Cozinha",ids2);
        divisoes.put("Sala de Estar",ids3);
        return divisoes;
    }

    private List<Casa> makeCasas(){
        List<Casa> casas = new ArrayList<>();
        casas.add(makeCasa());
        casas.add(makeCasa2());
        return casas;
    }

    private static List<Casa> makeCasas2(){
        List<Casa> casas = new ArrayList<>();
        casas.add(makeCasa3());
        casas.add(makeCasa4());
        return casas;
    }

    private List<Fornecedores> makeFornecedores(){
        List<Fornecedores> fornecedores = new ArrayList<>();
        fornecedores.add(new FornecEDP());
        fornecedores.add(new FornecEndesa());
        fornecedores.add(new FornecJomar());
        return fornecedores;
    }

    private static List<Fornecedores> makeFornecedores2(){
        List<Fornecedores> fornecedores = new ArrayList<>();
        fornecedores.add(new FornecEDP("formula2"));
        fornecedores.add(new FornecJomar("formula1"));
        fornecedores.add(new FornecEndesa("formula3"));
        return fornecedores;
    }

    @Test
    public void testContructor() {
        Estado estado = new Estado();
        assertTrue(estado.getCasas().isEmpty());
        assertTrue(estado.getFornecedores().isEmpty());
        assertEquals("01/01/2018", estado.getData());

        estado = new Estado(nCasas,nFornecedores);
        assertEquals(nCasas, estado.getCasas());
        assertEquals(nFornecedores, estado.getFornecedores());
        assertEquals("01/01/2018", estado.getData());

        Estado umEstado = new Estado(makeCasas(), makeFornecedores());
        estado = new Estado(umEstado);
        assertEquals(umEstado, estado);
        assertEquals(makeCasas(), estado.getCasas());
        assertEquals(makeFornecedores(), estado.getFornecedores());
        assertEquals("01/01/2018", estado.getData());
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
    public void testGetFornecedores() {
        Estado estado = new Estado();
        assertTrue(estado.getFornecedores().isEmpty());

        estado = new Estado(nCasas,nFornecedores);
        List<Fornecedores> fornecedores1 = estado.getFornecedores();
        assertEquals(nFornecedores, fornecedores1);
    }

    @Test
    public void testGetData() {
        Estado estado = new Estado();
        assertEquals("01/01/2018",estado.getData());

        estado.setData("02/02/2019");
        assertEquals("02/02/2019",estado.getData());
    }

    @Test
    public void testSetData() {
        Estado estado = new Estado();
        estado.setData("02/02/2019");
        assertEquals("02/02/2019",estado.getData());
        estado.setData("02-02-2019");
        assertEquals("02/02/2019",estado.getData());
        estado.setData("35/02/2019");
        assertEquals("02/02/2019",estado.getData());
        estado.setData("14/02/2019");
        assertEquals("14/02/2019",estado.getData());
    }

    @Test
    public void testAddCasa() {
        Estado estado = new Estado();
        Casa casa  = makeCasa();
        estado.addCasa(casa);
        assertEquals(1, estado.getCasas().size());
        assertEquals(casa,estado.getCasas().get(0));
    }

    @Test
    public void testUpdateCasas() {
        Estado estado = new Estado(nCasas, nFornecedores);
        Fornecedores forn = new FornecEDP("formula3");
        estado.updateCasas(forn);

        assertTrue(estado.getCasas().get(0).getFornecedor() instanceof FornecEDP);
        assertEquals("formula3",estado.getCasas().get(0).getFornecedor().getFormula());

        assertTrue(estado.getCasas().get(1).getFornecedor() instanceof FornecEndesa);
        assertEquals("formula3",estado.getCasas().get(1).getFornecedor().getFormula());
    }

    @Test
    public void testUpdateCasa() {
        Estado estado = new Estado(nCasas, nFornecedores);
        SmartBulb sb = new SmartBulb("1",true,2,32.1);

        Casa casa = nCasas.get(0);
        casa.setDivisonOn("Cozinha");
        casa.setFornecedor(new FornecJomar("formula1"));
        casa.setProprietario("Eu");
        casa.setNIF("1241535");
        casa.setCustoInstalacao(123);
        casa.addSmartDevice(sb);

        estado.updateCasa(casa,0);
        assertTrue(estado.getCasas().get(0).hasDevice(sb.getID()));
        assertEquals(casa, estado.getCasas().get(0));
    }

    @Test
    public void testUpdateFornecedor() {
        Estado estado = new Estado(nCasas, nFornecedores);
        Fornecedores forn = new FornecEDP("formula3");
        estado.updateFornecedor(forn);

        for(Fornecedores f : estado.getFornecedores()){
            if(f instanceof FornecEDP) assertEquals(forn, f);
        }
    }

    @Test
    public void testLerFicheiro(){
        List<String> lines = Estado.lerFicheiro("src/main/java/logs.txt");
        assertEquals("Fornecedor:EDP,formula2", lines.get(0));
        assertEquals("Fornecedor:Jomar,formula1", lines.get(1));
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
    public void testLoad_SaveEstadoObj() {
        Estado estadoObj = new Estado();
        Estado estadoTxt = new Estado();

        estadoObj.loadEstado("src/main/java/logs.txt");
        try {
            estadoObj.saveEstado("src/main/java/Estado.obj");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            estadoTxt.loadEstadoObj("src/main/java/Estado.obj");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(estadoObj.getCasas(), estadoTxt.getCasas());
        assertEquals(estadoObj.getFornecedores(), estadoTxt.getFornecedores());
        assertEquals(estadoObj.getData(), estadoTxt.getData());
    }

    @Test
    public void testIsDateValid(){
        assertTrue(Estado.isDateValid( "01/01/2017"));
        assertFalse(Estado.isDateValid("33/01/2019"));
        assertFalse(Estado.isDateValid("2019/01/01"));
        assertFalse(Estado.isDateValid("01-01-2017"));
    }
}
