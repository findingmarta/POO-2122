import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerSimulacao {
    public static void run(Estado estado) {
        boolean exit = false;
        while (!exit) {
            Menu.clearWindow ();
            List<Casa> l = estado.getCasas ();
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.menuSimulacao ();
            }
            Scanner scanner = new Scanner (System.in);
            switch (opcao) {
                case 1 -> {
                    double diff = 0;
                    System.out.println ("Insira a data no formato dd/MM/yyyy:");
                    String s2 = scanner.next();
                    String dateFormat = "dd/MM/yyyy";
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
                    while (!(Menu.isDateValid (s2))) {
                        System.out.println ("Insira a data no formato dd/MM/yyyy:");
                        s2 = scanner.next ();
                    }
                    String s1= estado.getData();
                    System.out.println (s2);
                    System.out.println (s1);
                    LocalDate dBefore = LocalDate.parse (s1, dateTimeFormatter);
                    LocalDate dAfter = LocalDate.parse (s2, dateTimeFormatter);
                    if (dAfter.isAfter (dBefore)) {
                        diff = ChronoUnit.DAYS.between (dBefore, dAfter);
                        for (Casa c : estado.getCasas ()) {
                            double precoFinal = c.consumoTotal () * diff;
                            Faturas fatura = new Faturas (precoFinal,s1, s2, c.consumoTotal ());
                            c.getFatura().add(fatura);
                            Fornecedores f = c.getFornecedor ();
                            f.aumentaVolumeFaturacao (precoFinal);
                        }
                       // Estado.ordenaListCasa (estado.getCasas ());
                        estado.setData (s2);
                    }
                }
                case 2 -> {
                    int escolha= -1,i=1;
                    while (escolha< 0 ||escolha> l.size()) {
                        escolha= Menu.MenuListaCasas(l);
                    }
                    if (escolha== 0) break;
                    int a = Menu.faturaList(l.get(escolha- 1));
                    System.out.println (a);
                    while( a!=0 && i!= 0) {
                        i = Menu.FaturaInfo(a-1, l.get(escolha- 1).getFatura());
                    }
                }
                case 3-> ControllerEstatistica.run(estado);
                case 0->{
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }
}
