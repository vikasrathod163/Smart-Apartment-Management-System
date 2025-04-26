package com.example.e_society;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.MemberNotice_Adapter;
import com.example.e_society.Pojo.NoticeInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MemberNotice extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    NoticeInfo noticeInfo;
    ArrayList<NoticeInfo> slist;

    MemberNotice_Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_notice);
        listView=findViewById(R.id.lv_notice);
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Notice");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<NoticeInfo>();
                ArrayList<String> keylist=new ArrayList<String>();
                Collections.reverse(slist);

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    noticeInfo = dataSnapshot.getValue(NoticeInfo.class);
                    slist.add(noticeInfo);
                    keylist.add(dataSnapshot.getKey());
                }
                adapter = new MemberNotice_Adapter(MemberNotice.this, slist);
                listView=findViewById(R.id.lv_notice);
                listView.setAdapter(adapter);

                //.reverse(slist);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}