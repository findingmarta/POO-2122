import java.io.IOException;
import java.util.*;

public class ControllerEstado {
    public static void run(Estado estado) {
        Menu.clearWindow ();
        boolean exit = false;
        while (!exit) {
            //List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > 3) {
                opcao = Menu.MenuEstado ();
            }
            String originalFilePath = "src/main/java/logs.txt";
            String newFilePath = "src/main/java/Estado.obj";
            //String faturasFile = "src/main/java/Faturas.obj";
            Scanner scanner = new Scanner(System.in);
            switch (opcao){
                case 1-> estado.loadEstado(originalFilePath);
                case 2-> {
                    try {
                        estado.loadEstadoObj(newFilePath);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        Menu.erros(10);
                    }
                }
                case 3->{
                    try {
                        estado.saveEstado("src/main/java/Estado.obj");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Menu.erros(9);
                    }
                }
                case 0-> {
                    exit = true;
                    Menu.clearWindow ();
                }
            }
        }
    }
}
