import java.util.List;
import java.util.Scanner;

public class ControllerCasa {
    public static void run(Estado estado) {
        Menu.clearWindow ();
        boolean exit = false;
        while (!exit) {
            List<Casa> l = estado.getCasas();
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.MenuCasa();
            }
            Scanner scanner = new Scanner(System.in);
            switch (opcao) {
                case 1 -> {
                    int escolha = -1;
                    while (escolha < 0 || escolha > l.size()) {
                        escolha = Menu.MenuListaCasas(l);
                    }
                    if (escolha == 0) break;
                    int a = Menu.MenuCasaInfo(escolha - 1, l);
                }
                case 2 ->{
                    int i = -1;
                    while (i < 0 || i > l.size ()) {
                        System.out.println ("Insira o índice da casa: ");
                        i = scanner.nextInt ();
                    }

                    Casa c = l.get (i - 1);
                    System.out.println ("Insira o id: ");
                    String id1 = scanner.next ();
                    while (!(c.hasDevice (id1))) {
                        System.out.println ("O id inserido nao existe, tente novamente: ");
                        id1 = scanner.next ();
                    }

                    System.out.println ("Ligar ou Desligar? ");
                    String m = scanner.next ().toLowerCase ();

                    while (!m.equals ("ligar") && !m.equals ("desligar")) {
                        System.out.println ("Ligar ou Desligar? ");
                        m = scanner.next ();
                    }
                    if (m.equals ("ligar")) c.setDeviceOn (id1);
                    else c.setDeviceOff (id1);
                    estado.updateCasa(c, i-1);
                }
                case 3 -> {
                    int i=-1;
                    while(i < 0 || i> l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);

                    if (!c.getDivisoes().isEmpty()) {
                        System.out.println("Insira a divisão: "); //verificar se a divsao existe
                        String d = scanner.next();

                        while (!(c.hasRoom(d))) {
                            System.out.println("A divisão inserida nao existe, tente novamente: ");
                            d = scanner.next();
                        }
                        System.out.println("Ligar ou Desligar? ");
                        String m = scanner.next().toLowerCase();
                        while (!m.equals("ligar") && !m.equals("desligar")) {
                            System.out.println("Ligar ou Desligar? ");
                            m = scanner.next();
                        }
                        if (m.equals("ligar")) c.setDivisonOn(d);
                        else c.setDivisonOff(d);
                        estado.updateCasa(c, i-1);
                    }
                    else System.out.println("Erro!");

                }
                case 4 -> {
                    System.out.println("Insira o NIF: ");
                    Scanner nif = new Scanner(System.in);
                    String n = nif.next();
                    System.out.println("Insira o nome do proprietário: ");
                    Scanner proprietario = new Scanner(System.in);
                    String prop = proprietario.next();
                    Casa c = new Casa(prop, n);
                    estado.addCasa(c);
                }
                case 5 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);

                    System.out.println("Insira a divisão: ");
                    String d = scanner.next().toLowerCase();
                    c.addRoom(d);
                    estado.updateCasa(c, i-1);
                }

                case 6 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);
                    if (!c.getDivisoes().isEmpty()) {

                        System.out.println("Insira a divisão: ");
                        String d = scanner.next().toLowerCase();
                        while (!(c.hasRoom(d))) {
                            System.out.println("A divisão inserida nao existe, tente novamente: ");
                            d = scanner.next().toLowerCase();
                        }

                        System.out.println("Off ou On: ");
                        String est = scanner.next().toLowerCase();
                        while (!est.equals("on") && !est.equals("off")){
                            System.out.println("Off ou On: ");
                            est = scanner.next().toLowerCase();
                        }
                        boolean turn;
                        turn = est.equals("on");

                        SmartDevice s = Menu.EscolhaDispositivos(turn);
                        assert s != null;
                        c.addSmartDevice(s);
                        c.addToRoom(d,s.getID());
                        estado.updateCasa(c, i-1);
                    }
                }

                case 7 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);

                    System.out.println("Insira o fornecedor: ");
                    String d = scanner.next().toLowerCase();
                    Fornecedores forn = switch (d) {
                        case "edp" -> new FornecEDP();
                        case "endesa" -> new FornecEndesa();
                        case "jomar" -> new FornecJomar();
                        default -> null;
                    };
                    c.setFornecedor(forn);
                    estado.updateCasa(c, i-1);
                }
                /*
                case 8 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                    }
                    Casa c = l.get(i - 1);
                    double precoFinal = c.consumoTotal();
                    System.out.println("\nPreço Final : " + precoFinal);
                    System.out.println("\nInsira 0 para retornar ao MenuCasa. ");
                    int s1 = scanner.nextInt();
                    if (s1 == 0) Controller.menucasa1(estado);


                }*/
                //List<Casa> ListCasa = Menu.listCasas();
                //ListCasa.add(scanner, nome, nif);
                case 0 -> {
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }

}
