package logic;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.victor.inventario.Lista_Objetos;
import com.victor.inventario.MainActivity;
import com.victor.inventario.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Espacio;
import model.Objeto;

public class LogicaTarjetas {

    public static class Load_Tarjetas_AsyncTask extends AsyncTask<String, Void, Void> {
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
            Type type = new TypeToken<List<Objeto>>() {
            }.getType();
            Adapter.listaObjetos = gson.fromJson(resultado, type);

            try {
                for (Objeto o : Adapter.listaObjetos) {
                    Log.i("OBJETOS", "" + o);
                }
                Intent intentDetalle = new Intent(MainActivity.context, Lista_Objetos.class);
                MainActivity.context.startActivity(intentDetalle.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }catch (Exception e){
                Toast.makeText(MainActivity.context, R.string.toast_no_objetos_asociados, Toast.LENGTH_SHORT).show();
        }
    }
    }
}


