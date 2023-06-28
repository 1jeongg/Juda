package com.example.juda.FindMenteeList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.juda.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindMenteeList extends AppCompatActivity {

    final private DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    private FindMenteeListAdapter mAdapter = null;
    private EditText search_ET;
    private Button search_BTN, filter_BTN;
    private FloatingActionButton newPost_FAB;
    private ListView findMentee_LV;
    private List<String> menteePostKey = null;
    private List<String> menteePostTitle = null;

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
        search_ET = findViewById(R.id.search_ET_FindMenteeList);
        search_BTN = findViewById(R.id.search_BTN_FindMenteeList);
        filter_BTN = findViewById(R.id.filter_BTN_FindMenteeList);
        newPost_FAB = findViewById(R.id.newPost_FAB_FindMenteeList);
        findMentee_LV = findViewById(R.id.findMentee_LV_FindMenteeList);

//        getMenteePostList();

//        show sample data
        setSampleAdapter();
    }

    /**
     * This method show sample data on ListView
     */
    private void setSampleAdapter() {
        List<String> sampleListData = new ArrayList<>();
        sampleListData.add("post_1");
        sampleListData.add("post_2");
        sampleListData.add("post_3");
        sampleListData.add("post_4");
        sampleListData.add("post_5");
        sampleListData.add("post_6");
        sampleListData.add("post_7");
        sampleListData.add("post_8");
        ArrayAdapter<String> sampleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sampleListData);
        findMentee_LV.setAdapter(sampleAdapter);
    }

    /**
     * This method gets data from firebase
     * Get all data under 'MenteePost' and save all at menteePostHashMap
     */
    private void getMenteePostList() {
        Query query = mDatabaseReference.child("MenteePost");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    menteePostKey.add(snapshot.getKey());
                    menteePostTitle.add((String)snapshot.getValue());
                }

//                ValueEventListener는 비동기 처리 되므로, 이 부분에 처리할 코드를 작성해야합니다!


                mAdapter = new FindMenteeListAdapter(getApplicationContext(), menteePostTitle);
                findMentee_LV.setAdapter(mAdapter); //FindMenteeList.java에서 코딩 필요합니다 - 그 전까지는 샘플 데이터를 출력하겠습니다!


//                ValueEventListener는 비동기 처리 되므로, 이 부분에 처리할 코드를 작성해야합니다!
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("PIGMONGKEY", "FindMenteeList.java_getMenteePostList() - onCancelled");
            }
        });
    }
}