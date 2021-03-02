package com.victor.inventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import logic.LogicUser;

public class MainActivity extends AppCompatActivity {
    public static EditText txtUser;
    public static EditText txtPass;
    public static Button btnReg;
    public static Button btnLog;
    public static Context context;
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onResume();

        context = getApplicationContext();
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnReg = findViewById(R.id.btnReg);
        btnLog = findViewById(R.id.btnLog);

        final MediaPlayer sonidoBoton = MediaPlayer.create(this, R.raw.boton);
        final MediaPlayer sonidoError = MediaPlayer.create(this, R.raw.error_boton);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String user = MainActivity.preferences.getString("user", "");
        String pass = MainActivity.preferences.getString("pass", "");
        txtUser.setText(user);
        txtPass.setText(pass);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUser.getText().toString().equals("") || txtPass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.toast_relleno_log, Toast.LENGTH_SHORT).show();
                    desactivarSonido(sonidoError);
                }else{
                    LogicUser.log(txtUser.getText().toString(), txtPass.getText().toString());
                    SharedPreferences.Editor editorPreferences = MainActivity.preferences.edit();
                    editorPreferences.putString("user", txtUser.getText().toString());
                    editorPreferences.putString("pass", txtPass.getText().toString());
                    editorPreferences.apply();
                    desactivarSonido(sonidoBoton);
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUser.getText().toString().equals("") || txtPass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.toast_campos_registro, Toast.LENGTH_SHORT).show();
                    desactivarSonidoError(sonidoError);
                }else{
                    desactivarSonido(sonidoBoton);
                    LogicUser.reg(txtUser.getText().toString());
                }
            }
        });

    }

    public static void desactivarSonidoError(MediaPlayer sonidoError){
        if(MainActivity.preferences.getBoolean("vol",true) && MainActivity.preferences.getBoolean("vol_error", true)){
            sonidoError.start();
        }else if(MainActivity.preferences.getBoolean("vol", true) && MainActivity.preferences.getBoolean("vol_error", false)){
            sonidoError.stop();
        }else if(MainActivity.preferences.getBoolean("vol", false)){
            sonidoError.stop();
        }
    }

    public static void desactivarSonido(MediaPlayer sonidoBoton){

        if(MainActivity.preferences.getBoolean("vol",true) && MainActivity.preferences.getBoolean("vol_boton", true)){
            sonidoBoton.start();
        }else if(MainActivity.preferences.getBoolean("vol", true) && MainActivity.preferences.getBoolean("vol_boton", false)){
            sonidoBoton.stop();
        }else if(MainActivity.preferences.getBoolean("vol", false)){
            sonidoBoton.stop();
        }
    }
}