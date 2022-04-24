import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class Estado_Test {
    @Test
    public void testContructor() {
        Fornecedores fornec1 = new Fornecedores();
        assertNotNull(fornec1);
        fornec1 = new Fornecedores(250.3, 23.0);
        assertNotNull(fornec1);
    }

    @Test
    public void testLerFicheiro(){
        Estado estado = new Estado();
        List<String> lines = estado.lerFicheiro("src\\main\\java\\estado.txt");
        lines.forEach(System.out::println);
    }
    @Test
    public void testLoadEstado() {
        Fornecedores fornec1 = new Fornecedores(200.7, 25.0);
        assertEquals(200.7, fornec1.getValor_base());
        fornec1 = new Fornecedores(390.4, 22.5);
        assertEquals(390.4,fornec1.getValor_base());
    }
}
