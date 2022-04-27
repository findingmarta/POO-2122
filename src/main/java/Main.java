import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import static javax.swing.UIManager.get;

public class Main {
    public static void main(String[] args) {
        List<Casa> l = new ArrayList<>();
        List<Fornecedores> fornecedores = new ArrayList<>();
        //Menu.listCasas(l);
        Main.menuinicial(l, fornecedores);
    }

    public static void menuinicial(List<Casa> l, List<Fornecedores> f){
        int opcao = -1;

        while(opcao < 0 || opcao > 4) {
            opcao = Menu.MenuInicial();
        }

        switch (opcao) {
            case 1 -> Main.menucasa1(l,f);
            case 2 -> Main.menuinicial(l,f);
            case 3 -> {
                try {
                    Estado.saveEstado(l,f);
                } catch (IOException e){
                    e.printStackTrace();
                }
                Main.menuinicial(l,f);
            }
            case 4 -> Menu.MenuEstado(l,f);
            case 0 -> System.exit(0);
        }
    }

    public static void menucasa1(List<Casa> l, List<Fornecedores> f) {
        int opcao = -1;
        while (opcao < 0 || opcao > 6) {
            opcao = Menu.MenuCasa();
        }
        switch (opcao) {
            case 1 -> Main.Menulistacasa(l,f);
            case 2 -> Menu.definirDispositivos(l,f);
            case 3 -> Menu.definirDivisoes(l,f);
            case 4 -> {
                System.out.println("Insira o NIF: ");
                Scanner nif = new Scanner(System.in);
                String n = nif.next();
                System.out.println("Insira o nome do proprietário: ");
                Scanner proprietario = new Scanner(System.in);
                String prop = proprietario.next();
                Casa c1 = new Casa(prop, n);
                l.add(c1);
                Main.menucasa1(l,f);
            }
            case 5 -> {
                int i=-1;
                while(i < 0 || i> l.size()) {
                    System.out.println("Insira o índice da casa: ");
                    Scanner scanner = new Scanner(System.in);
                    i = scanner.nextInt();
                }
                Casa c = l.get(i - 1);
                System.out.println("Insira a divisão: ");
                Scanner divisao = new Scanner(System.in);
                String d = divisao.next();
                c.addRoom(d);
                Main.menucasa1(l,f);
            }

            case 6 -> Menu.MenuDispositivos(l,f);

            //List<Casa> ListCasa = Menu.listCasas();
            //ListCasa.add(scanner, nome, nif);
            case 0 -> Main.menuinicial(l,f);
            default -> Main.menuinicial(l,f);
        }
    }



    public static void Menulistacasa (List<Casa> l, List<Fornecedores> f) {
        int opcao = -1;
        while (opcao < 0 || opcao > l.size()) {
            opcao = Menu.MenuListaCasas(l);
        }
        if (opcao ==0) Main.menucasa1(l,f);
        int a = Menu.MenuCasaInfo(opcao-1, l);
        if (a==0) Main.Menulistacasa(l,f);
    }
}