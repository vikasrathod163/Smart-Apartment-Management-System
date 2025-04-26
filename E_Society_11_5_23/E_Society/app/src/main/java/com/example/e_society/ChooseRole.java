package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.e_society.Pojo.MemberInfo;
import com.example.e_society.Pojo.SecurityInfo;
import com.google.gson.Gson;

public class ChooseRole extends AppCompatActivity {
    CardView admin, member, security;
    MemberInfo memberInfo;
    SecurityInfo securityInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);

        admin = findViewById(R.id.card_admin);
        member = findViewById(R.id.card_member);
        security = findViewById(R.id.card_security);
      /*  SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }
*/
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooseRole.this,Admin_Login.class);
                startActivity(intent);
            }
        });
       member.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                SharedPreferences sh=ChooseRole.this.getSharedPreferences("ESociety",MODE_PRIVATE);
                Gson gson=new Gson();

                String json=sh.getString("member","");
                if(!json.equals(""))
                {
                    memberInfo=gson.fromJson(json, MemberInfo.class);
                    Intent intent=new Intent(ChooseRole.this,Member_Dashboard.class);

                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent=new Intent(ChooseRole.this,MemberLogin.class);
                    startActivity(intent);
                    finish();
                }

            }


       });
        security.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences sh1=ChooseRole.this.getSharedPreferences("ESociety",MODE_PRIVATE);
                Gson gson1=new Gson();

                String json1=sh1.getString("security","");
                if(!json1.equals(""))
                {
                    securityInfo=gson1.fromJson(json1, SecurityInfo.class);
                    Intent security=new Intent(ChooseRole.this,Security_Dashboard.class);
                    startActivity(security);

                }
                else
                {
                    Intent i1=new Intent(ChooseRole.this,SecurityLogin.class);
                    startActivity(i1);

                    finish();
                }            }
        });

    }

}