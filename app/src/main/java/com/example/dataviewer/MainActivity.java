package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    Button hm,sd;
    ProgressBar pb;
    EditText name,email,phno,subject,messege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hm=findViewById(R.id.hm);
        sd=findViewById(R.id.sd);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phno=findViewById(R.id.phno);
        subject=findViewById(R.id.subject);
        messege=findViewById(R.id.messege);
        sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkHandeling networkHandeling=new NetworkHandeling();
                networkHandeling.execute();

            }
        });
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!(name.getText().toString().equals(""))&&(!(email.getText().toString().equals("")))&&(!(phno.getText().toString().equals("")))
                        &&(!(subject.getText().toString().equals("")))&&(!(messege.getText().toString().equals(""))))
                {
                    PostReqestHandeling postReqestHandeling=new PostReqestHandeling();
                    postReqestHandeling.execute();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Fill each context", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
   private   class NetworkHandeling extends AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            StringBuffer sb=new StringBuffer();
            HttpURLConnection connection;
            try {
                URL url=new URL("https://testapi.techqueuesolutions.in/showdata/");
                try {
                    connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(2000);
                    int status=connection.getResponseCode();
                    if(status==200)
                    {
                        Scanner fetch=new Scanner(new InputStreamReader(connection.getInputStream()));
                        while (fetch.hasNextLine())
                        {
                            sb.append(fetch.nextLine());
                        }
                        connection.disconnect();
                        return sb.toString();
                    }
                    else
                    {
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
            if(s!=null)
            {
                pb.setVisibility(View.INVISIBLE);
                Intent intent=new Intent(MainActivity.this,rc_view.class);
                intent.putExtra("result",s);
                startActivity(intent);
            }
            else
            {
                  Intent intent=new Intent(MainActivity.this,NoConnection.class);
                  startActivity(intent);

            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb=findViewById(R.id.pb);
            pb.setVisibility(View.VISIBLE);
        }
    }
    private class PostReqestHandeling extends  AsyncTask<Void,Void,String>
    {
        @Override
        protected String doInBackground(Void... voids)
        {
            String url="https://testapi.techqueuesolutions.in/createdata/";
            StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson=new Gson();
                    ModelForPost j = gson.fromJson(response, ModelForPost.class);
                    if(j.getStatus().equals("true")&&(j.getMessage().equals("inserted successfully")))
                    {
                        name.setText("");
                        email.setText("");
                        phno.setText("");
                        subject.setText("");
                        messege.setText("");
                        Toast.makeText(MainActivity.this, "You are successfully registered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Something wrong happend", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(MainActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>post=new HashMap<>();
                    post.put("name",name.getText().toString());
                    post.put("email",email.getText().toString());
                    post.put("phn_no",phno.getText().toString());
                    post.put("sub",subject.getText().toString());
                    post.put("msg",messege.getText().toString());
                    return post;
                }
            };
            RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
            queue.add(request);
            queue.start();
            return null;
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