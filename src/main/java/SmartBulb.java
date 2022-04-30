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

    public SmartBulb(boolean turn, int tone, double dimensao) {
        super(turn);
        this.tone = tone;
        this.dimensao = dimensao;
    }

    public SmartBulb(int tone, double dimensao) {
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
        String estado;
        if (getOn()) estado = "ON";
        else estado = "OF";
        final StringBuffer sb = new StringBuffer("\n SmartBulb (\u001B[36m").append(getID()).append("\u001B[0m");
        sb.append(",").append(estado).append("): ");
        sb.append("Tone-> ").append(tone).append("  ");
        sb.append("Dimensao-> ").append(dimensao).append("  ");
        sb.append("Consumo Diário-> ").append(consumoEnergia());
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartBulb sb = (SmartBulb) o;
        return  super.equals(sb) &&
                this.tone == sb.tone &&
                this.dimensao == sb.dimensao;
    }
    /*return Objects.equals(this.getID(), sb.getID()) && Objects.equals(this.getOn(), sb.getOn())
                && this.tone == sb.tone && this.dimensao == sb.dimensao;
    */

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

