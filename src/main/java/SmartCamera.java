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

    public SmartCamera (String id, double re, double size){
        super(id);
        this.resolution = re;
        this.size = size;
    }

    public SmartCamera (SmartCamera sc){
        super (sc);
        this.resolution = sc.getResolution();
        this.size = sc.getSize();
    }

    /**
     * Metodos setter e getters
     */
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }


    public double getResolution(){
        return this.resolution;
    }

    public void setResolution(double resolution) {
        this.resolution = resolution;
    }

    /**
     * Metodo toString
     */
    public String toString() {
        return "SmartCamera{" +
                "resolution=" + resolution +
                ", size=" + size +
                '}';
    }

    /**
     * Metodo equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartCamera)) return false;
        SmartCamera that = (SmartCamera) o;
        return Double.compare(that.getResolution(), getResolution()) == 0 && Double.compare(that.getSize(), getSize()) == 0;
    }

    /**
     * Metodo clone
     */
    public SmartCamera clone() {
        return new SmartCamera(this);
    }

    /**
     * Metodo Consumo de Energia
     */
    public double consumoEnergia(){
        return this.resolution * this.size;
    }
}
