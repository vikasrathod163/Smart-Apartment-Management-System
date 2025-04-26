package com.example.e_society;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.SecurityInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Add_Security extends AppCompatActivity {
    EditText security,address,contact,email,date,paas;
    Button btn_add;

    String Pattern = "[0-9]{10}";
    String Emailpattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    DatabaseReference databaseReference;
    int flg=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_security);

       address=findViewById(R.id.edit_Address);
        contact=findViewById(R.id.edit_contact);
        email=findViewById(R.id.edit_email);
        date=findViewById(R.id.edit_date);
        security=findViewById(R.id.security);
        btn_add=findViewById(R.id.btn_add);
        paas=findViewById(R.id.pass);


        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Security");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(security.getText().toString().isEmpty()){
                    security.setError("Enter Name");
                    return;
                }
                if( contact.getText().toString().isEmpty()){
                    contact.setError("Enter Contact Number");
                    return;
                }
                if(!contact.getText().toString().trim().matches(Pattern))
                {
                    contact.setError("Please enter valid 10 digit phone number");
                    return;
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("Enter Email ID");
                    return;
                }
                if (!email.getText().toString().trim().matches(Emailpattern)){
                    email.setError("Email is Invalid");
                    return;
                }
                if(address.getText().toString().isEmpty()){
                    address.setError("Enter address");
                    return;
                }
                if(date.getText().toString().isEmpty()){
                   date.setError("Enter date");
                    return;
                }
                if(paas.getText().toString().isEmpty()){
                    paas.setError("Enter date");
                    return;
                }


                SecurityInfo securityInfo=new SecurityInfo();

                securityInfo.setName(security.getText().toString());
                securityInfo.setAddress(address.getText().toString());
                securityInfo.setContact(contact.getText().toString());
                securityInfo.setEmail(email.getText().toString());
                securityInfo.setDate(date.getText().toString());
                securityInfo.setPass(paas.getText().toString());
                securityInfo.setOnDuty("off duty");



                databaseReference.child(securityInfo.getContact()).setValue(securityInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Add_Security.this,"data Added successfully",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              final Calendar c = Calendar.getInstance();

              // on below line we are getting
              // our day, month and year.
              int year = c.get(Calendar.YEAR);
              int month = c.get(Calendar.MONTH);
              int day = c.get(Calendar.DAY_OF_MONTH);


              DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Security.this,
                      new DatePickerDialog.OnDateSetListener() {
                          @Override
                          public void onDateSet(DatePicker view, int year,
                                                int monthOfYear, int dayOfMonth) {
                              // on below line we are setting date to our edit text.
                           date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                          }
                      },

                      year, month, day);

              datePickerDialog.show();
          }

      });

      /*  show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Add_Security.this,Show_Security_List.class);
                startActivity(i1);
            }
        });*/
    }
}