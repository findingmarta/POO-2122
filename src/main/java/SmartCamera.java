
public class SmartCamera extends SmartDevice {
    private double resolution;
    private double size;

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
        this.resolution = sc.getResolution();
        this.size = sc.getSize();
    }

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

    public String toString() {
        return "SmartCamera{" +
                "resolution=" + resolution +
                ", size=" + size +
                '}';
    }

    public double consumoEnergia(){
        return this.resolution * this.size;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SmartCamera)) return false;
        SmartCamera that = (SmartCamera) o;
        return Double.compare(that.getResolution(), getResolution()) == 0 && Double.compare(that.getSize(), getSize()) == 0;
    }

}
