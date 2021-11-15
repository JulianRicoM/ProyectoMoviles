package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Task extends Fragment {

    List<List_element> elements;
    private Button btnAñadir;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_task, container, false);

        btnAñadir = root.findViewById(R.id.añadir_tarea);
        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getActivity(), AddTask.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newIntent);
            }
        });

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
}