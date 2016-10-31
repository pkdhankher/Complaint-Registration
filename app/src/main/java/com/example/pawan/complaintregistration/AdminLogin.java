package com.example.pawan.complaintregistration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    EditText e1;
    EditText e2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


         e1 = (EditText) findViewById(R.id.editText2);
         e2 = (EditText) findViewById(R.id.editText3);

    }
         public void onclick(View v) {
             String s1 = e1.getText().toString();
             String s2 = e2.getText().toString();

             if (s1.equals("pawan") && (s2.equals("123"))) {

                 startActivity(new Intent(this, UpdateComplaints.class));

                 Toast.makeText(this, "Welcome to admin panel", Toast.LENGTH_LONG).show();
             } else {
                 Toast.makeText(this, "Wrong user name or password", Toast.LENGTH_LONG).show();
             }

         }

    }


