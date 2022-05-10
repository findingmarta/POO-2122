import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.*;

public class ControllerSimulacao {
    public static void run(Estado estado) throws InterruptedException {
        boolean exit = false;

        while (!exit) {
            List<Casa> l = estado.getCasas ();
            Menu.clearWindow ();
            int opcao = -1;
            while (opcao < 0 || opcao > 8) {
                opcao = Menu.menuSimulacao ();
            }
            Scanner scanner = new Scanner (System.in);
            switch (opcao) {
                case 1 -> {
                    double diff;
                    System.out.println ("Insira a data no formato dd/MM/yyyy:");
                    String s2 = scanner.next();
                    //String dateFormat = "dd/MM/yyyy";
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
                    while (!(ControllerSimulacao.isDateValid (s2))) {
                        Menu.erros(11);
                        System.out.println ("Insira a data no formato dd/MM/yyyy:");
                        s2 = scanner.next ();
                    }
                    String s1= estado.getData();
                    LocalDate dBefore = LocalDate.parse (s1, dateTimeFormatter);
                    LocalDate dAfter = LocalDate.parse (s2, dateTimeFormatter);
                    if (dAfter.isAfter (dBefore)) {
                        diff = ChronoUnit.DAYS.between (dBefore, dAfter);
                        for (Casa c : estado.getCasas()) {
                            //String formula = c.getFornecedor().getFormula();
                            double precoFinal = c.consumoTotal() * diff;
                            //double precoFinal = c.consumoTotal(formula) * diff;
                            Faturas fatura = new Faturas (precoFinal,s1, s2, c.consumoTotal ());
                            //Faturas fatura = new Faturas (precoFinal,s1, s2, c.consumoTotal (formula));
                            List<Faturas> faturasL = c.getFatura();
                            faturasL.add(fatura.clone());
                            c.setFatura(faturasL);
                            estado.updateCasa(c, estado.getCasas().indexOf(c)); //por causa da fatura

                            Fornecedores f = c.getFornecedor();
                            estado.updateFornecedor(f, precoFinal); //por causa da faturacao
                            //f.aumentaVolumeFaturacao (precoFinal);
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
                    //Thread.sleep(2000);
                }
            }
        }
    }

    public static boolean isDateValid(String strDate) {
        String dateFormat = "dd/MM/yyyy";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
