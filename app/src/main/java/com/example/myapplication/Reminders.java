package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Reminders extends Fragment {

    List<List_element_reminders> elements;
    private Button btnadd;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_reminders, container, false);

        btnadd = root.findViewById(R.id.add_reminder);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browse = new Intent(getActivity(), AddReminder.class);
                startActivity(browse);
            }
        });

        elements = new ArrayList<>();
        elements = reminder();

        ListAddReminders listAdapter= new ListAddReminders(elements, getActivity(), new ListAddReminders.OnItemLongClickListener() {
            @Override
            public void onItemClick(List_element_reminders item) {
                change_status(item);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.recycler_reminder_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);


        return root;
    }

    public List<List_element_reminders>  reminder(){

        List<List_element_reminders> e = new ArrayList<>();

        e.add(new List_element_reminders(1, R.drawable.urgente, "Pasta de planificar",
                "tomar pasta anti bebés", true, "10/12/22","Urgente" ));
        e.add(new List_element_reminders(1, R.drawable.deseable, "Ejercicios",
                "Salir atrotar", true, "10/12/22","Deseable" ));
        return e;

    }

    public void change_status(List_element_reminders item){

        if(item.getStatus_reminder()){
            item.setStatus_reminder(false);

        }else{
            item.setStatus_reminder(true);
        }
    }

    
}