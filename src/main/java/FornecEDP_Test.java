import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FornecEDP_Test {

    @Test
    public void testPrecoDiarioPorDispositivo(){
        Fornecedores fornec1 = new FornecEDP();
        SmartDevice sb = new SmartBulb("b1", true, SmartBulb.COLD, 32.0);
        assertEquals(69.05088, fornec1.PrecoDiarioPorDispositivo(sb));
        SmartSpeaker ss = new SmartSpeaker("5431",true,5,"RUM", "JBL");
        assertEquals(26.85312, fornec1.PrecoDiarioPorDispositivo(ss));
        SmartCamera sc = new SmartCamera("cam1",true, 120.0,82.1);
        assertEquals(18908.4326, fornec1.PrecoDiarioPorDispositivo(sc));

    }

}
