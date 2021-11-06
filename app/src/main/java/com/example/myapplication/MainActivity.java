package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void browse_task(View v){
        Intent browse = new Intent(this, Task.class);
        startActivity(browse);
    }

    public void browse_reminders(View v){
        Intent browse = new Intent(this, Reminders.class);
        startActivity(browse);
    }

    public void browse_settings(View v){
        Intent browse = new Intent(this, Settings.class);
        startActivity(browse);
    }

    public void browse_profile(View v){
        Intent browse = new Intent(this, Profile.class);
        startActivity(browse);
    }
}