package com.example.pawan.complaintregistration;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Pawan on 10/1/2016.
 */

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params)
    {

        String type = params[0];
        String logurl = "http://172.16.14.151/pp1.php";


        if (type.equals("login"))
        {
            try {
                String street = params[1];
                String colony = params[2];
                String city = params[3];
                String zipcode = params[4];
                String phoneno = params[5];
                String complaintdetails = params[6];
                String image = params[7];
                URL url = new URL(logurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String postdata = URLEncoder.encode("street", "UTF-8") + "=" + URLEncoder.encode(street, "UTF-8") + "&"
                        + URLEncoder.encode("colony", "UTF-8") + "=" + URLEncoder.encode(colony, "UTF-8") + "&"
                + URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&"
                + URLEncoder.encode("zipcode", "UTF-8") + "=" + URLEncoder.encode(zipcode, "UTF-8") + "&"
                + URLEncoder.encode("phoneno", "UTF-8") + "=" + URLEncoder.encode(phoneno, "UTF-8") + "&"
                + URLEncoder.encode("complaintdetails", "UTF-8") + "=" + URLEncoder.encode(complaintdetails, "UTF-8") + "&"
                +URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8");
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("login status");
    }


    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }
    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }


}

