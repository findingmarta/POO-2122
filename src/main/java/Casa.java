import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

/**
 * A CasaInteligente faz a gestao dos SmartDevices que existem e das
 * divisoes que existem na casa.
 */
public class Casa implements Serializable {
    //private List<SmartDevice> devices;
   // private String morada;
    private Map<String, SmartDevice> devices;      // identificador -> SmartDevice
    private Map<String, HashSet<String>> divisoes; // Espaço -> Lista codigo dos devices
    private String proprietario;
    private String NIF;
    private Fornecedores fornecedor;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public Casa() {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.proprietario = "";
        this.NIF = "";
    }

    public Casa(Map<String, SmartDevice> devices, Map<String, HashSet<String>> divisoes, String proprietario, String NIF) {
        this.setDevices(devices);
        this.setDivisoes(divisoes);
        this.setProprietario(proprietario);
        this.setNIF(NIF);
        this.setFornecedor(fornecedor);
    }

    public Casa (String proprietario, String NIF) {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setProprietario(proprietario);
        this.setNIF(NIF);
        this.setFornecedor(fornecedor);
    }

    public Casa (String proprietario, String NIF, Fornecedores fornecedor) {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setProprietario(proprietario);
        this.setNIF(NIF);
        this.setFornecedor(fornecedor);
    }

    public Casa(Casa umaCasa) {
        this.devices = umaCasa.getDevices();
        this.divisoes = umaCasa.getDivisoes();
        this.proprietario = umaCasa.getProprietario();
        this.NIF = umaCasa.getNIF();
        this.fornecedor = umaCasa.getFornecedor();
    }

    /**
     * Getters e Setters
     */
    public Map<String, HashSet<String>> getDivisoes (){
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));     //FALTA CLONE
    }

    public void setDivisoes (Map<String, HashSet<String>> divisoes){
        this.divisoes = divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); //FALTA CLONE
    }

    public Map<String, SmartDevice> getDevices(){
        return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public void setDevices (Map<String, SmartDevice> devices){
        this.devices = devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public String getProprietario (){
        return this.proprietario;
    }

    public void setProprietario (String proprietario){
        this.proprietario = proprietario;
    }

    public String getNIF (){
        return this.NIF;
    }

    public void setNIF (String NIF){
        this.NIF = NIF;
    }

    public Fornecedores getFornecedor(){
        return this.fornecedor;          //CLONEEEE??
    }

    public void setFornecedor(Fornecedores fornecedor){
        this.fornecedor = fornecedor;
    }

    /**
     * Metodo toString, equals e clone
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\u001B[36m Casa { \u001B[0m \n\n");
        //sb.append("Dispositivos: ").append(devices).append('\n');
        Set<String> setOfKeys = divisoes.keySet();
        for (String key : setOfKeys) {
            sb.append("\u001B[1m").append(key).append("\u001B[0m -> Dispositivos: ");
            HashSet<String> set = divisoes.get(key);
            for (String s : set){
                sb.append(getDevice(s));
            }
            sb.append("\n\n");
        }
        sb.append("\u001B[1mProprietario: \u001B[0m").append(proprietario).append('\n');
        sb.append("\u001B[1mNIF: \u001B[0m").append(NIF).append('\n');
        sb.append("\u001B[1mFornecedor: \u001B[0m").append(fornecedor).append('\n');
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa Casa = (Casa) o;
        return Objects.equals(this.divisoes, Casa.divisoes) && Objects.equals(this.proprietario, Casa.getProprietario()) &&
                Objects.equals(this.NIF, Casa.getNIF()) && Objects.equals(this.fornecedor, Casa.getFornecedor());
    }

    public Casa clone() {
        return new Casa(this);
    }

    /**
     * Metodos
     */
    public void setDeviceOn( String sd) {
        this.devices.get(sd).setOn(true);
    }

    public void setDeviceOff(String sd) {
        this.devices.get(sd).setOn(false);
    }

    public void setDivisonOn(String divisao) {
        this.divisoes.get(divisao).forEach(this::setDeviceOn);
    }

    public void setDivisonOff(String divisao) {
        this.divisoes.get(divisao).forEach(this::setDeviceOff);
    }

    public boolean hasRoom(String divisao) {
        return this.divisoes.keySet().stream().anyMatch(d -> d.equals(divisao));
    }

    public boolean roomisEmpty (String divisao) {
        return this.divisoes.get(divisao).isEmpty();
    }

    public boolean roomHasDevice (String divisao, String id) {
       return this.divisoes.get(divisao).stream().anyMatch(sd -> sd.equals(id));
    }

    public void addRoom(String divisao) {
        HashSet<String> id = new HashSet<>();
        if(!hasRoom(divisao)) this.divisoes.put(divisao, id);
    }

    public void addToRoom (String divisao, String id) {
        if(!roomHasDevice(divisao, id)) this.divisoes.get(divisao).add(id);
    }

    public boolean hasDevice(String id){
        return this.devices.keySet().stream().anyMatch(sd-> sd.equals(id));
    }

    public SmartDevice getDevice(String id) {
        if(hasDevice(id)) return this.devices.get(id);
        return null;
    }

    public void addSmartDevice (SmartDevice sd){
        int id = hashCode();
        sd.setID(String.valueOf(id));
        this.devices.put(String.valueOf(id),sd);
    }

    @Override
    public int hashCode() {
        return abs(Objects.hash(devices, divisoes, proprietario, NIF, fornecedor)/1000000);
    }  // VER MELHOR
    /*
    public void addDevices(String divisao, String id, SmartDevice devices) {
        this.divisoes.get(divisao).add(id);
        this.devices.put(id,devices);
    }*/


    public double consumoTotal() {
        Set<String> setOfKeys = devices.keySet();
        Fornecedores forn = getFornecedor();
        double contador = 0;
        for (String key : setOfKeys) {
            SmartDevice s = this.getDevice(key);
            if (s.getOn()) contador += forn.PrecoDiarioPorDispositivo(s);
            else contador += 0;
        }
        return contador;
    }

    /*
    // FALTA METER ISTO NOS TESTES
    public String estadoCasa() {
        StringBuilder str = new StringBuilder("Casa-");
        Set<String> setOfKeys = divisoes.keySet();
        int i=1;
        for (String key : setOfKeys) {
            i++;
            str.append(key).append("/").append(estadoListaSD(divisoes.get(key)));
            if(i==setOfKeys.size())str.append(">");
        }
        return str + ";" + proprietario + ";" + NIF + ";\n";
    }

    public String estadoListaSD(List<SmartDevice> devices) {
        StringBuilder str = new StringBuilder();
        int i=1;
        for(SmartDevice device : devices) {
            str.append(estadoSD(device,i,devices.size()));
            i++;
        }
        return str+"";
    }

    public String estadoSD (SmartDevice device, int i, int size){
        String str = null;
        switch (device.getClass().getSimpleName()) {
            case "SmartBulb" -> {
                SmartBulb sb = (SmartBulb) device;
                str = "SmartBulb" + "," + sb.getID() + "," + sb.getOn() + "," +
                        sb.getTone() + "," + sb.getDimensao();
                if(i!=size) str = str + "<";
            }
            case "SmartSpeaker" -> {
                SmartSpeaker ss = (SmartSpeaker) device;
                str = "SmartSpeaker" + "," + ss.getID() + "," + ss.getOn() + "," +
                        ss.getVolume() + "," + ss.getChannel() + "," + ss.getMarca();
                if(i!=size) str = str + "<";
            }

            case "SmartCamera" ->{
                SmartCamera sc = (SmartCamera) device;
                str = "SmartCamera" + "," + sc.getID() + "," + sc.getOn() + "," +
                        sc.getResolution() + "," + sc.getSize();
                if(i!=size) str = str + "<";
            }
        }
        return str;
    }
    /*


    @Override
    public int hashCode() {
        return Objects.hash(divisoes, devices, proprietario, NIF);
    }*/
}
