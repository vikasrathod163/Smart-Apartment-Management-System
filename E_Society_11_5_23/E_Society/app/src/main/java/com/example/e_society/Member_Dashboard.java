package com.example.e_society;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.e_society.Pojo.MemberInfo;

public class Member_Dashboard extends AppCompatActivity {

   SessionManager sessionManager;

    CardView notice,pay,history,book,show,voting;
TextView logout;
TextView textView;
MemberInfo memberInfo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_dashboard);
    textView=findViewById(R.id.ok);
        notice=findViewById(R.id.notice_card);
        pay=findViewById(R.id.pay_card);
        history=findViewById(R.id.history_card);
        book=findViewById(R.id.book_card);
        show=findViewById(R.id.card_showbooking);

        logout=findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myPrefs = getSharedPreferences("ESociety", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
              //  SessionManager.getSingleInstance().setLoggingOut(true);
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(Member_Dashboard.this,
                        ChooseRole.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

              /*  sessionManager.logoutuser();
                //Intent intent=new Intent(ProfileActivity.this,SplashActivity.class);
                //startActivity(intent);
                finish();
*/
                /*SharedPreferences.Editor sh=getSharedPreferences("ESociety",MODE_PRIVATE).edit();
                Gson gson=new Gson();
                String json=gson.toJson(memberInfo);
                sh.putString("member",json);
                sh.commit();
*//*
                Intent i=new Intent(Member_Dashboard.this,MemberLogin.class);
                startActivity(i);*/
            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Member_Dashboard.this,Add_Maintenance.class);
                startActivity(i1);
            }
        });
       notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2=new Intent(Member_Dashboard.this,MemberNotice.class);
                startActivity(i2);
            }
        });
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(Member_Dashboard.this, Profile.class);
               startActivity(intent);
           }
       });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(Member_Dashboard.this,Show_Maintenance_list.class);
                startActivity(i3);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Member_Dashboard.this,Book_Amenities.class);

                startActivity(i4);
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(Member_Dashboard.this,Member_Amenity.class);
                startActivity(i4);
            }
        });

      /*  details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i5=new Intent(Member_Dashboard.this, Profile_Adpter.class);
                startActivity(i5);
            }
        });*/
    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

}