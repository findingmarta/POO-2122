import java.time.LocalDate;

public class Faturas {

    private double fatura;
    private String dataInicial;
    private String  dataFinal;


    public double getpreco() {
        return fatura;
    }

    public void setFatura(double fatura) {
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

    public Faturas() {
        this.fatura = 0.0;
        this.dataInicial = ("");
        this.dataFinal = ("");
    }

    public Faturas( double fatura, String dataInicial, String  dataFinal) {
        this.fatura = fatura;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public String StringFaturas() {
        final StringBuilder sb = new StringBuilder("\n\u001B[36m   FATURA { \u001B[0m \n\n");
        //sb.append("Dispositivos: ").append(devices).append('\n');
        sb.append("\u001B[1m Data inicial: \u001B[0m").append(dataInicial).append('\n');
        sb.append("\u001B[1m Data Final: \u001B[0m").append(dataFinal).append('\n');
        sb.append("\u001B[1m Custo: \u001B[0m").append(fatura).append('\n');
        sb.append("\n\u001B[36m } \u001B[0m");
        return sb.toString();
    }
}


