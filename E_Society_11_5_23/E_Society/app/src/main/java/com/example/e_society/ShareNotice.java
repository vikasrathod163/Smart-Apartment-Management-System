package com.example.e_society;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_society.Pojo.NoticeInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShareNotice extends AppCompatActivity {
    Button btn_share;
    EditText description,title;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_notice);
        title=findViewById(R.id.tv_title);
        description=findViewById(R.id.tv_description);
        btn_share=findViewById(R.id.btn_share);


        databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+ "/Notice");

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().toString().isEmpty()){
                    title.setError("Enter Notice");
                    return;
                }
                if(description.getText().toString().isEmpty()){
                    description.setError("Enter Notice");
                    return;
                }
                NoticeInfo noticeInfo=new NoticeInfo();

                noticeInfo.setTitle(title.getText().toString());
                noticeInfo.setDescription(description.getText().toString());

                databaseReference.child(noticeInfo.getTitle()).setValue(noticeInfo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(ShareNotice.this,"Notice Added successfully",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



    }
}