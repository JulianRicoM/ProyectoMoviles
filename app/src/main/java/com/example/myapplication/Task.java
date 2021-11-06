package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Task extends AppCompatActivity {

    List<List_element> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        task_list();
    }

    public void task_list() {

        elements = new ArrayList<>();

        elements.add(new List_element("black", "Dispositivos moviles", "proyecto",
                "Activo", "Alto"));

        elements.add(new List_element("white", "Dispositivos moviles", "talle",
                "Activo", "Medio"));

        elements.add(new List_element("blue", "Redes neuronales", "proyecto",
                "Activo", "bajo"));

        ListAdd add = new ListAdd(elements, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_task_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(add);
    }
}