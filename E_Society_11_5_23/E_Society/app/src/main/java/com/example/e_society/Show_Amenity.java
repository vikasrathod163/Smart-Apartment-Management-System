package com.example.e_society;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.AmenityAdapter1;
import com.example.e_society.Pojo.AmenityInfo;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Show_Amenity extends AppCompatActivity {
    ListView listView;
    TextView textView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MemberInfo memberInfo;
    AmenityInfo amenityInfo;

    ArrayList<AmenityInfo> slist;

    AmenityAdapter1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_amenity);
        listView = findViewById(R.id.lv_amenity);
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Amenity");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<>();

                ArrayList<String> keylist=new ArrayList<String>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    amenityInfo = dataSnapshot.getValue(AmenityInfo.class);
                    amenityInfo.setAmenity(amenityInfo.getAmenity());
                    amenityInfo.setTime(amenityInfo.getTime());
                    amenityInfo.setDate(amenityInfo.getDate());




                    keylist.add(dataSnapshot.getKey());


                    SharedPreferences.Editor sh=getSharedPreferences("ESociety",MODE_PRIVATE).edit();
                    Gson gson=new Gson();
                    String json=gson.toJson(memberInfo);
                    sh.putString("member",json);
                    sh.commit();
                    slist.add(amenityInfo);





                }
                adapter = new AmenityAdapter1(Show_Amenity.this, slist);

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}