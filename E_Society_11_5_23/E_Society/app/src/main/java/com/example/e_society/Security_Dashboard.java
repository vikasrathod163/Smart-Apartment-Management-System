package com.example.e_society;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DatabaseReference;

public class Security_Dashboard extends AppCompatActivity {
CardView visitor,worker;
TextView logout;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_dashboard);

        visitor = findViewById(R.id.card_visitor);
        worker = findViewById(R.id.worker_card);
        logout=findViewById(R.id.logout1);

       worker.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent1=new Intent(Security_Dashboard.this,Worker.class);
               startActivity(intent1);
           }
       });
        visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Security_Dashboard.this,Visitor.class);
                startActivity(intent2);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences myPrefs = getSharedPreferences("ESociety", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                //  SessionManager.getSingleInstance().setLoggingOut(true);
                Toast.makeText(Security_Dashboard.this, "log out", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(Security_Dashboard.this,
                        ChooseRole.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });








    }
}