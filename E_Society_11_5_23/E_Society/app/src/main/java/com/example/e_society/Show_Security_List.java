package com.example.e_society;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Adapter.SecurityAdapter;
import com.example.e_society.Pojo.SecurityInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Security_List extends AppCompatActivity {
    ListView listView;
    TextView text;
    SecurityInfo securityInfo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    ArrayList<SecurityInfo> slist;

  SecurityAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_security_list);
      listView=findViewById(R.id.lv_security);



       /* SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("security","");
        if(!json.equals(""))
        {
            securityInfo=gson.fromJson(json, SecurityInfo.class);

        }
*/
        databaseReference = firebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Security");
         // text.setText(securityInfo.getName());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                slist=new ArrayList<SecurityInfo>();
                ArrayList<String> keylist=new ArrayList<String>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    SecurityInfo securityInfo;
                    securityInfo = dataSnapshot.getValue(SecurityInfo.class);
                    slist.add(securityInfo);
                    keylist.add(dataSnapshot.getKey());
                }




                adapter = new SecurityAdapter(Show_Security_List.this,slist );
                listView=findViewById(R.id.lv_security);
                listView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}