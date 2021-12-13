package com.example.myapplication.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.myapplication.List_element;
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

    public List_element_reminders verReminder(int id){
        DbHelperReminder dbHelperReminder = new DbHelperReminder(context);
        SQLiteDatabase db = dbHelperReminder.getWritableDatabase();


        List_element_reminders list_element_reminders = null;
        Cursor cursorETask = null;

        cursorETask = db.rawQuery("SELECT * FROM " + TABLE_REMINDER + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorETask.moveToFirst()){

            list_element_reminders = new List_element_reminders();
            list_element_reminders.setId_reminder(cursorETask.getInt(0));
            list_element_reminders.setName_reminder(cursorETask.getString(1));
            list_element_reminders.setDescription_reminder(cursorETask.getString(2));
            list_element_reminders.setFecha_reminder(cursorETask.getString(3));
            list_element_reminders.setType_reminder(cursorETask.getString(4));
            list_element_reminders.setStatus_reminder(true);        }

        cursorETask.close();

        return list_element_reminders;
    }



    public boolean editarReminder(int id,String nombre, String descripcion, String fecha, String typeTask){
        boolean correcto = false;
        DbHelperReminder dbHelperReminder = new DbHelperReminder(context);
        SQLiteDatabase db = dbHelperReminder.getWritableDatabase();
        try {


            db.execSQL(" UPDATE " + TABLE_REMINDER + " SET nombre = '"+nombre+"'," +
                    "descripcion = '"+descripcion+"'," +  "fecha = '"+fecha+"',typeTask = '"+typeTask+"" +
                    "' WHERE id='"+id+"' ");

            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarReminder(int id){
        boolean correcto = false;
        DbHelperReminder dbHelperReminder = new DbHelperReminder(context);
        SQLiteDatabase db = dbHelperReminder.getWritableDatabase();
        try {


            db.execSQL(" DELETE FROM " + TABLE_REMINDER + " WHERE id= '"+ id +"'");

            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }

        return correcto;
    }

}