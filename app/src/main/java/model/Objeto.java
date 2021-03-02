package model;

public class Objeto {

    private String NOMBRE, DESCRIPCION, LOCALIZACION;
    private int CANTIDAD, MINIMO, ID_OBJETO;
    private boolean CONSUMIBLE;

    public Objeto(int ID_OBJETO, String NOMBRE, int CANTIDAD, int MINIMO, boolean CONSUMIBLE, String DESCRIPCION, String LOCALIZACION) {
        this.ID_OBJETO = ID_OBJETO;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.LOCALIZACION = LOCALIZACION;
        this.CANTIDAD = CANTIDAD;
        this.MINIMO = MINIMO;
        this.CONSUMIBLE = CONSUMIBLE;
    }

    public int getID_OBJETO() { return ID_OBJETO; }

    public void setID_OBJETO(int ID_OBJETO) { this.ID_OBJETO = ID_OBJETO; }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getLOCALIZACION() {
        return LOCALIZACION;
    }

    public void setLOCALIZACION(String LOCALIZACION) {
        this.LOCALIZACION = LOCALIZACION;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public int getMINIMO() {
        return MINIMO;
    }

    public void setMINIMO(int MINIMO) {
        this.MINIMO = MINIMO;
    }

    public boolean isCONSUMIBLE() { return CONSUMIBLE; }

    public void setCONSUMIBLE(boolean CONSUMIBLE) { this.CONSUMIBLE = CONSUMIBLE; }

    @Override
    public String toString() {
        return "Objeto{" +
                "NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", LOCALIZACION='" + LOCALIZACION + '\'' +
                ", CANTIDAD=" + CANTIDAD +
                ", MINIMO=" + MINIMO +
                ", ID_OBJETO=" + ID_OBJETO +
                ", CONSUMIBLE=" + CONSUMIBLE +
                '}';
    }
}
