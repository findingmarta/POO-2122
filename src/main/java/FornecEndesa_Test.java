import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FornecEndesa_Test {

    @Test
    public void testPrecoDiarioPorDispositivo(){
        Fornecedores fornec1 = new FornecEndesa();
        SmartDevice sb = new SmartBulb("b1", true, SmartBulb.COLD, 32.0);
        assertEquals(179.34048, fornec1.PrecoDiarioPorDispositivo(sb));
        SmartSpeaker ss = new SmartSpeaker("5431",true,5,"RUM", "JBL");
        assertEquals(40.91904, fornec1.PrecoDiarioPorDispositivo(ss));
        SmartCamera sc = new SmartCamera("cam1",true, 120.0,82.1);
        assertEquals(50407.1424, fornec1.PrecoDiarioPorDispositivo(sc));

    }

}
