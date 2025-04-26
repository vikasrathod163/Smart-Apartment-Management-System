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

import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MemberLogin extends AppCompatActivity {
    EditText et_username,et_password;
    Button btn_login;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    int flg=0;
    MemberInfo memberInfo;
    String Pattern = "[0-9]{10}";
    TextView textView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member_login);
        et_username=findViewById(R.id.edit_username);
        et_password=findViewById(R.id.edit_pass);
        btn_login=findViewById(R.id.btn_login);
        textView=findViewById(R.id.tV_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.getText().toString().isEmpty())
                {
                    et_username.setError("Enter Username.");
                }
                if(et_password.getText().toString().isEmpty())
                {
                    et_password.setError("Enter Contact");
                }



                    databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+"/Members");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            flg=0;
                            MemberInfo memberInfo;
                            memberInfo =data.getValue(MemberInfo.class);

                            String contact =memberInfo.getContact();
                            String pass = memberInfo.getPass();



                            if (contact.equals(et_username.getText().toString()) && pass.equals(et_password.getText().toString()) ) {
                                if (memberInfo.getStatus().equalsIgnoreCase("active")) {

                                    SharedPreferences.Editor sh=getSharedPreferences("ESociety",MODE_PRIVATE).edit();
                                    Gson gson=new Gson();
                                    String json=gson.toJson(memberInfo);
                                    sh.putString("member",json);
                                    sh.commit();

                                    Toast.makeText(MemberLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(MemberLogin.this, Member_Dashboard.class);
                                    intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    finish();
                                    startActivity(intent1);
                                }
                                else {
                                    Toast.makeText(MemberLogin.this, "Status Deactive", Toast.LENGTH_SHORT).show();

                                }
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
                Intent intent2=new Intent(MemberLogin.this,Add_Members.class);

                startActivity(intent2);
                finish();

            }
        });
    }
}