import java.util.*;

public class ControllerCasa {
    public static void run(Estado estado) throws InterruptedException {
        List<Casa> l = estado.getCasas();
        Menu.clearWindow ();
        boolean exit = false;
        while (!exit) {
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.MenuCasa();
            }
            Scanner scanner = new Scanner(System.in);
            switch (opcao) {
                case 1 -> { //se meter letras como opcao dá erro, talvez resulte se tirar o nextInt e meter só next
                    List<Casa> lista = estado.getCasas();
                    int escolha = -1;
                    while (escolha < 0 || escolha > lista.size()) {
                        escolha = Menu.MenuListaCasas(lista);
                    }
                    if(escolha==0) break;
                    int escolha2 = Menu.MenuCasaInfo(escolha - 1,  lista);
                    while (escolha2 != 0) escolha2 = Menu.MenuCasaInfo(escolha - 1,  lista);
                }
                case 2 ->{
                    int i = -1;
                    while (i < 0 || i > l.size ()) {
                        System.out.println ("Insira o índice da casa: ");
                        i = scanner.nextInt ();
                        if (i < 0 || i> l.size()) Menu.erros(12);
                    }
                    Casa c = l.get (i - 1);

                    System.out.println ("Insira o id: ");
                    String id = scanner.next ();
                    while (!(c.hasDevice (id))) {
                        Menu.erros(6);
                        System.out.println ("O id inserido não existe, tente novamente: ");
                        id = scanner.next ();
                    }

                    System.out.println ("Ligar ou Desligar? ");
                    String m = scanner.next ().toLowerCase ();
                    while (!m.equals ("ligar") && !m.equals ("desligar")) {
                        Menu.erros(2);
                        System.out.println ("Escolha inválida, tente novamente: ");
                        m = scanner.next ();
                    }

                    if (m.equals ("ligar")) c.setDeviceOn (id);
                    else c.setDeviceOff (id);
                    estado.updateCasa(c, i-1);
                }
                case 3 -> {
                    int i=-1;
                    while(i < 0 || i> l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                        if (i < 0 || i> l.size()) Menu.erros(12);

                    }
                    Casa c = l.get(i - 1);

                    if (!c.getDivisoes().isEmpty()) {
                        System.out.println("Insira a divisão: ");
                        String d = scanner.next();

                        while (!(c.hasRoom(d))) {
                            Menu.erros(17);
                            System.out.println("Insira a divisão: ");
                            d = scanner.next();
                        }

                        System.out.println("Ligar ou Desligar? ");
                        String m = scanner.next().toLowerCase();
                        while (!m.equals("ligar") && !m.equals("desligar")) {
                            Menu.erros(2);
                            System.out.println ("Ligar ou Desligar? ");
                            m = scanner.next ();
                        }
                        if (m.equals("ligar")) c.setDivisonOn(d);
                        else c.setDivisonOff(d);
                        estado.updateCasa(c, i-1);
                    }
                    else {
                        Menu.erros(20);
                        System.out.println("Erro! A casa não tem divisões.");
                        Thread.sleep(2000);
                    }
                }
                case 4 -> {
                    System.out.println("Insira o NIF: ");
                    Scanner scan = new Scanner(System.in);
                    String nif = scan.next();

                    System.out.println("Insira o nome do proprietário: ");
                    String proprietario = scan.next();

                    System.out.println("Insira o nome do fornecedor: ");
                    Fornecedores fornecedor = null;
                    String forn = scan.next();
                    switch (forn) {
                        case "EDP" -> fornecedor = new FornecEDP();
                        case "Endesa" -> fornecedor = new FornecEndesa();
                        case "Jomar" -> fornecedor = new FornecJomar();
                        default -> {
                            System.out.println("Fornecedor inválido! Casa não será criada.");
                            Thread.sleep(2000);
                        }
                    }
                    if(fornecedor!=null){
                        Casa c = new Casa(proprietario, nif, fornecedor);
                        estado.addCasa(c);
                    }
                }
                case 5 -> {  //não está a verificar o lowercase da divisao
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                        if (i < 0 || i> l.size()) Menu.erros(12);
                    }
                    Casa c = l.get(i - 1);

                    System.out.println("Insira a divisão: ");
                    String d = scanner.next();
                    if(!c.hasRoom(d)) {
                        c.addRoom(d);
                        estado.updateCasa(c, i-1);
                    }
                    else {
                        System.out.println("A divisão já existe! ");
                        Thread.sleep(1000);
                    }
                }
                case 6 -> {
                    int i = -1;
                    while (i < 0 || i > l.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                        if (i < 0 || i> l.size()) Menu.erros(12);
                    }
                    Casa c = l.get(i - 1);
                    if (!c.getDivisoes().isEmpty()) {

                        System.out.println("Insira a divisão: ");
                        String d = scanner.next();
                        while (!(c.hasRoom(d))) {
                            Menu.erros(17);
                            System.out.println("Insira a divisão: ");
                            d = scanner.next();
                        }

                        System.out.println("Off ou On: ");
                        String est = scanner.next().toLowerCase();
                        while (!est.equals("on") && !est.equals("off")){
                            Menu.erros(2);
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
                        if (i < 0 || i> l.size()) Menu.erros(12);
                    }
                    Casa c = l.get(i - 1);

                    System.out.println("Insira o fornecedor: ");
                    String d = scanner.next().toLowerCase();
                    Fornecedores forn = switch (d) {
                        case "edp" -> new FornecEDP();
                        case "endesa" -> new FornecEndesa();
                        case "jomar" -> new FornecJomar();
                        default -> null;  //Menu.erros(7);
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
