public class formula1 implements formulaStrategy{
    @Override
    public double formula(SmartDevice sd) {
        double consumo = sd.consumoEnergia();
        return (0.148+20)*consumo*(0.60+0.5)*24;
        //return 13 * consumo;
    }
}
