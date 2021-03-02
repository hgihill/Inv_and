package controller;

import logic.LogicSeleccion;
import logic.LogicaTarjetas;
import model.Data;

public class ControlTarjetas {

    public static void getObjetos(int id){
        new LogicaTarjetas.Load_Tarjetas_AsyncTask().execute(Data.HOST + "/inventario/mostrarObjetos.php?ID_ESPACIO=" + id);
    }

    public static void getIdEspacioByName(String espacio){
        new LogicSeleccion().getIdEspacioByName(espacio);
    }

}
