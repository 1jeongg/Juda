/*
This adapter class is for findMentee_LV in FindMenteeList.java
 */

package com.example.juda.PostList.FindMenteeList;

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
    List<FindMenteePostData> data;

    public FindMenteeListAdapter(Context mContext, List<FindMenteePostData> data) {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public FindMenteePostData getItem(int position) {
        return data.get(position);
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

        title.setText(data.get(position).getmTitle());
        contents.setText(data.get(position).getmContent());
        writer.setText(data.get(position).getmWriterName());
        time.setText(data.get(position).getmWriteTime());

        return view;
    }
}
