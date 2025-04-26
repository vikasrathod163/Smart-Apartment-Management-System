package com.example.e_society.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.e_society.Appconstant;
import com.example.e_society.Pojo.NoticeInfo;
import com.example.e_society.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NoticeAdapter extends ArrayAdapter<String> {

    public static int flg=0;
    private final Activity context;
    ArrayList<NoticeInfo> slist=new ArrayList<NoticeInfo>();
    DatabaseReference databaseReference;
    ArrayList<String> keylist;



    public NoticeAdapter(@NonNull Activity context, ArrayList<NoticeInfo> slist, ArrayList<String> keylist) {
        super(context, R.layout.activity_show_notice);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.slist = slist;
        this.keylist=keylist;


    }
    public int getCount() {
        return slist.size();

    }
    public View getView( int position,View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.notice_list_info, null,true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView title=(TextView) rowView.findViewById(R.id.tv_title);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView description=(TextView) rowView.findViewById(R.id.tv_description);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView delete=(ImageView) rowView.findViewById(R.id.delete);


        NoticeInfo noticeInfo = slist.get(position);
        title.setText(noticeInfo.getTitle());
        description.setText(noticeInfo.getDescription());

         String key=keylist.get(position);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference= FirebaseDatabase.getInstance().getReference(Appconstant.BaseURL+"/Notice");
                databaseReference.child(noticeInfo.getTitle()).setValue(null);
                Toast.makeText(context, "Notice Delete", Toast.LENGTH_SHORT).show();
/*
                new AlertDialog.Builder(context).setTitle("Do you want to remove").setPositiveButton("yes",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        keylist.remove(position);
                       keylist.notifyAll();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                }).create().show();*/
            }
        });




        return rowView;
    };
}
