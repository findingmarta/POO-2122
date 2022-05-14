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

    public FornecJomar(String formula) {
        super(formula);
    }

    public FornecJomar(FornecJomar umFornecedor) {
        super(umFornecedor);
    }

    @Override
    public Fornecedores clone() {
        return new FornecJomar(this);
    }
}
