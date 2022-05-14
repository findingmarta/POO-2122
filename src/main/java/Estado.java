import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Estado implements Serializable {
    private List<Casa> casas;
    private List<Fornecedores> fornecedores;
    private String data;

    //private TreeMap<Double, Integer> faturas;

    public Estado() {
        this.casas = new ArrayList<>();
        this.fornecedores = new ArrayList<>(3);
        this.data= "01/01/2018";
    }

    public Estado(List<Casa> nCasas, List<Fornecedores> nFornecedores) {
        this.casas = new ArrayList<>();
        for (Casa c : nCasas){
            this.casas.add(c.clone());
        }
        this.fornecedores = new ArrayList<>(3);
        for (Fornecedores f : nFornecedores){
            this.fornecedores.add(f.clone());
        }
        this.data= "01/01/2018";
    }

    public Estado(Estado umEstado) {
        this.casas = umEstado.getCasas();
        this.fornecedores = umEstado.getFornecedores();
        this.data= umEstado.getData();
    }

    public List<Casa> getCasas(){
        List<Casa> nCasas = new ArrayList<>();
        for(Casa c : this.casas){
            nCasas.add(c.clone());
        }
        return nCasas;
    }

    public void setCasas(List<Casa> nCasas){
        this.casas = new ArrayList<>();
        if(nCasas!=null){
            for (Casa c : nCasas) casas.add(c.clone());
        }
        //assert nCasas != null;
    }

    public List<Fornecedores> getFornecedores(){
        List<Fornecedores> nFornecedores = new ArrayList<>();
        for(Fornecedores f : this.fornecedores){
            nFornecedores.add(f.clone());
        }
        return nFornecedores;
    }

    public void setFornecedores(List<Fornecedores> nFornecedores){
        this.fornecedores = new ArrayList<>();
        if(nFornecedores!=null) {
            for (Fornecedores f : nFornecedores) {
                fornecedores.add(f.clone());
            }
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Metodo equals, toString e clone
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return  Objects.equals(this.casas, estado.casas) &&
                Objects.equals(this.fornecedores, estado.fornecedores);
    }

    @Override
    public String toString() {
        return "Estado " +
                casas +
                fornecedores;
    }

    @Override
    public Estado clone() {
        return new Estado(this);
    }

    /*

    public List<Faturas> getFaturas(){
        List<Faturas> faturas = new ArrayList<>();
        for ( Casa c : this.casas){
            faturas.add(c.getFatura());
        }
        return faturas;
    }*/

    /**
     * Metodos
     */
    public void addCasa (Casa c){
        this.casas.add(c.clone());
    }

    public void updateCasas (Fornecedores f){
        this.casas.stream().filter(casa -> casa.getFornecedor().equals(f)).forEach(casa -> casa.setFornecedor(f));
    }

    public void updateCasa (Casa c, int index){
        Casa casa = this.casas.get(index);
        casa.setProprietario(c.getProprietario());
        casa.setNIF(c.getNIF());
        casa.setDevices(c.getDevices());
        casa.setDivisoes(c.getDivisoes());
        casa.setFornecedor(c.getFornecedor());
        casa.setFatura(c.getFatura());
        casa.setCustoInstalacao (c.getCustoInstalacao ());
    }

    public void updateFornecedor (Fornecedores f){
        int index = this.fornecedores.indexOf(f);
        Fornecedores forn = this.fornecedores.get(index);
        forn.setVolumeFaturacao(f.getVolumeFaturacao());
        forn.setFormula(f.getFormula());
    }

    public void loadEstado(String file) {
        this.casas.clear();
        this.fornecedores.clear();
        List<String> linhas = lerFicheiro(file);
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
                    //if (divisao == null) Menu.erros(15);
                    assert c != null;
                    assert divisao != null;
                    sb.setOn(false);
                    c.addSmartDevice(sb);
                    c.addToRoom(divisao,sb.getID());
                }
                case "SmartSpeaker" -> {
                    SmartSpeaker ss = parseSS(linhaPartida[1]);
                    //if (divisao == null) Menu.erros(15);
                    assert c != null;
                    assert divisao != null;
                    ss.setOn(false);
                    c.addSmartDevice(ss);
                    c.addToRoom(divisao,ss.getID());
                }
                case "SmartCamera" -> {
                    SmartCamera sc = parseSC(linhaPartida[1]);
                    //if (divisao == null) Menu.erros(15);
                    assert c != null;
                    assert divisao != null;
                    sc.setOn(false);
                    c.addSmartDevice(sc);
                    c.addToRoom(divisao,sc.getID());
                }
                default -> Menu.erros(2);
            }
        }
    }

    private Casa parseC(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        String proprietario = dados[0];
        String NIF = dados[1];
        Fornecedores fornecedor = null;
        switch (dados[2]) {
            case "EDP" -> fornecedor = new FornecEDP();
            case "Endesa" -> fornecedor = new FornecEndesa();
            case "Jomar" -> fornecedor = new FornecJomar();
            default -> Menu.erros(15);
        }
        fornecedor = this.getFornecedores().get(this.getFornecedores().indexOf(fornecedor));
        return new Casa(proprietario,NIF,fornecedor);
    }

    private static Fornecedores parseF(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        Fornecedores fornec = null;
        switch (dados[0]) {
            case "EDP" -> fornec = new FornecEDP();
            case "Endesa" -> fornec = new FornecEndesa();
            case "Jomar" -> fornec = new FornecJomar();
            default -> Menu.erros(15);
        }
        assert fornec != null;
        fornec.setFormula(dados[1]);
        return fornec;
    }

    private static SmartBulb parseSB(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartBulb sb = new SmartBulb();
        sb.setID(dados[0]);
        switch (dados[1]) {
            case "Cold" -> sb.setTone(0);
            case "Warm" -> sb.setTone(2);
            case "Neutral" -> sb.setTone(1);
            default -> Menu.erros(8);
        }
        sb.setDimensao(Double.parseDouble(dados[2]));
        return sb;
    }

    private static SmartSpeaker parseSS(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartSpeaker ss = new SmartSpeaker();
        ss.setID(dados[0]);
        ss.setVolume(Integer.parseInt(dados[1]));
        ss.setChannel(dados[2]);
        ss.setMarca(dados[3]);
        return ss;
    }

    private static SmartCamera parseSC(String linhaPartida){
        String[] dados = linhaPartida.split(",");
        SmartCamera sc = new SmartCamera();
        sc.setID(dados[0]);
        sc.setResolution(Double.parseDouble(dados[1]));
        sc.setSize(Double.parseDouble(dados[2]));
        return sc;
    }

    public static List<String> lerFicheiro(String file) {
        List<String> lines = new ArrayList<>();
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
        this.data = e.data;
        return e;
    }

    public static void ordenaListConsumo(List<Casa> casas){
        casas.sort(new Casa.ComparatorConsumo());
    }

    public Casa ordenaListGasto(List<Casa> casas){
        casas.sort(new Casa.ComparatorGasto());
        //casas.sort(new Casa.ComparatorConsumo());
        return casas.get(0).clone();
    }

    public Fornecedores ordenaListFornecedores(List<Fornecedores> forn){
        forn.sort(new Fornecedores.fornecedoresComparator());
        int index = forn.size()-1;
        return forn.get(index).clone();
    }
}