import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static javax.swing.UIManager.get;

public class Main {



    public static void main(String[] args) {
        List<Casa> l = new ArrayList<>();
        menu.listCasas(l);
        Main.menuinicial(l);
    }

    public static void menuinicial(List<Casa> l){
        int opcao = -1;

        while(opcao < 0 || opcao > 1) {
            opcao = menu.menuInicial();
        }

        switch (opcao) {
            case 1 -> Main.menucasa1(l);
            case 0 -> System.exit(0);
        }
    }

    public static void menucasa1(List<Casa> l) {

        int opcao = -1;
        while (opcao < 0 || opcao > 6) {
            opcao = menu.menuCasa();
        }
        switch (opcao) {
            case 1 -> Main.menulistacasa(l);
            case 3 -> {
                System.out.println("Insira o índice da casa: ");
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                System.out.println("Insira a divisão: ");
                Scanner divisao = new Scanner(System.in);
                String d = divisao.nextLine();
                System.out.println("Insira o dispositivo: ");
                Scanner dispositivo = new Scanner(System.in);
                int di = dispositivo.nextInt();
                Casa c = l.get(i - 1);
                SmartDevice s = c.getDivisoes().get(d).get(di - 1);
                System.out.println(s);
                c.setDeviceOn(d, s);
                Boolean b = s.getOn();
                c.setDeviceOff(d, s);
                Boolean b1 = s.getOn();
                System.out.println(b);
                System.out.println(b1);
            }
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

            //List<Casa> ListCasa = menu.listCasas();
            //ListCasa.add(scanner, nome, nif);

            case 0 -> Main.menuinicial(l);
            default -> Main.menuinicial(l);
        }
    }
        public static void menulistacasa (List<Casa> l) {
            int opcao = -1;
            while (opcao < 0 || opcao > l.size()) {
                opcao = menu.menuListaCasas(l);
            }
                if (opcao ==0) Main.menucasa1(l);
                int a = menu.menuCasaInfo(opcao-1, l);
                if (a==0) Main.menulistacasa(l);
        }
    }