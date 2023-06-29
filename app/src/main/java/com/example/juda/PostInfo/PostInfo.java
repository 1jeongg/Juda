package com.example.juda.PostInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.juda.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostInfo extends AppCompatActivity {

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        init();
        Log.d("PIGMONGKEY", title);
    }

    private void init() {
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
    }

    private void getPostInfo() {

    }
}