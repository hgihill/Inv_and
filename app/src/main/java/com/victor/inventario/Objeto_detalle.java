package com.victor.inventario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import logic.Adapter;
import logic.CRUD;
import model.Data;
import model.Objeto;

public class Objeto_detalle extends AppCompatActivity {

    public static Context context;

    public static EditText txtNombreObj;
    public static EditText txtCantObj;
    public static EditText txtMinObj;
    public static Switch switchConsumible;
    public static EditText txtDescObj;
    public static EditText txtLocObj;
    public static Button btnGuardar;
    public static Button btnBorrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto_detalle);

        txtNombreObj = findViewById(R.id.txtNombreObj);
        txtCantObj = findViewById(R.id.txtCantObj);
        txtMinObj = findViewById(R.id.txtMinObj);
        switchConsumible = findViewById(R.id.switchConsumible);
        txtDescObj = findViewById(R.id.txtDescObj);
        txtLocObj = findViewById(R.id.txtLocObj);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnBorrar = findViewById(R.id.btnBorrar);

        context = getApplicationContext();

        txtNombreObj.setText(Adapter.listaObjetos.get(logic.Adapter.iCont).getNOMBRE());
        txtCantObj.setText("" + Adapter.listaObjetos.get(logic.Adapter.iCont).getCANTIDAD());
        txtMinObj.setText("" + Adapter.listaObjetos.get(logic.Adapter.iCont).getMINIMO());
        txtDescObj.setText(Adapter.listaObjetos.get(logic.Adapter.iCont).getDESCRIPCION());
        txtLocObj.setText(Adapter.listaObjetos.get(logic.Adapter.iCont).getLOCALIZACION());
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new logic.CRUD.update().execute(Data.HOST + "/inventario/updateObjeto.php?CANTIDAD=" + txtCantObj.getText().toString() +"&MINIMO=" + txtMinObj.getText().toString() +
                        "&CONSUMIBLE=" + switchConsumible.isChecked() + "&DESCRIPCION=" + txtDescObj.getText().toString() + "&LOCALIZACION=" + txtLocObj.getText().toString() + "&NOMBRE=" + txtNombreObj.getText().toString());
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo = new AlertDialog.Builder(Objeto_detalle.this);
                dialogo.setTitle(R.string.eliminar_objeto);
                dialogo.setMessage(R.string.confirmar_borrar);
                dialogo.setCancelable(false);
                dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        new CRUD.delete().execute(Data.HOST +  "/inventario/deleteObjeto.php?NOMBRE=" + txtNombreObj.getText().toString());
                    }
                });
                dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        finish();
                    }
                });
                dialogo.show();
            }
        });
    }

}