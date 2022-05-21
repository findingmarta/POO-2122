import java.io.Serializable;
import java.util.*;

public class Faturas implements Serializable {
    private double fatura;
    private double consumo;
    private String dataInicial;
    private String  dataFinal;

    public Faturas() {
        this.fatura = 0.0;
        this.dataInicial = "";
        this.dataFinal = "";
        this.consumo = 0.0;
    }

    public Faturas(double fatura, String dataInicial, String  dataFinal, double consumo) {
        this.fatura = fatura;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.consumo = consumo;
    }

    public Faturas(Faturas umaFatura) {
        this.fatura = umaFatura.getFatura();
        this.dataInicial = umaFatura.getDataInicial();
        this.dataFinal = umaFatura.getDataFinal();
        this.consumo = umaFatura.getConsumo();
    }

    public double getFatura() {
        return fatura;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public double getConsumo() {
        return consumo;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\u001B[36m   FATURA { \u001B[0m \n\n");
        sb.append("\u001B[1m Data inicial: \u001B[0m").append(dataInicial).append('\n');
        sb.append("\u001B[1m Data Final: \u001B[0m").append(dataFinal).append("\n\n");
        sb.append("\u001B[1m Consumo: \u001B[0m").append(consumo).append("\n\n");
        sb.append("\u001B[1m Custo: \u001B[0m").append(fatura).append("\n\n");
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faturas faturas = (Faturas) o;
        return  Objects.equals(this.fatura, faturas.fatura) &&
                Objects.equals(this.consumo, faturas.consumo) &&
                Objects.equals(this.dataFinal, faturas.dataFinal) &&
                Objects.equals(this.dataInicial, faturas.dataInicial);
    }

    @Override
    public Faturas clone() {
        return new Faturas(this);
    }
}

