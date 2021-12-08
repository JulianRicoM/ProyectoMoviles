package com.example.myapplication;

import java.io.Serializable;

public class List_element_reminders implements Serializable {

    private int id_reminder;
    private int image_reminder;
    private String name_reminder;
    private String description_reminder;
    private Boolean status_reminder;
    private String fecha_reminder;
    private String type_reminder;

    public List_element_reminders(int id_reminder, int image_reminder, String name_reminder,
                                  String description_reminder, Boolean status, String fecha,
                                  String type_reminder) {

        this.id_reminder = id_reminder;
        this.image_reminder = image_reminder;
        this.name_reminder = name_reminder;
        this.description_reminder = description_reminder;
        this.status_reminder = status;
        this.fecha_reminder = fecha;
        this.type_reminder = type_reminder;
    }

    public int getId_reminder() {
        return id_reminder;
    }

    public void setId_reminder(int id_reminder) {
        this.id_reminder = id_reminder;
    }

    public int getImage_reminder() {
        return image_reminder;
    }

    public void setImage_reminder(int image_reminder) {
        this.image_reminder = image_reminder;
    }

    public String getName_reminder() {
        return name_reminder;
    }

    public void setName_reminder(String name_reminder) {
        this.name_reminder = name_reminder;
    }

    public String getDescription_reminder() {
        return description_reminder;
    }

    public void setDescription_reminder(String description_reminder) {
        this.description_reminder = description_reminder;
    }

    public Boolean getStatus_reminder() {
        return status_reminder;
    }

    public void setStatus_reminder(Boolean status_reminder) {
        this.status_reminder = status_reminder;
    }

    public String getFecha_reminder() {
        return fecha_reminder;
    }

    public void setFecha_reminder(String fecha_reminder) {
        this.fecha_reminder = fecha_reminder;
    }

    public String getType_reminder() {
        return type_reminder;
    }

    public void setType_reminder(String type_reminder) {
        this.type_reminder = type_reminder;
    }
}
