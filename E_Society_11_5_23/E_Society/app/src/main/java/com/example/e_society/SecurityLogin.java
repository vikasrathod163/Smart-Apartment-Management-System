package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.SecurityInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class SecurityLogin extends AppCompatActivity {
    EditText et_username,et_pass;
    Button btn_login;
    TextView textView;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    int flg=0;
    String Pattern = "[0-9]{10}";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_login);
        textView=findViewById(R.id.tV_signup);
        et_username=findViewById(R.id.edit_username);
        et_pass=findViewById(R.id.edit_pass);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (et_username.getText().toString().isEmpty())
                {
                    et_username.setError("Enter Contact.");
                }
                if(et_pass.getText().toString().isEmpty())
                {
                    et_pass.setError("Enter Contact");
                }

                databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Security");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            flg=0;
                            SecurityInfo securityInfo;
                            securityInfo =data.getValue(SecurityInfo.class);

                            String contact =securityInfo.getContact();
                            String pass = securityInfo.getPass();


                            if (contact.equals(et_username.getText().toString()) && pass.equals(et_pass.getText().toString()) ) {

                                securityInfo.setOnDuty("on duty");
                                databaseReference.child(data.getKey()).setValue(securityInfo);
                                databaseReference.removeEventListener(this);
                                SharedPreferences.Editor sh=getSharedPreferences("ESociety",MODE_PRIVATE).edit();
                                Gson gson=new Gson();
                                String json=gson.toJson(securityInfo);
                                sh.putString("security",json);
                                sh.putString("seckey",data.getKey());
                                sh.commit();

                                Toast.makeText(SecurityLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(SecurityLogin.this, Security_Dashboard.class);
                                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                                startActivity(intent1);
                            }

                                else{
                                    Toast.makeText(SecurityLogin.this, "Enter Valid Username And Contact", Toast.LENGTH_SHORT).show();
                                }




                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(SecurityLogin.this,Add_Security.class);
                startActivity(intent2);

            }
        });

    }
}