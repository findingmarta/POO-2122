import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
                opcao = Menu.MenuCasa();
            }

            switch (opcao) {
                case 1 -> {
                    while (i< 0 || i > l.size()) {
                        i = Menu.MenuListaCasas(l);
                    }
                    if(i==0) break;
                    int escolha2 = Menu.MenuCasaInfo(i - 1, l);
                    while (escolha2 != 0) escolha2 = Menu.MenuCasaInfo(i - 1, l);
                }
                case 2 ->{
                    if(estado.getCasas().isEmpty()) {
                        Menu.erros(23);
                        Thread.sleep(3000);
                        break;
                    }

                    Casa c = indiceCasa (l);
                    if(c.getDevices().isEmpty()) {
                        Menu.erros(35);
                        Thread.sleep(3000);
                        break;
                    }

                    System.out.println ("Insira o id: ");
                    String id = scanner.next ();
                    while (!(c.hasDevice (id))) {
                        Menu.erros(6);
                        System.out.println ("Tente novamente: ");
                        id = scanner.next ();
                    }

                    System.out.println ("Ligar ou Desligar? ");
                    String m = scanner.next ().toLowerCase ();
                    while (!m.equals ("ligar") && !m.equals ("desligar")) {
                        Menu.erros(2);
                        System.out.println ("Ligar ou Desligar?: ");
                        m = scanner.next ();
                    }

                    if (m.equals ("ligar")) c.setDeviceOn (id);
                    else c.setDeviceOff (id);
                    estado.updateCasa(c, l.indexOf (c));
                }
                case 3 -> {
                    if(estado.getCasas().isEmpty()) {
                        Menu.erros(23);
                        Thread.sleep(3000);
                        break;
                    }

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
                        Thread.sleep(2000);
                    }
                }
                case 4 -> {
                    if(estado.getFornecedores().isEmpty()){
                        estado.addForn(new FornecEDP("formula2"));
                        estado.addForn(new FornecJomar("formula1"));
                        estado.addForn(new FornecEndesa("formula3"));
                    }
                    List<Fornecedores> fornecedores = estado.getFornecedores();

                    System.out.println("Insira o NIF: ");
                    String nif = scanner.next();
                    while (!Casa.onlyDigits(nif,nif.length())){
                        System.out.println("Insira o NIF: ");
                        nif = scanner.next();
                    }

                    System.out.println("Insira o nome do proprietário: ");
                    String proprietario = scanner.next();

                    System.out.println("Insira o nome do fornecedor: ");
                    Fornecedores fornecedor = null;
                    String forn = scanner.next();
                    switch (forn) {
                        case "EDP" -> fornecedor = new FornecEDP();
                        case "Endesa" -> fornecedor = new FornecEndesa();
                        case "Jomar" -> fornecedor = new FornecJomar();
                        default -> {
                            Menu.erros(7);
                            Thread.sleep(3000);
                        }
                    }
                    if(fornecedor!=null){
                        fornecedor = fornecedores.get(fornecedores.indexOf(fornecedor));
                        Casa c = new Casa(proprietario, nif, fornecedor);
                        estado.addCasa(c);
                    }
                }
                case 5 -> {
                    if(estado.getCasas().isEmpty()) {
                        Menu.erros(23);
                        Thread.sleep(3000);
                        break;
                    }

                    Casa c = indiceCasa (l);

                    System.out.println("Insira a divisão: ");
                    String d = scanner.next ();
                    if(!c.hasRoom(d)) {
                        c.addRoom(d);
                        estado.updateCasa(c, l.indexOf (c));
                    }
                    else {
                        Menu.erros(4);
                        Thread.sleep(3000);
                    }
                }
                case 6 -> {
                    if(estado.getCasas().isEmpty()) {
                        Menu.erros(23);
                        Thread.sleep(3000);
                        break;
                    }

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
                        while (!Casa.onlyDigits(id,id.length())){
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
                        estado.updateCasa(c, l.indexOf (c));
                    }
                    else {
                        Menu.erros(29);
                        Thread.sleep(3000);
                    }
                }
                case 7 -> {
                    List<Fornecedores> fornecedores = estado.getFornecedores();
                    if(estado.getCasas().isEmpty()) {
                        Menu.erros(23);
                        Thread.sleep(3000);
                        break;
                    }

                    Casa c = indiceCasa (l);
                    Fornecedores forn = null;


                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
                    LocalDate dFornecedor = LocalDate.parse (c.getFornecedor ().getDataInicial (), dateTimeFormatter);
                    LocalDate dAfter = LocalDate.parse (estado.getData (), dateTimeFormatter);

                    if (dAfter.isAfter (dFornecedor)) {
                        long diff = ChronoUnit.DAYS.between (dFornecedor, dAfter);

                        if (diff > 30) {

                            System.out.println ("Insira o nome do fornecedor: ");

                            String f = scanner.next ();
                            switch (f) {
                                case "EDP" -> forn = new FornecEDP ();
                                case "Endesa" -> forn = new FornecEndesa ();
                                case "Jomar" -> forn = new FornecJomar ();
                                default -> {
                                    Menu.erros(7);
                                    Thread.sleep(3000);
                                }
                            }

                            forn = fornecedores.get (fornecedores.indexOf (forn));
                            forn.setDataInicial (estado.getData ());
                            c.setFornecedor (forn);
                            estado.updateCasa (c, l.indexOf (c));
                            estado.updateFornecedor (forn);
                        }
                        else {
                            Menu.erros (39);
                            Thread.sleep (3000);
                        }
                    }
                    else {
                        Menu.erros (39);
                        Thread.sleep (3000);
                    }

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
        while (i <= 0 || i > l.size ()) {
            System.out.println ("Insira o índice da casa: ");
            String s = scanner.next();
            if (Casa.onlyDigits(s, s.length())) {
                i= Integer.parseInt(s);
                if (i <= 0 || i> l.size()) Menu.erros(12);
            }
        }
        return l.get (i - 1);
    }

    public static SmartDevice EscolhaDispositivos (String id,boolean turn){
        int disp = -1, i=-1;
        Scanner scanner = new Scanner(System.in).useDelimiter ("\n");
        while (disp < 0 || disp > 3) {
            disp = Menu.EscolhaDispotivios();
            if (disp < 0 || disp > 3) Menu.erros(1);

        }
        switch (disp) {
            case 1-> {
                while (i<1 || i> 3) {
                    System.out.println ("Insira o tone (1,2 ou 3): ");
                    String s = scanner.next ();
                    if (Casa.onlyDigits (s, s.length ())) {
                        i = Integer.parseInt (s);
                    }
                }
                System.out.println("Insira a dimensão: ");
                Scanner dimensao = new Scanner(System.in);
                double di = dimensao.nextDouble();
                return new SmartBulb(id,turn,i,di);
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
}
