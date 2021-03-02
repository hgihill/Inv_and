package com.victor.inventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import controller.ControlTarjetas;
import logic.LogicSeleccion;
import logic.LogicUser;
import logic.LogicaTarjetas;
import model.Espacio;
import model.Habitacion;
import model.Objeto;
import model.Vivienda;

public class Seleccion_objeto extends AppCompatActivity {
    public static Spinner spinVivienda;
    public static Spinner spinHabitacion;
    public static Spinner spinEspacio;
    public static Button btnSiguiente;
    public static Context context;
    public static List<Vivienda> lstViviendas = new ArrayList<>();
    public static List<String> nombreViviendas = new ArrayList<>();
    public static List<Habitacion> lstHabitaciones = new ArrayList<>();
    public static List<String> nombreHabitaciones = new ArrayList<>();
    public static List<Espacio> lstEspacios = new ArrayList<>();
    public static List<String> nombreEspacios = new ArrayList<>();
    public static List<Objeto> lstObjetos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_objeto);
        onResume();

        context = getApplicationContext();
        spinVivienda = findViewById(R.id.spinVivienda);
        spinHabitacion = findViewById(R.id.spinRoom);
        spinEspacio = findViewById(R.id.spinEspacio);
        btnSiguiente = findViewById(R.id.btnSeleccionSiguiente);

        final MediaPlayer sonidoBoton = MediaPlayer.create(this, R.raw.boton);

        for(Vivienda v : lstViviendas){
            nombreViviendas.add(v.getNOMBRE());
        }

        spinVivienda.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombreViviendas));

        spinVivienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogicSeleccion.habitaciones(spinVivienda.getSelectedItem()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for(Habitacion h : lstHabitaciones){
            nombreHabitaciones.add(h.getNOMBRE());
        }

        spinHabitacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombreHabitaciones));

        spinHabitacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogicSeleccion.espacios(spinHabitacion.getSelectedItem()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        for(Espacio e : lstEspacios){
            nombreEspacios.add(e.getNOMBRE());
        }

        spinEspacio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombreEspacios));

        spinEspacio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSiguiente.setOnClickListener(v -> {
            MainActivity.desactivarSonido(sonidoBoton);
            ControlTarjetas.getIdEspacioByName(spinEspacio.getSelectedItem()+"");
        });
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuBuilder mb = (MenuBuilder) menu;
        mb.setGroupDividerEnabled(true);
        mb.setOptionalIconsVisible(true);
        mb.findItem(R.id.itmPreferences).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.itmPreferences:
                Intent preferencias = new Intent(this, Preferencias.class);
                startActivity(preferencias);
                break;
        }
        return true;
    }

    public void onItemSelected(AdapterView<?> MainActivity, View v, int pos, long id){
        Context context;
        CharSequence text;
    }
}

