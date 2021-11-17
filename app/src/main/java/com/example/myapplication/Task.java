package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class Task extends Fragment {

    List<List_element> elements;
    List<List_element> elements1;
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
                //newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newIntent);
            }
        });

        elements1 = new ArrayList<>();
        elements1= task_list();

        ListAdd listAdapter =  new ListAdd(elements, getActivity(), new ListAdd.OnItemClickListener() {
            @Override
            public void onItemClick(List_element item) {
                change_status(item);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.recycler_task_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);

        return root;
    }

    public void change_status(List_element item){

        if(item.getStatus()){
            item.setStatus(false);

        }else{
            item.setStatus(true);
        }
    }

    public List<List_element> task_list() {

        elements =  new ArrayList<>();

        elements.add(new List_element(R.drawable.alerta, "Dispositivos moviles", "proyecto",
                true, "Alto"));

        elements.add(new List_element(R.drawable.alerta, "Dispositivos moviles", "talle",
                false, "Medio"));

        elements.add(new List_element(R.drawable.alerta, "Redes neuronales", "proyecto",
                true, "bajo"));

        return elements;

    }


}