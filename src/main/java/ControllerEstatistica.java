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
                                "Selecione a opção pretendida:\s";
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
                    //if (i == 0) Controller.menuinicial (estado);
                    // int a = Menu.FaturaInfo(opcao-1, casas);
                    // if (a==0) Controller.menuEstatistica(estado);
                }
                case 4 -> {
                    Estado.ordenaListConsumo(l);
                    int i = -1;
                    while (i < 0 || i > l.size ()) {
                        i = Menu.MenuListaCasas (l);
                    }
                    if (i == 0) break;
                    int a = Menu.MenuCasaInfo (opcao - 1, l);
                    if (a == 0) break;
                }
                case 0 -> {
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }
}
