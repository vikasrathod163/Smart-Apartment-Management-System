package com.example.e_society.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_society.Pojo.MaintenanceInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class MemberMaintenanceAdapter extends ArrayAdapter<String> {
    public static int flg=0;
    private  Activity context;
    ArrayList<MaintenanceInfo> slist=new ArrayList<>();

    public MemberMaintenanceAdapter(Activity context, ArrayList<MaintenanceInfo> slist) {
        super(context,R.layout.activity_show_maintenance_list);
        this.context = context;
        this.slist = slist;
    }
    public int getCount() {
        return slist.size();

    }
    public View getView( int position,View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.maintenance_list_info, null,true);

         @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name=(TextView) rowView.findViewById(R.id.text_name);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView tamount=(TextView) rowView.findViewById(R.id.text_amount);
         TextView date=(TextView) rowView.findViewById(R.id.tv_date);
         TextView amount=(TextView) rowView.findViewById(R.id.tv_amount);



        MaintenanceInfo maintenanceInfo = slist.get(position);
       // name.setText(maintenanceInfo.getName());
        date.setText(maintenanceInfo.getDate());
        amount.setText(maintenanceInfo.getAmount());


        return rowView;
    }





}
