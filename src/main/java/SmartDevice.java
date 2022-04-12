import java.util.List;

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

    public SmartDevice( SmartDevice umDevice) {
        this.id = umDevice.id;
        this.on = umDevice.on;
    }

    public SmartDevice(String s, boolean b) {
        this.id = "";
        this.on = false;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public boolean getOn() {
        return this.on;
    }

    public void setOn(boolean b) {
        this.on = b;
    }

    public String getID() {
        return this.id;
    }

    public void turnAllOn(List<SmartDevice> devices) {
        for(SmartDevice sd : devices) {
            sd.turnOn();
        }
    }

    public void turnAllOff(List<SmartDevice> devices) {
        for(SmartDevice sd : devices) {
            sd.turnOff();
        }
    }

    public SmartDevice clone() {
        return new SmartDevice(this);
    }
}
