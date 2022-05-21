import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {
    @Test
    public void testContructor() {
        Faturas fatura = new Faturas();
        assertEquals(0.0, fatura.getFatura());
        assertEquals("", fatura.getDataInicial());
        assertEquals("", fatura.getDataFinal());
        assertEquals(0.0, fatura.getConsumo());

        fatura = new Faturas(30.45, "01/01/2017","27/01/2017", 59.2);
        assertEquals(30.45, fatura.getFatura());
        assertEquals("01/01/2017", fatura.getDataInicial());
        assertEquals("27/01/2017", fatura.getDataFinal());
        assertEquals(59.2, fatura.getConsumo());

        Faturas umaFatura = new Faturas(99.9, "13/10/2018","25/11/2018", 31.1);
        fatura = new Faturas(umaFatura);
        assertEquals(99.9, fatura.getFatura());
        assertEquals("13/10/2018", fatura.getDataInicial());
        assertEquals("25/11/2018", fatura.getDataFinal());
        assertEquals(31.1, fatura.getConsumo());
    }

    @Test
    public void testgetFatura (){
        Faturas fatura = new Faturas();
        assertEquals (0.0, fatura.getFatura());

        fatura = new Faturas(30.45, "01/01/2017","27/01/2017", 59.2);
        assertEquals (30.45, fatura.getFatura());

        fatura = new Faturas(99.9, "13/10/2018","25/11/2018", 31.1);
        assertEquals (99.9, fatura.getFatura());
    }

    @Test
    public void testGetDataInicial (){
        Faturas fatura = new Faturas();
        assertEquals ("", fatura.getDataInicial());

        fatura = new Faturas(30.45, "01/01/2017","27/01/2017", 59.2);
        assertEquals ("01/01/2017", fatura.getDataInicial());

        fatura = new Faturas(99.9, "13/10/2018","25/11/2018", 31.1);
        assertEquals ("13/10/2018", fatura.getDataInicial());
    }

    @Test
    public void testGetDataFinal (){
        Faturas fatura = new Faturas();
        assertEquals ("", fatura.getDataFinal());

        fatura = new Faturas(30.45, "01/01/2017","27/01/2017", 59.2);
        assertEquals ("27/01/2017", fatura.getDataFinal());

        fatura = new Faturas(99.9, "13/10/2018","25/11/2018", 31.1);
        assertEquals ("25/11/2018", fatura.getDataFinal());
    }

    @Test
    public void testgetConsumo (){
        Faturas fatura = new Faturas();
        assertEquals (0.0, fatura.getConsumo());

        fatura = new Faturas(30.45, "01/01/2017","27/01/2017", 59.2);
        assertEquals (59.2, fatura.getConsumo());

        fatura = new Faturas(99.9, "13/10/2018","25/11/2018", 31.1);
        assertEquals (31.1, fatura.getConsumo());
    }
}
