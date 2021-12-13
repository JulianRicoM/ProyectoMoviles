package com.example.myapplication;

import android.app.DatePickerDialog;
import android.net.DnsResolver;
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

import com.example.myapplication.db.DbReminder;
import com.example.myapplication.db.DbTask;

import java.util.Calendar;

public class EditarActivityReminder extends AppCompatActivity {


    Button btn;

    EditText register_reminder_name,register_description_reminder,register_date_reminder;
    boolean correcto = false;
    List_element_reminders list_element_reminders;
    int id = 0;

    //<<---------------- Dropdown ---------------->>
    AutoCompleteTextView autoCompleteItems;
    ArrayAdapter<String> adapterItems;
    String[] items = {"Prorrogable", "Deseable", "Urgente"};
    //<<---------------- Calendar ---------------- >>
    TextView reminder_date;
    DatePickerDialog.OnDateSetListener setListener;

    Button btna,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_reminder);

        register_reminder_name = findViewById(R.id.register_reminder_name);
        autoCompleteItems = findViewById(R.id.Dropdown);
        register_description_reminder = findViewById(R.id.register_description_reminder);
        register_date_reminder = findViewById(R.id.register_date_reminder);

        btna = findViewById(R.id.btn_add_reminder);

        btnEliminar = findViewById(R.id.btnBorrar);
        btnEliminar.setVisibility(View.INVISIBLE);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarActivityReminder.this,
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

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);

            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbReminder dbReminder = new DbReminder(EditarActivityReminder.this);
        list_element_reminders = dbReminder.verReminder(id);

        if (list_element_reminders != null) {
            register_reminder_name.setText(list_element_reminders.getName_reminder());
            register_description_reminder.setText(list_element_reminders.getDescription_reminder());
            register_date_reminder.setText(list_element_reminders.getFecha_reminder());

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!register_reminder_name.getText().toString().equals("")) {

                    correcto = dbReminder.editarReminder(id, register_reminder_name.getText().toString(),
                            register_description_reminder.getText().toString(),
                            register_date_reminder.getText().toString(), autoCompleteItems.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarActivityReminder.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivityReminder.this, "ERROR AL REGISTRAR CAMBIOS", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivityReminder.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void verRegistro() {
        onBackPressed();
    }
}