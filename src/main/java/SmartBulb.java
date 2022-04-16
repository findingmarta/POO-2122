import java.util.Objects;

/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação
 * (a cor da mesma).
 */
public class SmartBulb extends SmartDevice {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    private int tone;
    private double dimensao;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.dimensao = 0.0;
    }

    public SmartBulb(String id, boolean turn, int tone, double dimensao) {
        super(id,turn);
        this.tone = tone;
        this.dimensao = dimensao;
    }

    public SmartBulb (SmartBulb sb)  {
        super(sb);
        this.tone = sb.getTone();
        this.dimensao = sb.getDimensao();
    }

    public SmartBulb(String id) {
        super(id);
        this.tone = NEUTRAL;
    }

    /**
     * Getters e Setters
     */
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public double getDimensao() {
        return this.dimensao;
    }

    /**
     * Metodo toString, equals e clone
     */
    public String toString() {
        final StringBuffer sb = new StringBuffer("\n SmartBulb: ");
        sb.append("Tone-> ").append(tone).append("  ");
        sb.append("Dimensao-> ").append(dimensao).append("  ");
        sb.append("Consumo Diário-> ").append(consumoEnergia());
        sb.append("\n");
        return sb.toString();
    }

    // falta ter em conta as outras variaveis
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartBulb sb = (SmartBulb) o;
        return (this.tone == sb.tone && this.dimensao == sb.dimensao);
    }

    public SmartBulb clone() {
        return new SmartBulb(this);
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        return 1 + this.tone + this.dimensao;
    }


}

