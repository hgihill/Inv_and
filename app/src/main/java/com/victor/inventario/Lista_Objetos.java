package com.victor.inventario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import logic.Adapter;

public class Lista_Objetos extends AppCompatActivity {

    public static Context context;
    public static RecyclerView RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_objetos);
        context = getApplicationContext();

        RV = findViewById(R.id.RV);
        RV.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        RV.setLayoutManager(llm);

        logic.Adapter adapter = new Adapter(this);
        RV.setAdapter(adapter);
        adapter.refresh();
    }
}