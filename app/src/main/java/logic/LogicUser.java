package logic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.victor.inventario.MainActivity;
import com.victor.inventario.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import controller.ControlTarjetas;
import model.Data;
import model.User;
import model.Vivienda;

public class LogicUser {

    public static User user;

    public static void log(String txtUser, String txtPass) {
        new Load_User_AsyncTask().execute(Data.HOST + "/inventario/getUsuarioCompleto.php?NOMBRE=" + txtUser + "&PASS=" + txtPass);
    }

    private static class Load_User_AsyncTask extends AsyncTask<String, Void, Void> {
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
                System.out.println(resultado);
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {
            }.getType();
            List<User> lstUsers = new ArrayList<User>();
            lstUsers = gson.fromJson(resultado, type);
            if(lstUsers.size()> 0){
                Toast.makeText(MainActivity.context, R.string.toast_usuario_correcto, Toast.LENGTH_SHORT).show();
                LogicSeleccion.viviendas(lstUsers.get(0).getID_USER());
            }else{
                Toast.makeText(MainActivity.context, R.string.toast_usuario_incorrecto, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void reg(String txtUser) {
        new Load_User1_AsyncTask().execute(Data.HOST + "/inventario/getUserName.php?");
    }

    private static class Load_User1_AsyncTask extends AsyncTask<String, Void, Void> {
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
                System.out.println(resultado);
            } catch (IOException e) {
                resultado = e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Type type = new TypeToken<List<User>>() {
            }.getType();
            List<User> listUsers = new Gson().fromJson(resultado, type);
            compare1(listUsers);
        }

        private void compare1(List<User> listUsers) {
            int iCont = 0;
            for(User u : listUsers) {
                if (MainActivity.txtUser.getText().toString().equals(u.getNOMBRE())) {
                    iCont++;
                }
            }
            if(iCont > 0) {
                Toast.makeText(MainActivity.context, R.string.toast_usuario_existente, Toast.LENGTH_SHORT).show();
            }else{
                new Load_User2_AsyncTask().execute(Data.HOST + "/inventario/insertUser.php?NOMBRE=" + MainActivity.txtUser.getText().toString() + "&PASS=" + MainActivity.txtPass.getText().toString());
                Toast.makeText(MainActivity.context, R.string.toast_usuario_registrado, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private static class Load_User2_AsyncTask extends AsyncTask<String, Void, Void> {
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
            if(resultado.equals("ok")){
                Toast.makeText(MainActivity.context,  R.string.toast_usuario_registrado, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.context, R.string.toast_usuario_existente, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
