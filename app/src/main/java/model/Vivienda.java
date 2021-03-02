package model;

public class Vivienda {
    private String NOMBRE;
    private int ID_VIVIENDA;
    public Vivienda(String NOMBRE, int ID_VIVIENDA) {
        this.NOMBRE = NOMBRE;
        this.ID_VIVIENDA = ID_VIVIENDA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getID_VIVIENDA() {
        return ID_VIVIENDA;
    }

    public void setID_VIVIENDA(int ID_VIVIENDA) {
        this.ID_VIVIENDA = ID_VIVIENDA;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "NOMBRE='" + NOMBRE + '\'' +
                ", ID_VIVIENDA=" + ID_VIVIENDA +
                '}';
    }
}
