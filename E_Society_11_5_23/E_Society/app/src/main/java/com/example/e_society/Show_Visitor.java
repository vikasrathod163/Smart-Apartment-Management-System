package com.example.e_society;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.Visitor_Adapter;
import com.example.e_society.Pojo.VisitorInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Visitor extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    ArrayList<VisitorInfo> slist;

    Visitor_Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_visitor);
        listView=findViewById(R.id.lv_visitor);
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Visitor");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<VisitorInfo>();
                ArrayList<String> keylist=new ArrayList<String>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    VisitorInfo visitorInfo;
                    visitorInfo = dataSnapshot.getValue(VisitorInfo.class);
                    slist.add(visitorInfo);
                    keylist.add(dataSnapshot.getKey());
                }

                adapter = new Visitor_Adapter(Show_Visitor.this,slist );
                listView=findViewById(R.id.lv_visitor);
                listView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}