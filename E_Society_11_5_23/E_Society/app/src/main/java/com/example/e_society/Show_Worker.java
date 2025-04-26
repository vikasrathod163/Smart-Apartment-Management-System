package com.example.e_society;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.WorkerAdapter;
import com.example.e_society.Pojo.WorkerInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Worker extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    ArrayList<WorkerInfo> slist;

    WorkerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_worker);
        listView=findViewById(R.id.lv_worker);
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Workers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<WorkerInfo>();
                ArrayList<String> keylist=new ArrayList<String>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    WorkerInfo workerInfo;
                    workerInfo = dataSnapshot.getValue(WorkerInfo.class);
                    slist.add(workerInfo);
                    keylist.add(dataSnapshot.getKey());
                }
                adapter = new WorkerAdapter(Show_Worker.this,slist );
                listView=findViewById(R.id.lv_worker);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}