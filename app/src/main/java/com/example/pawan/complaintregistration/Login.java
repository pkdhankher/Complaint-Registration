package com.example.pawan.complaintregistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

<<<<<<< HEAD
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText e1;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = (EditText)findViewById(R.id.editText);
        Button b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    public void login()
    {
        String phone = e1.getText().toString();

        String type = "get";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type,phone);

        try{
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Getting Data : ");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
            String result=backgroundWorker.execute(type,phone).get();
            progress.cancel();
            Log.d("After query", "login() called with: " + result + "");
            Intent intent=new Intent(Login.this,ShowData.class);
            intent.putExtra("result",result);
            startActivity(intent);
            JSONObject jsonObject = new JSONObject(result);
            Log.d("abc", "login() called with: " +jsonObject.get("count")+ "");
            for(int i=0;i<Integer.parseInt(jsonObject.get("count").toString());i++){
                JSONObject inside=jsonObject.getJSONObject(String.valueOf(i));
                Log.d("abc", inside.getString("street") + " " + inside.getString("colony") + " " + inside.getString("city") + " " + inside.getString("zipcode") + " " +
                        inside.getString("phoneno") + " " + inside.getString("complaintdetails") + " " + inside.getString("id") + " " + inside.getString("image"));
            }
        }
        catch (java.lang.InterruptedException e){
            Log.d("exc", "login() called with: " + e +"");

        }
        catch (java.util.concurrent.ExecutionException e){
            Log.d("exc", "login() called with: " + e +"");

        }
        catch (org.json.JSONException E){
            Log.d("exc", "login() called with: " + E +"");

        }

    }


    public void opnlogin (View view){
        startActivity(new Intent(this, AdminLogin.class));
    }
}

