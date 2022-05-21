import java.util.*;

public class ControllerCasa {
    public static void run(Estado estado) throws InterruptedException {

        boolean exit = false;
        Menu.clearWindow ();

        while (!exit) {
            Scanner scanner = new Scanner(System.in).useDelimiter ("\n");
            int opcao = -1, i=-1;
            List<Casa> l = estado.getCasas();
            while (opcao < 0 || opcao > 8) {
                //Menu.erros(1);
                //Thread.sleep(1000);
                opcao = Menu.MenuCasa();
            }

            switch (opcao) {
                case 1 -> { //se meter letras como opcao dá erro, talvez resulte se tirar o nextInt e meter só next
                    while (i< 0 || i > l.size()) {
                        //Menu.erros(12);
                        i = Menu.MenuListaCasas(l);
                    }
                    if(i==0) break;
                    int escolha2 = Menu.MenuCasaInfo(i - 1, l);
                    while (escolha2 != 0) escolha2 = Menu.MenuCasaInfo(i - 1, l);
                }
                case 2 ->{

                    Casa c = indiceCasa (l);

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
                    estado.updateCasa(c, l.indexOf (c));
                }
                case 3 -> {

                    Casa c = indiceCasa (l);

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
                        estado.updateCasa(c, l.indexOf (c) );
                    }
                    else {
                        Menu.erros(20);
                        System.out.println("Erro! A casa não tem divisões.");
                        Thread.sleep(2000);
                    }
                }
                case 4 -> {
                    System.out.println("Insira o NIF: ");
                    String nif = scanner.next();
                    while (!onlyDigits(nif,nif.length())){
                        //Menu.erros(2);
                        System.out.println("Insira o NIF: ");
                        nif = scanner.next();
                    }

                    System.out.println("Insira o nome do proprietário: ");   // SE EU METER +1 NOME DÁ ERRO
                    String proprietario = scanner.next();

                    System.out.println("Insira o nome do fornecedor: ");
                    Fornecedores fornecedor = null;
                    String forn = scanner.next();
                    switch (forn) {
                        case "EDP" -> fornecedor = estado.getFornecedores().get(0);
                        case "Endesa" -> fornecedor = estado.getFornecedores().get(2);
                        case "Jomar" -> fornecedor = estado.getFornecedores().get(1);
                        default -> {
                            System.out.println("Fornecedor inválido! Casa não será criada.");
                            Thread.sleep(3000);
                        }
                    }
                    if(fornecedor!=null){
                        Casa c = new Casa(proprietario, nif, fornecedor);
                        estado.addCasa(c);
                    }
                }
                case 5 -> {                                         //não está a verificar o lowercase da divisao

                    Casa c = indiceCasa (l);

                    System.out.println("Insira a divisão: ");
                    String d = scanner.next ();
                    if(!c.hasRoom(d)) {
                        c.addRoom(d);
                        estado.updateCasa(c, l.indexOf (c));
                    }
                    else {
                        System.out.println("A divisão já existe! ");
                        Thread.sleep(3000);
                    }
                }
                case 6 -> {

                    Casa c = indiceCasa (l);

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

                        if (Fornecedores.Stringfornecedor(c.getFornecedor()).equals("Jomar"))
                            c.setCustoInstalacao (30);
                        if (Fornecedores.Stringfornecedor(c.getFornecedor()).equals("EDP"))
                            c.setCustoInstalacao (20);
                        if (Fornecedores.Stringfornecedor(c.getFornecedor()).equals("Endesa"))
                            c.setCustoInstalacao (10);
                       // System.out.println (c.getCustoInstalacao ());
                        estado.updateCasa(c, l.indexOf (c));
                    }
                }
                case 7 -> {
                    List<Fornecedores> fornecedores = estado.getFornecedores();

                    Casa c = indiceCasa (l);

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
                    c.setFornecedor(forn);
                    estado.updateCasa(c, l.indexOf (c));
                }
                case 0 -> {
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }

    public static  Casa indiceCasa (List<Casa> l){
        int i=-1;
        Scanner scanner = new Scanner(System.in).useDelimiter ("\n");
        while (i < 0 || i > l.size ()) {
            System.out.println ("Insira o índice da casa: ");
            String s = scanner.next();
            if (onlyDigits(s, s.length())) {
                i= Integer.parseInt(s);
                if (i < 0 || i> l.size()) Menu.erros(12);
            }
        }

        return l.get (i - 1);
    }
    public static SmartDevice EscolhaDispositivos (String id,boolean turn){
        int disp = -1, i=-1;
        Scanner scan = new Scanner(System.in);
        while (disp < 0 || disp > 3) {
            disp = Menu.EscolhaDispotivios();
            if (disp < 0 || disp > 3) Menu.erros(1);

        }
        switch (disp) {
            case 1-> {
                Scanner scanner = new Scanner(System.in).useDelimiter ("\n");
                while (i < 1 || i > 3) {
                    System.out.println ("Insira o tone (1,2 ou 3): ");
                    String s = scan.next ();
                    if (onlyDigits (s, s.length ())) {
                        i = Integer.parseInt (s);
                    }
                }

                    System.out.println ("Insira a dimensão: ");
                    double di = scan.nextDouble ();

                    return new SmartBulb (id, turn, i, di);
            }
            case 2-> {
                System.out.println("Insira a resolução: ");
                double r = scan.nextDouble();
                System.out.println("Insira o tamanho da imagem: ");
                Scanner size = new Scanner(System.in);
                double s = size.nextDouble();
                return new SmartCamera(id,turn,r,s);
            }
            case 3-> {
                System.out.println("Insira a marca: ");
                String m = scan.next();
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
