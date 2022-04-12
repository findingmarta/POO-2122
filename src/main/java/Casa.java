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

    public Casa(Casa umaCasa) {
        this.divisoes = umaCasa.getDivisoes();
        this.proprietario = umaCasa.getProprietario();
        this.NIF = umaCasa.getNIF();
    }

    /**
     * Setters e Getters
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
     * Metodos
     */
   /* public boolean existsDevice(List<SmartDevice> devices, String id) {
        return divisoes..stream().anyMatch(c -> c.equals(id));  //implementar o equals na classe smartDevice
    }

    public void addDevice(SmartDevice s) {

    }

    public SmartDevice getDevice(String s) {
        return null;
    }
*/
    public void setDeviceOn(String divisao, String id) {
        this.divisoes.get(divisao).get(this.divisoes.get(divisao).indexOf(id)).turnOn();
    }

    public void setDeviceOff(String divisao, String id) {
        this.divisoes.get(divisao).get(this.divisoes.get(divisao).indexOf(id)).turnOff();
    }

    public void setDivisonOn(String divisao) {
        this.divisoes.get(divisao).get(0).turnAllOn(this.divisoes.get(divisao));
    }

    public void setDivisonOff(String divisao) {
        this.divisoes.get(divisao).get(0).turnAllOff(this.divisoes.get(divisao));
    }

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
    /*
    @Override
    public int hashCode() {
        return Objects.hash(divisoes, devices, proprietario, NIF);
    }*/

    // falta ter em conta as outras variaveis
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa Casa = (Casa) o;
        return Objects.equals(divisoes, Casa.divisoes);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("Casa {\n");
        //sb.append("Dispositivos: ").append(devices).append('\n');
        sb.append("Divisoes: ").append(divisoes).append('\n');
        sb.append("Proprietario: ").append(proprietario).append('\n');
        sb.append("NIF: ").append(NIF).append('\n');
        sb.append("\n}");
        return sb.toString();
    }

    public Casa clone() {
        return new Casa(this);
    }
}
