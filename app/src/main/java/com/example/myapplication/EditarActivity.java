package com.example.myapplication;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbTask;

import java.util.Calendar;

public class EditarActivity extends AppCompatActivity {


    Button btn;

    EditText register_task_name, register_description_task, register_date_task;
    boolean correcto = false;
    List_element list_element;
    int id = 0;

    //<<---------------- Dropdown ---------------->>
    AutoCompleteTextView autoCompleteItems;
    ArrayAdapter<String> adapterItems;
    String[] items = {"Prorrogable", "Deseable", "Urgente"};
    //<<---------------- Calendar ---------------- >>
    TextView task_date;
    DatePickerDialog.OnDateSetListener setListener;

    Button btna,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        register_task_name = findViewById(R.id.register_task_name);
        autoCompleteItems = findViewById(R.id.Dropdown);
        register_description_task = findViewById(R.id.register_description_task);
        register_date_task = findViewById(R.id.register_date_task);

        btna = findViewById(R.id.btn_add_task);
        btna.setVisibility(View.INVISIBLE);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditarActivity.this,
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
        DbTask dbTask = new DbTask(EditarActivity.this);
        list_element = dbTask.verTask(id);

        if (list_element != null) {
            register_task_name.setText(list_element.getName());
            register_description_task.setText(list_element.getDescription());
            register_date_task.setText(list_element.getFecha());

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!register_task_name.getText().toString().equals("")) {

                    correcto = dbTask.editarTask(id, register_task_name.getText().toString(),
                            register_description_task.getText().toString(),
                            register_date_task.getText().toString(), autoCompleteItems.getText().toString());

                    if (correcto) {
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL REGISTRAR CAMBIOS", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void verRegistro() {
        onBackPressed();
    }
}