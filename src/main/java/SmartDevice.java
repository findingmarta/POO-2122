import java.io.Serializable;
import java.util.*;

/**
 * A classe SmartDevice é um contactor simples.
 * Permite ligar ou desligar circuitos.
 */
public abstract class SmartDevice implements Serializable {
    private String id;
    private boolean on;

    /**
     * Constructor for objects of class SmartDevice
     */
    public SmartDevice() {
        this.id = "";
        this.on = false;
    }

    public SmartDevice(String id, boolean b) {
        this.id = id;
        this.on = b;
    }

    public SmartDevice(SmartDevice umDevice) {
        this.id = umDevice.getID();
        this.on = umDevice.getOn();
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
        final StringBuilder sd = new StringBuilder("SmartDevice{\n");
        sd.append("Id: ").append(id).append("\n");
        sd.append("Ligado? ").append(on).append("\n");
        sd.append("\n}");
        return sd.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevice sd = (SmartDevice) o;
        return  Objects.equals(this.id, sd.getID()) &&
                this.on == sd.getOn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, on);
    }

    public abstract SmartDevice clone();

    /**
     * Metodos
     */
    public abstract double consumoEnergia();
}