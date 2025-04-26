package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class Profile extends AppCompatActivity {
TextView name,mail,contact,flat,password;
MemberInfo memberInfo;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.pname);
        mail=findViewById(R.id.padd);
        flat=findViewById(R.id.pflat);
        contact=findViewById(R.id.pcontact);
        password=findViewById(R.id.ppassword);
        SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Member").child(memberInfo.getContact());

        name.setText(memberInfo.getName());
        mail.setText(memberInfo.getEmail());
        flat.setText(memberInfo.getFlatno());
        password.setText(memberInfo.getPass());
        contact.setText(memberInfo.getContact());

    }
}