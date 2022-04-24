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

    }
}
