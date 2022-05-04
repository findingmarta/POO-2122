import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void run() throws IOException {
            Estado estado = new Estado();
            Controller.menuinicial(estado);
        }

        public static void menuinicial (Estado estado) throws IOException {
            int opcao = -1;
            boolean exit = false;
            while (!exit) {
                while (opcao < 0 || opcao > 6) {
                    opcao = Menu.MenuInicial();
                }

                switch (opcao) {
                    case 1 -> Controller.menucasa1(estado);
                    case 2 -> Controller.menuinicial(estado);
                    case 3 -> {
                        try {
                            estado.saveEstado("src/Controller/java/Estado.obj");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Controller.menuinicial(estado);
                    }
                    case 4 -> {
                        String newFilePath = "src/Controller/java/Estado.obj";
                        String faturasFile = "src/Controller/java/Faturas.obj";
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
                        if (i == 1) {
                            estado.loadEstado();
                        } else if (i == 2) {
                            try {
                                estado.loadEstadoObj(newFilePath);
                            } catch (IOException | ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else if (i == 3) {
                            Controller.Menulistafatura(estado);

                        }
                    /*
                    try {
                        estado.loadFaturasObj(faturasFile);
                        //Faturas f = new Faturas ();
                        Controller.Menulistafatura(estado);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                Controller.menuinicial(estado);*/
                    }
                    case 5 -> Menu.emissaoFaturas(estado);
                    case 6 -> Controller.menuEstatistica(estado);
                    case 0 -> System.exit(0);
                }
            }
        }
        public static void menucasa1 (Estado estado) throws IOException {
            List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.MenuCasa();
            }
            switch (opcao) {
                case 1 -> Controller.Menulistacasa(estado);
                case 2 -> Menu.definirDispositivos(estado, l);
                case 3 -> Menu.definirDivisoes(estado, l);
                case 4 -> {
                    System.out.println("Insira o NIF: ");
                    Scanner nif = new Scanner(System.in);
                    String n = nif.next();
                    System.out.println("Insira o nome do proprietário: ");
                    Scanner proprietario = new Scanner(System.in);
                    String prop = proprietario.next();
                    Casa c1 = new Casa(prop, n);
                    l.add(c1);
                    Controller.menucasa1(estado);
                }
                case 5 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        Scanner scanner = new Scanner(System.in);
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);

                    System.out.println("Insira a divisão: ");
                    Scanner divisao = new Scanner(System.in);
                    String d = divisao.next().toLowerCase();
                    c.addRoom(d);
                    Controller.menucasa1(estado);
                }

                case 6 -> Menu.MenuDispositivos(estado, l);
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
                    Controller.menucasa1(estado);

                }
                case 8 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        Scanner scanner = new Scanner(System.in);
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);
                    double precoFinal = c.consumoTotal();
                    System.out.println("\nPreço Final : " + precoFinal);
                    System.out.println("\nInsira 0 para retornar ao MenuCasa. ");
                    Scanner s = new Scanner(System.in);
                    int s1 = s.nextInt();
                    if (s1 == 0) Controller.menucasa1(estado);


                }
                //List<Casa> ListCasa = Menu.listCasas();
                //ListCasa.add(scanner, nome, nif);
                case 0 -> Controller.menuinicial(estado);
                default -> Controller.menuinicial(estado);
            }
        }
        public static void Menulistafatura (Estado estado) throws IOException {
            List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > l.size()) {
                opcao = Menu.MenuListaCasas(l);
            }
            if (opcao == 0) Controller.menuinicial(estado);
            int a = Menu.faturaList(l.get(opcao - 1));
            if (a == 0) Controller.menuinicial(estado);
            Scanner scanner = new Scanner(System.in);
            int i = scanner.nextInt();
            Menu.FaturaInfo(i, l.get(opcao - 1).getFatura());

        }


        public static void Menulistacasa (Estado estado) throws IOException {
            List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > l.size()) {
                opcao = Menu.MenuListaCasas(l);
            }
            if (opcao == 0) Controller.menucasa1(estado);
            int a = Menu.MenuCasaInfo(opcao - 1, l);
            if (a == 0) Controller.menuinicial(estado);
        }
        public static void menuEstatistica (Estado estado) throws IOException {
            List<Casa> l = estado.getCasas();
            Scanner scanner = new Scanner(System.in);
            int opcao = -1;
            while (opcao < 0 || opcao > 5) {
                opcao = Menu.MenuEstatistica();
            }
            switch (opcao) {
                case 1 -> {
                    System.out.println(l.get(l.size() - 1));
                    int i = scanner.nextInt();
                    while (i != 0) {
                        i = scanner.nextInt();
                    }
                    Controller.menuEstatistica(estado);
                }
                case 2 -> {
                    List<Fornecedores> f = estado.getFornecedores();
                    System.out.println(f.get(0).toString());
                    int i = scanner.nextInt();
                    if (i == 0) Controller.menuEstatistica(estado);
                }
                case 3 -> {
                    List<Casa> casas = new ArrayList<>();
                    System.out.println("Insira o fornecedor: ");
                    String d = scanner.next().toLowerCase();
                    for (Casa c : l) {
                        if ((d.equals(c.Stringfornecedor(c.getFornecedor()).toLowerCase()))) {
                            casas.add(c);
                        }
                    }
                    int i = -1;
                    while (i < 0 || i > casas.size()) {
                        i = Menu.MenuListaCasas(casas);
                    }
                    if (i == 0) Controller.menuinicial(estado);
                    // int a = Menu.FaturaInfo(opcao-1, casas);
                    // if (a==0) Controller.menuEstatistica(estado);

                }
                case 4 -> {
                    Controller.Menulistacasa(estado);


                }
            }

        }
    }
