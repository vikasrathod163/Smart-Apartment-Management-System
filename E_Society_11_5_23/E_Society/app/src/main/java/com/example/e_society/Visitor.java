package com.example.e_society;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.VisitorInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Visitor extends AppCompatActivity {
    EditText et_flatno;
    EditText et_name;
    EditText et_contact;
    EditText et_email;
    EditText time,dtime;
    EditText date;

    Button btn_add;

    String Pattern = "[0-9]{10}";
    String Emailpattern="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    DatabaseReference databaseReference;
    int flg=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        et_flatno=findViewById(R.id.edit_flatno);
        et_name=findViewById(R.id.edit_visitor);
        et_contact=findViewById(R.id.edit_contact);
        et_email=findViewById(R.id.edit_email);
        time=findViewById(R.id.edit_time);
        date=findViewById(R.id.edit_date);
        btn_add=findViewById(R.id.btn_add);
        dtime=findViewById(R.id.timed);
        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Visitor");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                if(et_flatno.getText().toString().isEmpty()){
                    et_flatno.setError("Enter Flat No");
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
                if(date.getText().toString().isEmpty()){
                    date.setError("Enter Date");
                    return;
                }
                if(time.getText().toString().isEmpty()){
                    time.setError("Enter Time");
                    return;
                }
                if(dtime.getText().toString().isEmpty()){
                    dtime.setError("Enter departure Time");
                    return;
                }

                VisitorInfo visitorInfo=new VisitorInfo();

                visitorInfo.setName(et_name.getText().toString());
                visitorInfo.setContact(et_contact.getText().toString());
                visitorInfo.setTime(time.getText().toString());
                visitorInfo.setDtime(dtime.getText().toString());


                visitorInfo.setDate(date.getText().toString());
                visitorInfo.setEmail(et_email.getText().toString());
                visitorInfo.setFlat(et_flatno.getText().toString());
                visitorInfo.setDtime(dtime.getText().toString());

                databaseReference.child(visitorInfo.getContact()).setValue(visitorInfo, new DatabaseReference.CompletionListener() {
                    @Override

                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Visitor.this,"Data Added successfully",Toast.LENGTH_SHORT).show();



                    }
                });

            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Visitor.this,
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
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Visitor.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


                        time.setText(hourOfDay + ":" + minutes);

                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });
        dtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Visitor.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


                        dtime.setText(hourOfDay + ":" + minutes);

                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });


    }
}