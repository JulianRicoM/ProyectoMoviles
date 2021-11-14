package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Task extends Fragment implements View.OnClickListener {

    List<List_element> elements;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_task, container, false);

        task_list();
        return root;
    }

    public void task_list() {

        elements =  new ArrayList<>();

        elements.add(new List_element("black", "Dispositivos moviles", "proyecto",
                "Activo", "Alto"));

        elements.add(new List_element("white", "Dispositivos moviles", "talle",
                "Activo", "Medio"));

        elements.add(new List_element("blue", "Redes neuronales", "proyecto",
                "Activo", "bajo"));


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.añadir_tarea:
                //browse_create_task();
                break;
        }
    }

    public void browse_create_task(){

        Intent intent = new Intent(getActivity(), Reminders.class);
        startActivity(intent);
    }

}