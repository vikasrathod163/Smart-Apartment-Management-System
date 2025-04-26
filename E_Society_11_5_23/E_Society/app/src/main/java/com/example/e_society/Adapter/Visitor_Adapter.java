package com.example.e_society.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.e_society.Pojo.VisitorInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class Visitor_Adapter extends ArrayAdapter<String> {
    public static int flg=0;
    private final Activity context;
    ArrayList<VisitorInfo>slist=new ArrayList<>();
    public Visitor_Adapter(@NonNull Activity context, ArrayList<VisitorInfo> slist) {
        super(context, R.layout.visitor_info);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.slist = slist;


    }
    public int getCount() {
        return slist.size();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.visitor_info, null, true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name = (TextView) rowView.findViewById(R.id.tv_viname);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView contact = (TextView) rowView.findViewById(R.id.tv_vicontact);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView flat=(TextView)rowView.findViewById(R.id.tv_flatno);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView time1=(TextView)rowView.findViewById(R.id.tv_time1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView time2=(TextView)rowView.findViewById(R.id.time2);


        VisitorInfo visitorInfo = slist.get(position);
        name.setText(visitorInfo.getName());
        contact.setText(visitorInfo.getContact());
        flat.setText(visitorInfo.getFlat());
        time1.setText(visitorInfo.getTime());
        time2.setText(visitorInfo.getDtime());
        return rowView;
    };
}
