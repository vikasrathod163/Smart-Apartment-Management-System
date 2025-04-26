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

import com.example.e_society.Pojo.WorkerInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Worker extends AppCompatActivity {
    EditText name,contact,address,date,time,time2;
    String Pattern = "[0-9]{10}";
    Button btn;

    DatabaseReference databaseReference;
    int flg=0;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


        name = findViewById(R.id.edit_worker);
        contact=findViewById(R.id.edit_contact);
        address=findViewById(R.id.edit_address);
        date=findViewById(R.id.edit_date);
        time=findViewById(R.id.edit_time);
        btn=findViewById(R.id.btn_add);
        time2=findViewById(R.id.time2);


        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Workers");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(name.getText().toString().isEmpty()){
                   name.setError("Please Enter Your Name");
                   return;
               }
                if(contact.getText().toString().isEmpty()){
                    contact.setError("Please Enter Your Contact");
                    return;
                }
                if(!contact.getText().toString().trim().matches(Pattern))
                {
                    contact.setError("Please enter valid 10 digit phone number");
                    return;
                }
                if(address.getText().toString().isEmpty()){
                    address.setError("Please Enter Your Address");
                    return;
                }
                if(date.getText().toString().isEmpty()){
                    date.setError("Please Enter Date");
                     return;
                }
                if(time.getText().toString().isEmpty()){
                    time.setError("Please Select Time");
                    return;
                }
                if(time2.getText().toString().isEmpty()){
                    time2.setError("Please Select Time");
                    return;
                }

                WorkerInfo workerInfo=new WorkerInfo();

                workerInfo.setName(name.getText().toString());
                workerInfo.setContact(contact.getText().toString());
                workerInfo.setAddress(address.getText().toString());
                workerInfo.setDate(date.getText().toString());
                workerInfo.setTime(time.getText().toString());
                workerInfo.setDtime(time2.getText().toString());

                databaseReference.child(workerInfo.getContact()).setValue(workerInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Worker.this,"Data Added successfully",Toast.LENGTH_SHORT).show();

                       /* Intent intent=new Intent(Worker.this,SecurityLogin.class);
                        startActivity(intent);*/
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(Worker.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, day);

                datePickerDialog.show();
            }

        });
           time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Worker.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


                        time.setText(hourOfDay + ":" + minutes);

                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Worker.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {


                        time2.setText(hourOfDay + ":" + minutes);

                    }
                }, 0, 0, false);

                timePickerDialog.show();

            }
        });



    }
}