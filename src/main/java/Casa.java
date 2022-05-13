import java.util.*;
import java.io.Serializable;
import java.util.stream.Collectors;

/**
 * A CasaInteligente faz a gestao dos SmartDevices que existem e das
 * divisoes que existem na casa.
 */
public class Casa implements Serializable {
    private Map<String, SmartDevice> devices;
    private Map<String, HashSet<String>> divisoes;
    private String proprietario;
    private String NIF;
    private Fornecedores fornecedor;
    private List<Faturas> faturas;

    /**
     * Constructor for objects of class CasaInteligente
     */
    public Casa() {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.proprietario = "";
        this.NIF = "";
        this.fornecedor = null;
        this.faturas= new ArrayList<>();
    }

    public Casa(Map<String, SmartDevice> d, Map<String, HashSet<String>> divisoes, String proprietario, String NIF, Fornecedores fornecedor) {
        this.proprietario = proprietario;
        this.NIF = NIF;
        this.divisoes = divisoes;
        this.devices = new HashMap<>();
        for (SmartDevice sd : d.values()){
            this.devices.put(sd.getID(),sd.clone());
        }
        this.fornecedor = fornecedor.clone();
        this.faturas= new ArrayList<>();
    }

    public Casa (String proprietario, String NIF, Fornecedores fornecedor) {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.faturas= new ArrayList<>();
        this.setNIF(NIF);
        this.setProprietario(proprietario);
        this.setFornecedor(fornecedor);
    }

    public Casa(Casa umaCasa) {
        this.NIF = umaCasa.getNIF();
        this.devices = umaCasa.getDevices();
        this.divisoes = umaCasa.getDivisoes();
        this.fornecedor = umaCasa.getFornecedor();
        this.proprietario = umaCasa.getProprietario();
        this.faturas= umaCasa.getFatura();
    }


    /**
     * Getters e Setters
     */
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
        if (this.fornecedor != null) return this.fornecedor.clone();
        return null;
    }

    public void setFornecedor(Fornecedores umFornecedor){
        if (umFornecedor != null) this.fornecedor = umFornecedor.clone();
        //else this.fornecedor = null;
    }

    public Map<String, HashSet<String>> getDivisoes (){
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setDivisoes (Map<String, HashSet<String>> divisoes){
        this.divisoes = divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, SmartDevice> getDevices(){
        Map<String, SmartDevice> d = new HashMap<>();
        for(String id : this.devices.keySet()){
            d.put(id, this.devices.get(id).clone());
        }
        return d;
        //return this.devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setDevices (Map<String, SmartDevice> d){
        this.devices = new HashMap<>();
        for (Map.Entry<String, SmartDevice> e : d.entrySet()){
            devices.put(e.getKey(), e.getValue().clone());
        }
        //this.devices = devices.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Faturas> getFatura() {
        List<Faturas> fat = new ArrayList<>();
        for (Faturas f : this.faturas){
           fat.add(f.clone());
        }
        return fat;
    }

    public void setFatura(List<Faturas> nFaturas) {
        this.faturas = new ArrayList<>();
        for (Faturas f : nFaturas){
            faturas.add(f.clone());
        }
    }

    /**
     * Metodo toString, equals, hascode e clone
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\u001B[36m Casa { \u001B[0m \n\n");
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
        sb.append("\u001B[1mFornecedor: \u001B[0m").append(fornecedor.Stringfornecedor(fornecedor)).append('\n');
        sb.append("\u001B[1mFormula: \u001B[0m").append(fornecedor.getFormula()).append('\n');
        sb.append("\u001B[1mVolume: \u001B[0m").append(fornecedor.getVolumeFaturacao()).append('\n');
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa Casa = (Casa) o;
        return  Objects.equals(this.devices, Casa.devices) &&
                Objects.equals(this.divisoes, Casa.divisoes) &&
                Objects.equals(this.proprietario, Casa.getProprietario()) &&
                Objects.equals(this.NIF, Casa.getNIF()) &&
                Objects.equals(this.fornecedor, Casa.getFornecedor());
    }

    @Override
    public Casa clone() {
        return new Casa(this);
    }

    /**
     * Metodos
     */
    public void setDeviceOn(String id) {
        if(hasDevice(id)) this.devices.get(id).setOn(true);
    }

    public void setDeviceOff(String id) {
        if(hasDevice(id)) this.devices.get(id).setOn(false);
    }

    public void setDivisonOn(String divisao) {
        if (hasRoom(divisao) && !roomisEmpty(divisao))
            this.divisoes.get(divisao).forEach(this::setDeviceOn);
    }

    public void setDivisonOff(String divisao) {
        if (hasRoom(divisao) && !roomisEmpty(divisao))
            this.divisoes.get(divisao).forEach(this::setDeviceOff);
    }

    public boolean hasRoom(String divisao) {
        if(this.divisoes.isEmpty()) return false;
        return this.divisoes.keySet().stream().anyMatch(d -> d.equals(divisao));
    }

    public boolean roomisEmpty (String divisao) {
        if(this.divisoes.isEmpty()) return true;
        if(!hasRoom(divisao)) return true;
        return this.divisoes.get(divisao).isEmpty();
    }

    public boolean roomHasDevice (String divisao, String id) {
        if(this.divisoes.isEmpty()) return false;
        if(!hasRoom(divisao) || !hasDevice(id)) return false;
        return this.divisoes.get(divisao).stream().anyMatch(sd -> sd.equals(id));
    }

    public void addRoom(String divisao) {
        HashSet<String> ids = new HashSet<>();
        if(!hasRoom(divisao)) this.divisoes.put(divisao, ids);
        else Menu.erros(4);
    }

    public void addToRoom (String divisao, String id) {
        if(this.divisoes.isEmpty()) return;
        if(hasRoom(divisao) && hasDevice(id) && !roomHasDevice(divisao,id))
            this.divisoes.get(divisao).add(id);
      //else Menu.erros(5);
    }

    public boolean hasDevice(String id){
        if(this.devices.isEmpty()) return false;
        return this.devices.keySet().stream().anyMatch(sd-> sd.equals(id));
    }

    public SmartDevice getDevice(String id) {
        if(hasDevice(id)) return this.devices.get(id).clone();
        else{
            Menu.erros(6);
            return null;}
    }

    public void addSmartDevice (SmartDevice sd){
        if(!sd.getID().equals("") && this.devices.keySet().stream().noneMatch(id -> id.equals(sd.getID()))) this.devices.put(sd.getID(), sd.clone());
        else System.out.println("ID repetido ou inválido!");
    }

    public double consumoTotal(String formula) {
        Set<String> setOfKeys = devices.keySet();
        //Fornecedores forn = getFornecedor();
        double contador = 0;

        formulaStrategy strategy = null;
        switch (formula){
            case "formula1" -> strategy = new formula1();
            case "formula2" -> strategy = new formula2();
            case "formula3" -> strategy = new formula3();
            default -> Menu.erros(21);
        }
        for (String key : setOfKeys) {
            SmartDevice s = this.getDevice(key);
            if (s.getOn()) {
                assert strategy != null;
                contador += strategy.formula(s);
            }
        }
        return contador;

        /*for (String key : setOfKeys) {
            SmartDevice s = this.getDevice(key);
            if (s.getOn()) contador += forn.PrecoDiarioPorDispositivo(s);
            else contador += 0;
        }*/
    }

    public static class ComparatorConsumo implements Comparator<Casa> {
        @Override
        public int compare(Casa c1, Casa c2) {
            List<Faturas> listfaturas = c1.getFatura();
            List<Faturas> listfaturas2 = c2.getFatura();
            return -Double.compare(listfaturas.get((listfaturas.size()) - 1).getConsumo(), listfaturas2.get((listfaturas2.size()) - 1).getConsumo());
        }
    }

    /*public static class ComparatorGasto implements Comparator<Casa> {
        @Override
        public int compare(Casa c1, Casa c2) {
            if(c1.getFatura()!=null && c2.getFatura()!=null){
                List<Faturas> listfaturas = c1.getFatura();
                List<Faturas> listfaturas2 = c2.getFatura();
                return Double.compare(listfaturas.get((listfaturas.size())-1).getConsumo(), listfaturas2.get((listfaturas2.size())-1).getConsumo());
            }
            else {
                Menu.erros(22);
                return -1;
            }
        }
    }*/
}
