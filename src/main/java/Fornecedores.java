import java.io.Serializable;

public abstract class Fornecedores implements Serializable {
    private double valor_base;
    private double imposto;

    /**
     * Construtores
     */
    public Fornecedores (){
        this.valor_base = 0.148;
        this.imposto = 0.60;
    }

    public Fornecedores (double valor_base, double imposto){
        this.valor_base = valor_base;
        this.imposto = imposto;
    }

    public Fornecedores (Fornecedores umFornecedor){
        this.valor_base = umFornecedor.valor_base;
        this.imposto = umFornecedor.imposto;
    }


    /**
     * Getters e Setters
     */
    /**
    public double getValor_base() {
        return valor_base;
    }

    public void setValor_base(double valor_base) {
        this.valor_base = valor_base;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }
**/
    /**
     * Metodo toString, equals e clone
     */
    public String toString() {
        return "Fornecedores{" +
                "valor_base=" + valor_base +
                ", imposto=" + imposto +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fornecedores that = (Fornecedores) o;
        return Double.compare(that.valor_base, valor_base) == 0 && Double.compare(that.imposto, imposto) == 0;
    }

    /**
    public Fornecedores clone (){
        return new Fornecedores(this);
    }**/

    public abstract double PrecoDiarioPorDispositivo (SmartDevice sb);

    /**
     * Metodos
     */
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
