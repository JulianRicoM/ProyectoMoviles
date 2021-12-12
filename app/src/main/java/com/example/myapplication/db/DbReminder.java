package com.example.myapplication.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.myapplication.List_element_reminders;

import java.util.ArrayList;
import java.util.List;


public class DbReminder extends DbHelperReminder {
    Context context;



    public DbReminder(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertarReminder(String nombre, String descripcion, String fecha, String typeTask){

        long id = 0;
        try {

            DbHelperReminder dbHelper = new DbHelperReminder(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("fecha", fecha);
            values.put("typeTask", typeTask);

            id = db.insert(TABLE_REMINDER, null, values);

        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<List_element_reminders> mostrarReminder(){
        DbHelperReminder dbHelper = new DbHelperReminder(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<List_element_reminders> listaReminder = new ArrayList<>();
        List_element_reminders list_element =null;
        Cursor cursorEReminder = null;

        cursorEReminder = db.rawQuery("SELECT * FROM "+ TABLE_REMINDER, null);

        if(cursorEReminder.moveToFirst()){
            do{
                list_element = new List_element_reminders();
                list_element.setId_reminder(cursorEReminder.getInt(0));
                list_element.setName_reminder(cursorEReminder.getString(1));
                list_element.setDescription_reminder(cursorEReminder.getString(2));
                list_element.setFecha_reminder(cursorEReminder.getString(3));
                list_element.setType_reminder(cursorEReminder.getString(4));
                list_element.setStatus_reminder(true);

                listaReminder.add(list_element);
            }while ((cursorEReminder.moveToNext()));
        }
        cursorEReminder.close();
        return listaReminder;
    }
}