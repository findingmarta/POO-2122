import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerEstatistica {
    public static void run(Estado estado) {

        Menu.clearWindow ();
        boolean exit = false;

        while (!exit) {
            List<Casa> l = estado.getCasas ();
            List<Fornecedores> f = estado.getFornecedores ();
            Scanner scanner = new Scanner (System.in);
            int opcao = -1;
            while (opcao < 0 || opcao > 5) {
                opcao = Menu.MenuEstatistica ();
            }
            switch (opcao) {
                case 1 -> {
                    Casa c = estado.ordenaListGasto(l);
                    System.out.println(c);

                    int i = scanner.nextInt ();
                    while (i != 0) {
                        i = scanner.nextInt ();
                    }
                }
                case 2 -> {
                    Fornecedores forn = estado.ordenaListFornecedores (f);
                    String sb = "\u001B[1m\u001B[36m_________________________________________\u001B[0m\s\n\n" +
                            "\u001B[1m   FORNECEDOR COM MAIOR VOLUME DE FATURAÇÃO \u001B[0m\n\n" +
                            "Fornecedor= " + forn.Stringfornecedor(forn) + forn +
                            "\n\u001B[1m\u001B[36m_________________________________________\u001B[0m\s\n\n"+
                            "Selecione 0 para voltar atrás.\s";
                    System.out.println(sb);
                    int i = scanner.nextInt ();
                    while (i != 0) {
                        i = scanner.nextInt ();
                    }
                }
                case 3 -> {
                    List<Casa> casas = new ArrayList<> ();
                    System.out.println ("Insira o fornecedor: ");
                    String d = scanner.next ().toLowerCase ();
                    for (Casa c : l) {
                        if ((d.equals (c.getFornecedor().Stringfornecedor (c.getFornecedor ()).toLowerCase ())))
                            casas.add(c.clone());
                        else Menu.erros(7);
                    }
                    int i = -1;
                    while (i < 0 || i > casas.size ()) {
                        i = Menu.MenuListaCasas (casas);
                    }
                }
                case 4 -> {
                    System.out.println ("Insira o x: ");
                    int x = scanner.nextInt ();

                    Estado.ordenaListConsumo(l);

                    int i = -1;
                    while (i < 0 || i > x) {
                        i = Menu.MenuListaX (l, x);
                    }
                    if (i == 0) break;
                    int a = Menu.MenuCasaInfo (i- 1, l);
                    if (a == 0) break;
                }
                case 5 ->{
                    
                    int edp=0,jomar = 0,endesa = 0, max =0;
                    String forn = "";
                    
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");

                    System.out.println ("Insira a data inicial no formato dd/MM/yyyy:");
                    String s1 = scanner.next();

                    System.out.println ("Insira a data final no formato dd/MM/yyyy:");
                    String s2 = scanner.next();

                    while (!(Estado.isDateValid (s1)) && !(Estado.isDateValid (s2))) {
                        System.out.println ("Insira a data inicial no formato dd/MM/yyyy:");
                        s1 = scanner.next();
                        System.out.println ("Insira a data final no formato dd/MM/yyyy:");
                        s2 = scanner.next();
                    }
                    LocalDate dBefore = LocalDate.parse(s1, dateTimeFormatter);
                    LocalDate dAfter = LocalDate.parse(s2, dateTimeFormatter);
                    for (Casa c : l){
                        List<Faturas> faturas = c.getFatura();
                        for (Faturas fatura : faturas){
                            LocalDate datai = LocalDate.parse (fatura.getDataInicial (), dateTimeFormatter);
                            LocalDate dataf = LocalDate.parse (fatura.getDataFinal(), dateTimeFormatter);
                            if ((datai.isAfter(dBefore) || datai.isEqual (dBefore)) && (dataf.isBefore(dAfter) || dataf.isEqual (dAfter)) && dBefore.isBefore (dAfter)) {
                                if (c.getFornecedor() instanceof FornecJomar) {
                                    jomar+=fatura.getFatura();
                                    if (jomar > max) {
                                        max= jomar;
                                        forn = "Jomar";
                                    }
                                }
                                else if (c.getFornecedor() instanceof FornecEDP) {
                                    edp+=fatura.getFatura();
                                    if ( edp > max) {
                                        max= jomar;
                                        forn = "EDP";
                                    }
                                }
                                else if (c.getFornecedor() instanceof FornecEndesa) {
                                    endesa+=fatura.getFatura();
                                    if ( endesa > max) {
                                        max= jomar;
                                        forn = "Endesa";
                                    }
                                }
                            }
                        }
                    }
                    String sb = "\u001B[1m\u001B[36m_________________________________________\u001B[0m\s\n\n" +
                            "\u001B[1m   FORNECEDOR COM MAIOR VOLUME DE FATURAÇÃO \u001B[0m\n\n" +
                            "Fornecedor= " + forn +
                            "\nFaturação= " + max  +
                            "\n\u001B[1m\u001B[36m_________________________________________\u001B[0m\s\n\n"+
                            "Selecione 0 para voltar atrás.\s";
                    System.out.println(sb);
                    int i = scanner.nextInt ();
                    while (i != 0) {
                        i = scanner.nextInt ();
                    }
                }
                case 0 -> {
                    exit = true;
                    Menu.clearWindow();
                    //Thread.sleep(2000);
                }
            }
        }
    }
}
