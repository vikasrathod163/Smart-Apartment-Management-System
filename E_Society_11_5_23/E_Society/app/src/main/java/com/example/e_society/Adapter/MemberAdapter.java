package com.example.e_society.Adapter;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.e_society.Pojo.MemberInfo;
import com.example.e_society.R;
import com.example.e_society.Show_MembersList;

import java.util.ArrayList;


public class MemberAdapter extends ArrayAdapter<String> {


    public static int flg=0;
    private final Activity context;
    ArrayList<MemberInfo> slist=new ArrayList<>();

    public MemberAdapter(@NonNull Activity context, ArrayList<MemberInfo> slist) {
        super(context,R.layout.activity_show_members_list);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.slist = slist;



    }
    @Override
    public int getCount() {
        return slist.size();

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_member_list_info, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.tv_name);
        TextView contact = (TextView) rowView.findViewById(R.id.tv_contact);
        TextView flat = (TextView) rowView.findViewById(R.id.tv_flat);
        CheckBox check=rowView.findViewById(R.id.check_activate);
        MemberInfo memberInfo= Show_MembersList.memberInfos.get(position);

        if(memberInfo.getStatus().equalsIgnoreCase("active"))
        {
            check.setChecked(true);
        }
        check.setText(memberInfo.getStatus());

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


               if(check.isChecked())
               {
                   memberInfo.setStatus("Active");

               }
               else {

                   memberInfo.setStatus("Deactive");
               }
               check.setText(memberInfo.getStatus());
               if (memberInfo.getStatus().equalsIgnoreCase("Active")){
                   check.setChecked(true);

               }
               else {
                   check.setChecked(false);

               }

            }
        });




        name.setText(memberInfo.getName());
        contact.setText(memberInfo.getContact());
        flat.setText(memberInfo.getFlatno());
        return rowView;
    };
}
