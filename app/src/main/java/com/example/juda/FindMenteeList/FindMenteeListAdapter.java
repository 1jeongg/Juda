/*
This adapter class is for findMentee_LV in FindMenteeList.java
 */

package com.example.juda.FindMenteeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.juda.R;

import java.util.List;

public class FindMenteeListAdapter extends BaseAdapter {
    private Context mContext = null;
    FindMenteePostData data[];

    public FindMenteeListAdapter(Context mContext, FindMenteePostData[] data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_view_layout_for_mentee_mentor_find_post, null);

        TextView title, contents, writer, time;

        title = view.findViewById(R.id.title_TV_ListViewLayout);
        contents = view.findViewById(R.id.contents_TV_ListViewLayout);
        writer = view.findViewById(R.id.writer_TV_ListViewLayout);
        time = view.findViewById(R.id.write_time_TV_ListViewLayout);

        title.setText(data[position].getTitle());
        contents.setText(data[position].getContents());
        writer.setText(data[position].getWriter());
        time.setText(data[position].getDate());

        return view;
    }
}
