package com.example.e_society;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.AmenityInfo;
import com.example.e_society.Pojo.MemberInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

public class Book_Amenities extends AppCompatActivity {
   Spinner amenity;
    Spinner time;
    EditText date;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Button add;

    MemberInfo memberInfo;

    AmenityInfo amenityInfo;

ArrayList<AmenityInfo>amlist=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_amenities);
        amenity=findViewById(R.id.amenity);
        date=findViewById(R.id.edit_date);
        time=findViewById(R.id.time_slot);
        add=findViewById(R.id.btn_add);


        ArrayAdapter<String>myadapter=new ArrayAdapter<>(Book_Amenities.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Amenities));
        amenity.setAdapter(myadapter);
        ArrayAdapter<String>adapter1=new ArrayAdapter<>(Book_Amenities.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.Time_slot));
        time.setAdapter(adapter1);


        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Amenity");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()) {


                  AmenityInfo am = data.getValue(AmenityInfo.class);
                  amlist.add(am);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(date.getText().toString().isEmpty()){
                    date.setError("Enter date");
                    return;
                }

               if(amenity.getSelectedItem().toString().equalsIgnoreCase("Choose Amenity") || amenity.getSelectedItem().toString().equalsIgnoreCase("")){
                    Toast.makeText(Book_Amenities.this,"Please Select amenity  !!", Toast.LENGTH_LONG) .show();
                    return;
                }
                Spinner time1 = (Spinner) findViewById(R.id.time_slot);
                time1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

                    @Override
                    public void onItemSelected(AdapterView adapter, View v, int i, long lng) {
                        String time1=time.getSelectedItem().toString();
                       time1=adapter.getItemAtPosition(i).toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView arg0) {
                        Toast.makeText(Book_Amenities.this,"Choose Time Slot!!", Toast.LENGTH_LONG).show();
                        return;
                    }
                });

                if(time.getSelectedItem().toString().equalsIgnoreCase("Choose Time")  ||time.equals("")){
                    Toast.makeText(Book_Amenities.this,"Choose Time Slot", Toast.LENGTH_LONG) .show();
                    return;
                }


                AmenityInfo amenityInfo=new AmenityInfo();
                amenityInfo.setTime(time.getSelectedItem().toString());
                amenityInfo.setDate(date.getText().toString());
                amenityInfo.setAmenity(amenity.getSelectedItem().toString());
                amenityInfo.setMemberInfo(memberInfo);

                for(int i=0;i<amlist.size();i++)
                {
                    if(amenityInfo.getDate().equalsIgnoreCase(amlist.get(i).getDate()) && amenityInfo.getTime().equalsIgnoreCase(amlist.get(i).getTime()))
                    {
                        Toast.makeText(Book_Amenities.this, "this slot already booked", Toast.LENGTH_SHORT).show();
                        return;

                    }
                }

                databaseReference.push().setValue(amenityInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {


                        Toast.makeText(Book_Amenities.this,"Data Added Successfully",Toast.LENGTH_SHORT).show();

                        Calendar cal = Calendar.getInstance();
                        Intent intent = new Intent(Intent.ACTION_EDIT);
                        intent.setType("vnd.android.cursor.item/event");
                        intent.putExtra("beginTime", cal.getTimeInMillis());
                        intent.putExtra("allDay", false);
                        intent.putExtra("rrule", "FREQ=DAILY");
                        intent.putExtra("endTime", cal.getTimeInMillis()+60*1000*2);
                        intent.putExtra("title", "Your Booked Amenity "+amenity+" ");
                        startActivity(intent);


                       /* Intent intent1=new Intent(Book_Amenities.this,Member_Dashboard.class);

                        startActivity(intent1);

*/
                    }
                });



            }
        });
        SharedPreferences sh=this.getSharedPreferences("ESociety",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sh.getString("member","");
        if(!json.equals(""))
        {
            memberInfo=gson.fromJson(json, MemberInfo.class);
        }


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Book_Amenities.this,
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