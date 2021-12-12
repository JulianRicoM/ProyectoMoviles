package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
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
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.myapplication.db.DbHelper;
import com.example.myapplication.db.DbHelperReminder;
import com.example.myapplication.db.DbReminder;


import java.util.Calendar;

public class AddReminder extends AppCompatActivity {

    Button btn;

    EditText register_reminder_name,register_description_reminder,register_date_reminder;

    //<<---------------- Dropdown ---------------->>
    AutoCompleteTextView autoCompleteItems;
    ArrayAdapter<String> adapterItems;
    String[] items = {"Prorrogable", "Deseable", "Urgente"};

    //<<---------------- Calendar ---------------- >>
    TextView reminder_date;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        //<<-------------------- interface values assignment ------------------------->>
        register_reminder_name = findViewById(R.id.register_reminder_name);
        autoCompleteItems = findViewById(R.id.Dropdown);
        register_description_reminder = findViewById(R.id.register_description_reminder);
        register_date_reminder = findViewById(R.id.register_date_reminder);

        //<<-------------------------------- Dropdown -------------------------------->>

        autoCompleteItems = findViewById(R.id.Dropdown);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item_task, items);
        autoCompleteItems.setAdapter(adapterItems);

        autoCompleteItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Log.d("ejemplo", item);
            }
        });

        //<<-------------------------------- Calendar -------------------------------->>

        reminder_date = findViewById(R.id.register_date_reminder);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        reminder_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddReminder.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day + "/" + month + "/" + year;
                                reminder_date.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        //<<-------------------------------- Btn_add_task -------------------------------->>


        btn = findViewById(R.id.btn_add_reminder);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelperReminder dbHelper = new DbHelperReminder(AddReminder.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                /*if (db != null) {
                    Toast.makeText(AddReminder.this, "TAREA CREADA CON EXITO", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(AddReminder.this, "ERROR AL CREAR LA BASE DE DATOS", Toast.LENGTH_LONG).show();
                }*/


                DbReminder dbReminder = new DbReminder(AddReminder.this);
                long id = dbReminder.insertarReminder(String.valueOf(register_reminder_name.getText()),
                        String.valueOf(register_description_reminder.getText()),
                        String.valueOf(reminder_date.getText()),
                        String.valueOf(autoCompleteItems.getText()));

                onBackPressed();
                //Intent browse = new Intent(AddReminder.this, Reminders.class);
                //startActivity(browse);

            }
        });

    }
    private void limpiar(){
        register_reminder_name.setText("");
        register_description_reminder.setText("");
        register_date_reminder.setText("");
    }
}