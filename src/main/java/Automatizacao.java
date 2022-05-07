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
                    parseComandoF(linhaPartida[3], linhaPartida[4], nomeF);
                }
                default -> System.out.println("Linha inválida!");
            }
        }
    }

    private static void parseComandoC(String comando, String objeto, Casa casa){
        switch (comando) {
            case "setOn" -> casa.getDevice(objeto).setOn(true);
            case "setOff" -> casa.getDevice(objeto).setOn(false);
            case "setDivisionOn", "setDivisionOff" -> casa.setDivisonOn(objeto);
            default -> System.out.println("Linha inválida!");
        }
    }

    private static void parseComandoF(String comando, String objeto, String nomeF){
        if ("alterarValorDesconto".equals(comando)) {
            System.out.println("AAAA");
        } else {
            System.out.println("Linha inválida!");
        }
    }
}
