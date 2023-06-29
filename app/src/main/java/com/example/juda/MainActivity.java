package com.example.juda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.juda.PostList.FindMenteeList.FindMenteeList;
import com.example.juda.PostList.FindMentorList.FindMentorList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button toFindMentee, toFindMentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init() {
        toolbar = findViewById(R.id.basic_tool_bar_MainActivity);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        FindMenteeList로 이동하기 위한 테스트 리스너
        toFindMentee = findViewById(R.id.toFindMentee);
        toFindMentor = findViewById(R.id.toFindMentor);
        testListener();
    }

    private void testListener() {
        toFindMentee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindMenteeList.class);
                startActivity(intent);
            }
        });

        toFindMentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FindMentorList.class);
                startActivity(intent);
            }
        });
    }
}