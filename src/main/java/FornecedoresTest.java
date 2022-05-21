import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FornecedoresTest {

    @Test
    public void testContructor() {
        Fornecedores fornecEDP = new FornecEDP();
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(0.0, fornecEDP.getVolumeFaturacao());
        assertEquals("", fornecEDP.getFormula());
        assertEquals("01/01/2018", fornecEDP.getDataInicial());

        fornecEDP = new FornecEDP(12.0);
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(12.0, fornecEDP.getVolumeFaturacao());
        assertEquals("", fornecEDP.getFormula());
        assertEquals("01/01/2018", fornecEDP.getDataInicial());

        fornecEDP = new FornecEDP("formula2");
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(0.0, fornecEDP.getVolumeFaturacao());
        assertEquals("formula2", fornecEDP.getFormula());
        assertEquals("01/01/2018", fornecEDP.getDataInicial());

        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(0.148, fornecEDP.getValorBase());
        assertEquals(0.60, fornecEDP.getImposto());
        assertEquals(23.2, fornecEDP.getVolumeFaturacao());
        assertEquals("", fornecEDP.getFormula());
        assertEquals("01/01/2018", fornecEDP.getDataInicial());



        Fornecedores fornecEndesa = new FornecEndesa();
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(0.0, fornecEndesa.getVolumeFaturacao());
        assertEquals("", fornecEndesa.getFormula());
        assertEquals("01/01/2018", fornecEndesa.getDataInicial());

        fornecEndesa = new FornecEndesa(2.0);
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(2.0, fornecEndesa.getVolumeFaturacao());
        assertEquals("", fornecEndesa.getFormula());
        assertEquals("01/01/2018", fornecEndesa.getDataInicial());

        fornecEndesa = new FornecEndesa("formula1");
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(0.0, fornecEndesa.getVolumeFaturacao());
        assertEquals("formula1", fornecEndesa.getFormula());
        assertEquals("01/01/2018", fornecEndesa.getDataInicial());

        FornecEndesa umFornecEndesa = new FornecEndesa(5);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        assertEquals(0.148, fornecEndesa.getValorBase());
        assertEquals(0.60, fornecEndesa.getImposto());
        assertEquals(5.0, fornecEndesa.getVolumeFaturacao());
        assertEquals("", fornecEndesa.getFormula());
        assertEquals("01/01/2018", fornecEndesa.getDataInicial());



        Fornecedores fornecJomar = new FornecJomar();
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(0.0, fornecJomar.getVolumeFaturacao());
        assertEquals("", fornecJomar.getFormula());
        assertEquals("01/01/2018", fornecJomar.getDataInicial());

        fornecJomar = new FornecJomar(22.20);
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(22.20, fornecJomar.getVolumeFaturacao());
        assertEquals("", fornecJomar.getFormula());
        assertEquals("01/01/2018", fornecJomar.getDataInicial());

        fornecJomar = new FornecJomar("formula1");
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(0.0, fornecJomar.getVolumeFaturacao());
        assertEquals("formula1", fornecJomar.getFormula());
        assertEquals("01/01/2018", fornecJomar.getDataInicial());


        FornecJomar umFornecJomar = new FornecJomar(1.0);
        fornecJomar = new FornecJomar(umFornecJomar);
        assertEquals(0.148, fornecJomar.getValorBase());
        assertEquals(0.60, fornecJomar.getImposto());
        assertEquals(1.0, fornecJomar.getVolumeFaturacao());
        assertEquals("", fornecJomar.getFormula());
        assertEquals("01/01/2018", fornecJomar.getDataInicial());
    }

    @Test
    public void testFormula (){
        Fornecedores forn = new FornecEndesa();
        forn.setFormula("formula1");
        assertEquals("formula1", forn.getFormula());
        forn.setFormula("fdsa");
        assertEquals("formula1", forn.getFormula());
        forn.setFormula(null);
        assertEquals("formula1", forn.getFormula());
        forn.setFormula("formula2");
        assertEquals("formula2", forn.getFormula());
        forn.setFormula("formula3");
        assertEquals("formula3", forn.getFormula());
    }


    @Test
    public void testVolumeFaturacao (){
        Fornecedores fornecEDP = new FornecEDP();
        fornecEDP.setVolumeFaturacao(44.1);
        assertEquals(44.1, fornecEDP.getVolumeFaturacao());

        fornecEDP = new FornecEDP(12.0);
        fornecEDP.setVolumeFaturacao(9.21);
        assertEquals(9.21, fornecEDP.getVolumeFaturacao());
        fornecEDP.setVolumeFaturacao(-14);
        assertEquals(9.21, fornecEDP.getVolumeFaturacao());

        FornecEDP umFornecEDP = new FornecEDP(23.2);
        fornecEDP = new FornecEDP(umFornecEDP);
        assertEquals(23.2, fornecEDP.getVolumeFaturacao());
        fornecEDP.setVolumeFaturacao(10.0);
        assertEquals(10.0, fornecEDP.getVolumeFaturacao());



        Fornecedores fornecEndesa = new FornecEndesa();
        fornecEndesa.setVolumeFaturacao(33.1);
        assertEquals(33.1, fornecEndesa.getVolumeFaturacao());

        fornecEndesa = new FornecEndesa(12.0);
        fornecEndesa.setVolumeFaturacao(9.21);
        assertEquals(9.21, fornecEndesa.getVolumeFaturacao());
        fornecEndesa.setVolumeFaturacao(-13);
        assertEquals(9.21, fornecEndesa.getVolumeFaturacao());

        FornecEndesa umFornecEndesa = new FornecEndesa(31.12);
        fornecEndesa = new FornecEndesa(umFornecEndesa);
        assertEquals(31.12, fornecEndesa.getVolumeFaturacao());
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
        assertEquals(9.21, fornecJomar.getVolumeFaturacao());

        FornecJomar umFornecJomar = new FornecJomar(31.12);
        fornecJomar = new FornecJomar(umFornecJomar);
        fornecJomar.setVolumeFaturacao(5.0);
        assertEquals(5.0, fornecJomar.getVolumeFaturacao());
        for(int i=0; i<3; i++) fornecJomar.aumentaVolumeFaturacao(-2);
        assertEquals(5.0, fornecJomar.getVolumeFaturacao());
    }

    @Test
    public void testDataInicial (){
        Fornecedores forn = new FornecEndesa();
        forn.setDataInicial("01/02/2019");
        assertEquals("01/02/2019", forn.getDataInicial());
        forn.setDataInicial("02/02/2019");
        assertEquals("02/02/2019", forn.getDataInicial());
        forn.setDataInicial(null);
        assertEquals("02/02/2019", forn.getDataInicial());
    }

    @Test
    public void testFornecedorComparator (){
        Fornecedores forn1 = new FornecEndesa(200.0);
        Fornecedores forn2 = new FornecJomar(123.5);
        Fornecedores forn3 = new FornecEndesa("formula1");
        Fornecedores forn4 = new FornecEDP();

        List<Fornecedores> fornecedores = new ArrayList<>();
        fornecedores.add(forn1.clone());
        fornecedores.add(forn2.clone());
        fornecedores.add(forn3.clone());
        fornecedores.add(forn4.clone());


        fornecedores.sort(new Fornecedores.fornecedoresComparator());
        assertEquals(forn3, fornecedores.get(0));
        assertEquals(forn4, fornecedores.get(1));
        assertEquals(forn2, fornecedores.get(2));
        assertEquals(forn1, fornecedores.get(3));
    }
}