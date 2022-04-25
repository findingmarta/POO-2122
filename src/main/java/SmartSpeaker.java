import java.util.Objects;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 */
public class SmartSpeaker extends SmartDevice {
    public static final int MAX = 20; //volume máximo

    private int volume;
    private String channel; //rádio que estão a tocar
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
        this.volume = volume;
        this.channel = channel;
        this.marca = marca;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        this.volume = ss.getVolume();
        this.channel = ss.getChannel();
        this.marca = ss.getMarca();
    }

    /*
    public SmartSpeaker(String s) {
        // initialise instance variables
        this.volume = 10;
    }

    public SmartSpeaker(String s, String s1, int i) {
        // initialise instance variables
        this.volume = 10;
    }*/

    /**
     * Getters e Setters
     */
    public int getVolume() {
        if (this.volume<0) this.setVolume(0);
        if (this.volume>20) this.setVolume(20);
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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
    public String toString (){
        String estado;
        if (getOn()) estado = "ON";
        else estado = "OF";
        final StringBuffer ss = new StringBuffer("\n SmarSpeaker (\u001B[36m").append(getID()).append("\u001B[0m");
        ss.append(",").append(estado).append("): ");
        ss.append("Marca-> ").append(marca).append("  ");
        ss.append("Channel-> ").append(channel).append("  ");
        ss.append("Volume-> ").append(volume).append("  ");
        ss.append("Consumo Diário-> ").append(consumoEnergia());
        return ss.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartSpeaker ss = (SmartSpeaker) o;
        return Objects.equals(this.getID(), ss.getID()) && Objects.equals(this.getOn(), ss.getOn()) &&
                this.volume == ss.getVolume() &&
                Objects.equals(this.channel, ss.getChannel()) &&
                Objects.equals(this.marca, ss.getMarca());
    }

    public SmartSpeaker clone (){
        return new SmartSpeaker(this);
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        return  2 + (getVolume() + this.marca.length());
    }


    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }
}
