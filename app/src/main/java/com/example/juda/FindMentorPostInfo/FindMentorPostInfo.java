package com.example.juda.FindMentorPostInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juda.FindMenteeList.FindMenteeList;
import com.example.juda.FindMenteeList.FindMenteePostData;
import com.example.juda.FindMenteePostInfo.FindMenteePostInfo;
import com.example.juda.FindMentorList.FindMentorList;
import com.example.juda.FindMentorList.FindMentorPostData;
import com.example.juda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindMentorPostInfo extends AppCompatActivity {

    Toolbar toolbar;
    TextView writer_TV, senior_TV, title_TV, date_TV, contents_TV;
    Button signup_BTN, inquire_BTN;

    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    SimpleDateFormat format;

    private String POST_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentor_post_info);
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.basic_tool_bar_PostInfo);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        writer_TV = findViewById(R.id.writer_TV_MentorPostInfo);
        senior_TV = findViewById(R.id.senior_TV_MentorPostInfo);
        title_TV = findViewById(R.id.title_TV_MentorPostInfo);
        date_TV = findViewById(R.id.date_TV_MentorPostInfo);
        contents_TV = findViewById(R.id.content_TV_MentorPostInfo);
        signup_BTN = findViewById(R.id.signup_BTN_MentorPostInfo);
        inquire_BTN = findViewById(R.id.inquire_BTN_MentorPostInfo);
        format = new SimpleDateFormat("yyyy. MM. dd");
        getIntentData();
        getPostInfo();
    }

    /**
     * 이전페이지에서 보낸 데이터 받는 메소드
     */
    private void getIntentData() {
        Intent intent = getIntent();
        POST_ID = intent.getStringExtra("ID");
    }

    /**
     * Get Post information from firebase
     * And call setContects to set Text
     */
    private void getPostInfo() {
        db.collection("postRequest")
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

                            setContents(new FindMentorPostData(
                                            (String) document.getId(),
                                            (String) document.get("author"),
                                            (String) document.get("title"),
                                            (String) document.get("content"),
                                            format.format(temp_time)
                                    )
                            );
                        } else {
                            Log.d("PIGMONGKEY", "Get data fail");
                        }
                    }
                });
    }

    /**
     * Set Text
     * @param dbData -> FindMenteePostData
     */
    private void setContents(FindMentorPostData dbData) {
        writer_TV.setText(dbData.getWriter());
        title_TV.setText(dbData.getTitle());
        date_TV.setText(dbData.getDate());
        contents_TV.setText(dbData.getContents());
    }

    /**
     * Set OnClickMethod
     */
    private void setOnClick() {
        signup_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 툴바 설정하는 함수
     * @param menu The options menu in which you place your items.
     *
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.back_post_no_logo, menu);
        return true;
    }

    /**
     * 툴바 항목 클릭 리스너
     * @param item The menu item that was selected.
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.complite:
                Intent intent = new Intent(FindMentorPostInfo.this, FindMentorList.class);
                startActivity(intent);
        }
        return false;
    }
}