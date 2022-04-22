import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static javax.swing.UIManager.get;

public class Main {



    public static void main(String[] args) {
        List<Casa> l = new ArrayList<>();
        Menu.listCasas(l);
        Main.menuinicial(l);
    }

    public static void menuinicial(List<Casa> l){
        int opcao = -1;

        while(opcao < 0 || opcao > 1) {
            opcao = Menu.MenuInicial();
        }

        switch (opcao) {
            case 1 -> Main.menucasa1(l);
            case 0 -> System.exit(0);
        }
    }

    public static void menucasa1(List<Casa> l) {

        int opcao = -1;
        while (opcao < 0 || opcao > 6) {
            opcao = Menu.MenuCasa();
        }
        switch (opcao) {
            case 1 -> Main.Menulistacasa(l);
            case 2 -> Menu.MenuLigar(l);
            case 6 -> {
                System.out.println("Insira o NIF: ");
                Scanner nif = new Scanner(System.in);
                String n = nif.next();
                System.out.println("Insira o nome do proprietário: ");
                Scanner proprietario = new Scanner(System.in);
                String prop = proprietario.next();
                Casa c1 = new Casa(prop, n);
                l.add(c1);
                Main.menucasa1(l);
            }

            //List<Casa> ListCasa = Menu.listCasas();
            //ListCasa.add(scanner, nome, nif);

            case 0 -> Main.menuinicial(l);
            default -> Main.menuinicial(l);
        }
    }
        public static void Menulistacasa (List<Casa> l) {
            int opcao = -1;
            while (opcao < 0 || opcao > l.size()) {
                opcao = Menu.MenuListaCasas(l);
            }
                if (opcao ==0) Main.menucasa1(l);
                int a = Menu.MenuCasaInfo(opcao-1, l);
                if (a==0) Main.Menulistacasa(l);
        }
    }