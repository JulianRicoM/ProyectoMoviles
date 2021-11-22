package com.example.myapplication;

import android.app.DatePickerDialog;

import java.io.Serializable;
import java.util.Date;

public class List_element implements Serializable {

    public int imageId;
    public String name;
    public String description;
    public Boolean status;
    public String type_task;
    public String fecha;

    public List_element(int imageId, String name, String description, Boolean status,
                        String type_task, String fecha) {
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type_task = type_task;
        this.fecha = fecha;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getType_task() {
        return type_task;
    }

    public void setType_task(String type_task) {
        this.type_task = type_task;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
