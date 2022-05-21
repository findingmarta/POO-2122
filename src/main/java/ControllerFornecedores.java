import java.util.List;
import java.util.Scanner;

public class ControllerFornecedores {
    public static void run(Estado estado) throws InterruptedException {
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
                    List<Fornecedores> fornecedores = estado.getFornecedores();
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
                case 2 ->{
                    List<Fornecedores> fornecedores = estado.getFornecedores();
                    Fornecedores forn = null;

                    System.out.println("Insira o nome do fornecedor: ");
                    Scanner scan = new Scanner(System.in);
                    String nomeF = scan.next();
                    System.out.println("Insira a formula: ");
                    String formula = scan.next();

                    if(formula.equals("formula1") || formula.equals("formula2") || formula.equals("formula3")){
                        switch (nomeF) {
                            case "EDP" -> forn = new FornecEDP();
                            case "Endesa" -> forn = new FornecEndesa();
                            case "Jomar" -> forn = new FornecJomar();
                            default -> {
                                Menu.erros(7);
                                Thread.sleep(3000);
                            }
                        }
                    }
                    else {
                        Menu.erros(21);
                        Thread.sleep(3000);
                    }
                    if(forn!=null){
                    forn = fornecedores.get(fornecedores.indexOf(forn));
                    forn.setFormula(formula);
                    estado.updateFornecedor(forn);
                    estado.updateCasas(forn);
                    }
                }
                case 3 -> {
                    Menu.clearWindow ();

                    String sb = """
                            \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                            \u001B[1m FÓRMULA 1 \u001B[0m -> 13 * consumo
                            \u001B[1m FÓRMULA 2 \u001B[0m -> consumo
                            \u001B[1m FÓRMULA 3 \u001B[0m -> 0.5 * consumo
                                            
                            \u001B[1m \u001B[36m_________________________________________________________\u001B[0m\s

                             \s""";

                    System.out.println(sb);

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

