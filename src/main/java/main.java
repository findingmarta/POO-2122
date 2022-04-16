import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        main.menuinicial();
    }

    public static void menuinicial(){
        int opcao = -1;

        while(opcao < 0 || opcao > 7) {
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
        while (opcao < 0 || opcao > 2) {
            opcao = menu.menuCasa();
        }
        switch (opcao) {
            case 1:
                 main.menulistacasa();
                 break;
                 /*
            case 2:
                StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m____________________________________\u001B[0m \n\n");
                sb.append(" Insira o dispositivo e Off ou On.\n");
                System.out.println(sb.toString());
                Scanner dispositivo = new Scanner(System.in);
                Scanner scanner = new Scanner(System.in);
                break;



            case 6:

                Casa c = new Casa();
                StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m____________________________________\u001B[0m \n\n");
                sb.append(" Insira os dispositivos .\n");

                Scanner scanner = new Scanner(System.in);
                Scanner nome = new Scanner(System.in);
                Scanner nif = new Scanner(System.in);
                List<Casa> ListCasa = menu.listCasas();
                //ListCasa.add(scanner, nome, nif);

                break;
                */

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