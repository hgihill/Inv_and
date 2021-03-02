package logic;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.victor.inventario.MainActivity;
import com.victor.inventario.R;
import com.victor.inventario.Seleccion_objeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import controller.ControlTarjetas;
import model.Data;
import model.Espacio;
import model.Habitacion;
import model.Objeto;
import model.User;
import model.Vivienda;

public class LogicSeleccion {

    public static void viviendas(int id_user) {
        new LogicSeleccion.Load_Viviendas_AsyncTask().execute(Data.HOST + "/inventario/viviendaXUser.php?ID_USER=" + id_user);
    }

    private static class Load_Viviendas_AsyncTask extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                };
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Vivienda>>() {
            }.getType();
            List<Vivienda> lstViviendas = new ArrayList<Vivienda>();
            lstViviendas = gson.fromJson(resultado, type);
            Seleccion_objeto.lstViviendas = lstViviendas;
            MainActivity.context.startActivity(new Intent(MainActivity.context, com.victor.inventario.Seleccion_objeto.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        }
    }

    public static void habitaciones(int id_vivienda) {
        new LogicSeleccion.Load_Habitaciones_AsyncTask().execute(Data.HOST + "/inventario/habitacionXVivienda.php?ID_VIVIENDA=" + id_vivienda);
    }

    private static class Load_Habitaciones_AsyncTask extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                }
                ;
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Habitacion>>() {
            }.getType();
            List<Habitacion> lstHabitaciones = new ArrayList<Habitacion>();
            Seleccion_objeto.nombreHabitaciones = new ArrayList<String>();
            Seleccion_objeto.lstHabitaciones = new ArrayList<Habitacion>();
            try {
                lstHabitaciones = gson.fromJson(resultado, type);
                Seleccion_objeto.lstHabitaciones = lstHabitaciones;
                for(Habitacion h : lstHabitaciones){
                    Seleccion_objeto.nombreHabitaciones.add(h.getNOMBRE());
                }
            }catch (Exception e){
                Toast.makeText(MainActivity.context, R.string.toast_hab_no_asociadas, Toast.LENGTH_SHORT).show();
            }

            Seleccion_objeto.spinHabitacion.setAdapter(new ArrayAdapter<String>(MainActivity.context, android.R.layout.simple_spinner_item, Seleccion_objeto.nombreHabitaciones));

        }
    }

    public static void habitaciones(String nombre) {
        nombre = nombre.replaceAll(" ", "%20");
        new LogicSeleccion.Load_Nombre_Habitaciones_AsyncTask().execute(Data.HOST + "/inventario/getIdViviendaByName.php?NOMBRE=" + nombre);
    }

    private static class Load_Nombre_Habitaciones_AsyncTask extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                }
                ;
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Vivienda>>() {
            }.getType();
            List<Vivienda> lstViviendas = new ArrayList<Vivienda>();
            lstViviendas = gson.fromJson(resultado, type);
            habitaciones(lstViviendas.get(0).getID_VIVIENDA());
        }
    }

    public static void espacios(int id_habitacion) {
        new LogicSeleccion.Load_Espacios_AsyncTask().execute(Data.HOST + "/inventario/espacioXHabitacion.php?ID_HABITACION=" + id_habitacion);
    }

    private static class Load_Espacios_AsyncTask extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                }
                ;
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Espacio>>() {
            }.getType();
            List<Espacio> lstEspacios = new ArrayList<Espacio>();
            Seleccion_objeto.nombreEspacios = new ArrayList<String>();
            Seleccion_objeto.lstEspacios = new ArrayList<Espacio>();
            try {
                lstEspacios = gson.fromJson(resultado, type);
                Seleccion_objeto.lstEspacios = lstEspacios;
                for(Espacio e : lstEspacios){
                    Seleccion_objeto.nombreEspacios.add(e.getNOMBRE());
                }
            }catch (Exception e){
                Toast.makeText(MainActivity.context, R.string.toast_espacio_no_asociado, Toast.LENGTH_SHORT).show();
            }

            Seleccion_objeto.spinEspacio.setAdapter(new ArrayAdapter<String>(MainActivity.context, android.R.layout.simple_spinner_item, Seleccion_objeto.nombreEspacios));
        }
    }

    public static void espacios(String nombre) {
        nombre = nombre.replaceAll(" ", "%20");
        new LogicSeleccion.Load_Nombre_Espacios_AsyncTask().execute(Data.HOST + "/inventario/getIdHabitacionByName.php?NOMBRE=" + nombre);
    }

    private static class Load_Nombre_Espacios_AsyncTask extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                }
                ;
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Habitacion>>() {
            }.getType();
            List<Habitacion> lstHabitaciones = new ArrayList<Habitacion>();
            lstHabitaciones = gson.fromJson(resultado, type);
            espacios(lstHabitaciones.get(0).getID_HABITACION());

        }
    }

    public static void getIdEspacioByName(String nombre) {
        nombre = nombre.replaceAll(" ", "%20");
        new LogicSeleccion.Load_Espacios_By_Name().execute(Data.HOST + "/inventario/getIdEspacioByName.php?NOMBRE=" + nombre);
    }

    private static class Load_Espacios_By_Name extends AsyncTask<String, Void, Void> {
        String resultado;

        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String str = "";
                while ((stringBuffer = br.readLine()) != null) {
                    str = String.format("%s%s", str, stringBuffer);
                }
                ;
                br.close();
                resultado = str;
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Espacio>>() {
            }.getType();
            List<Espacio> lstEspacios = new ArrayList<Espacio>();
            lstEspacios = gson.fromJson(resultado, type);
            ControlTarjetas.getObjetos(lstEspacios.get(0).getID_ESPACIO());

        }
    }
}
