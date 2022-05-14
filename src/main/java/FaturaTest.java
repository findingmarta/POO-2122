import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {

    //verificar valor da fatura em função do consumo e do tipo de fornecedor

    @Test
    public void testContructor() {
        Faturas fatura = new Faturas();

        assertEquals(0.0, fatura.getFatura());
        assertEquals("", fatura.getDataInicial());
        assertEquals("", fatura.getDataFinal());
        assertEquals(0.0, fatura.getConsumo());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals(30.45, fatura.getFatura());
        assertEquals("01-01-2017", fatura.getDataInicial());
        assertEquals("27-01-2017", fatura.getDataFinal());
        assertEquals(59.2, fatura.getConsumo());


        Faturas umFatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura = new Faturas(umFatura);
        assertEquals(99.9, fatura.getFatura());
        assertEquals("13-10-2018", fatura.getDataInicial());
        assertEquals("25-11-2018", fatura.getDataFinal());
        assertEquals(31.1, fatura.getConsumo());
    }

    @Test
    public void testgetFatura (){
        Faturas fatura = new Faturas();
        assertEquals (0.0, fatura.getFatura());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals (30.45, fatura.getFatura());

        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals (99.9, fatura.getFatura());
    }

   /* @Test
    public void testsetValorFatura (){
        Fatura fatura = new Fatura();
        fatura.setValorFatura(3.5);
        assertEquals(3.5, fatura.getFatura());

        fatura = new Fatura(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setValorFatura(44.0);
        assertEquals (44.0, fatura.getFatura());

        Fatura umFatura = new Fatura(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura = new Fatura(umFatura);
        fatura.setValorFatura(12.6);
        assertEquals (12.6, fatura.getFatura());
        fatura.setValorFatura(12);
        assertEquals (12, fatura.getFatura());
    }*/

    @Test
    public void testGetDataInicial (){
        Faturas fatura = new Faturas();
        assertEquals ("", fatura.getDataInicial());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals ("01-01-2017", fatura.getDataInicial());

        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals ("13-10-2018", fatura.getDataInicial());
    }

    @Test
    public void testSetDataInical (){
        Faturas fatura = new Faturas();
        fatura.setDataInicial("19-12-2010");
        assertEquals("19-12-2010", fatura.getDataInicial());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setDataInicial("05-05-2005");
        assertEquals ("05-05-2005", fatura.getDataInicial());

        Faturas umFatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura = new Faturas(umFatura);
        fatura.setDataInicial("15-10-2018");
        assertEquals ("15-10-2018", fatura.getDataInicial());
        fatura.setDataInicial("15-10-2019");
        assertEquals ("15-10-2019", fatura.getDataInicial());
    }

    @Test
    public void testGetDataFinal (){
        Faturas fatura = new Faturas();
        assertEquals ("", fatura.getDataFinal());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals ("27-01-2017", fatura.getDataFinal());

        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals ("25-11-2018", fatura.getDataFinal());
    }

   /* @Test
    public void testSetDataFinal (){
        Fatura fatura = new Fatura();
        fatura.setDataFinal("19-12-2010");
        assertEquals("19-12-2010", fatura.getDataFinal());

        fatura = new Fatura(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setDataFinal("29-01-2017");
        assertEquals ("29-01-2017", fatura.getDataFinal());

        Fatura umFatura = new Fatura(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura = new Fatura(umFatura);
        fatura.setDataFinal("01-01-2019");
        assertEquals ("01-01-2019", fatura.getDataFinal());
        fatura.setDataFinal("01-10-2019");
        assertEquals ("01-10-2019", fatura.getDataFinal());
    }*/

    @Test
    public void testgetConsumo (){
        Faturas fatura = new Faturas();
        assertEquals (0.0, fatura.getConsumo());

        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals (59.2, fatura.getConsumo());

        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals (31.1, fatura.getConsumo());
    }

   /* @Test
    public void testsetGastoTotal (){
        Fatura fatura = new Fatura();
        fatura.setGastoTotal(30.2);
        assertEquals(30.2, fatura.getConsumo());

        fatura = new Fatura(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setGastoTotal(40.0);
        assertEquals (40.0, fatura.getConsumo());

        Fatura umFatura = new Fatura(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura = new Fatura(umFatura);
        fatura.setGastoTotal(67.6);
        assertEquals (67.6, fatura.getConsumo());
        fatura.setGastoTotal(15.2);
        assertEquals (15.2, fatura.getConsumo());
    }*/
}
