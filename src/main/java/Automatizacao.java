import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Automatizacao {
    public static void parseFile(String file, Estado estado) throws InterruptedException {
        List<String> linhas = Estado.lerFicheiro(file);
        List<Casa> casas = estado.getCasas();
        List<Fornecedores> fornecedores = estado.getFornecedores();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");

        for (String linha : linhas) {
            String[] linhaPartida = linha.split(",");
            LocalDate dataFutura = LocalDate.parse(linhaPartida[0], dateTimeFormatter);

            if (dataFutura.isAfter(LocalDate.parse(estado.getData(),dateTimeFormatter))) {
                switch (linhaPartida[1]) {
                    case "Casa" -> {
                        int indice = Integer.parseInt(linhaPartida[2]) - 1;
                        Casa casa = casas.get(indice);
                        parseComandoC(linhaPartida[3], linhaPartida[4], casa, fornecedores);
                        estado.updateCasa(casa, indice);
                    }
                    case "Fornecedor" -> {
                        String nomeF = linhaPartida[2];
                        Fornecedores fornecedor = null;
                        switch (nomeF) {
                            case "EDP" -> fornecedor = new FornecEDP();
                            case "Endesa" -> fornecedor = new FornecEndesa();
                            case "Jomar" -> fornecedor = new FornecJomar();
                            default -> Menu.erros(7);
                        }
                        fornecedor = fornecedores.get(fornecedores.indexOf(fornecedor));
                        parseComandoF(linhaPartida[3], linhaPartida[4], fornecedor);
                        estado.updateFornecedor(fornecedor);
                        estado.updateCasas(fornecedor);
                    }
                    default -> Menu.erros(15);
                }
            }
            else {
                Menu.erros(15);
                Menu.erros(24);
                System.out.println("Data atual: "+estado.getData());
                Thread.sleep(3000);
            }
        }
    }

    private static void parseComandoC(String comando, String objeto, Casa casa, List<Fornecedores> fornecedores){
        switch (comando) {
            case "setOn" -> casa.setDeviceOn(objeto);
            case "setOff" -> casa.setDeviceOff(objeto);
            case "setDivisionOn" -> casa.setDivisonOn(objeto);
            case "setDivisionOff" -> casa.setDivisonOff(objeto);
            case "mudarFornecedor" -> {
                switch (objeto) {
                    case "EDP" -> {
                        String formula = fornecedores.get(0).getFormula();
                        casa.setFornecedor(new FornecEDP(formula));
                    }
                    case "Endesa" -> {
                        String formula = fornecedores.get(2).getFormula();
                        casa.setFornecedor(new FornecEndesa(formula));
                    }
                    case "Jomar" -> {
                        String formula = fornecedores.get(1).getFormula();
                        casa.setFornecedor(new FornecJomar(formula));
                    }
                    default -> Menu.erros(7);
                }
            }
            default -> Menu.erros(15);
        }
    }

    private static void parseComandoF(String comando, String objeto, Fornecedores fornecedor){
        switch (comando){
            case "alterarValorDesconto" -> System.out.println("Do something!");
            case "alterarFormula" -> fornecedor.setFormula(objeto);
            default -> Menu.erros(15);
        }
    }
}
