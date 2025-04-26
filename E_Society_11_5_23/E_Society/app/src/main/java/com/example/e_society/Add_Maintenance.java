package com.example.e_society;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.MaintenanceInfo;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Calendar;

public class Add_Maintenance extends AppCompatActivity {
    EditText date, amount;
    Button btn_submit;
    MemberInfo memberInfo;


    DatabaseReference databaseReference;
    int flg = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maintenance);

        date = findViewById(R.id.edit_date);
        amount = findViewById(R.id.edit_amount);
        btn_submit = findViewById(R.id.btn_sub);
        //contact=findViewById(R.id.edit_contact);
        SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();

        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);

        }

        databaseReference = FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL + "/Maintenance");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (amount.getText().toString().isEmpty()) {
                    amount.setError("Enter Amount");
                    return;
                }
                if (date.getText().toString().isEmpty()) {
                    date.setError("Enter Date");
                    return;
                }

                MaintenanceInfo maintenanceInfo = new MaintenanceInfo();

                maintenanceInfo.setAmount(amount.getText().toString());
                maintenanceInfo.setDate(date.getText().toString());
                maintenanceInfo.setContact(memberInfo.getContact());
                maintenanceInfo.setName(memberInfo.getName());
                maintenanceInfo.setFlat(memberInfo.getFlatno());


                databaseReference.child(memberInfo.getContact()).push().setValue(maintenanceInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Add_Maintenance.this, "Payment Added", Toast.LENGTH_SHORT).show();
                    }

                });

                /*databaseReference.child(memberInfo.getContact()).push().setValue(maintenanceInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(Add_Maintenance.this,"Payment Added",Toast.LENGTH_SHORT).show();


                    }
                });

            }
        });*/


               date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();

                        // on below line we are getting
                        // our day, month and year.
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Maintenance.this,
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


 /*show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Add_Maintenance.this,Show_Maintenance_list.class);
                startActivity(intent);
            }
        });
*/


            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Maintenance.this,
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
    }
}