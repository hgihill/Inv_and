package model;

public class Espacio {

    private String NOMBRE;
    private int ID_ESPACIO;

    public Espacio(String NOMBRE, int ID_ESPACIO) {
        this.NOMBRE = NOMBRE;
        this.ID_ESPACIO = ID_ESPACIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getID_ESPACIO() {
        return ID_ESPACIO;
    }

    public void setID_ESPACIO(int ID_ESPACIO) {
        this.ID_ESPACIO = ID_ESPACIO;
    }
}
