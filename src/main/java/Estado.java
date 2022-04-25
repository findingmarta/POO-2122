import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Estado {
    private List<Casa> casas;
    private List<Fornecedores> fornecedores;
    public Estado() {
        this.casas = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
    }

    public Estado(Estado umEstado) {
        this.casas = umEstado.casas;
        this.fornecedores = umEstado.fornecedores;
    }

    public List<Casa> getCasas(){
        return this.casas;
    }

    public void setCasas(List<Casa> casas){           //CLONE !!!!
        this.casas = casas;
    }

    public List<Fornecedores> getFornecedores(){
        return this.fornecedores;
    }

    public void setFornecedores(List<Fornecedores> fornecedores){
        this.fornecedores = fornecedores;
    }

    /**
     * Metodos
     */
    public static void loadEstado(List<Casa> casasList, List<Fornecedores> fornecedoresList, String filePath) {
        List<String> linhas = lerFicheiro(filePath);
        for (String linha : linhas) {
            String[] linhaPartida = linha.split("-");
            switch (linhaPartida[0]) {
                case "Casa" -> {
                    Casa c = parseC(linhaPartida[1]);
                    casasList.add(c);
                }
                case "Fornecedor" -> {
                    Fornecedores f = parseF(linhaPartida[1]);
                    fornecedoresList.add(f);
                }
                default -> System.out.println("Linha invalida.");
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
            default -> System.out.println("Linha invalida.");
        }
        return sd;
    }

    public static Fornecedores parseF(String linhaPartida){
        return null;
    }

    public static void saveEstado(List<Casa> casasList, List<Fornecedores> fornecedoresList) throws IOException {
        Writer fos =  new FileWriter("src\\main\\java\\EstadoNovo.txt");
        for (Casa casa : casasList) fos.write(casa.estadoCasa());
        for (Fornecedores fornecedor : fornecedoresList) fos.write(fornecedor.toString());
        fos.flush();
        fos.close();
    }

    /*public void loadEstadoObj(String file) throws IOException, ClassNotFoundException {
        Estado e = loadAux(file);
        this.casas = e.casas;
        this.fornecedores = e.fornecedores;
    }

    public Estado loadAux(String file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Estado e = (Estado) ois.readObject();
        ois.close();
        return e;
    }*/

    public static List<String> lerFicheiro(String file) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
        return lines;
    }
}
