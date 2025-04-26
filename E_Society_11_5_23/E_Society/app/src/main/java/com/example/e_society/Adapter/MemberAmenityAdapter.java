package com.example.e_society.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_society.Pojo.AmenityInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class MemberAmenityAdapter extends ArrayAdapter<String> {
    public static int flg = 0;
    private Activity context;
    ArrayList<AmenityInfo> slist = new ArrayList<>();

    public MemberAmenityAdapter(Activity context, ArrayList<AmenityInfo> slist) {
        super(context, R.layout.activity_member_amenity);
        this.context = context;
        this.slist = slist;
    }

    public int getCount() {
        return slist.size();

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.member_amenity_info, null, true);

        TextView amenity = (TextView) rowView.findViewById(R.id.tv_name);
        TextView date = (TextView) rowView.findViewById(R.id.tv_date);
        TextView time = (TextView) rowView.findViewById(R.id.tv_time);

        AmenityInfo amenityInfo = slist.get(position);
        amenity.setText(amenityInfo.getAmenity());
        date.setText(amenityInfo.getDate());
        time.setText(amenityInfo.getTime());



        return rowView;
    }

}
