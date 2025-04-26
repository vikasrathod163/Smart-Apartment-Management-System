package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.AdminAllMaintenanceAdapter;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Show_Allmaintenance_List extends AppCompatActivity {
    ListView listView;
    TextView textView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    MemberInfo memberInfo;

    ArrayList<MemberInfo> slist;

    AdminAllMaintenanceAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allmaintenance_list);

        listView = findViewById(R.id.list_main);
        textView=findViewById(R.id.tv_history);
       /* SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }*/

        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Members");




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<>();

                ArrayList<String> keylist=new ArrayList<String>();

                /*slist=new ArrayList<MemberInfo>();*/
                /*  ArrayList<String> keylist=new ArrayList<String>();
                 */
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    memberInfo = dataSnapshot.getValue(MemberInfo.class);
                    memberInfo.setName(memberInfo.getName());
                    memberInfo.setContact(memberInfo.getContact());
                    memberInfo.setFlatno(memberInfo.getFlatno());



                    keylist.add(dataSnapshot.getKey());
                   // if (memberInfo.getStatus().equalsIgnoreCase("active")) {

                        SharedPreferences.Editor sh=getSharedPreferences("ESociety",MODE_PRIVATE).edit();
                        Gson gson=new Gson();
                        String json=gson.toJson(memberInfo);
                        sh.putString("member",json);
                        sh.commit();
                        slist.add(memberInfo);


                   // }


                }

                adapter = new AdminAllMaintenanceAdapter(Show_Allmaintenance_List.this, slist);

                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}