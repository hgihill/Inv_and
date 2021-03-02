package logic;

import android.os.AsyncTask;
import android.widget.Toast;

import com.victor.inventario.Objeto_detalle;
import com.victor.inventario.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class CRUD {

    public static class  update extends AsyncTask<String, Void, Void> {
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
            Toast.makeText(Objeto_detalle.context, R.string.toast_info_actulizada, Toast.LENGTH_SHORT).show();
        }
    }

    public static class  delete extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (IOException e) {
                Toast.makeText(Objeto_detalle.context, R.string.toast_problema, Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(Objeto_detalle.context, R.string.toast_objeto_borrado, Toast.LENGTH_SHORT).show();
        }
    }
}
