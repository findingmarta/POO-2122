public class FornecJomar extends Fornecedores{

    /**
     * Esta é uma classe extendida de Fornecedor que usufrui dos métodos da mesma
     * Define as diferentes fórmulas relativas aos diferentes fornecedores
     */

    public double PrecoDiarioPorDispositivo(SmartDevice sd){
        double consumo = sd.consumoEnergia();
        return ((0.148+20)*consumo*(0.6+0.5)*24);
    }
}
