import java.util.*;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos.
 */
public class SmartDevice {

    private String id;
    private boolean on;


    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
    }

    public SmartDevice(String id) {
        this.id = id;
        this.on = false;
    }

    public SmartDevice(String id, boolean b) {
        this.id = id;
        this.on = b;
    }

    public SmartDevice(SmartDevice umDevice) {
        this.id = umDevice.id;
        this.on = umDevice.on;
    }

    /**
     * Getters e Setters
     */
    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean b) {
        this.on = b;
    }

    public String getID() {
        return this.id;
    }

    public void setID(String id){
        this.id=id;
    }

    /**
     * Metodo toString, equals e clone
     */
    public String toString (){
        final StringBuffer sd = new StringBuffer("SmartDevice{\n");
        sd.append("Id: ").append(id).append("\n");
        sd.append("Ligado? ").append(on).append("\n");
        sd.append("\n}");
        return sd.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return (Objects.equals(this.id, sd.getID()) &&
                this.on == sd.getOn());
    }

    /**
    public SmartDevice clone() {
        return new SmartDevice(this);
    }**/

    /**
     * Metodos
     */
    // IMPEDIR DE CRIAR DEVICES REPETIDOS (mesmo id)
    //public abstract double consumoEnergia();

    public void turnAllOn(List<SmartDevice> devices) {
        for(SmartDevice sd : devices) {
            sd.setOn(true);
        }
    }

    public void turnAllOff(List<SmartDevice> devices) {
        for(SmartDevice sd : devices) {
            sd.setOn(false);
        }
    }
    public static HashSet<String> idDevices() {
        HashSet<String> devices = new HashSet<>();
        devices.add("1111");
        devices.add("2222");
        devices.add("123456789");
        return devices;
    }

    public static HashSet<String> idDevices2() {
        HashSet<String> devices = new HashSet<>();
        devices.add("123");
        devices.add("5553");
        devices.add("123456789");
        return devices;
    }
}
