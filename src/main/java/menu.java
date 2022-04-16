import java.util.*;
import java.time.LocalDateTime;

public class menu {
    private static List<SmartDevice> makeDevices() {
        List<SmartDevice> devices = new ArrayList<SmartDevice>();
        devices.add(new SmartSpeaker("1111", false,15, "RFM", "Marshall"));
        devices.add(new SmartBulb("12345", true, SmartBulb.WARM, 2.0));
        return devices;
    }

    private static Map<String, List<SmartDevice>> makeDivisoes() {
        Map <String, List<SmartDevice>> divisoes = new HashMap<>();
        divisoes.put("Sala",menu.makeDevices());
        divisoes.put("Cozinha",menu.makeDevices());
        return divisoes;
    }

    static List<Casa> listCasas() {
        Map <String, List<SmartDevice>> divisoes = makeDivisoes();
        List<Casa> Listcasa = new ArrayList<Casa>();
        Listcasa.add( new Casa());
        Listcasa.add( new Casa(divisoes, "Joana", "123456789"));
        return Listcasa;

    }
    public static int menuInicial(){
        clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m____________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m           MENU INICIAL \u001B[0m\n\n");
        sb.append(" \u001B[1m 1) \u001B[0m Menu Casa.\n");
        sb.append(" \u001B[1m 2) \u001B[0m Ver fornecedores.\n");
        sb.append(" \u001B[1m 3) \u001B[0m Salvar Estado.\n");
        sb.append(" \u001B[1m 4) \u001B[0m Carregar Estado.\n");
        sb.append(" \u001B[1m 0) \u001B[0m Sair.\n");
        sb.append("\u001B[1m \u001B[36m____________________________________\u001B[0m \n\n");
        sb.append(" Selecione a opção pretendida: ");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();

    }


    public static int menuCasa() {
        clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m                   MENU CASA \u001B[0m\n\n");
        sb.append("\u001B[1m 1) \u001B[0m Lista de Casas.\n");
        sb.append("\u001B[1m 2) \u001B[0m Ligar/Desligar dispositivo.\n");
        sb.append("\u001B[1m 3) \u001B[0m Ligar/Desligar todos os dispositivos de uma divisão.\n");
        sb.append("\u001B[1m 4) \u001B[0m Consumo total de uma casa.\n");
        sb.append("\u001B[1m 5) \u001B[0m Casa que mais gastou num determinado período.\n");
        sb.append("\u001B[1m 6) \u001B[0m Criar casa.\n");
        sb.append("\u001B[1m 0) \u001B[0m Sair.\n\n");
        sb.append("\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n");
        sb.append("Selecione a opção pretendida: ");

        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int menuListaCasas(){
        List<Casa> ListCasa = listCasas();
        menu.clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m             LISTAS DE CASAS \u001B[0m\n\n");
        for (int i=0; i < ListCasa.size(); i++){
            String nif = listCasas().get(i).getNIF();
            String prop = listCasas().get(i).getProprietario();
            sb.append("  \u001B[1m" + (i+1) + ") \u001B[0m Casa" + (i+1) + " -> NIF: " + nif +"  Proprietário: "+prop+"\n");
        }
        sb.append(" \u001B[1m 0) \u001B[0m Menu Inicial\n");
        sb.append("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int menuCasaInfo(int i) {
        clearWindow();
        List<Casa> ListCasa = menu.listCasas();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m                   CASA INFO \u001B[0m\n\n");
        /*
        for( Casa ln : ListCasa){
            sb.append("\u001B[1m 0) \u001B[0m " + ln +"\n");
        }
        */
        sb.append( ListCasa.get(i) );
        sb.append("\n\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n");
        sb.append("Selecione 0 para voltar atrás: ");

        System.out.println(sb.toString());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int menuFornecedores() {
        clearWindow();
        StringBuilder sb = new StringBuilder("-----------MENU FORNECEDORES-----------\n\n");
        sb.append("1) Comercializador com maior volume de facturação.\n");
        sb.append("2) Listar as facturas emitidas por um comercializador.\n");
        sb.append("3) Ordenar consumidores de energia por ordem decrescente.\n");
        sb.append("0) Sair.\n\n");
        sb.append("Selecione a opção pretendida: ");
        System.out.println(sb.toString());
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

    //Tentar encontrar outra forma mais elegante
    public static void clearWindow() {
        for (int i = 0;i<100;i++){
            System.out.println();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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