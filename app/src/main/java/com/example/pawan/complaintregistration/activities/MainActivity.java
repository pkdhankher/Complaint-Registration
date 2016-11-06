package com.example.pawan.complaintregistration.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pawan.complaintregistration.BitmapDetails;
import com.example.pawan.complaintregistration.R;
import com.example.pawan.complaintregistration.workers.BackgroundWorker;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private static final int REQUEST_PICK_IMAGE = 1;
    ImageButton imgbtn;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String spresult;
    int check = 0;
    String TAG = "MainACtivity";

    public MainActivity() throws ExecutionException, InterruptedException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = 0;


        spinner = (Spinner) findViewById(R.id.spinner);
        String [] array =getResources().getStringArray(R.array.city_arrays);
        adapter= new ArrayAdapter<CharSequence>(this,R.layout.spinner_layout,array);
        adapter.setDropDownViewResource (android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter (adapter);

        //spinner = (Spinner) findViewById(R.id.spinner);
        //adapter = ArrayAdapter.createFromResource(this, R.array.city_arrays, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                check = check + 1;
                Log.d(TAG, "oncheckincrement() called with: " + "parent = [" + check + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                if (check > 1) {
                    spresult = parent.getItemAtPosition(position).toString();
                    Log.d(TAG, "onItemSelected() called with: " + "parent = [" + parent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                    login();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void clickPic(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, "Camra not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void selectPic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap imageBitmap = null;

        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_CAPTURE:
                    imageBitmap = (Bitmap) data.getExtras().get("data");
                    break;

                case REQUEST_PICK_IMAGE:
                    try {
                        Uri filePath = data.getData();
                        imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    } catch (IOException e) {
                        imageBitmap = null;
                        e.printStackTrace();
                    }
                    break;
            }

        }
        BitmapDetails.setBitmap(imageBitmap);

        if (BitmapDetails.getBitmap() != null) {
            Intent newactivity = new Intent(this, AddDetails.class);
            startActivity(newactivity);
        } else {
            Toast.makeText(MainActivity.this, "Something went Wrong, Try again!", Toast.LENGTH_SHORT).show();
        }
    }


    public void clkLogin(View view) {
        startActivity(new Intent(MainActivity.this, Login.class));
    }

    public void clkHelp(View view) {

        startActivity((new Intent(MainActivity.this, Help.class)));

    }

    public void login() {

        String type = "city";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        try {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Getting Data : ");
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();
            String result = backgroundWorker.execute(type, spresult).get();
            progress.cancel();
            Log.d("After query", "login() called with: " + result + "");
            Intent intent = new Intent(this, ShowData.class);
            intent.putExtra("result", result);
            startActivity(intent);
        } catch (java.lang.InterruptedException e) {
            Log.d("exc", "login() called with: " + e + "");

        } catch (java.util.concurrent.ExecutionException e) {
            Log.d("exc", "login() called with: " + e + "");

        }

    }
}