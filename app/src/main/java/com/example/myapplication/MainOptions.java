package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainOptions extends AppCompatActivity implements View.OnClickListener {

    Button registerbtn, loginbtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_main_options);
        getSupportActionBar().hide();

        registerbtn = findViewById(R.id.register_btn);
        registerbtn.setOnClickListener(this);
        loginbtn = findViewById(R.id.login_btn);
        loginbtn.setOnClickListener(this);
        mAuth= FirebaseAuth.getInstance();

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

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent newIntent = new Intent(MainOptions.this, MainMenu.class);
            startActivity(newIntent);
            finish();
        }
    }
}
