import java.util.*;

public class ControllerCasa {
    public static void run(Estado estado) throws InterruptedException {
        List<Casa> l = estado.getCasas();
        Menu.clearWindow ();
        boolean exit = false;

        Menu.clearWindow ();
        while (!exit) {
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                //Menu.erros(1);
                //Thread.sleep(1000);
                opcao = Menu.MenuCasa();
            }

            Scanner scanner = new Scanner(System.in);
            switch (opcao) {
                case 1 -> { //se meter letras como opcao dá erro, talvez resulte se tirar o nextInt e meter só next
                    List<Casa> lista = estado.getCasas();
                    int escolha = -1;

                    while (escolha < 0 || escolha > lista.size()) {
                        //Menu.erros(12);
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

                    System.out.println("Insira o nome do proprietário: ");   // SE EU METER +1 NOME DÁ ERRO
                    String proprietario = scan.next();

                    System.out.println("Insira o nome do fornecedor: ");
                    Fornecedores fornecedor = null;
                    String forn = scan.next();
                    switch (forn) {
                        case "EDP" -> fornecedor = estado.getFornecedores().get(0);
                        case "Endesa" -> fornecedor = estado.getFornecedores().get(2);
                        case "Jomar" -> fornecedor = estado.getFornecedores().get(1);
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
                case 5 -> {                                         //não está a verificar o lowercase da divisao
                    int i = -1;
                    List<Casa> lista = estado.getCasas();
                    while (i < 0 || i > lista.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                        if (i < 0 || i> lista.size()) Menu.erros(12);
                    }
                    Casa c = lista.get(i - 1);

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
                    List<Casa> lista = estado.getCasas();
                    while (i < 0 || i > lista.size()) {
                        System.out.println("Insira o índice da casa: ");
                        i = scanner.nextInt();
                        if (i < 0 || i> lista.size()) Menu.erros(12);
                    }
                    Casa c = lista.get(i - 1);
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

                        System.out.println("Insira o ID: ");
                        String id = scanner.next();
                        while (!onlyDigits(id,id.length())){
                            //Menu.erros(2);
                            System.out.println("Insira o ID: ");
                            id = scanner.next();
                        }

                        SmartDevice s = ControllerCasa.EscolhaDispositivos(id,turn);
                        assert s != null;
                        c.addSmartDevice(s);
                        c.addToRoom(d,s.getID());
                        estado.updateCasa(c, i-1);
                    }
                }
                case 7 -> {
                    List<Fornecedores> fornecedores = estado.getFornecedores();
                    System.out.println("Insira o indice da casa: ");
                    int i = scanner.nextInt();

                    System.out.println("Insira o nome do fornecedor: ");
                    Fornecedores forn = null;
                    String f = scanner.next();
                    switch (f) {
                        case "EDP" -> forn = new FornecEDP();
                        case "Endesa" -> forn = new FornecEndesa();
                        case "Jomar" -> forn = new FornecJomar();
                        default -> {
                            System.out.println("Fornecedor inválido! Casa não será criada.");
                            Thread.sleep(3000);
                        }
                    }
                    forn = fornecedores.get(fornecedores.indexOf(forn));
                    Casa c = l.get(i-1);
                    c.setFornecedor(forn);
                    estado.updateCasa(c, i-1);
                }
                case 0 -> {
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }

    public static SmartDevice EscolhaDispositivos (String id,boolean turn){
        int disp = -1;
        while (disp < 0 || disp > 3) {
            disp = Menu.EscolhaDispotivios();
            if (disp < 0 || disp > 3) Menu.erros(1);

        }
        switch (disp) {
            case 1-> {
                System.out.println("Insira o tone (1,2 ou 3): ");
                Scanner tone = new Scanner(System.in);
                int t = tone.nextInt();
                System.out.println("Insira a dimensão: ");
                Scanner dimensao = new Scanner(System.in);
                double di = dimensao.nextDouble();
                return new SmartBulb(id,turn,t,di);
            }
            case 2-> {
                System.out.println("Insira a resolução: ");
                Scanner resolucao = new Scanner(System.in);
                double r = resolucao.nextDouble();
                System.out.println("Insira o tamanho da imagem: ");
                Scanner size = new Scanner(System.in);
                double s = size.nextDouble();
                return new SmartCamera(id,turn,r,s);
            }
            case 3-> {
                System.out.println("Insira a marca: ");
                Scanner marca = new Scanner(System.in);
                String m = marca.next();
                System.out.println("Insira a rádio : ");
                Scanner radio = new Scanner(System.in);
                String r = radio.next();
                System.out.println("Insira o volume : ");
                Scanner volume = new Scanner(System.in);
                int v = volume.nextInt();
                return new SmartSpeaker(id,turn,v,r,m);
            }

        }
        return null;
    }

    public static boolean onlyDigits(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }

}
