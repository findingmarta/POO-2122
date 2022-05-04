import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Faturas {

    private double fatura;
    private double consumo;
    private String dataInicial;
    private String  dataFinal;

    public Faturas() {
        this.fatura = 0.0;
        this.dataInicial = ("");
        this.dataFinal = ("");
        this.consumo = 0.0;
    }


    public Faturas( double fatura, String dataInicial, String  dataFinal, double consumo) {
        this.fatura = fatura;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.consumo = consumo;
    }



    public double getpreco() {
        return fatura;
    }

    public void setpreco(double fatura) {
        this.fatura = fatura;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }


    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public String StringFaturas() {
        final StringBuilder sb = new StringBuilder("\n\u001B[36m   FATURA { \u001B[0m \n\n");
        //sb.append("Dispositivos: ").append(devices).append('\n');
        sb.append("\u001B[1m Data inicial: \u001B[0m").append(dataInicial).append('\n');
        sb.append("\u001B[1m Data Final: \u001B[0m").append(dataFinal).append("\n\n");
        sb.append("\u001B[1m Consumo: \u001B[0m").append(consumo).append("\n\n");
        sb.append("\u001B[1m Custo: \u001B[0m").append(fatura).append("\n\n");
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }
}


