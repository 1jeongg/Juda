package com.example.juda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.juda.PostList.FindMenteeList.FindMenteeList;
import com.example.juda.PostList.FindMentorList.FindMentorList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button toFindMentee, toFindMentor;
    private BottomNavigationView navigationView;

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
        navigationView = findViewById(R.id.navigation_bar_MainActivity);

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

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.find_mentor:
                        Intent intent = new Intent(MainActivity.this, FindMentorList.class);
                        startActivity(intent);
                        return true;
                    case R.id.mypage:
                        return true;
                    case R.id.to_home:
                        return true;
                }
                return false;
            }
        });
    }
}