import java.time.format.DateTimeFormatter;
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
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");

                    while (!(Estado.isDateValid (s2))) {
                        System.out.println ("Insira a data no formato dd/MM/yyyy:");
                        s2 = scanner.next ();
                    }

                    String s1= estado.getData();
                    LocalDate dBefore = LocalDate.parse(s1, dateTimeFormatter);
                    LocalDate dAfter = LocalDate.parse(s2, dateTimeFormatter);

                    if (dAfter.isAfter(dBefore)) {
                        diff = ChronoUnit.DAYS.between (dBefore, dAfter);
                        for (Casa c : estado.getCasas()) {
                            String formula = c.getFornecedor().getFormula();
                            double precoFinal = c.consumoTotal(formula) * diff;
                            double consumoTotal = c.consumoTotal2();

                            Faturas fatura = new Faturas (precoFinal + c.getCustoInstalacao (),s1, s2, consumoTotal );
                            List<Faturas> faturasL = c.getFatura();
                            faturasL.add(fatura.clone());
                            c.setFatura(faturasL);

                            Fornecedores f = estado.getFornecedores().get(estado.getFornecedores().indexOf(c.getFornecedor()));
                            f.setVolumeFaturacao(f.getVolumeFaturacao() + precoFinal + c.getCustoInstalacao());
                            c.setCustoInstalacao(0);

                            estado.updateCasa(c, estado.getCasas().indexOf(c));
                            estado.updateFornecedor(f);
                            estado.updateCasas(f);
                        }
                        estado.setData(s2);
                    }
                    else {
                        Menu.erros(24);
                        System.out.println("Data atual: "+estado.getData());
                        Thread.sleep(3000);
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
                case 3-> {
                    if(estado.getCasas().stream().noneMatch(casa -> casa.getFatura().isEmpty()))
                        ControllerEstatistica.run(estado);
                    else {
                        Menu.erros(22);
                        Thread.sleep(2000);
                    }
                }
                case 0->{
                    exit = true;
                    Menu.clearWindow();
                }
            }
        }
    }
}