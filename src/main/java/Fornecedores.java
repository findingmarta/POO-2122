import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public abstract class Fornecedores implements Serializable {
    private double valorBase = 0.148;
    private double imposto = 0.60;
    private double volumeFaturacao = 0.0;
    private String formula;
    private String dataInicial = "01/01/2018";

    /**
     * Construtores
     */
    public Fornecedores() {
        this.formula = "";
    }

    public Fornecedores (double volumeFaturacao){
        this.volumeFaturacao = volumeFaturacao;
        this.formula = "";
    }

    public Fornecedores (String formula){
        this.formula = formula;
    }

    public Fornecedores (Fornecedores umFornecedor){
        this.volumeFaturacao = umFornecedor.getVolumeFaturacao();
        this.valorBase = umFornecedor.getValorBase();
        this.imposto = umFornecedor.getImposto();
        this.formula = umFornecedor.getFormula();
        this.dataInicial = umFornecedor.getDataInicial();
    }

    /**
     * Getters e Setters
     */
    public double getValorBase() {
        return this.valorBase;
    }

    public double getImposto() {
        return this.imposto;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String formula) {
        if(formula!=null && formula.equals("formula1") || Objects.equals(formula, "formula2") || Objects.equals(formula, "formula3"))
            this.formula = formula;
    }

    public double getVolumeFaturacao() {
        return this.volumeFaturacao;
    }

    public void setVolumeFaturacao(double volumeFaturacao) {
        if(volumeFaturacao>=0) this.volumeFaturacao = volumeFaturacao;
        else Menu.erros(19);
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        if(dataInicial != null) this.dataInicial = dataInicial;
    }

    /**
     * Metodo toString, equals e clone
     */
    @Override
    public String toString() {
        return  "\nValor Base= " + valorBase +
                "\nImposto= " + imposto +
                "\nFaturação=" + volumeFaturacao +
                "\nFórmula= " + formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public abstract Fornecedores clone ();

    /**
     * Metodos
     */
    public void aumentaVolumeFaturacao(double volumeFaturacao) {
        if(volumeFaturacao >= 0) setVolumeFaturacao(this.volumeFaturacao += volumeFaturacao);
        else Menu.erros(19);
    }

    public static String Stringfornecedor(Fornecedores fornecedor){
        String forn= "";
        if (fornecedor instanceof FornecJomar) forn = "Jomar";
        else if (fornecedor instanceof FornecEDP) forn = "EDP";
        else if (fornecedor instanceof FornecEndesa) forn = "Endesa";
        return forn;
    }

    public static class fornecedoresComparator implements Comparator<Fornecedores> {
        @Override
        public int compare(Fornecedores f1, Fornecedores f2) {
            return Double.compare(f1.getVolumeFaturacao(), f2.getVolumeFaturacao());
        }
    }
}