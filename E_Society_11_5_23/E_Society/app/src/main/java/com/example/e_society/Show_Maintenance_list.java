package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.MemberMaintenanceAdapter;
import com.example.e_society.Pojo.MaintenanceInfo;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Show_Maintenance_list extends AppCompatActivity {
    ListView listView;
    TextView name,tamount;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    MemberInfo memberInfo;

    ArrayList<MaintenanceInfo> slist;

    MemberMaintenanceAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_maintenance_list);
        listView = findViewById(R.id.lv_history);
        name=findViewById(R.id.text_name);
        tamount=findViewById(R.id.text_amount);


        SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }

       // databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Maintenance").child(memberInfo.getContact());
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Maintenance").child(memberInfo.getContact());

        name.setText(memberInfo.getName());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              //  MaintenanceInfo.clear();
                int total = 0;
                slist=new ArrayList<>();
                /*ArrayList<String> keylist=new ArrayList<String>();*/

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    MaintenanceInfo mt = dataSnapshot.getValue(MaintenanceInfo.class);
                    total=total+Integer.parseInt(mt.getAmount());
                    slist.add(mt);


                }
                tamount.setText(total+"");
                adapter = new MemberMaintenanceAdapter(Show_Maintenance_list.this, slist);
                MemberInfo memberInfo=new MemberInfo();
                listView.setAdapter(adapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }

        });

    }
}