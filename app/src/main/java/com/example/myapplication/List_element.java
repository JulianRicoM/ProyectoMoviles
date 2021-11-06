package com.example.myapplication;

public class List_element {

    public String color;
    public String name;
    public String description;
    public String status;
    public String type_task;

    public List_element(String color, String name, String description, String status, String type_task) {
        this.color = color;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type_task = type_task;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType_task() {
        return type_task;
    }

    public void setType_task(String type_task) {
        this.type_task = type_task;
    }
}
