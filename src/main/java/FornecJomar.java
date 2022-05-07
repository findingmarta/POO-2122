/**
 * Esta é uma classe extendida de Fornecedor que usufrui dos métodos da mesma
 * Define as diferentes fórmulas relativas aos diferentes fornecedores
 */
public class FornecJomar extends Fornecedores{

    public FornecJomar() {
        super();
    }

    public FornecJomar(double volumeFaturacao) {
        super(volumeFaturacao);
    }

    public FornecJomar(FornecJomar umFornecedor) {
        super(umFornecedor);
    }

    @Override
    public Fornecedores clone() {
        return new FornecJomar(this);
    }

    public double PrecoDiarioPorDispositivo(SmartDevice sd){
        double consumo = sd.consumoEnergia();
        return (this.getValorBase()+20)*consumo*(this.getImposto()+0.5)*24;
    }
}
