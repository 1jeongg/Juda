/*
This adapter class is for findMentee_LV in FindMenteeList.java
 */

package com.example.juda.FindMenteeList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class FindMenteeListAdapter extends BaseAdapter {
    private Context mContext = null;
    List<String> mMenteePostTitle = null;

    public FindMenteeListAdapter(Context mContext, List<String> menteePostTitle) {
        this.mContext = mContext;
        this.mMenteePostTitle = menteePostTitle;
    }

    @Override
    public int getCount() {
        return mMenteePostTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return mMenteePostTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
