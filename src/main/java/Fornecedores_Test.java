/**
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Fornecedores_Test {
        @Test
        public void testContructor() {
            Fornecedores fornec1;
            assertNotNull(fornec1);
        }

    //testes da abstrata

    @Test
    public void testPrecoDiarioPorDispositivo_SB(){
        Fornecedores fornec1 = new Fornecedores(105.2, 23.2);
        SmartBulb smartBul1 = new SmartBulb("b1", true, SmartBulb.COLD, 32.0);
        assertEquals(105.2*33*21.78*24, fornec1.PrecoDiarioPorDispositivo_SB(smartBul1));
        fornec1 = new Fornecedores(311.0, 31.9);
        smartBul1 = new SmartBulb("b1", true, SmartBulb.WARM, 54.2);
        assertEquals(311.0*57.2*29.61*24, fornec1.PrecoDiarioPorDispositivo_SB(smartBul1));
    }
    @Test
    public void testPrecoDiarioPorDispositivo_SS(){
        Fornecedores fornec1 = new Fornecedores(200.9, 27.7);
        SmartSpeaker smartSpe1 = new SmartSpeaker("5431",true,5,"RUM", "JBL");
        assertEquals(Math.round(200.9*10*25.83*24), Math.round(fornec1.PrecoDiarioPorDispositivo_SS(smartSpe1)));
        fornec1 = new Fornecedores(450.2, 30.9);
        smartSpe1 = new SmartSpeaker("920",true,8,"HITS", "NOKIA");
        assertEquals(Math.round(450.2*15*28.71*24), Math.round(fornec1.PrecoDiarioPorDispositivo_SS(smartSpe1)));
    }
    @Test
    public void testPrecoDiarioPorDispositivo_SC(){
        Fornecedores fornec1 = new Fornecedores(300.0, 29.1);
        SmartCamera smartCam1 = new SmartCamera("cam1",true, 134.0,150.1);
        assertEquals(Math.round(300*20116.4*27.09*24), Math.round(fornec1.PrecoDiarioPorDispositivo_SC(smartCam1)));
        fornec1 = new Fornecedores(510.2, 27.4);
        smartCam1 = new SmartCamera("cam1",true, 142.2,40.0);
        assertEquals(Math.round(510.2*5691*25.56*24), Math.round(fornec1.PrecoDiarioPorDispositivo_SC(smartCam1)));
    }



}
 **/