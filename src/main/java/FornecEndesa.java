/**
 * Esta é uma classe extendida de Fornecedor que usufrui dos métodos da mesma
 * Define as diferentes fórmulas relativas aos diferentes fornecedores
 */
public class FornecEndesa extends Fornecedores{

    public FornecEndesa() {
        super();
    }

    public FornecEndesa(double volumeFaturacao) {
        super(volumeFaturacao);
    }

    public FornecEndesa(String formula) {
        super(formula);
    }

    public FornecEndesa(FornecEndesa umFornecedor) {
        super(umFornecedor);
    }

    @Override
    public Fornecedores clone() {
        return new FornecEndesa(this);
    }

    public double PrecoDiarioPorDispositivo(SmartDevice sd){
        double consumo = sd.consumoEnergia();
        return this.getValorBase()*consumo*(this.getImposto()+1)*0.9*24;
    }
}
