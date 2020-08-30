package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class rc_view extends AppCompatActivity {

    RecyclerView list;
    ArrayList<Model>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_view);
        list=findViewById(R.id.list);
        Intent intent=getIntent();
        String result=intent.getStringExtra("result");
        try {
            JSONArray jsonArray=new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject child=jsonArray.getJSONObject(i);
                int a= child.getInt("msg_id");
                String b= child.getString("sender_name");
                String c=  child.getString("sender_email");
                String d= child.getString("sender_contact_no");
                String e=  child.getString("subject_of_msg");
                String f=  child.getString("message");
                String g= child.getString("sent_time");
                arrayList.add(new Model(a,b,c,d,e,f,g));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this);
        recyclerViewAdapter.setModels(arrayList);
        list.setHasFixedSize(true);
        list.setAdapter(recyclerViewAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onBackPressed() {
        Intent intent =new Intent(rc_view.this,MainActivity.class);
        startActivity(intent);
    }
}