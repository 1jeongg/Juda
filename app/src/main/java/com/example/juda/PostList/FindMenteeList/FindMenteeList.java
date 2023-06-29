package com.example.juda.PostList.FindMenteeList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.juda.NewPost.FindMenteePost.NewFindMenteePost;
import com.example.juda.PostInfo.FindMenteePostInfo.FindMenteePostInfo;
import com.example.juda.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FindMenteeList extends AppCompatActivity {


    private Toolbar toolbar;
    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentee_LV;
    private FindMenteeListAdapter mAdapter = null;

    private SimpleDateFormat format;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentee_list);

        Init();
    }

    /**
     * get ID and Connect
     */
    private void Init() {
        toolbar = findViewById(R.id.basic_tool_bar_FindMenteeList);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        filter_BTN = findViewById(R.id.filter_BTN_FindMenteeList);
        newPost_FAB = findViewById(R.id.newPost_FAB_FindMenteeList);
        findMentee_LV = findViewById(R.id.findMentee_LV_FindMenteeList);

        format = new SimpleDateFormat("yyyy. MM. dd");

        getMenteePostList();
        setOnClick();
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MenteePost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        db.collection("FindMentee")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FindMenteePostData> dbData = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("PIGMONGKEY", document.getId() + " => " + document.get("date"));

                                Timestamp temp_timestamp = (Timestamp) document.get("WriteTime");
                                Date temp_time = new Date(temp_timestamp.getSeconds()*1000);

                                dbData.add(new FindMenteePostData(
                                        (String) document.getId(),
                                        (String) document.get("WriterID"),
                                        (String) document.get("WriterName"),
                                        format.format(temp_time),
                                        (String) document.get("Title"),
                                        (String) document.get("Contents")
                                ));
                            }
                            setMenteePostListAdapter(dbData);
                        } else {
                            Log.d("PIGMONGKEY", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
  
    /**
     * This method set onclick method to each item
     */
    private void setOnClick() {
        findMentee_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindMenteeList.this, FindMenteePostInfo.class);
                intent.putExtra("ID", mAdapter.getItem(position).getmPostID());
                startActivity(intent);
            }
        });

        newPost_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindMenteeList.this, NewFindMenteePost.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Set adapter to findMentee_LV
     * @param dbData - FindMenteePostData List
     */
    private void setMenteePostListAdapter(List<FindMenteePostData> dbData) {
        mAdapter = new FindMenteeListAdapter(getApplicationContext(), dbData);
        findMentee_LV.setAdapter(mAdapter);
    }

    /**
     * 툴바 설정하는 함수
     * @param menu The options menu in which you place your items.
     *
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.only_search_menu, menu);
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
            case R.id.search:
                return true;
        }

        return false;
    }
}