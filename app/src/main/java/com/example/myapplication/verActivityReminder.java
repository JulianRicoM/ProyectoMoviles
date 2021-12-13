package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.db.DbReminder;
import com.example.myapplication.db.DbTask;

import java.util.Calendar;

public class verActivityReminder extends AppCompatActivity {


    Button btn,btnEliminar;

    EditText register_reminder_name,register_description_reminder,register_date_reminder;

    List_element_reminders list_element_reminders;
    int id = 0;

    //<<---------------- Dropdown ---------------->>
    AutoCompleteTextView autoCompleteItems;
    ArrayAdapter<String> adapterItems;
    String[] items = {"Prorrogable", "Deseable", "Urgente"};
    //<<---------------- Calendar ---------------- >>
    TextView reminder_date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reminder);

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(verActivityReminder.this,
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


        //btn = findViewById(R.id.btn_add_task);
        btn = findViewById(R.id.btn_add_reminder);
        btnEliminar = findViewById(R.id.btnBorrar);
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);

            }else{
                id =extras.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbReminder dbReminder = new DbReminder(verActivityReminder.this);
        list_element_reminders = dbReminder.verReminder(id);

        if(list_element_reminders != null){
            register_reminder_name.setText(list_element_reminders.getName_reminder());
            register_description_reminder.setText(list_element_reminders.getDescription_reminder());
            autoCompleteItems.setText(list_element_reminders.getType_reminder());
            register_date_reminder.setText(list_element_reminders.getFecha_reminder());
            //btn.setVisibility(View.INVISIBLE);

            register_reminder_name.setInputType(InputType.TYPE_NULL);
            register_description_reminder.setInputType(InputType.TYPE_NULL);
            autoCompleteItems.setInputType(InputType.TYPE_NULL);
            register_date_reminder.setInputType(InputType.TYPE_NULL);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(verActivityReminder.this, EditarActivityReminder.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(verActivityReminder.this);
                builder.setMessage("¿Delete Task?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if( dbReminder.eliminarReminder(id)){
                                    lista();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });















    }
    private void lista(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}