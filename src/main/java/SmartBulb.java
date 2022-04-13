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
    private double consumoDiario;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.dimensao = 0.0;
        this.consumoDiario = 0.0;
    }

    public SmartBulb(String id, int tone, double dimensao, double consumoDiario) {
        super(id);
        this.tone = tone;
        this.dimensao = dimensao;
        this.consumoDiario = consumoDiario;
    }

    public SmartBulb (SmartBulb sb)  {
        super(sb);
        this.tone = sb.getTone();
        this.dimensao = sb.getDimensao();
        this.consumoDiario = sb.getConsumoDiario();
    }

    public SmartBulb(String id) {
        super(id);
        this.tone = NEUTRAL;
    }

    /**
     * Metodos setter e getters
     */
    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public double getDimensao() {
        return this.dimensao;
    }

    public void setConsumoDiario(int consumo) {
        this.consumoDiario = consumo;
    }

    public double getConsumoDiario() {
        return this.consumoDiario;
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        return 5 + this.tone;
    }

    // falta ter em conta as outras variaveis
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartBulb sb = (SmartBulb) o;
        return (this.tone == sb.tone && this.consumoDiario == sb.consumoDiario && this.dimensao == sb.dimensao);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartBulb {\n");
        sb.append("Tone: ").append(tone).append('\n');
        sb.append("Consumo Diário: ").append(consumoDiario).append('\n');
        sb.append("Dimensao: ").append(dimensao).append('\n');
        sb.append("\n}");
        return sb.toString();
    }

    public SmartBulb clone() {
        return new SmartBulb(this);
    }
}
