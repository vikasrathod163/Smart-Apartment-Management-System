package com.example.e_society.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_society.Pojo.NoticeInfo;
import com.example.e_society.R;

import java.util.ArrayList;

public class MemberNotice_Adapter extends ArrayAdapter<String> {
    public static int flg = 0;
    private Activity context;
    ArrayList<NoticeInfo> slist = new ArrayList<>();

    public MemberNotice_Adapter(Activity context, ArrayList<NoticeInfo> slist) {
        super(context, R.layout.activity_member_notice);
        this.context = context;
        this.slist = slist;
    }

    public int getCount() {
        return slist.size();

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.member_notice_info, null, true);

        TextView title=(TextView) rowView.findViewById(R.id.tv_title);
      TextView description=(TextView) rowView.findViewById(R.id.tv_description);


        NoticeInfo noticeInfo = slist.get(position);
        title.setText(noticeInfo.getTitle());
        description.setText(noticeInfo.getDescription());




        return rowView;
    }
}
