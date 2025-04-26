package com.example.e_society;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Members extends AppCompatActivity {
    EditText et_flatno,et_name,et_contact,et_email,et_noof_members,pass;

    Button btn_add;
    String Pattern = "[0-9]{10}";
    String Emailpattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    DatabaseReference databaseReference;
    int flg=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);
        et_flatno=findViewById(R.id.edit_flatno);
        et_name=findViewById(R.id.edit_name);
        et_contact=findViewById(R.id.edit_contact);
        et_email=findViewById(R.id.edit_email);
        et_noof_members=findViewById(R.id.edit_familyno);
        btn_add=findViewById(R.id.btn_add);
        pass=findViewById(R.id.pass);

        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Members");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_flatno.getText().toString().isEmpty()){
                    et_flatno.setError("Enter Flat No");
                    return;
                }
                if(et_name.getText().toString().isEmpty()){
                    et_name.setError("Enter Name");
                    return;
                }
                if( et_contact.getText().toString().isEmpty()){
                    et_contact.setError("Enter Contact Number");
                    return;
                }
                if(!et_contact.getText().toString().trim().matches(Pattern))
                {
                    et_contact.setError("Please enter valid 10 digit phone number");
                    return;
                }
                if(et_email.getText().toString().isEmpty()){
                    et_email.setError("Enter Email ID");
                    return;
                }
                if (!et_email.getText().toString().trim().matches(Emailpattern)){
                    et_email.setError("Email not valid");
                    return;
                }
                if(et_noof_members.getText().toString().isEmpty()){
                    et_noof_members.setError("Enter No Of Family Members");
                    return;
                }
                if(pass.getText().toString().isEmpty()){
                    pass.setError("Enter password");
                    return;
                }



                MemberInfo memberInfo=new MemberInfo();

                memberInfo.setName(et_name.getText().toString());
                memberInfo.setPass(pass.getText().toString());
                memberInfo.setContact(et_contact.getText().toString());
                //memberInfo.setPassword(password.getText().toString());
                memberInfo.setEmail(et_email.getText().toString());
                memberInfo.setNo_of_members(et_noof_members.getText().toString());

                memberInfo.setFlatno(et_flatno.getText().toString());
                memberInfo.setStatus("deactive");

                databaseReference.child(memberInfo.getContact()).setValue(memberInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Add_Members.this,"Data Added successfully",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(Add_Members.this,MemberLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        });
       /* show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Add_Members.this,Show_MembersList.class);
                startActivity(intent);
            }
        });
*/

    }
}