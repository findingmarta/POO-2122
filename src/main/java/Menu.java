import java.util.*;
//import java.time.LocalDateTime;

public class Menu {
    public static List<SmartDevice> makeDevices() {
        List<SmartDevice> devices = new ArrayList<>();
        devices.add(new SmartSpeaker("1111", false,15, "RFM", "Marshall"));
        devices.add(new SmartBulb("12345", true, SmartBulb.WARM, 2.0));
        return devices;
    }

    private static Map<String, List<SmartDevice>> makeDivisoes() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        divisoes.put("Sala",Menu.makeDevices());
        divisoes.put("Cozinha",Menu.makeDevices());
        return divisoes;
    }

    static void listCasas(List<Casa> l) {
        Map <String, List<SmartDevice>> divisoes = makeDivisoes();
        l.add( new Casa());
        l.add( new Casa(divisoes, "Joana", "123456789"));

    }
    public static int MenuInicial(){
        clearWindow();
        String sb = """
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 \u001B[1m           MENU INICIAL \u001B[0m

                 \u001B[1m 1) \u001B[0m Menu Casa.
                 \u001B[1m 2) \u001B[0m Ver fornecedores.
                 \u001B[1m 3) \u001B[0m Salvar Estado.
                 \u001B[1m 4) \u001B[0m Carregar Estado.
                 \u001B[1m 0) \u001B[0m Sair.
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 Selecione a opção pretendida:\s""";
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }


    public static int MenuCasa() {
        clearWindow();
        String sb = """
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 \u001B[1m                   MENU CASA \u001B[0m

                \u001B[1m 1) \u001B[0m Lista de Casas.
                \u001B[1m 2) \u001B[0m Ligar/Desligar dispositivo.
                \u001B[1m 3) \u001B[0m Ligar/Desligar todos os dispositivos de uma divisão.
                \u001B[1m 4) \u001B[0m Criar casa.
                \u001B[1m 5) \u001B[0m Adicionar divisões.
                \u001B[1m 6) \u001B[0m Adicionar dispositivos.
                \u001B[1m 7) \u001B[0m Consumo total de uma casa.
                \u001B[1m 8) \u001B[0m Casa que mais gastou num determinado período.
                \u001B[1m 0) \u001B[0m Menu Inicial.

                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                Selecione a opção pretendida:\s""";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int MenuListaCasas(List<Casa> l){
        Menu.clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m             LISTAS DE CASAS \u001B[0m\n\n");
        for (int i=0; i < l.size(); i++){
            String nif = l.get(i).getNIF();
            String prop = l.get(i).getProprietario();
            sb.append("  \u001B[1m").append(i + 1).append(") \u001B[0m Casa").append(i + 1).append(" -> NIF: ").append(nif).append("  Proprietário: ").append(prop).append("\n");
        }
        sb.append(" \u001B[1m 0) \u001B[0m Menu Casa\n");
        sb.append("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuCasaInfo(int i, List<Casa> l) {
        clearWindow();
        String sb = "\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n" +
                    " \u001B[1m                   CASA INFO \u001B[0m\n\n" +
                                                        l.get(i) +
                    "\n\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n" +
                    "Selecione 0 para voltar atrás: ";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public static void definirDispositivos(List<Casa> l) {
        int i=-1;
        while(i < 0 || i> l.size()) {
            System.out.println("Insira o índice da casa: ");
            Scanner scanner = new Scanner(System.in);
            i = scanner.nextInt();
        }

        Casa c = l.get(i - 1);

        if(!c.getDivisoes().isEmpty()) {
            System.out.println("Insira a divisão: ");
            Scanner divisao = new Scanner(System.in);
            String d = divisao.next();
            while (!(c.hasRoom(d))) {
                System.out.println("Insira a divisão: ");
                divisao = new Scanner(System.in);
                d = divisao.next();
            }

            if (!(c.roomisEmpty(d))){
                System.out.println("Insira o id: ");
                Scanner id = new Scanner(System.in);
                String id1 = id.next();

                while (!(c.roomHasDevice(d, id1))) {
                    System.out.println("Insira o id: ");
                    id = new Scanner(System.in);
                    id1 = id.next();
                }
                    SmartDevice s = c.getDevice(d, id1);
                    //System.out.println(s);

                    System.out.println("Ligar ou Desligar? ");
                    Scanner modo = new Scanner(System.in);
                    String m = modo.next();

                    if (m.equals("Ligar") || m.equals("ligar")) {
                        c.setDeviceOn(d, s);
                        //System.out.println("\nLigado : " + s);
                    } else if (m.equals("Desligar") || m.equals("desligar")) {
                        c.setDeviceOff(d, s);
                        //System.out.println("\nDesligado : " + s);
                    } else {
                        System.out.println("Ligar ou Desligar? ");
                        modo = new Scanner(System.in);
                    }
                    Main.menucasa1(l);
                }
            else{

                System.out.println("\n\u001B[1m Esta casa nao tem dispositivos.\u001B[0m\n ");
                System.out.println("Insira 0 para retornar ao MenuCasa. ");
                Scanner s1 = new Scanner(System.in);
                int si = s1.nextInt();
                if (si == 0) Main.menucasa1(l);
            }

        }
        else {
                System.out.println("\n\u001B[1m Esta casa nao tem dispositivos.\u001B[0m\n ");
                System.out.println("Insira 0 para retornar ao MenuCasa. ");
                Scanner s2 = new Scanner(System.in);
                int si = s2.nextInt();
                if (si == 0) Main.menucasa1(l);
            }


    }

    public static void definirDivisoes(List<Casa> l) {
        int i=-1;

        while(i < 0 || i> l.size()) {
            System.out.println("Insira o índice da casa: ");
            Scanner scanner = new Scanner(System.in);
            i = scanner.nextInt();
        }
        System.out.println("Insira a divisão: "); //verificar se a divsao existe
        Scanner divisao = new Scanner(System.in);
        String d = divisao.next();
        Casa c = l.get(i - 1);
        while (!(c.hasRoom(d))) {
            System.out.println("Insira a divisão: ");
            divisao = new Scanner(System.in);
            d = divisao.next();
        }
        System.out.println("Ligar ou Desligar? ");
        Scanner modo = new Scanner(System.in);
        String m = modo.next();

        if (m.equals("Ligar")|| m.equals("ligar")) {
            c.setDivisonOn(d);
            //SmartDevice s = c.getDevice(d,"1111");
            //System.out.println(s.getOn());
        }
        else if (m.equals("Desligar") || m.equals("desligar")) {
            c.setDivisonOff(d);
            //SmartDevice s = c.getDevice(d,"1111");
            //System.out.println(s.getOn());
        }
        else {
            System.out.println("Ligar ou Desligar? ");
            modo = new Scanner(System.in);
            m = modo.next();
        }
        Main.menucasa1(l);
        /*
        System.out.println("\nInsira 0 para retornar para o Menu Casa");
        Scanner decisao = new Scanner(System.in);
        int deci = decisao.nextInt();
        if (deci==0) Main.menucasa1(l);
         */
    }
    /*

    public static int MenuFornecedores() {
        clearWindow();
        String sb = """
                -----------MENU FORNECEDORES-----------

                1) Comercializador com maior volume de facturação.
                2) Listar as facturas emitidas por um comercializador.
                3) Ordenar consumidores de energia por ordem decrescente.
                0) Sair.

                Selecione a opção pretendida:\s""";
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String pressEnter(){
        System.out.println("Pressione qualquer tecla para continuar...");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    //double to TimeString
    public static String time(double d){
        int hour = (int) d;
        int min = (int)((d-hour)*60);
        return (hour+":"+ min+" H");
    }

     */
    //Tentar encontrar outra forma mais elegante
    public static void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }


    /*
    public static void maisFreqT(List<AbstractMap.SimpleEntry<String, Double>> l) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Entidades que utilizam mais o sistema-----------\n\n");
        for (AbstractMap.SimpleEntry<String, Double> m : l) {
            String result = String.format("%.2f", m.getValue());
            sb.append(m.getKey()).append("----->").append(result).append("Km percorridos\n");
        }
        System.out.println(sb.toString());
    }

    public static void maisFreqU(List<AbstractMap.SimpleEntry<String, Integer>> l) {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------Utilizadores que mais utilizam mais o sistema-----------\n\n");
        for (AbstractMap.SimpleEntry<String, Integer> m : l) sb.append(m.getKey()+"----->"+m.getValue()+"encomendas transportadas\n");
        System.out.println(sb.toString());
    }*/
}