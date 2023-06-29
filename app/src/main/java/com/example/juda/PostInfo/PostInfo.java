package com.example.juda.PostInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.juda.FindMenteeList.FindMenteePostData;
import com.example.juda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostInfo extends AppCompatActivity {

    TextView writer_TV, senior_TV, title_TV, date_TV, contents_TV;
    Button signup_BTN, inquire_BTN;

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    SimpleDateFormat format;

    private String POST_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);
        init();
        Log.d("PIGMONGKEY", POST_ID);
    }

    private void init() {
        writer_TV = findViewById(R.id.writer_TV_PostInfo);
        senior_TV = findViewById(R.id.senior_TV_PostInfo);
        title_TV = findViewById(R.id.title_TV_PostInfo);
        date_TV = findViewById(R.id.date_TV_PostInfo);
        contents_TV = findViewById(R.id.content_TV_PostInfo);
        signup_BTN = findViewById(R.id.signup_BTN_PostInfo);
        inquire_BTN = findViewById(R.id.inquire_BTN_PostInfo);
        format = new SimpleDateFormat("yyyy. MM. dd");
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        POST_ID = intent.getStringExtra("ID");
        getPostInfo();
    }

    private void getPostInfo() {
        db.collection("postProvider")
                .document(POST_ID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            Log.d("PIGMONGKEY", task.getResult().get("title").toString());

                            Timestamp temp_timestamp = (Timestamp) document.get("date");
                            Date temp_time = new Date(temp_timestamp.getSeconds()*1000);

                            setContents(new FindMenteePostData(
                                    (String) document.getId(),
                                    (String) document.get("author"),
                                    (String) document.get("title"),
                                    (String) document.get("content"),
                                    format.format(temp_time),
                                    (String) document.get("tag1"),
                                    (String) document.get("tag2")
                                    )
                            );
                        } else {
                            Log.d("PIGMONGKEY", "Get data fail");
                        }
                    }
                });
    }

    private void setContents(FindMenteePostData dbData) {
        writer_TV.setText(dbData.getWriter());
        title_TV.setText(dbData.getTitle());
        date_TV.setText(dbData.getDate());
        contents_TV.setText(dbData.getContents());
    }
}