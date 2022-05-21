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

    /**
     * Getters e Setters
     */
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else this.tone = Math.max(t, COLD);
    }

    public int getTone() {
        return this.tone;
    }

    public void setDimensao(double dimensao) {
        if(dimensao>=0) this.dimensao = dimensao;
        else {
            System.out.println("Dimensao invalida!");
            this.dimensao = 0.0;
        }
    }

    public double getDimensao() {
        return this.dimensao;
    }

    /**
     * Metodo toString, equals e clone
     */
    @Override
    public String toString() {
        String estado;
        if (getOn()) estado = "ON";
        else estado = "OF";
        final StringBuilder sb = new StringBuilder("\n SmartBulb (\u001B[36m").append(getID()).append("\u001B[0m");
        sb.append(",").append(estado).append("): ");
        sb.append("Tone-> ").append(tone).append("  ");
        sb.append("Dimensao-> ").append(dimensao).append("  ");
        sb.append("Consumo Diário-> ").append(consumoEnergia());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartBulb sb = (SmartBulb) o;
        return  super.equals(sb) &&
                this.tone == sb.tone &&
                this.dimensao == sb.dimensao;
    }

    @Override
    public SmartBulb clone() {
        return new SmartBulb(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tone, dimensao);
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        if(getDimensao()<=0) return 0;
        return 1 + this.tone + this.dimensao;
        //return 1;
    }
}
