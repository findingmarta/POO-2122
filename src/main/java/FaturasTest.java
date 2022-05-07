import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaturasTest {

    //verificar valor da fatura em função do consumo e do tipo de fornecedor

    @Test
    public void testContructor() {
        Faturas fatura = new Faturas();
        assertNotNull(fatura);
        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertNotNull(fatura);
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertNotNull(fatura);
    }

    @Test
    public void testGetFatura (){
        Faturas fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals (30.45, fatura.getFatura());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals (99.9, fatura.getFatura());
    }

    @Test
    public void testSetFatura (){
        Faturas fatura = new Faturas();
        fatura.setFatura(3.5);
        assertEquals(3.5, fatura.getFatura());
        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setFatura(44.0);
        assertEquals (44.0, fatura.getFatura());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura.setFatura(12.6);
        assertEquals (12.6, fatura.getFatura());
    }

    @Test
    public void testGetDataInicial (){
        Faturas fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
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
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura.setDataInicial("15-10-2018");
        assertEquals ("15-10-2018", fatura.getDataInicial());
    }

    @Test
    public void testGetDataFinal (){
        Faturas fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals ("27-01-2017", fatura.getDataFinal());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals ("25-11-2018", fatura.getDataFinal());
    }

    @Test
    public void testSetDataFinal (){
        Faturas fatura = new Faturas();
        fatura.setDataFinal("19-12-2010");
        assertEquals("19-12-2010", fatura.getDataFinal());
        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setDataFinal("29-01-2017");
        assertEquals ("29-01-2017", fatura.getDataFinal());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura.setDataFinal("01-01-2019");
        assertEquals ("01-01-2019", fatura.getDataFinal());
    }

    @Test
    public void testGetConsumo (){
        Faturas fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        assertEquals (59.2, fatura.getConsumo());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        assertEquals (31.1, fatura.getConsumo());
    }

    @Test
    public void testSetConsumo (){
        Faturas fatura = new Faturas();
        fatura.setConsumo(30.2);
        assertEquals(30.2, fatura.getConsumo());
        fatura = new Faturas(30.45, "01-01-2017","27-01-2017", 59.2);
        fatura.setConsumo(40.0);
        assertEquals (40.0, fatura.getConsumo());
        fatura = new Faturas(99.9, "13-10-2018","25-11-2018", 31.1);
        fatura.setConsumo(67.6);
        assertEquals (67.6, fatura.getConsumo());
    }
}
