import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerEstado {
    public static void run(Estado estado) {
        Menu.clearWindow ();
        boolean exit = false;
        while (!exit) {
            List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > 3) {
                opcao = Menu.MenuEstado ();
            }
            String newFilePath = "src/Controller/java/Estado.obj";
            String faturasFile = "src/Controller/java/Faturas.obj";
            Scanner scanner = new Scanner(System.in);
            switch (opcao){
                case 1-> estado.loadEstado();
                case 2-> {
                    try {
                        estado.loadEstadoObj(newFilePath);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                case 3->{
                    try {
                        estado.saveEstado("src/Controller/java/Estado.obj");
                    } catch (IOException e) {
                        e.printStackTrace();
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
