import java.io.IOException;
import java.util.List;
import java.util.Scanner;

//import static javax.swing.UIManager.get;

public class Main {
    public static void main(String[] args) throws IOException {
        Estado estado = new Estado();
        Main.menuinicial(estado);
    }

    public static void menuinicial(Estado estado) throws IOException {
        int opcao = -1;

        while(opcao < 0 || opcao > 5) {
            opcao = Menu.MenuInicial();
        }

        switch (opcao) {
            case 1 -> Main.menucasa1(estado);
            case 2 -> Main.menuinicial(estado);
            case 3 -> {
                try {
                    estado.saveEstado("src/main/java/Estado.obj");
                } catch (IOException e){
                    e.printStackTrace();
                }
                Main.menuinicial(estado);
            }
            case 4 -> {
                String newFilePath = "src/main/java/Estado.obj";
                String faturasFile = "src/main/java/Faturas.obj";
                Menu.clearWindow();
                String sb = """
                        \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s
                        \u001B[1m 1) \u001B[0m Ficheiro Original.
                        \u001B[1m 2) \u001B[0m Novo Ficheiro.
                        \u001B[1m 3) \u001B[0m Faturas.
                        \u001B[1m 0) \u001B[0m Menu Inicial.
                        \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s
                        Selecione a opção pretendida:\s""";
                System.out.println(sb);

                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                if (i==1) estado.loadEstado();
                else if (i==2) {
                    try {
                        estado.loadEstadoObj(newFilePath);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if (i==3){
                    try {
                        estado.loadFaturasObj(faturasFile);
                        Main.Menulistafatura(estado);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                Main.menuinicial(estado);
            }
            case 5 -> Menu.emissaoFaturas(estado);
            case 0 -> System.exit(0);
        }
    }

    public static void menucasa1(Estado estado) throws IOException {
        List<Casa> l = estado.getCasas();
        int opcao = -1;
        while (opcao < 0 || opcao > 8) {
            opcao = Menu.MenuCasa();
        }
        switch (opcao) {
            case 1 -> Main.Menulistacasa(estado);
            case 2 -> Menu.definirDispositivos(estado,l);
            case 3 -> Menu.definirDivisoes(estado,l);
            case 4 -> {
                System.out.println("Insira o NIF: ");
                Scanner nif = new Scanner(System.in);
                String n = nif.next();
                System.out.println("Insira o nome do proprietário: ");
                Scanner proprietario = new Scanner(System.in);
                String prop = proprietario.next();
                Casa c1 = new Casa(prop, n);
                l.add(c1);
                Main.menucasa1(estado);
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
                String d = divisao.next().toLowerCase();
                c.addRoom(d);
                Main.menucasa1(estado);
            }

            case 6 -> Menu.MenuDispositivos(estado,l);
            case 7 -> {
                int i = -1;
                while (i < 0 || i > l.size()) {
                    System.out.println("Insira o índice da casa: ");
                    Scanner scanner = new Scanner(System.in);
                    i = scanner.nextInt();
                }
                Casa c = l.get(i - 1);

                System.out.println("Insira o fornecedor: ");
                Scanner divisao = new Scanner(System.in);
                String d = divisao.next().toLowerCase();
                Fornecedores forn = switch (d) {
                    case "edp" -> new FornecEDP();
                    case "endesa" -> new FornecEndesa();
                    case "jomar" -> new FornecJomar();
                    default -> null;
                };
                c.setFornecedor(forn);
                Main.menucasa1(estado);

            }
            case 8 -> {
                int i=-1;
                while(i < 0 || i> l.size()) {
                    System.out.println("Insira o índice da casa: ");
                    Scanner scanner = new Scanner(System.in);
                    i = scanner.nextInt();
                }
                Casa c = l.get(i - 1);
                double precoFinal = c.consumoTotal() * Menu.CalculateDays();
                System.out.println("\nPreço Final : " + precoFinal);
                System.out.println("\nInsira 0 para retornar ao MenuCasa. ");
                Scanner s = new Scanner(System.in);
                int s1 = s.nextInt();
                if (s1 == 0) Main.menucasa1(estado);


            }
            //List<Casa> ListCasa = Menu.listCasas();
            //ListCasa.add(scanner, nome, nif);
            case 0 -> Main.menuinicial(estado);
            default -> Main.menuinicial(estado);
        }
    }
    public static void Menulistafatura (Estado estado) throws IOException {
        List<Casa> l = estado.getCasas();
        int opcao = -1;
        while (opcao < 0 || opcao > l.size()) {
            opcao = Menu.MenuListaCasas(l);
        }
        if (opcao ==0) Main.menucasa1(estado);
        int a = Menu.FaturaInfo(opcao-1, l);
        if (a==0) Main.Menulistacasa(estado);
    }


    public static void Menulistacasa (Estado estado) throws IOException {
        List<Casa> l = estado.getCasas();
        int opcao = -1;
        while (opcao < 0 || opcao > l.size()) {
            opcao = Menu.MenuListaCasas(l);
        }
        if (opcao ==0) Main.menucasa1(estado);
        int a = Menu.MenuCasaInfo(opcao-1, l);
        if (a==0) Main.Menulistacasa(estado);
    }
}