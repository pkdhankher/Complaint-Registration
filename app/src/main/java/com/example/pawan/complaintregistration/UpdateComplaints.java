package com.example.pawan.complaintregistration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateComplaints extends AppCompatActivity {
    EditText e1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_complaints);

        e1 = (EditText) findViewById(R.id.editText4);
        b1 = (Button) findViewById(R.id.changeStatus);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
 public void login() {
            String id = e1.getText().toString();
            String type = "update";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, id);
     Toast.makeText(this,"ID "+id+" has been updated",Toast.LENGTH_LONG).show();

 }
    }
