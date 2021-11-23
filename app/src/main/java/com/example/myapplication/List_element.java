package com.example.myapplication;

import java.io.Serializable;

public class List_element implements Serializable {

    private int id;
    private int imageId;
    private String name;
    private String description;
    private Boolean status;
    private String fecha;
    private String type_task;

    public List_element(int id, int imageId, String name, String description, Boolean status,
                         String type_task, String fecha) {

        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type_task = type_task;
        this.fecha = fecha;
    }

    public List_element() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
