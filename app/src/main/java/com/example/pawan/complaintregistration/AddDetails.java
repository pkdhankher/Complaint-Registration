package com.example.pawan.complaintregistration;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddDetails extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6;
    Button  b1;
    Bitmap img;
    byte[]  imageInByte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        e1 = (EditText) findViewById(R.id.street);
        e2 = (EditText) findViewById(R.id.colony);
        e3 = (EditText) findViewById(R.id.city);
        e4 = (EditText) findViewById(R.id.zip);
        e5 = (EditText) findViewById(R.id.phone);
        e6 = (EditText) findViewById(R.id.cd);
        b1 = (Button) findViewById(R.id.submit);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        String street = e1.getText().toString();
        String colony = e2.getText().toString();
        String city = e3.getText().toString();
        String zipcode = e4.getText().toString();
        String phoneno = e5.getText().toString();
        String complaintdetails = e6.getText().toString();

    // try {

        img = (Bitmap) getIntent().getExtras().get("dhankher");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        img.compress(Bitmap.CompressFormat.PNG, 100, stream);
        imageInByte = stream.toByteArray();


    /*  }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
      } */
       // String image = Base64.encodeToString(imageInByte, Base64.NO_WRAP);
        String image = Base64.encodeToString(imageInByte, Base64.DEFAULT);


        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        try{
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Uploading :) ");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
            String result=backgroundWorker.execute(type,street,colony,city,zipcode,phoneno,complaintdetails,image).get();
            Log.d("After query", "login() called with: " + result + "");
            progress.cancel();
        }
        catch (java.lang.InterruptedException e){

        }
        catch (java.util.concurrent.ExecutionException e){
gi
        }
    }
}
