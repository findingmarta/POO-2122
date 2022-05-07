import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FornecedoresTest {

    @Test
    public void testContructor() {
        Fornecedores fornecEDP = new FornecEDP();
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(0.0, fornecEDP.getVolumeFaturacao());

        fornecEDP = new FornecEDP(12.0);
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(12.0, fornecEDP.getVolumeFaturacao());

        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(23.2, fornecEDP.getVolumeFaturacao());



        Fornecedores fornecEndesa = new FornecEndesa();
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(0.0, fornecEndesa.getVolumeFaturacao());

        fornecEndesa = new FornecEndesa(2.0);
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(2.0, fornecEndesa.getVolumeFaturacao());

        FornecEndesa umFornecEndesa = new FornecEndesa(5);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(5.0, fornecEndesa.getVolumeFaturacao());



        Fornecedores fornecJomar = new FornecJomar();
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(0.0, fornecJomar.getVolumeFaturacao());

        fornecJomar = new FornecJomar(22.20);
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(22.20, fornecJomar.getVolumeFaturacao());

        FornecJomar umFornecJomar = new FornecJomar(1.0);
        fornecJomar = new FornecJomar(umFornecJomar);
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(1.0, fornecJomar.getVolumeFaturacao());
    }
/*
    @Test
    public void testGetValorBase (){
        Fornecedores fornecEDP = new FornecEDP();
        assertEquals(0.148, fornecEDP.getValorBase());
        fornecEDP = new FornecEDP(12.0);
        assertEquals(0.148, fornecEDP.getValorBase());
        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(0.148, fornecEDP.getValorBase());

        Fornecedores fornecEndesa = new FornecEndesa();
        assertEquals(0.148, fornecEndesa.getValorBase());
        fornecEndesa = new FornecEndesa(2.0);
        assertEquals(0.148, fornecEndesa.getValorBase());
        FornecEndesa umFornecEndesa = new FornecEndesa(5);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        assertEquals(0.148, fornecEndesa.getValorBase());

        Fornecedores fornecJomar = new FornecJomar();
        assertEquals(0.148, fornecJomar.getValorBase());
        fornecJomar = new FornecJomar(22.20);
        assertEquals(0.148, fornecJomar.getValorBase());
        FornecJomar umFornecJomar = new FornecJomar(1.0);
        fornecJomar = new FornecJomar(umFornecJomar);
        assertEquals(0.148, fornecJomar.getValorBase());
    }

    @Test
    public void testGetImposto (){
        Fornecedores fornecEDP = new FornecEDP();
        assertEquals(0.60, fornecEDP.getImposto());
        fornecEDP = new FornecEDP(12.0);
        assertEquals(0.60, fornecEDP.getImposto());
        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(0.60, fornecEDP.getImposto());

        Fornecedores fornecEndesa = new FornecEndesa();
        assertEquals(0.60, fornecEndesa.getImposto());
        fornecEndesa = new FornecEndesa(2.0);
        assertEquals(0.60, fornecEndesa.getImposto());
        FornecEndesa umFornecEndesa = new FornecEndesa(5);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        assertEquals(0.60, fornecEndesa.getImposto());

        Fornecedores fornecJomar = new FornecJomar();
        assertEquals(0.60, fornecJomar.getImposto());
        fornecJomar = new FornecJomar(22.20);
        assertEquals(0.60, fornecJomar.getImposto());
        FornecJomar umFornecJomar = new FornecJomar(1.0);
        fornecJomar = new FornecJomar(umFornecJomar);
        assertEquals(0.60, fornecJomar.getImposto());
    }

    @Test
    public void testGetVolumeFaturacao (){
        Fornecedores fornecEDP = new FornecEDP();
        assertEquals(0.0, fornecEDP.getVolumeFaturacao());
        fornecEDP = new FornecEDP(12.0);
        assertEquals(12.0, fornecEDP.getVolumeFaturacao());
        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(23.2, fornecEDP.getVolumeFaturacao());

        //......
    }*/

    @Test
    public void testSetVolumeFaturacao (){
        Fornecedores fornecEDP = new FornecEDP();
        fornecEDP.setVolumeFaturacao(44.1);

        assertEquals(44.1, fornecEDP.getVolumeFaturacao());

        fornecEDP = new FornecEDP(12.0);
        fornecEDP.setVolumeFaturacao(9.21);
        assertEquals(9.21, fornecEDP.getVolumeFaturacao());
        fornecEDP.setVolumeFaturacao(-14);
        assertEquals(0.0, fornecEDP.getVolumeFaturacao());

        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        fornecEDP.setVolumeFaturacao(10.0);
        assertEquals(10.0, fornecEDP.getVolumeFaturacao());



        Fornecedores fornecEndesa = new FornecEndesa();
        fornecEndesa.setVolumeFaturacao(33.1);
        assertEquals(33.1, fornecEndesa.getVolumeFaturacao());

        fornecEndesa = new FornecEndesa(12.0);
        fornecEndesa.setVolumeFaturacao(9.21);
        assertEquals(9.21, fornecEndesa.getVolumeFaturacao());
        fornecEndesa.setVolumeFaturacao(-13);
        assertEquals(0.0, fornecEndesa.getVolumeFaturacao());

        FornecEndesa umFornecEndesa = new FornecEndesa(31.12);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        fornecEndesa.setVolumeFaturacao(10.0);
        assertEquals(10.0, fornecEndesa.getVolumeFaturacao());
        for(int i=0; i<3; i++) fornecEndesa.aumentaVolumeFaturacao(10);
        assertEquals(40.0, fornecEndesa.getVolumeFaturacao());



        Fornecedores fornecJomar = new FornecJomar();
        fornecJomar.setVolumeFaturacao(33.1);
        assertEquals(33.1, fornecJomar.getVolumeFaturacao());

        fornecJomar = new FornecJomar(12.0);
        fornecJomar.setVolumeFaturacao(9.21);
        assertEquals(9.21, fornecJomar.getVolumeFaturacao());
        fornecJomar.setVolumeFaturacao(-13);
        assertEquals(0.0, fornecJomar.getVolumeFaturacao());

        FornecJomar umFornecJomar = new FornecJomar(31.12);
        fornecJomar = new FornecJomar(umFornecJomar);
        fornecJomar.setVolumeFaturacao(5.0);
        assertEquals(5.0, fornecJomar.getVolumeFaturacao());
        for(int i=0; i<3; i++) fornecJomar.aumentaVolumeFaturacao(-2);
        assertEquals(0.0, fornecJomar.getVolumeFaturacao());
    }

    @Test
    public void testPrecoDiarioPorDispositivo_EDP(){
        double scale = 1000000d;
        Fornecedores fornecEDP = new FornecEDP();
        SmartDevice sb = new SmartBulb( SmartBulb.COLD, 32.0);
        assertEquals(69.05088, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb();
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, 32.0);
        assertEquals(69.05088, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, -3);
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sb)*scale)/scale);

        SmartSpeaker ss = new SmartSpeaker(5,"RUM", "JBL");
        assertEquals(24.93504,Math.round(fornecEDP.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker();
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker(-2,"Comercial", "JBL");
        assertEquals(15.34464, Math.round(fornecEDP.PrecoDiarioPorDispositivo(ss)*scale)/scale);

        SmartCamera sc = new SmartCamera( 120.0,82.1);
        assertEquals(18908.43264, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera();
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(-2, 32.0);
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(1080, -3);
        assertEquals(5.754240, Math.round(fornecEDP.PrecoDiarioPorDispositivo(sc)*scale)/scale);
    }

    @Test
    public void testPrecoDiarioPorDispositivo_Endesa(){
        double scale = 1000000d;
        Fornecedores fornecEndesa = new FornecEndesa();
        SmartDevice sb = new SmartBulb( SmartBulb.COLD, 32.0);
        assertEquals(168.79104, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb();
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, 32.0);
        assertEquals(168.79104, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, -3);
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sb)*scale)/scale);


        SmartSpeaker ss = new SmartSpeaker(5,"RUM", "JBL");
        assertEquals(51.1488, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker();
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker(-2,"Comercial", "JBL");
        assertEquals(25.5744, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(ss)*scale)/scale);

        SmartCamera sc = new SmartCamera( 120.0,82.1);
        assertEquals(50407.1424,Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera();
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(-2, 32.0);
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(1080, -3);
        assertEquals(0.0, Math.round(fornecEndesa.PrecoDiarioPorDispositivo(sc)*scale)/scale);
    }

    @Test
    public void testPrecoDiarioPorDispositivo_Jomar(){
        double scale = 1000000d;
        Fornecedores fornecJomar = new FornecJomar();
        SmartDevice sb = new SmartBulb( SmartBulb.COLD, 32.0);
        assertEquals(17552.9376, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb();
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, 32.0);
        assertEquals(17552.9376, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sb)*scale)/scale);
        sb = new SmartBulb(-2, -3);
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sb)*scale)/scale);

        SmartSpeaker ss = new SmartSpeaker(5,"RUM", "JBL");
        assertEquals(5319.072, Math.round(fornecJomar.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker();
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(ss)*scale)/scale);
        ss = new SmartSpeaker(-2,"Comercial", "JBL");
        assertEquals(2659.536, Math.round(fornecJomar.PrecoDiarioPorDispositivo(ss)*scale)/scale);

        SmartCamera sc = new SmartCamera( 120.0,82.1);
        assertEquals(5241945.456, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera();
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(-2, 32.0);
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sc)*scale)/scale);
        sc = new SmartCamera(1080, -3);
        assertEquals(0.0, Math.round(fornecJomar.PrecoDiarioPorDispositivo(sc)*scale)/scale);

    }
}