package com.example.pawan.complaintregistration.activities;

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

import com.example.pawan.complaintregistration.R;
import com.example.pawan.complaintregistration.workers.BackgroundWorker;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int PICK_IMAGE_REQUEST = 1;
    ImageButton imgbtn;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    String spresult;
    int check = 0;
    Uri filePath;
    int me = 0;
    String TAG = "MainACtivity";

    public MainActivity() throws ExecutionException, InterruptedException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        check = 0;


        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.city_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
        }
    }

    public void selectPic(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            Intent newactivity = new Intent(this, AddDetails.class);
            newactivity.putExtra("dhankher", imageBitmap);
            startActivity(newactivity);
        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //     Intent newactivity = new Intent(this, AddDetails.class);
                //    newactivity.putExtra("dhankher", bitmap);
                //     startActivity(newactivity);

            } catch (IOException e) {
                e.printStackTrace();
            }
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