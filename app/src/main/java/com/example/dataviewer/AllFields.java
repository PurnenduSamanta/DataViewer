package com.example.dataviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AllFields extends AppCompatActivity {

    TextView name,email,ph,subject,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_fields);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        ph=findViewById(R.id.ph);
        subject=findViewById(R.id.subject);
        msg=findViewById(R.id.msg);


        Intent intent=getIntent();
        String n=intent.getStringExtra("name");
        String e=intent.getStringExtra("email");
        String p=intent.getStringExtra("ph");
        String s=intent.getStringExtra("sub");
        String m=intent.getStringExtra("msg");
        name.setText(n);
        email.setText(e);
        ph.setText(p);
        subject.setText(s);
        msg.setText(m);





    }
}