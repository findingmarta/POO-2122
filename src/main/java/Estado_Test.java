import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class Estado_Test {
    private Map<String, List<SmartDevice>> makeDivisoes1() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartSpeaker("3333", false,19, "RFM", "JBL"));
        devices.add(new SmartBulb("12345", false, 2, 23.34));
        divisoes.put("Sala",devices);
        return divisoes;
    }

    private Map<String, List<SmartDevice>> makeDivisoes2() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        List<SmartDevice> devices1 = new ArrayList<>();
        devices1.add(new SmartCamera("666", true, 19.00, 20.34));
        devices1.add(new SmartSpeaker("9876", true,19, "MegaHits", "Marshall"));

        List<SmartDevice> devices2 = new ArrayList<>();
        devices2.add(new SmartBulb("00000", true, 2, 57.57));
        devices2.add(new SmartSpeaker("64352", true,19, "RUM", "JBL"));

        divisoes.put("Sala",devices2);
        divisoes.put("Cozinha",devices1);
        return divisoes;
    }

    private boolean mapEquals (Map<String, List<SmartDevice>> first, Map<String, List<SmartDevice>> second) {
        if (first.size() != second.size()) return false;
        return first.entrySet().stream().allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }

    @Test
    public void testContructor() {
        Fornecedores fornec1 = new Fornecedores();
        assertNotNull(fornec1);
        fornec1 = new Fornecedores(250.3, 23.0);
        assertNotNull(fornec1);
    }

    @Test
    public void testLerFicheiro(){
        List<String> lines = Estado.lerFicheiro("src\\main\\java\\Estado.txt");
        assertEquals("Casa-Sala/SmartSpeaker,3333,false,19,RFM,JBL<SmartBulb,12345,false,2,23.34;Ana Maria;241019999;", lines.get(0));
        assertEquals("Casa-Sala/SmartBulb,00000,true,2,57.57<SmartSpeaker,64352,true,19,RUM,JBL>Cozinha/SmartCamera,666,true,19.0,20.34<SmartSpeaker,9876,true,19,MegaHits,Marshall;Julia;0909909909;", lines.get(1));
    }

    @Test
    public void testsaveEstado(){
        List<Fornecedores> fornecedores = new ArrayList<>();
        List<Casa> casas = new ArrayList<>();
        Estado.loadEstado(casas,fornecedores,"src\\main\\java\\Estado.txt");
        try {
            Estado.saveEstado(casas,fornecedores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoadEstado() {
        Map<String, List<SmartDevice>> divisoes1 = makeDivisoes1();
        Map<String, List<SmartDevice>> divisoes2 = makeDivisoes2();
        List<Fornecedores> fornecedores = new ArrayList<>();
        List<Casa> casas = new ArrayList<>();
        String filePath = "src\\main\\java\\Estado.txt";
        Estado.loadEstado(casas, fornecedores, filePath);

        Casa casa1 = casas.get(0);
        assertEquals("Ana Maria",casa1.getProprietario());
        assertEquals("241019999",casa1.getNIF());
        assertTrue(mapEquals(casa1.getDivisoes(),divisoes1));

        Casa casa2 = casas.get(1);
        assertEquals("Julia",casa2.getProprietario());
        assertEquals("0909909909",casa2.getNIF());
        assertTrue(mapEquals(casa2.getDivisoes(),divisoes2));
    }
    //FALTA PARA OS FORNECEDORES
}
