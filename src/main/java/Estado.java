import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Estado implements Serializable {
    private List<Casa> casas;
    private List<Fornecedores> fornecedores;

    public Estado() {
        this.casas = new ArrayList<>();
        this.fornecedores = new ArrayList<>();
    }

    public Estado(List<Casa> casas, List<Fornecedores> fornecedores) {
        this.casas = casas;
        this.fornecedores = fornecedores;
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
    public void loadEstado() {
        List<String> linhas = lerFicheiro();
        Fornecedores f;
        Casa c = null;
        String divisao = null;
        for (String linha : linhas) {
            String[] linhaPartida = linha.split(":");
            switch (linhaPartida[0]) {
                case "Casa" -> {
                    c = parseC(linhaPartida[1]);
                    this.casas.add(c);
                }
                case "Fornecedor" -> {
                    f = parseF(linhaPartida[1]);
                    this.fornecedores.add(f);
                }
                case "Divisao" -> {
                    divisao = linhaPartida[1];
                    assert c != null;
                    c.addRoom(divisao);
                }
                case "SmartBulb" -> {
                    SmartBulb sb = parseSB(linhaPartida[1]);
                    if (divisao == null) System.out.println("Linha inválida.");
                    assert c != null;
                    sb.setOn(false);
                    c.addSmartDevice(sb);
                    c.addToRoom(divisao,sb.getID());
                }
                case "SmartSpeaker" -> {
                    SmartSpeaker ss = parseSS(linhaPartida[1]);
                    if (divisao == null) System.out.println("Linha inválida.");
                    assert c != null;
                    ss.setOn(false);
                    c.addSmartDevice(ss);
                    c.addToRoom(divisao,ss.getID());
                }
                case "SmartCamera" -> {
                    SmartCamera sc = parseSC(linhaPartida[1]);
                    if (divisao == null) System.out.println("Linha inválida.");
                    assert c != null;
                    sc.setOn(false);
                    c.addSmartDevice(sc);
                    c.addToRoom(divisao,sc.getID());
                }
                default -> Menu.erros(2);
            }
        }
    }

    public static Casa parseC(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        String proprietario = dados[0];
        String NIF = dados[1];
        Fornecedores fornecedor = parseF(dados[2]);
        return new Casa(proprietario,NIF,fornecedor);
    }

    public static Fornecedores parseF(String linhaPartida){
        //String[] dados = linhaPartida.split(",");
        Fornecedores fornec1 = null;
        switch (linhaPartida) {
            case "EDP" -> fornec1 = new FornecEDP();
            case "Endesa" -> fornec1 = new FornecEndesa();
            case "Jomar" -> fornec1 = new FornecJomar();
            default -> Menu.erros(2);
        }
        return fornec1;
    }

    public static SmartBulb parseSB(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartBulb sb = new SmartBulb();
        switch (dados[0]) {
            case "Cold" -> sb.setTone(0);
            case "Warm" -> sb.setTone(2);
            case "Neutral" -> sb.setTone(1);
            default -> System.out.println("Linha invalida.");
        }
        sb.setDimensao(Double.parseDouble(dados[1]));
        return sb;
    }

    public static SmartSpeaker parseSS(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartSpeaker ss = new SmartSpeaker();
        ss.setVolume(Integer.parseInt(dados[0]));
        ss.setChannel(dados[1]);
        ss.setMarca(dados[2]);
        return ss;
    }

    public static SmartCamera parseSC(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartCamera sc = new SmartCamera();
        sc.setResolution(Double.parseDouble(dados[0]));
        sc.setSize(Double.parseDouble(dados[1]));
        return sc;
    }

    public static List<String> lerFicheiro() {
        List<String> lines = new ArrayList<>();
        String file = "src/main/java/logs.txt";
        try {
            lines = Files.readAllLines(Paths.get(file));
        } catch (IOException exc) {
            Menu.erros(3);
        }
        return lines;
    }

    public void saveEstado(String file) throws IOException {
        FileOutputStream fos =  new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public Estado loadEstadoObj(String file) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Estado e = (Estado) ois.readObject();
        ois.close();
        this.casas = e.casas;
        this.fornecedores = e.fornecedores;
        return e;
    }
}
