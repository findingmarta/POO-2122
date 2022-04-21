import java.util.List;
import java.util.Scanner;

import static javax.swing.UIManager.get;

public class main {

    public static void main(String[] args) {
        main.menuinicial();
    }

    public static void menuinicial(){
        int opcao = -1;

        while(opcao < 0 || opcao > 1) {
            opcao = menu.menuInicial();
        }

        switch(opcao) {
            case 1:
                main.menucasa1();
                break;

            case 0:
                System.exit(0);
                break;
        }
    }

    public static void menucasa1() {
        int opcao = -1;
        while (opcao < 0 || opcao > 3) {
            opcao = menu.menuCasa();
        }
        switch (opcao) {
            case 1:
                 main.menulistacasa();
                 break;

            case 3:
                System.out.println("Insira o índice da casa: ");
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                System.out.println("Insira a divisão: ");
                Scanner divisao = new Scanner(System.in);
                String d = divisao.nextLine();
                System.out.println("Insira o dispositivo: ");
                Scanner dispositivo = new Scanner(System.in);
                int di  = dispositivo.nextInt();
                Casa c = menu.listCasas().get(i-1);
                SmartDevice s = c.getDivisoes().get(d).get(di-1);
                System.out.println(s);
                c.setDeviceOn(d,s);
                Boolean b = s.getOn();
                c.setDeviceOff(d,s);
                Boolean b1 = s.getOn();
                System.out.println(b);
                System.out.println(b1);
                break;



            case 6:
                System.out.println("Insira o NIF: ");
                Scanner nif = new Scanner(System.in);
                String n = nif.next();
                System.out.println("Insira o nome do proprietário: ");
                Scanner proprietario = new Scanner(System.in);
                String prop = proprietario.next();
                Casa c1 = new Casa (n,prop);
                //Listacasa.add(c1);

                //List<Casa> ListCasa = menu.listCasas();
                //ListCasa.add(scanner, nome, nif);

                break;

            case 0:
                main.menuinicial();
                break;
            default:
                main.menuinicial();
                break;
        }
    }
        public static void menulistacasa () {
            int opcao = -1;
            List<Casa> ListCasa = menu.listCasas();
            while (opcao < 0 || opcao > ListCasa.size()) {
                opcao = menu.menuListaCasas();
            }
                if (opcao ==0) main.menucasa1();
                int a = menu.menuCasaInfo(opcao-1);
                if (a==0) main.menulistacasa();
        }
    }