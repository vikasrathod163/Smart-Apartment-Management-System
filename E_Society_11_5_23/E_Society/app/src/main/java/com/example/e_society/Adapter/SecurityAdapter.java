package com.example.e_society.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.e_society.Pojo.SecurityInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class SecurityAdapter extends ArrayAdapter<String> {

    public static int flg=0;
    private final Activity context;
    ArrayList<SecurityInfo> slist=new ArrayList<>();


    public SecurityAdapter(@NonNull Activity context, ArrayList<SecurityInfo> slist) {
        super(context,R.layout.security_list_info);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.slist = slist;



    }

    public int getCount() {
        return slist.size();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.security_list_info, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.tv_name);
        TextView contact = (TextView) rowView.findViewById(R.id.tv_contact);
        TextView address=(TextView)rowView.findViewById(R.id.tv_address);

        SecurityInfo securityInfo = slist.get(position);
        name.setText(securityInfo.getName()+ " ,"+securityInfo.getOnDuty());
        contact.setText(securityInfo.getContact());
        address.setText(securityInfo.getAddress());
        return rowView;
    };

}
