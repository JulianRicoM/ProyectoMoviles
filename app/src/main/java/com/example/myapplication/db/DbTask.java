package com.example.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.myapplication.List_element;

import java.util.ArrayList;
import java.util.List;

public class DbTask extends DbHelper {
    Context context;

    public DbTask(@Nullable Context context) {

        super(context);
        this.context = context;
    }



    public long insertarTask(String nombre, String descripcion, String fecha, String typeTask){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("fecha", fecha);
            values.put("typeTask", typeTask);

            id = db.insert(TABLE_TASK, null, values);

        } catch (Exception ex){
            ex.toString();
        }

        return id;
    }

    public ArrayList<List_element> mostrarTask(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<List_element> listaTask = new ArrayList<>();
        List_element list_element = null;
        Cursor cursorETask = null;

        cursorETask = db.rawQuery("SELECT * FROM " + TABLE_TASK, null);

        if(cursorETask.moveToFirst()){
            do{
                list_element = new List_element();
                list_element.setId(cursorETask.getInt(0));
                list_element.setImageId(cursorETask.getInt(1));
                list_element.setName(cursorETask.getString(2));
                list_element.setDescription(cursorETask.getString(3));
                list_element.setFecha(cursorETask.getString(4));
                list_element.setType_task(cursorETask.getString(5));
                list_element.setStatus(true);

                listaTask.add(list_element);

            } while (cursorETask.moveToNext());
        }

        cursorETask.close();

        return listaTask;
    }
}
