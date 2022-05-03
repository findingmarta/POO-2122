public class FornecEDP extends Fornecedores{

    private String nome;


    public FornecEDP() {
        super();
        this.nome = "";
    }

    public FornecEDP(String nome) {
        super();
        this.nome = nome;
    }

    public FornecEDP(FornecEDP umFornecedor) {
        super(umFornecedor);
        this.nome = umFornecedor.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Esta é uma classe extendida de Fornecedor que usufrui dos métodos da mesma
     * Define as diferentes fórmulas relativas aos diferentes fornecedores
     */


    public double PrecoDiarioPorDispositivo(SmartDevice sd){
        double consumo = sd.consumoEnergia();
        return (0.148*(consumo+3)*(0.6*0.9)*24);
    }
}
