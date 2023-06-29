package com.example.juda.PostList.FindMentorList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.juda.PostInfo.FindMentorPostInfo.FindMentorPostInfo;
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

public class FindMentorList extends AppCompatActivity {

    private Toolbar toolbar;
    final private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentor_LV;
    private FindMentorListAdapter mAdapter = null;
    private SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mentor_list);

        Init();
    }

    /**
     * get ID and Connect
     */
    private void Init() {
        toolbar = findViewById(R.id.basic_tool_bar_FindMentorList);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        filter_BTN = findViewById(R.id.filter_BTN_FindMentorList);
        newPost_FAB = findViewById(R.id.newPost_FAB_FindMentorList);
        findMentor_LV = findViewById(R.id.findMentor_LV_FindMentorList);

        format = new SimpleDateFormat("yyyy. MM. dd");

        getMenteePostList();
        setOnClick();
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MentorPost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        db.collection("postRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FindMentorPostData> dbData = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("PIGMONGKEY", document.getId() + " => " + document.get("date"));

                                Timestamp temp_timestamp = (Timestamp) document.get("date");
                                Date temp_time = new Date(temp_timestamp.getSeconds()*1000);

                                dbData.add(new FindMentorPostData(
                                        (String) document.getId(),
                                        (String) document.get("author"),
                                        (String) document.get("title"),
                                        (String) document.get("content"),
                                        format.format(temp_time)
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
        findMentor_LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindMentorList.this, FindMentorPostInfo.class);
                intent.putExtra("ID", mAdapter.getItem(position).getID());
                startActivity(intent);
            }
        });
    }

    /**
     * Set adapter to findMentor_LV
     * @param dbData - FindMentorPostData List
     */
    private void setMenteePostListAdapter(List<FindMentorPostData> dbData) {
        mAdapter = new FindMentorListAdapter(getApplicationContext(), dbData);
        findMentor_LV.setAdapter(mAdapter);
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