package com.example.myapplication;

public class User {
    public String name, email, photo;

    public  User(){}

    public User(String name, String email, String photo){
        this.name = name;
        this.email = email;
        this.photo = photo;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }

}
