package com.example.e_society;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.MemberAmenityAdapter;
import com.example.e_society.Pojo.AmenityInfo;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Member_Amenity extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
AmenityInfo amenityInfo;
    MemberInfo memberInfo;

    ArrayList<AmenityInfo> slist;

    MemberAmenityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_amenity);
        listView=findViewById(R.id.lv_memamenity);

        SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Amenity");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<AmenityInfo>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    amenityInfo = dataSnapshot.getValue(AmenityInfo.class);

                    if (amenityInfo.getMemberInfo().getName().equals(memberInfo.getName()))
                    {
                        slist.add(amenityInfo);
                    }
                }
                adapter = new MemberAmenityAdapter(Member_Amenity.this, slist);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}