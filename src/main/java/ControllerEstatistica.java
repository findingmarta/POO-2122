import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControllerEstatistica {
    public static void run(Estado estado) {
        Menu.clearWindow ();
        boolean exit = false;
        while (!exit) {
            List<Casa> l = estado.getCasas ();
            Scanner scanner = new Scanner (System.in);
            int opcao = -1;
            while (opcao < 0 || opcao > 5) {
                opcao = Menu.MenuEstatistica ();
            }
            switch (opcao) {
                case 1 -> {
                    Estado.ordenaListGasto(l);
                    System.out.println (l.get (l.size ()-1));
                    int i = scanner.nextInt ();
                    while (i != 0) {
                        i = scanner.nextInt ();
                    }
                }
                case 2 -> {
                    List<Fornecedores> f = estado.getFornecedores ();
                    Estado.ordenaListFornecedores (f);
                    //Fornecedores forn = (f.get ((f.size ()) - 1));
                    for (Fornecedores forn : f) {
                        String Stringforn = forn.Stringfornecedor (forn);
                        System.out.println ("Fornecedor: " + Stringforn + forn.toString ());
                        int i = scanner.nextInt ();
                    }
                }
                case 3 -> {
                    List<Casa> casas = new ArrayList<> ();
                    System.out.println ("Insira o fornecedor: ");
                    String d = scanner.next ().toLowerCase ();
                    for (Casa c : l) {
                        if ((d.equals (c.getFornecedor().Stringfornecedor (c.getFornecedor ()).toLowerCase ()))) {
                            casas.add(c.clone());
                        }
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
