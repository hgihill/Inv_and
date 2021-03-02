package model;

public class Habitacion {

    private String NOMBRE;
    private int ID_HABITACION;

    public Habitacion(String NOMBRE, int ID_HABITACION) {
        this.NOMBRE = NOMBRE;
        this.ID_HABITACION = ID_HABITACION;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getID_HABITACION() {
        return ID_HABITACION;
    }

    public void setID_HABITACION(int ID_HABITACION) {
        this.ID_HABITACION = ID_HABITACION;
    }
}
