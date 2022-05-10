import java.time.LocalDate;
import java.util.List;

public class Automatizacao {
// "src/main/java/automatizacao.txt"

    public void parseFile(String file, Estado estado) {
        List<String> linhas = Estado.lerFicheiro(file);
        List<Casa> casas = estado.getCasas();
        List<Fornecedores> fornecedores = estado.getFornecedores();


        for (String linha : linhas) {
            String[] linhaPartida = linha.split(",");
            LocalDate dataFutura = LocalDate.parse(linhaPartida[0]);

            switch (linhaPartida[1]) {
                case "Casa" -> {
                    int indice = Integer.parseInt(linhaPartida[2]);
                    Casa casa = casas.get(indice);
                    parseComandoC(linhaPartida[3], linhaPartida[4], casa);
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
                    parseComandoF(linhaPartida[3], linhaPartida[4], fornecedor);
                }
                default -> Menu.erros(15);
            }
        }
    }

    private static void parseComandoC(String comando, String objeto, Casa casa){
        switch (comando) {
            case "setOn" -> casa.getDevice(objeto).setOn(true);
            case "setOff" -> casa.getDevice(objeto).setOn(false);
            case "setDivisionOn" -> casa.setDivisonOn(objeto);
            case "setDivisionOff" -> casa.setDivisonOff(objeto);
            case "mudarFornecedor" -> {
                switch (objeto) {
                    case "EDP" -> casa.setFornecedor(new FornecEDP());
                    case "Endesa" -> casa.setFornecedor(new FornecEndesa());
                    case "Jomar" -> casa.setFornecedor(new FornecJomar());
                    default -> Menu.erros(7);
                }
            }
            default -> Menu.erros(15);
        }
    }

    private static void parseComandoF(String comando, String objeto, Fornecedores fornecedor){
        switch (comando){
            case "alteraValorDesconto" -> System.out.println("Do something!");
            case "alteraFormula" -> System.out.println("Do something again!");
            default -> Menu.erros(15);
        }
    }
}
