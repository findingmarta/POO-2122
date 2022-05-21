public class formula3 implements formulaStrategy {
    @Override
    public double formula(SmartDevice sd) {
        double consumo = sd.consumoEnergia();
        return 0.148*consumo*(0.60+1)*0.9*24;
        //return 0.5*consumo;
    }
}
