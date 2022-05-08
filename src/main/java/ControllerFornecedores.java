import java.util.List;
import java.util.Scanner;

public class ControllerFornecedores {
    public static void run(Estado estado) {
        boolean exit = false;
        while (!exit) {
            Menu.clearWindow ();
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.menuFornecedores ();
            }
            Scanner scanner = new Scanner (System.in);
            switch (opcao) {
                case 1 -> {
                   Menu.clearWindow ();
                    int j = 1;
                    List<Fornecedores> fornecedores = estado.getFornecedores ();
                    for (Fornecedores forn : fornecedores) {
                        StringBuilder sb = new StringBuilder ("\n\u001B[1m \u001B[36m___________\u001B[0m");
                        sb.append ("FORNECEDOR ").append (j).append ("\u001B[1m \u001B[36m___________\u001B[0m\n");
                        sb.append ("\nFornecedor: ").append (forn.Stringfornecedor (forn)).append (forn);
                        System.out.println (sb);
                        j++;
                    }

                    System.out.println ("\nSelecione 0 para voltar atrás: ");
                    int i = scanner.nextInt ();
                    while (i != 0) {
                        i = scanner.nextInt ();
                    }
                }
                    case 0 -> {
                        Menu.clearWindow ();
                        exit=true;
                    }
            }
        }
    }
}
