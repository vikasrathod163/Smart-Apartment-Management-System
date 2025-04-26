package com.example.e_society.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.e_society.Pojo.MemberInfo;
import com.example.e_society.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Profile_Adpter  extends ArrayAdapter<String> {

    public static int flg = 0;
    private Activity context;
    ArrayList<MemberInfo> slist = new ArrayList<>();
    MemberInfo memberInfo;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    public Profile_Adpter(Activity context, ArrayList<MemberInfo> slist) {
        super(context, R.layout.detailsinfo);
        this.context = context;
        this.slist = slist;
    }

    public int getCount() {
        return slist.size();

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.detailsinfo, null, true);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name = (TextView) rowView.findViewById(R.id.pname);
        Button pass = rowView.findViewById(R.id.ppassword);
        TextView contact = (TextView) rowView.findViewById(R.id.pcontact);
        TextView email = (TextView) rowView.findViewById(R.id.padd);
        TextView Flat = (TextView) rowView.findViewById(R.id.pflat);



        MemberInfo memberInfo = slist.get(position);
        pass.setText(memberInfo.getPass());
        contact.setText(memberInfo.getContact());
        email.setText(memberInfo.getEmail());
        Flat.setText(memberInfo.getFlatno());



        return rowView;
    }
}
