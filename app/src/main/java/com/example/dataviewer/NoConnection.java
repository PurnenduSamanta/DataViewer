package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NoConnection extends AppCompatActivity {
    ImageButton refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_connection);
        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               NetworkHandeling1 networkHandeling=new NetworkHandeling1();
                networkHandeling.execute();

            }
        });
    }

    private class NetworkHandeling1 extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuffer sb = new StringBuffer();
            HttpURLConnection connection;
            try {
                URL url = new URL("https://testapi.techqueuesolutions.in/showdata/");
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(2000);
                    int status = connection.getResponseCode();
                    if (status == 200) {
                        Scanner fetch = new Scanner(new InputStreamReader(connection.getInputStream()));
                        while (fetch.hasNextLine()) {
                            sb.append(fetch.nextLine());
                        }
                        connection.disconnect();
                        return sb.toString();
                    } else {
                        return null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Intent intent = new Intent(NoConnection.this, rc_view.class);
                intent.putExtra("result", s);
                startActivity(intent);
            } else {
                Toast.makeText(NoConnection.this, "Something Wrong Happend", Toast.LENGTH_SHORT).show();

            }

        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAffinity();
                        System.exit(0);
                    }
                }).create().show();
    }



}