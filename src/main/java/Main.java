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
            case 2 -> {
                System.out.println("Insira o índice da casa: ");
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                System.out.println("Insira a divisão: ");
                Scanner divisao = new Scanner(System.in);
                String d = divisao.next();
                System.out.println("Insira o id: ");
                Scanner dispositivo = new Scanner(System.in);
                String id = dispositivo.next();
                System.out.println("Ligar ou desligar? ");
                Scanner modo = new Scanner(System.in);
                String m = modo.next();
                Casa c = l.get(i - 1);
                SmartDevice s = c.getDevice(d,id);
                if (m.equals("Ligar")) {
                    c.setDeviceOn(d, s);
                    System.out.println("\nLigado : " + s);
                }
                else if (m.equals("Desligar")) {
                    c.setDeviceOff(d, s);
                    System.out.println("\nDesligado : " + s);
                }
                else System.out.println("Insiram algo de jeito, BURROS! ass: Joana Branc");
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