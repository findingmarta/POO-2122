import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Estado {

    public static void loadEstado(List<Casa> casasList, List<Fornecedores> fornecedoresList) {
        List<String> linhas = lerFicheiro("src/main/java/estado.txt");
        Casa c;
        Fornecedores f = null;
        for (String linha : linhas) {
            String[] linhaPartida = linha.split("-");
            //System.out.println("121213");
            switch (linhaPartida[0]) {
                case "Casa" -> {
                    c = parseC(linhaPartida[1]);
                    casasList.add(c);
                }
                case "Fornecedor" -> {
                    f = parseF(linhaPartida[1]);
                    fornecedoresList.add(f);
                }
                default -> Menu.erros(2);
            }
        }
    }

    public static Casa parseC(String linhaPartida){
        String[] dados = linhaPartida.split(";");
        Map <String, List<SmartDevice>> divisoes = parseDivisoes(dados[0]);
        String proprietario = dados[1];
        String NIF = dados[2];
        return new Casa(divisoes,proprietario,NIF);
    }

    public static Map<String, List<SmartDevice>> parseDivisoes(String linhaPartida) {
        String[] dados = linhaPartida.split(">");
        Map<String, List<SmartDevice>> divisoes = new HashMap<>();
        for (String dado : dados) {
            String[] campos = dado.split("/");
            List<SmartDevice> devices = parseDevices(campos[1]);
            divisoes.put(campos[0], devices);
        }
        return divisoes;
    }

    public static List<SmartDevice> parseDevices(String linhaPartida){
        List<SmartDevice> devices = new ArrayList<>();
        String[] dados = linhaPartida.split("<");
        for (String dado : dados) {
            SmartDevice sd = parseDevice(dado);
            devices.add(sd);
        }
        return devices;
    }

    public static SmartDevice parseDevice(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartDevice sd = null;
        switch (dados[0]) {
            case "SmartBulb" -> {
                sd = new SmartBulb();
                sd.setID(dados[1]);
                sd.setOn(dados[2].equals("true"));
                ((SmartBulb) sd).setTone(Integer.parseInt(dados[3]));
                ((SmartBulb) sd).setDimensao(Double.parseDouble(dados[4]));
            }
            case "SmartSpeaker" -> {
                sd = new SmartSpeaker();
                sd.setID(dados[1]);
                sd.setOn(dados[2].equals("true"));
                ((SmartSpeaker) sd).setVolume(Integer.parseInt(dados[3]));
                ((SmartSpeaker) sd).setChannel(dados[4]);
                ((SmartSpeaker) sd).setMarca(dados[5]);
            }
            case "SmartCamera" -> {
                sd = new SmartCamera();
                sd.setID(dados[1]);
                sd.setOn(dados[2].equals("true"));
                ((SmartCamera) sd).setResolution(Double.parseDouble(dados[3]));
                ((SmartCamera) sd).setSize(Double.parseDouble(dados[4]));
            }
            default -> Menu.erros(2);
        }
        return sd;
    }

    public static Fornecedores parseF(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        Fornecedores fornec1 = null;
        switch (dados[0]) {
            case "EDP" -> {
                fornec1 = new FornecEDP();
            }
            case "Endesa" -> {
                fornec1 = new FornecEndesa();
            }
            case "Jomar" -> {
                fornec1 = new FornecJomar();
            }
        default -> Menu.erros(2);
        }
        return fornec1;
    }



    public static List<String> lerFicheiro(String file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (IOException exc) {
            Menu.erros(3);
        }
        lines.forEach(System.out::println);
        return lines;
    }
}
