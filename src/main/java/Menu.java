import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Menu {

    //Tentar encontrar outra forma mais elegante
    public static void clearWindow() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static int MenuInicial() {
        clearWindow();
        String sb = """
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 \u001B[1m           MENU INICIAL \u001B[0m

                 \u001B[1m 1) \u001B[0m Estado.
                 \u001B[1m 2) \u001B[0m Menu Casa.
                 \u001B[1m 3) \u001B[0m Menu Fornecedores.
                 \u001B[1m 4) \u001B[0m Simulação.
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
                \u001B[1m 0) \u001B[0m Voltar atrás.
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 Selecione a opção pretendida:\s""";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int MenuListaCasas(List<Casa> l) {
        Menu.clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m             LISTAS DE CASAS \u001B[0m\n\n");
        for (int i = 0; i < l.size(); i++) {
            String nif = l.get(i).getNIF();
            String prop = l.get(i).getProprietario();
            sb.append("  \u001B[1m").append(i + 1).append(") \u001B[0m Casa").append(i + 1).append(" -> NIF: ").append(nif).append("  Proprietário: ").append(prop).append("\n");
        }
        sb.append(" \u001B[1m 0) \u001B[0m Voltar atrás.\n");
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
                "Selecione 0 para voltar atrás. ";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int EscolhaDispotivios() {
        String sb = """
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 \u001B[1m                   DISPOSITIVO \u001B[0m

                \u001B[1m 1) \u001B[0m SmartBulb.
                \u001B[1m 2) \u001B[0m SmartCamera.
                \u001B[1m 3) \u001B[0m SmartSpeaker.

                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 Selecione a opção pretendida:\s""";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }




    public static int menuFornecedores() {
        clearWindow();
        String sb = """
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 \u001B[1m           FORNECEDORES \u001B[0m

                 \u001B[1m 1) \u001B[0m Info fornecedores.
                 \u001B[1m 2) \u001B[0m Outras coisas
                 \u001B[1m 0) \u001B[0m Voltar atrás.
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 Selecione a opção pretendida:\s""";
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public static int MenuEstado(){
        Menu.clearWindow();
        String sb = """
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s
                
                \u001B[1m           MENU ESTADO\u001B[0m
                
                \u001B[1m 1) \u001B[0m Carregar ficheiro Original.
                \u001B[1m 2) \u001B[0m Carregar novo Ficheiro.
                \u001B[1m 3) \u001B[0m Salvar Estado.
                \u001B[1m 0) \u001B[0m Voltar atrás.     
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s
                 
                 Selecione a opção pretendida:\s""";
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


    public static int menuSimulacao() {
        String sb = """
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 \u001B[1m                   SIMULACAO \u001B[0m

                \u001B[1m 1) \u001B[0m Emissão faturas.
                \u001B[1m 2) \u001B[0m Faturas.
                \u001B[1m 3) \u001B[0m Estatísticas.
                \u001B[1m 0) \u001B[0m Voltar atrás.
                \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                 Selecione a opção pretendida:\s""";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static int faturaList( Casa c) {
        Menu.clearWindow();
        StringBuilder sb = new StringBuilder("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        sb.append(" \u001B[1m             LISTAS DE FATURAS \u001B[0m\n\n");
        List<Faturas> l = c.getFatura();
        for (int i = 0; i < l.size(); i++) {
        String nif = l.get(i).getDataInicial();
        String prop = l.get(i).getDataFinal();
        sb.append("  \u001B[1m").append(i + 1).append(") \u001B[0m").append("Data Inicial: ").append(nif).append("  Data Final: ").append(prop).append("\n");
    }
        sb.append(" \u001B[1m 0) \u001B[0m Voltar atrás\n");

        sb.append("\u001B[1m \u001B[36m___________________________________________________\u001B[0m \n\n");
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
}
    public static int FaturaInfo(int i, List<Faturas> l) {
        clearWindow();
        String sb = "\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n" +
                " \u001B[1m                   FATURA INFO \u001B[0m\n\n" +
                l.get(i).StringFaturas() +
                "\n\u001B[1m \u001B[36m_________________________________________________________\u001B[0m \n\n" +
                "Selecione 0 para voltar atrás. ";

        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int MenuEstatistica() {
        clearWindow();
        String sb = """
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 \u001B[1m           MENU ESTATÍSTICAS \u001B[0m

                 \u001B[1m 1) \u001B[0m Casa com maior gasto.
                 \u001B[1m 2) \u001B[0m Fornecedor com maior volume de facturação.
                 \u001B[1m 3) \u001B[0m Faturas emitidas por um fornecedor.
                 \u001B[1m 4) \u001B[0m Top x consumidores.
                 \u001B[1m 0) \u001B[0m Voltar atrás.
                \u001B[1m \u001B[36m____________________________________\u001B[0m\s

                 \nSelecione a opção pretendida:\s""";
        System.out.println(sb);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static final String RESET = "\033[0m";
    public static final String CYAN_BRIGHT = "\033[0;96m";


    public static void erros (int i){
        StringBuilder sb = new StringBuilder();
        if (i==1) sb.append(CYAN_BRIGHT).append("***** Opção inserida inválida *****").append(RESET).append("\n");
        else if (i==2) sb.append(CYAN_BRIGHT).append("***** Dados inseridos são inválidos *****").append(RESET).append("\n");
        else if (i==3) sb.append(CYAN_BRIGHT).append("***** Não foi possível ler o ficheiro *****").append(RESET).append("\n");
        else if (i==4) sb.append(CYAN_BRIGHT).append("***** Divisão já existente *****").append(RESET).append("\n");
        else if (i==5) sb.append(CYAN_BRIGHT).append("***** Dispositivo já existente nesta divisão (com o mesmo id) *****").append(RESET).append("\n");
        else if (i==6) sb.append(CYAN_BRIGHT).append("***** Dispositivo não existe *****").append(RESET).append("\n");
        else if (i==7) sb.append(CYAN_BRIGHT).append("***** Fornecedor inserido não existe *****").append(RESET).append("\n");
        else if (i==8) sb.append(CYAN_BRIGHT).append("***** Tom inserido inválido *****").append(RESET).append("\n");
        else if (i==9) sb.append(CYAN_BRIGHT).append("***** Estado não foi guardado *****").append(RESET).append("\n");
        else if (i==10) sb.append(CYAN_BRIGHT).append("***** Estado não foi carregado *****").append(RESET).append("\n");
        else if (i==11) sb.append(CYAN_BRIGHT).append("***** Data no formato inválido *****").append(RESET).append("\n");
        else if (i==12) sb.append(CYAN_BRIGHT).append("***** Índice da casa inválido *****").append(RESET).append("\n");
        else if (i==13) sb.append(CYAN_BRIGHT).append("***** Divisão não existe *****").append(RESET).append("\n");
        else if (i==14) sb.append(CYAN_BRIGHT).append("***** Volume inserido é inválido *****").append(RESET).append("\n");
        else if (i==15) sb.append(CYAN_BRIGHT).append("***** Linha do ficheiro inválida *****").append(RESET).append("\n");
        else if (i==16) sb.append(CYAN_BRIGHT).append("***** Dispositivo não existe nesta divisão *****").append(RESET).append("\n"); //nop
        else if (i==17) sb.append(CYAN_BRIGHT).append("***** Divisão não existe nesta casa *****").append(RESET).append("\n");
        else if (i==18) sb.append(CYAN_BRIGHT).append("***** Divisão inválida *****").append(RESET).append("\n");
        else if (i==19) sb.append(CYAN_BRIGHT).append("***** Volume de faturação inválido *****").append(RESET).append("\n");
        else if (i==20) sb.append(CYAN_BRIGHT).append("***** Não foi possível ligar/desligar todos os dispositivos da divisão *****").append(RESET).append("\n");

        System.out.print(sb);
        //Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        //clearWindow();
    }
    }
