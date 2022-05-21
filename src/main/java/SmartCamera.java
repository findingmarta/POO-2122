import java.util.Objects;

/**
 * Uma SmartCamera é uma câmara de vigilância com uma certa resolução e tamanho de imagem
 */

public class SmartCamera extends SmartDevice {
    private double resolution;
    private double size;

    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartCamera () {
        super();
        this.resolution=0.0;
        this.size = 0.0;
    }

    public SmartCamera (String id, boolean turn, double re, double size){
        super(id,turn);
        if(re<0) this.resolution = 0;
        else this.resolution = re;
        if(size<0) this.size = 0;
        else this.size = size;
    }

    public SmartCamera (SmartCamera sc){
        super(sc);
        this.resolution = sc.getResolution();
        this.size = sc.getSize();
    }

    /**
     * Getters e Setters
     */
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        if(size>=0)this.size = size;
        else Menu.erros(36);
    }

    public double getResolution(){
        return this.resolution;
    }

    public void setResolution(double resolution) {
        if(resolution>=0) this.resolution = resolution;
        else Menu.erros(37);
    }

    /**
     * Metodo toString, equals e clone
     */
    @Override
    public String toString (){
        String estado;
        if (getOn()) estado = "ON";
        else estado = "OF";
        final StringBuilder sc = new StringBuilder("\n SmarCamera (\u001B[36m").append(getID()).append("\u001B[0m");
        sc.append(",").append(estado).append("): ");
        sc.append("Resolução-> ").append(resolution).append("  ");
        sc.append("Tamanho-> ").append(size).append("  ");
        sc.append("Consumo Diário-> ").append(consumoEnergia());
        return sc.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartCamera sc = (SmartCamera) o;
        return super.equals(sc) &&
                this.resolution==sc.getResolution() &&
                this.size==sc.getSize();
    }

    @Override
    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),resolution, size);
    }

    /**
     * Metodos
     */
    public double consumoEnergia(){
        if(getResolution()==0 || getSize()==0) return 0;
        return 0.3 * (this.resolution + this.size);
        //return 100;
    }
}