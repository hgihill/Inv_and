package model;

public class User {
    private String NOMBRE, PASS;
    private int ID_USER;


    public User(int ID_USER, String NOMBRE, String PASS){
        this.ID_USER = ID_USER;
        this.NOMBRE = NOMBRE;
        this.PASS = PASS;
    }
    public User(String NOMBRE, String PASS) {
        this.NOMBRE = NOMBRE;
        this.PASS = PASS;
    }

    public User(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String sNombre) {
        this.NOMBRE = NOMBRE;
    }

    public String getsPASS() {
        return PASS;
    }

    public void setsPASS(String PASS) {
        this.PASS = PASS;
    }

    public int getID_USER() {
        return ID_USER;
    }

    public void setID_USER(int ID_USER) {
        this.ID_USER = ID_USER;
    }
}
