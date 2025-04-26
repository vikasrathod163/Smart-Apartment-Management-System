package com.example.e_society.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.e_society.Pojo.WorkerInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class WorkerAdapter extends ArrayAdapter {
    public static int flg=0;
    private final Activity context;
    ArrayList<WorkerInfo> slist=new ArrayList<>();
    public WorkerAdapter(@NonNull Activity context, ArrayList<WorkerInfo> slist) {
        super(context, R.layout.worker_info);

        // TODO Auto-generated constructor stub
        this.context = context;
        this.slist = slist;




    }
    public int getCount() {
        return slist.size();
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.worker_info, null, true);

         TextView name = (TextView) rowView.findViewById(R.id.tv_woname);
        TextView contact = (TextView) rowView.findViewById(R.id.tv_wocontact);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView address=(TextView)rowView.findViewById(R.id.tv_woadd);
        TextView date=(TextView)rowView.findViewById(R.id.tv_wodate);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView time=(TextView)rowView.findViewById(R.id.tv_time1);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView time2=(TextView)rowView.findViewById(R.id.time2);

        WorkerInfo workerInfo = slist.get(position);
        name.setText(workerInfo.getName());
        contact.setText(workerInfo.getContact());
        address.setText(workerInfo.getAddress());
        date.setText(workerInfo.getDate());
        time.setText(workerInfo.getTime());
        time2.setText(workerInfo.getDtime());
        return rowView;
    }
}
