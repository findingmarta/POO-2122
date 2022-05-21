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
    private double custoInstalacao;

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
        this.custoInstalacao = 0.0;
    }

    public Casa(Map<String, SmartDevice> d, Map<String, HashSet<String>> divisoes, String proprietario, String NIF, Fornecedores fornecedor) {
        this.proprietario = proprietario;
        this.NIF = NIF;
        this.divisoes = divisoes;
        this.devices = new HashMap<>();
        for (SmartDevice sd : d.values()){
            this.devices.put(sd.getID(),sd.clone());
        }
        this.setFornecedor(fornecedor);
        this.faturas= new ArrayList<>();
        this.custoInstalacao = 0.0;
    }

    public Casa (String proprietario, String NIF, Fornecedores fornecedor) {
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.faturas= new ArrayList<>();
        this.setNIF(NIF);
        this.setProprietario(proprietario);
        this.setFornecedor(fornecedor);
        this.custoInstalacao = 0.0;
    }

    public Casa(Casa umaCasa) {
        this.NIF = umaCasa.getNIF();
        this.devices = umaCasa.getDevices();
        this.divisoes = umaCasa.getDivisoes();
        this.fornecedor = umaCasa.getFornecedor();
        this.proprietario = umaCasa.getProprietario();
        this.faturas= umaCasa.getFatura();
        this.custoInstalacao = umaCasa.getCustoInstalacao();
    }


    /**
     * Getters e Setters
     */
    public String getProprietario (){
        return this.proprietario;
    }

    public void setProprietario (String proprietario){
        if (proprietario != null) this.proprietario = proprietario;
        else Menu.erros(26);
    }

    public String getNIF (){
        return this.NIF;
    }

    public void setNIF (String NIF){
        if(NIF != null && onlyDigits(NIF, NIF.length())) this.NIF = NIF;
        else Menu.erros(27);
    }

    public Fornecedores getFornecedor(){
        if (this.fornecedor == null) {
            Menu.erros(28);
            return null;
        }
        return this.fornecedor.clone();
    }

    public void setFornecedor(Fornecedores umFornecedor){
        if (umFornecedor != null) this.fornecedor = umFornecedor.clone();
        else Menu.erros(28);
    }

    public Map<String, HashSet<String>> getDivisoes (){
        return this.divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void setDivisoes (Map<String, HashSet<String>> divisoes){
        if (divisoes != null) this.divisoes = divisoes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        else Menu.erros(29);
    }

    public Map<String, SmartDevice> getDevices(){
        Map<String, SmartDevice> d = new HashMap<>();
        for(String id : this.devices.keySet()){
            d.put(id, this.devices.get(id).clone());
        }
        return d;
    }

    public void setDevices (Map<String, SmartDevice> d){
        if(d != null) {
            this.devices = new HashMap<>();
            for (Map.Entry<String, SmartDevice> e : d.entrySet()) {
                devices.put(e.getKey(), e.getValue().clone());
            }
        }
        else Menu.erros(30);
    }

    public List<Faturas> getFatura() {
        List<Faturas> fat = new ArrayList<>();
        for (Faturas f : this.faturas){
            fat.add(f.clone());
        }
        return fat;
    }

    public void setFatura(List<Faturas> nFaturas) {
        if(nFaturas != null) {
            this.faturas = new ArrayList<>();
            for (Faturas f : nFaturas) {
                faturas.add(f.clone());
            }
        }
        else Menu.erros(31);
    }

    public double getCustoInstalacao() {
        return this.custoInstalacao;
    }

    public void setCustoInstalacao(double custoInstalacao) {
        if(custoInstalacao>=0) this.custoInstalacao = custoInstalacao;
        else Menu.erros(33);
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
        sb.append("\u001B[1mCusto Instalação: \u001B[0m").append(custoInstalacao).append('\n');
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
        else Menu.erros(6);
    }

    public void setDeviceOff(String id) {
        if(hasDevice(id)) this.devices.get(id).setOn(false);
        else Menu.erros(6);
    }

    public void setDivisonOn(String divisao) {
        if (hasRoom(divisao) && !roomisEmpty(divisao))
            this.divisoes.get(divisao).forEach(this::setDeviceOn);
        else Menu.erros(18);
    }

    public void setDivisonOff(String divisao) {
        if (hasRoom(divisao) && !roomisEmpty(divisao))
            this.divisoes.get(divisao).forEach(this::setDeviceOff);
        else Menu.erros(18);
    }

    public boolean hasRoom(String divisao) {
        if(this.divisoes.isEmpty()) return false;
        return this.divisoes.keySet().stream().anyMatch(d -> d.equals(divisao));
    }

    public boolean roomisEmpty (String divisao) {
        if(this.divisoes.isEmpty()) {
            Menu.erros(34);
            return true;
        }
        if(!hasRoom(divisao)) {
            Menu.erros(13);
            return true;
        }
        return this.divisoes.get(divisao).isEmpty();
    }

    public boolean roomHasDevice (String divisao, String id) {
        if(this.divisoes.isEmpty()) {
            Menu.erros(34);
            return false;
        }
        if(!hasRoom(divisao)) {
            Menu.erros(13);
            return false;
        }
        if(!hasDevice(id)){
            Menu.erros(6);
            return false;
        }
        return this.divisoes.get(divisao).stream().anyMatch(sd -> sd.equals(id));
    }

    public void addRoom(String divisao) {
        HashSet<String> ids = new HashSet<>();
        if(!hasRoom(divisao) && divisao!=null) this.divisoes.put(divisao, ids);
        else Menu.erros(4);
    }

    public void addToRoom (String divisao, String id) {
        if(this.divisoes.isEmpty()) Menu.erros(34);
        else if(hasRoom(divisao) && hasDevice(id) && !roomHasDevice(divisao,id))
            this.divisoes.get(divisao).add(id);
        else Menu.erros(2);
    }

    public boolean hasDevice(String id){
        if(this.devices.isEmpty()) {
            Menu.erros(35);
            return false;
        }
        return this.devices.keySet().stream().anyMatch(sd-> sd.equals(id));
    }

    public SmartDevice getDevice(String id) {
        if(hasDevice(id)) return this.devices.get(id).clone();
        else{
            Menu.erros(6);
            return null;
        }
    }

    public void addSmartDevice (SmartDevice sd){
        if(!sd.getID().equals("") && this.devices.keySet().stream().noneMatch(id -> id.equals(sd.getID())))
            this.devices.put(sd.getID(), sd.clone());
        else Menu.erros(32);
    }

    public double consumoTotal2(){
        double consumoTotal=0;
        for(SmartDevice sd : this.getDevices().values()){
            if(sd.getOn()) consumoTotal+=sd.consumoEnergia();
        }
        return consumoTotal;
    }

    public double consumoTotal(String formula) {
        Set<String> setOfKeys = devices.keySet();
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
    }

    public static class ComparatorConsumo implements Comparator<Casa> {
        @Override
        public int compare(Casa c1, Casa c2) {
            List<Faturas> listfaturas = c1.getFatura();
            List<Faturas> listfaturas2 = c2.getFatura();
            return -Double.compare(listfaturas.get((listfaturas.size()) - 1).getConsumo(), listfaturas2.get((listfaturas2.size()) - 1).getConsumo());
        }
    }

    public static class ComparatorGasto implements Comparator<Casa> {
        @Override
        public int compare(Casa c1, Casa c2) {
            if(c1.getFatura()!=null && c2.getFatura()!=null){
                List<Faturas> listfaturas = c1.getFatura();
                List<Faturas> listfaturas2 = c2.getFatura();
                return -Double.compare(listfaturas.get((listfaturas.size())-1).getFatura(), listfaturas2.get((listfaturas2.size())-1).getFatura());
            }
            else {
                Menu.erros(22);
                return -1;
            }
        }
    }

    public static boolean onlyDigits(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }
        return true;
    }
}