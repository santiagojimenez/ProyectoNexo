package com.example.nexo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter; //
import android.widget.ListView; //

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList; //

public class registroTareas extends AppCompatActivity {

    // 1. Declarar las variables aquí arriba (fuera de los métodos)
    ListView listView;
    ArrayList<String> misTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_tareas);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Conectar con el ID creado en el diseño
        listView = findViewById(R.id.listaTareasRealizadas);

        // Crear la lista de datos
        misTareas = new ArrayList<>();
        misTareas.add("ID: 122, Cliente: Concesionario Luxury Car");
        misTareas.add("ID:253, Cliente: Conjunto Residencial Uribe");
        misTareas.add("ID: 348, Cliente: Funerario La Esperanza");
        misTareas.add("ID: 389, Cliente: Restaurante Las Rosas");
        misTareas.add("ID: 514, Cliente: Grupo Éxito");


        // Crear el adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // Diseño básico de Android
                misTareas
        );

        // Mostrar los datos en la lista
        listView.setAdapter(adapter);

        // -----------------------------------------------
    }

    public void irVolverTareasProgramadas(View view) {
        Intent intent = new Intent(this, trabajosDelDia.class);
        startActivity(intent);
    }
}