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

public class AmenityAdapter1 extends ArrayAdapter<String> {
    public static int flg=0;
    private Activity context=null;

    ArrayList<AmenityInfo> slist=new ArrayList<>();
    public AmenityAdapter1(Activity context, ArrayList<AmenityInfo> slist) {
        super(context, R.layout.activity_show_amenity);
        this.context = context;
        this.slist = slist;
    }
    public int getCount() {
        return slist.size();

    }
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.amenity_list_info, null,true);

        TextView amenity=(TextView) rowView.findViewById(R.id.text_amenity);
        TextView time =(TextView) rowView.findViewById(R.id.text_time);
        TextView name =(TextView) rowView.findViewById(R.id.text_name);
        TextView date=(TextView) rowView.findViewById(R.id.text_date);
        TextView flat =(TextView) rowView.findViewById(R.id.text_flat);
        TextView contact =(TextView) rowView.findViewById(R.id.text_contact);




        AmenityInfo amenityInfo = slist.get(position);
        amenity.setText(amenityInfo.getAmenity());
        time.setText(amenityInfo.getTime());
        date.setText(amenityInfo.getDate());
        name.setText(amenityInfo.getMemberInfo().getName());
        flat.setText(amenityInfo.getMemberInfo().getFlatno());
        contact.setText(amenityInfo.getMemberInfo().getContact());
        return rowView;
    }

}
