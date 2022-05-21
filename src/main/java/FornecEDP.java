/**
 * Esta é uma classe extendida de Fornecedor que usufrui dos métodos da mesma
 * Define as diferentes fórmulas relativas aos diferentes fornecedores
 */
public class FornecEDP extends Fornecedores{

    public FornecEDP() {
        super();
    }

    public FornecEDP(double volumeFaturacao) {
        super(volumeFaturacao);
    }

    public FornecEDP(String formula) {
        super(formula);
    }

    public FornecEDP(FornecEDP umFornecedor) {
        super(umFornecedor);
    }


    @Override
    public Fornecedores clone() {
        return new FornecEDP(this);
    }
}
