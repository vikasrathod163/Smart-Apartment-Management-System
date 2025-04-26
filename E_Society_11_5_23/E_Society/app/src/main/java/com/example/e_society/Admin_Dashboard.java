package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Admin_Dashboard extends AppCompatActivity {
CardView show,security,maintenance,notice,shownotice,showbooking,visitor,worker,device;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        security=findViewById(R.id.security_card);
        notice=findViewById(R.id.notice_card);
       show=findViewById(R.id.show_card);
       shownotice=findViewById(R.id.show_notice);
       maintenance=findViewById(R.id.maintenance);
       showbooking=findViewById(R.id.show_booking);
       visitor=findViewById(R.id.visitor);
       worker=findViewById(R.id.worker);



        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Admin_Dashboard.this,ShareNotice.class);
                startActivity(i2);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Admin_Dashboard.this,Show_MembersList.class);
                startActivity(intent);
            }
        });
       maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(Admin_Dashboard.this,Show_Allmaintenance_List.class);
                startActivity(i3);
            }
        });
        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Admin_Dashboard.this,Show_Security_List.class);
                startActivity(i1);
            }
        });
        shownotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Admin_Dashboard.this,Show_Notice.class);
                startActivity(i4);
            }
        });
        showbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Admin_Dashboard.this,Show_Amenity.class);
                startActivity(i4);
            }
        });
       /* visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5=new Intent(Admin_Dashboard.this,Show_Visitor.class);
                startActivity(i5);
            }
        });*/
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5=new Intent(Admin_Dashboard.this,Show_Visitor.class);
                startActivity(i5);
            }
        });
        worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i6=new Intent(Admin_Dashboard.this,Show_Worker.class);
                startActivity(i6);
            }
        });


    }
}