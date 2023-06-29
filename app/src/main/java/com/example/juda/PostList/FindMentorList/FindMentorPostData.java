package com.example.juda.PostList.FindMentorList;

public class FindMentorPostData {
    private String mPostID;
    private String mWriterID;
    private String mWriterName;
    private String mWriteTime;
    private String mTitle;
    private String mContent;

    public FindMentorPostData(String post_id, String writer_id, String writer_name, String write_time, String title, String contents) {
        this.mPostID = post_id;
        this.mWriterID = writer_id;
        this.mWriterName = writer_name;
        this.mWriteTime = write_time;
        this.mTitle = title;
        this.mContent = contents;
    }

    public String getmPostID() {
        return mPostID;
    }

    public String getmWriterID() {
        return mWriterID;
    }

    public String getmWriterName() {
        return mWriterName;
    }

    public String getmWriteTime() {
        return mWriteTime;
    }

    public String getmTitle() {
        return mTitle;
    }
    public String getmContent() {
        return mContent;
    }
}
