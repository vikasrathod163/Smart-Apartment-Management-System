package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.MemberAdapter;
import com.example.e_society.Pojo.MemberInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_MembersList extends AppCompatActivity {

    ListView listView;
FloatingActionButton btn_add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    MemberInfo memberInfo;

    ArrayList<MemberInfo> slist;

    MemberAdapter adapter;
public static ArrayList<MemberInfo>memberInfos=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_members_list);
        btn_add=findViewById(R.id.btn_add);
        listView = findViewById(R.id.lv_member);

        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Members");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                slist=new ArrayList<MemberInfo>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    memberInfo = dataSnapshot.getValue(MemberInfo.class);
                    slist.add(memberInfo);
                    //memberInfo.setName(memberInfo.getName());
                    //memberInfo.setContact(memberInfo.getContact());
                    //memberInfo.setFlatno(memberInfo.getFlatno());
                    memberInfos.add(memberInfo);
                }

                adapter = new MemberAdapter(Show_MembersList.this, slist);
                listView=findViewById(R.id.lv_member);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference=firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+"/Members");

                for (int i=0;i<memberInfos.size();i++){
                    databaseReference.child(memberInfos.get(i).getContact()).setValue(memberInfos.get(i)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(Show_MembersList.this, "Data Added", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Show_MembersList.this,Admin_Dashboard.class);
                        }
                    });
                }
            }
        });
       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ArrayList<MemberInfo> mlist=new ArrayList<MemberInfo>();
                ArrayList<String> keylist=new ArrayList<String>();
                *//*for (DataSnapshot data : snapshot.getChildren()) {
                    memberInfo = data.getValue(MemberInfo.class);
                    slist.add(memberInfo);
                    keylist.add(data.getKey());
                }*//*
                for (DataSnapshot data : snapshot.getChildren())
                {
                    memberInfo = data.getValue(MemberInfo.class);
                    slist.add(memberInfo);
                    keylist.add(data.getKey());
                }


                adapter = new MemberAdapter(Show_MembersList.this, slist);
                listView=findViewById(R.id.lv_member);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

    }
}