package com.example.juda.FindMenteeList;

public class FindMenteePostData {
    private String writer, title, date, contents, tag1, tag2;


    public FindMenteePostData(String writer, String title, String contents, String date, String tag1, String tag2) {
        this.writer = writer;
        this.title = title;
        this.date = date;
        this.contents = contents;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContents() {
        return contents;
    }

    public String getTag1() {
        return tag1;
    }

    public String getTag2() {
        return tag2;
    }
}
