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
    private double consumoDiario;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        this.volume = 0;
        this.channel = "";
        this.marca = "";
        this.consumoDiario = 0.0;
    }

    public SmartSpeaker(int volume, String channel, String marca, double consumoDiario) {
        this.volume = volume;
        this.channel = channel;
        this.marca = marca;
        this.consumoDiario = consumoDiario;
    }

    public SmartSpeaker(SmartSpeaker ss) {
        this.volume = ss.getVolume();
        this.channel = ss.getChannel();
        //this.marca = ss.getMarca();
        //this.consumoDiario = ss.getConsumoDiario();
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

    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }

    public int getVolume() {
        return this.volume;
    }

    public String getChannel() {
        return "";
    }

    public void setChannel(String c) {

    }

    //METODO CLONE

}
