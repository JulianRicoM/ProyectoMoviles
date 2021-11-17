package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTask extends AppCompatActivity {

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

        //<<---------------- Dropdown ---------------->>

        autoCompleteItems = findViewById(R.id.autoCompleteItems);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item_task, items);
        autoCompleteItems.setAdapter(adapterItems);

        autoCompleteItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });

        //<<---------------- Calendar ---------------- >>

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


        Button btn = findViewById(R.id.btn_add_task);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name_task = findViewById(R.id.register_task_name);
                EditText description_task =findViewById(R.id.register_description_task);

                String name_t = name_task.getText().toString();
                String description_t = description_task.getText().toString();

                List<List_element> elements = new ArrayList<>();

                elements.add(new List_element(R.drawable.alerta, name_t, description_t, true, "Alto"));

                Task add = new Task();
                //add.add_task(elements);

                Intent browse = new Intent(AddTask.this, Task.class);
                startActivity(browse);
            }
        });
    }
}