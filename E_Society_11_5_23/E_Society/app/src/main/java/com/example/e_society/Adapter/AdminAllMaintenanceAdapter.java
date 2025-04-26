package com.example.e_society.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_society.Pojo.MemberInfo;
import com.example.e_society.R;
import com.example.e_society.Show_Maintenance_list;
import com.google.gson.Gson;

import java.util.ArrayList;

public class AdminAllMaintenanceAdapter extends ArrayAdapter<String> {
    public static int flg=0;
    private Activity context;
    ArrayList<MemberInfo> slist=new ArrayList<>();

    public AdminAllMaintenanceAdapter(Activity context, ArrayList<MemberInfo> slist) {
        super(context, R.layout.activity_show_allmaintenance_list);
        this.context = context;
        this.slist = slist;
    }
    public int getCount() {
        return slist.size();

    }





    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.all_maintenance_info, null,true);
        TextView textView=(TextView) rowView.findViewById(R.id.tv_history);
        TextView name=(TextView) rowView.findViewById(R.id.text_name);
        TextView contact=(TextView) rowView.findViewById(R.id.text_contact);
        TextView flat=(TextView) rowView.findViewById(R.id.text_flat);



        MemberInfo memberInfo = slist.get(position);
        name.setText(memberInfo.getName());
        flat.setText(memberInfo.getFlatno());
        contact.setText(memberInfo.getContact());


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor sh=context.getSharedPreferences("ESociety", Context.MODE_PRIVATE).edit();
                Gson gson=new Gson();
                String json=gson.toJson(memberInfo);
                sh.putString("member",json);
                sh.commit();


                Intent intent=new Intent(context, Show_Maintenance_list.class);
                context.startActivity(intent);



            }
        });
        return rowView;

    }




}
