package com.example.e_society;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.AdminInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_Login extends AppCompatActivity {
    EditText et_username,et_password;
    Button btn_login;
    DatabaseReference databaseReference;
    int flg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        et_username=findViewById(R.id.edit_username);
        et_password=findViewById(R.id.edit_pass);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_username.getText().toString().isEmpty()) {
                    et_username.setError("Enter Username");
                    return;
                }
                if (et_password.getText().toString().isEmpty()) {
                    et_password.setError("Enter Password");
                    return;
                }

                if (et_username.getText().toString().equals("Admin") && (et_password.getText().toString().equals("admin")))
                {

                    Toast.makeText(Admin_Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Admin_Login.this,Admin_Dashboard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(Admin_Login.this, "Login UnSuccessfull", Toast.LENGTH_LONG).show();
                }
                databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/ESociety_Admin");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data : snapshot.getChildren()) {
                            flg = 0;
                            AdminInfo adminInfo = data.getValue(AdminInfo.class);

                            assert adminInfo != null;
                            String username = adminInfo.getAdmin_Username();
                            String pass = adminInfo.getAdmin_Pass();
                            if (username != null && username.equals("Admin")) {
                                if (pass.equals("admin")) {
                                    flg = 1;
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
    }
}