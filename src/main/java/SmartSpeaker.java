import java.util.Objects;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 100;
    private int volume;
    private String channel;
    private String marca;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        super();
        this.volume = 0;
        this.channel = "";
        this.marca = "";
    }

    public SmartSpeaker(String id, boolean turn, int volume, String channel, String marca) {
        super(id, turn);
        if (volume>=0 && volume<=MAX) this.volume = volume;
        else if (volume>MAX) this.volume = MAX;
        else this.volume = 0;
        this.channel = channel;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        super(ss);
        this.volume = ss.getVolume();
        this.channel = ss.getChannel();
        this.marca = ss.getMarca();
    }

    /**
     * Getters e Setters
     */
    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        if (this.volume>=0 && this.volume<=MAX) this.volume = volume;
        else Menu.erros(14);
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String c) {
        this.channel = c;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Metodo toString, equals e clone
     */
    @Override
    public String toString (){
        String estado;
        if (getOn()) estado = "ON";
        else estado = "OF";
        final StringBuilder ss = new StringBuilder("\n SmarSpeaker (\u001B[36m").append(getID()).append("\u001B[0m");
        ss.append(",").append(estado).append("): ");
        ss.append("Marca-> ").append(marca).append("  ");
        ss.append("Channel-> ").append(channel).append("  ");
        ss.append("Volume-> ").append(volume).append("  ");
        ss.append("Consumo Diário-> ").append(consumoEnergia());
        return ss.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartSpeaker ss = (SmartSpeaker) o;
        return  super.equals(ss) &&
                this.volume == ss.getVolume() &&
                this.channel.equals(ss.getChannel()) &&
                this.marca.equals(ss.getMarca());
    }

    @Override
    public SmartSpeaker clone (){
        return new SmartSpeaker(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, channel, marca);
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        if(getVolume()==0 && getMarca().length()==0) return 0;
        return  2 + (this.getVolume() + this.marca.length());
        //return  20;
    }

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
        else Menu.erros(2);
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
        else Menu.erros(2);
    }
}