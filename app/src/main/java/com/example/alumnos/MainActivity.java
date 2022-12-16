package com.example.alumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.alumnos.ListaContactosAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;
    FloatingActionButton fabNuevo;
    ListaContactosAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtBuscar = findViewById(R.id.txtBuscar);
        listaArrayContactos = new ArrayList<>();
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        fabNuevo = findViewById(R.id.favNuevo);

        DbContactos dbContactos = new DbContactos(MainActivity.this);

        adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());
        listaContactos.setAdapter(adapter);

        int orientation=this.getResources().getConfiguration().orientation;
        if (orientation== Configuration.ORIENTATION_PORTRAIT){
            listaContactos.setLayoutManager(new GridLayoutManager(this,1));
        }
        else {
            listaContactos.setLayoutManager(new GridLayoutManager(this,2));
        }

        fabNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoRegistro();
            }
        });

        txtBuscar.setOnQueryTextListener(this);
    }


    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}