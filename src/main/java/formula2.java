public class formula2 implements formulaStrategy{
    @Override
    public double formula(SmartDevice sd) {
        double consumo = sd.consumoEnergia();
        return 0.148*(consumo*3)*0.60*0.9*24;
        //return consumo;
    }
}
