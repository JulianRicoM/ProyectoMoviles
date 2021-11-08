package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainOptions extends AppCompatActivity implements View.OnClickListener {

    Button registerbtn, loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_main_options);
        getSupportActionBar().hide();

        registerbtn = findViewById(R.id.register_btn);
        registerbtn.setOnClickListener(this);
        loginbtn = findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_btn:
                startActivity(new Intent(MainOptions.this, Register_activity.class));
                break;
            case R.id.login_btn:
                startActivity(new Intent(MainOptions.this, Login_activity.class));
                break;
        }
    }
}
