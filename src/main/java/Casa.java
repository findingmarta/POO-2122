import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A CasaInteligente faz a gestao dos SmartDevices que existem e das
 * divisoes que existem na casa.
 */
public class Casa {
    private Map <String, List<SmartDevice>> divisoes;
    private String proprietario;
    private String NIF;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public Casa() {
        this.divisoes = new HashMap<>();
        this.proprietario = "";
        this.NIF = "";
    }

    public Casa(Map<String, List<SmartDevice>> divisoes, String proprietario, String NIF) {
        this.setDivisoes(divisoes);
        this.setProprietario(proprietario);
        this.setNIF(NIF);
    }

    public Casa (String proprietario, String NIF) {
        this.divisoes = new HashMap<>();
        this.setProprietario(proprietario);
        this.setNIF(NIF);
    }

    public Casa(Casa umaCasa) {
        this.divisoes = umaCasa.getDivisoes();
        this.proprietario = umaCasa.getProprietario();
        this.NIF = umaCasa.getNIF();
    }

    /**
     * Getters e Setters
     */
    public Map<String, List<SmartDevice>> getDivisoes (){
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));     //FALTA CLONE
    }

    public void setDivisoes (Map<String, List<SmartDevice>> divisoes){
        this.divisoes = divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); //FALTA CLONE
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
     * Metodo toString, equals e clone
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n\u001B[36m Casa { \u001B[0m \n\n");
        //sb.append("Dispositivos: ").append(devices).append('\n');
        Set<String> setOfKeys = divisoes.keySet();
        for (String key : setOfKeys) {
            sb.append("\u001B[1m").append(key).append("\u001B[0m -> Dispositivos: ").append(divisoes.get(key)).append("\n");
        }

      //  for (String divisao: divisoes) {
       //     sb.append(divisao).append(": ").append(divisoes.get(divisao));
       // }
        //sb.append("Divisoes: \n").append(divisoes).append('\n');
        sb.append("\u001B[1m Proprietario: \u001B[0m").append(proprietario).append('\n');
        sb.append("\u001B[1m NIF: \u001B[0m").append(NIF).append('\n');
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa Casa = (Casa) o;
        return Objects.equals(this.divisoes, Casa.divisoes) && Objects.equals(this.proprietario, Casa.getProprietario()) &&
                Objects.equals(this.NIF, Casa.getNIF());
    }

    public Casa clone() {
        return new Casa(this);
    }

    /**
     * Metodos
     */
    public void setDeviceOn( String divisao, SmartDevice sd) {
        this.divisoes.get(divisao).get(this.divisoes.get(divisao).indexOf(sd)).setOn(true);
    }

    public void setDeviceOff(String divisao, SmartDevice sd) {
        this.divisoes.get(divisao).get(this.divisoes.get(divisao).indexOf(sd)).setOn(false);
    }

    public void setDivisonOn(String divisao) {
        this.divisoes.get(divisao).get(0).turnAllOn(this.divisoes.get(divisao));
    }

    public void setDivisonOff(String divisao) {
        this.divisoes.get(divisao).get(0).turnAllOff(this.divisoes.get(divisao));
    }

    public boolean hasRoom(String divisao) {
        return this.divisoes.keySet().stream().anyMatch(d -> d.equals(divisao));
    }

    public void addRoom(String divisao, List<SmartDevice> devices) {
       if(!hasRoom(divisao)) this.divisoes.put(divisao, devices);
    }

    public boolean roomHasDevice (String divisao, String id) {
       return this.divisoes.get(divisao).stream().anyMatch(sd -> sd.getID().equals(id));
    }

    public void addToRoom (String divisao, SmartDevice sd) {
        if(!roomHasDevice(divisao, sd.getID())) this.divisoes.get(divisao).add(sd);
    }

    public SmartDevice getDevice(String divisao, String id) {
        if(roomHasDevice(divisao, id)) return this.divisoes.get(divisao).stream().filter(sd -> id.equals(sd.getID())).findAny().orElse(null);
        return null;
    }

    /*
    @Override
    public int hashCode() {
        return Objects.hash(divisoes, devices, proprietario, NIF);
    }*/
}
