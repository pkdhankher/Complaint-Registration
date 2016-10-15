package com.example.pawan.complaintregistration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageButton;

import static com.example.pawan.complaintregistration.R.id.spinner;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void clickPic(View view) {
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicture, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

            Intent newactivity = new Intent(this, AddDetails.class);
             newactivity.putExtra("dhankher", imageBitmap);
            startActivity(newactivity);

        }

    }
    public void clkLogin(View view){

        startActivity(new Intent(this, Login.class));
    }
    public void clkHelp(View view){

        startActivity((new Intent(this, Help.class)));


         /*   spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()


            {
                public void onItemSelected (AdapterView < ? > parent, View view,int position, long id)
                {
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    if (selectedItem.equals("Add new category")) {
                        // code
                    }

                  else  if (selectedItem.equals("Add new category")) {
                        // code
                    }

                 else   if (selectedItem.equals("Add new category")) {
                        // code
                    }

                 else   if (selectedItem.equals("Add new category")) {
                        // code
                    }

                  else  if (selectedItem.equals("Add new category")) {
                        // code
                    }
                }

            public void onNothingSelected(AdapterView<?> parent) {

            }
       });      */

    }
}