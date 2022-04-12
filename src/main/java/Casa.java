import java.util.*;
import java.util.stream.Collectors;


/**
 * A CasaInteligente faz a gestao dos SmartDevices que existem e das
 * divisoes que existem na casa.
 */
public class Casa {
    private  List<String> divisoes;
    private Map<String, SmartDevice> devices;
    private String proprietario;
    private String NIF;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public Casa() {
        this.divisoes = new ArrayList<>(); //ou null??
        this.devices = new HashMap<>();
        this.proprietario = "";
        this.NIF = "";
    }

    public Casa(List<String> divisoes, Map<String, SmartDevice> devices, String proprietario, String NIF) {
        this.setDivisoes(divisoes);
        this.setDevices(devices);
        this.setProprietario(proprietario);
        this.setNIF(NIF);
    }

    public Casa(Casa umaCasa) {
        this.divisoes = umaCasa.getDivisoes();
        this.devices = umaCasa.getDevices();
        this.proprietario = umaCasa.getProprietario();
        this.NIF = umaCasa.getNIF();
    }

    /**
     * Setters e Getters
     */
    public List<String> getDivisoes (){
        return new ArrayList<>(this.divisoes);
    }

    public void setDivisoes (List<String> divisoes){
        this.divisoes = new ArrayList<>(divisoes);
    }

    public Map<String, SmartDevice> getDevices (){
        return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
    }

    public void setDevices (Map<String, SmartDevice> devices){
        this.devices = devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().clone()));
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

    /**
     * Metodos
     */
    public boolean existsDevice(String id) {
        return this.devices.keySet().stream().anyMatch(c -> c.equals(id));  //implementar o equals na classe smartDevice
    }

    public void addDevice(SmartDevice s) {

    }

    public SmartDevice getDevice(String s) {
        return null;
    }

    public void setDeviceOn(String devCode) {
        this.devices.get(devCode).turnOn();
    }

    public void setDeviceOff(String devCode) {
        this.devices.get(devCode).turnOff();
    }

    public boolean belongs2Division(String division){
        return true;
    }
/*
    public void setDivisonOn(String s) {
        this.devices.entrySet().stream().reduce(a -> a.belongs2Division(s)).setDeviceOn();
    }

    public void setDivisonOff(String s) {
        this.devices.entrySet().stream().reduce(a -> a.belongs2Division());
    }*/

    public void addRoom(String s) {

    }

    public boolean hasRoom(String s) {
        return false;
    }

    public void addToRoom (String s1, String s2) {

    }

    public boolean roomHasDevice (String s1, String s2) {
        return false;
    }

    // falta ter em conta as outras variaveis
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa Casa = (Casa) o;
        return Objects.equals(devices, Casa.devices);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Casa {\n");
        sb.append("Dispositivos: ").append(devices).append('\n');
        sb.append("\n}");
        return sb.toString();
    }


    public Casa clone() {
        return new Casa(this);
    }
}
