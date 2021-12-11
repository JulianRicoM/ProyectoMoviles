package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.db.DbHelper;
import com.example.myapplication.db.DbTask;

import java.util.ArrayList;

import java.util.Calendar;


public class AddTask extends AppCompatActivity {

    Button btn;

    EditText register_task_name,register_description_task,register_date_task;

    //<<---------------- Dropdown ---------------->>
    AutoCompleteTextView autoCompleteItems;
    ArrayAdapter<String> adapterItems;
    String[] items = {"Prorrogable", "Deseable", "Urgente"};

    //<<---------------- Calendar ---------------- >>
    TextView task_date;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        //<<-------------------- interface values assignment ------------------------->>
        register_task_name = findViewById(R.id.register_task_name);
        autoCompleteItems = findViewById(R.id.Dropdown);
        register_description_task = findViewById(R.id.register_description_task);
        register_date_task = findViewById(R.id.register_date_task);

        //<<-------------------------------- Dropdown -------------------------------->>

        autoCompleteItems = findViewById(R.id.Dropdown);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item_task, items);
        autoCompleteItems.setAdapter(adapterItems);

        autoCompleteItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        //<<-------------------------------- Calendar -------------------------------->>

        task_date = findViewById(R.id.register_date_task);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        task_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddTask.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                task_date.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //<<-------------------------------- Btn_add_task -------------------------------->>


        btn = findViewById(R.id.btn_add_task);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(AddTask.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                /*if (db != null) {
                    Toast.makeText(AddTask.this, "TAREA CREADA CON EXITO", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddTask.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                }*/


                DbTask dbTask = new DbTask(AddTask.this);
                long id = dbTask.insertarTask(String.valueOf(register_task_name.getText()),
                        String.valueOf(register_description_task.getText()),
                        String.valueOf(task_date.getText()),
                        String.valueOf(autoCompleteItems.getText()));

                onBackPressed();

            }
        });
    }

    private void limpiar(){
        register_task_name.setText("");
        register_description_task.setText("");
        register_date_task.setText("");
    }
}