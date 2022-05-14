import java.io.Serializable;
import java.util.Comparator;

public abstract class Fornecedores implements Serializable {
    private double valorBase = 0.148;
    private double imposto = 0.60;
    private double volumeFaturacao = 0.0;
    private String formula;

    /**
     * Construtores
     */
    public Fornecedores() {
        //this.volumeFaturacao = 0.0;
        this.formula = "";
    }

    public Fornecedores (double volumeFaturacao){
        this.volumeFaturacao = volumeFaturacao;
    }

    public Fornecedores (String formula){
        this.formula = formula;
    }

    public Fornecedores (Fornecedores umFornecedor){
        this.volumeFaturacao = umFornecedor.getVolumeFaturacao();
        this.valorBase = umFornecedor.getValorBase();
        this.imposto = umFornecedor.getImposto();
        this.formula = umFornecedor.getFormula();
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
        this.formula = formula;
    }

    public double getVolumeFaturacao() {
        return this.volumeFaturacao;
    }

    public void setVolumeFaturacao(double volumeFaturacao) {
        if(volumeFaturacao>=0) this.volumeFaturacao = volumeFaturacao;
        else Menu.erros(19);
    }

    /**
     * Metodo toString, equals e clone
     */
    @Override
    public String toString() {
        return  "\nValor Base= " + valorBase +
                "\nImposto= " + imposto +
                "\nFaturação= " + volumeFaturacao +
                "\nFórmula= " + formula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public abstract Fornecedores clone ();

    public static class fornecedoresComparator implements Comparator<Fornecedores> {
        @Override
        public int compare(Fornecedores f1, Fornecedores f2) {
            return Double.compare(f1.getVolumeFaturacao(), f2.getVolumeFaturacao());
        }
    }

    /**
     * Metodos
     */
    //public abstract double PrecoDiarioPorDispositivo (SmartDevice sd);

    public void aumentaVolumeFaturacao(double volumeFaturacao) {
        if(volumeFaturacao >= 0) setVolumeFaturacao(this.volumeFaturacao += volumeFaturacao);
        else Menu.erros(19);
    }

    public String Stringfornecedor (Fornecedores fornecedor){
        String forn= "";
        if (fornecedor instanceof FornecJomar) forn = "Jomar";
        else if (fornecedor instanceof FornecEDP) forn = "EDP";
        else if (fornecedor instanceof FornecEndesa) forn = "Endesa";
        return forn;
    }

    /*public double PrecoDiarioPorDispositivo (SmartDevice sd){
        double consumo = 0.0;
        switch (sd){
            case sd instanceof SmartBulb -> consumo = ;
            case sd instanceof SmartSpeaker -> consumo = SmartSpeaker.consumoEnergia();
            case sd instanceof SmartCamera -> consumo = SmartCamera.consumoEnergia();
        }
        return this.getValor_base()*consumo*(this.getImposto()+1)*0.9;
    }*/
}